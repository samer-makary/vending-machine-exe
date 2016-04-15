package com.onfido.vendingmachine.change;

import java.util.EnumMap;
import java.util.Map;

/**
 * A model class that represents an amount of money in terms of the
 * {@link Denomination}s available in the system. </br>
 * A {@code Wallet} represents money as a map that holds a {@code Denomination}
 * as a key and the value is the {@code count} of this specific
 * {@code Denomination} that is in the {@code Wallet}.
 * 
 * @author Samer Makary
 *
 */
public class Wallet {

	private static final Denomination[] DENOMS = Denomination.getOrdered(false);

	private final Map<Denomination, Integer> money;
	private int pennyValue;

	public Wallet() {
		Denomination[] denoms = Denomination.values();
		money = new EnumMap<Denomination, Integer>(Denomination.class);
		for (Denomination d : denoms)
			money.put(d, 0);
		pennyValue = 0;
	}

	/**
	 * Increases the number of units, the count, of the given denomination in
	 * the wallet.
	 * 
	 * @param denom
	 *            A given denomination that is being added into the wallet.
	 * @param count
	 *            The number of units of the denomination that is being added.
	 */
	public void add(Denomination denom, int count) {
		money.put(denom, money.get(denom) + count);
		pennyValue += denom.pennyValue() * count;
	}

	/**
	 * Adds, for every corresponding denomination, the units in the other wallet
	 * to the units in this wallet.</br>
	 * Note that the other wallet remains unchanged.
	 * 
	 * @param other
	 *            The wallet that will be added into this wallet.
	 * 
	 * @throws IllegalStateException
	 *             If the count to be removed is greater than the number of
	 *             units that already exists in the wallet.
	 */
	public void add(Wallet other) {
		for (Denomination denom : money.keySet()) {
			add(denom, other.money.get(denom));
		}
	}

	/**
	 * Decreases the number of units, the count, of the given denomination in
	 * the wallet.
	 * 
	 * @param denom
	 *            A given denomination that is being removed from the wallet.
	 * @param count
	 *            The number of units of the denomination that is being removed.
	 * 
	 * @throws IllegalStateException
	 *             If the count to be removed is greater than the number of
	 *             units that already exists in the wallet.
	 */
	public void remove(Denomination denom, int count) {
		int orig = money.get(denom);
		if (count > orig) {
			throw new IllegalStateException(String.format(
					"Cannot remove %d of denom %s since wallet is holding %d",
					count, denom.toString(), orig));
		} else {
			money.put(denom, orig - count);
			pennyValue -= denom.pennyValue() * count;
		}
	}

	/**
	 * Subtracts, for every corresponding denomination, the units in the other
	 * wallet from the units in this wallet.</br>
	 * Note that the other wallet remains unchanged.
	 * 
	 * @param other
	 *            The wallet that will be removed from this wallet.
	 * 
	 * @throws IllegalStateException
	 *             If the count to be removed is greater than the number of
	 *             units that already exists in the wallet.
	 */
	public void remove(Wallet other) {
		for (Denomination denom : money.keySet()) {
			remove(denom, other.money.get(denom));
		}
	}

	public int getPennyValue() {
		return pennyValue;
	}

	public double getPoundValue() {
		return getPennyValue() / 100.0;
	}

	/**
	 * Tries to decompose the given value using the denominations available in
	 * this wallet. This function rounds up pennies if needed. This function
	 * favors higher denominations. </br>
	 * This function does NOT remove the given value from this wallet, it just
	 * computes whether it is possible or not to remove the value from this
	 * wallet. In other words, this function does NOT change this wallet in
	 * anyway.
	 * 
	 * @param valueInPounds
	 *            Value to check in this wallet.
	 * @return A wallet that holds a sample decomposition of the given value
	 *         using denominations available in this wallet, or
	 *         <code>null</code> if no such decomposition exists.
	 * 
	 * @see Wallet#remove(Wallet)
	 */
	public Wallet tryGetValue(float valueInPounds) {
		int valueInPennies = Math.round(valueInPounds * 100.0f);
		Wallet valueInDenoms = new Wallet();
		boolean worked = tryToDecompose(valueInDenoms, valueInPennies, 0);
		if (worked && valueInDenoms.getPennyValue() == valueInPennies)
			return valueInDenoms;
		return null;
	}

	private boolean tryToDecompose(Wallet wallet, int valueInPennies, int denomIdx) {
		if (valueInPennies == 0)
			return true;
		if (denomIdx == DENOMS.length)
			return false;

		Denomination denom = DENOMS[denomIdx];
		int denomValue = denom.pennyValue();
		int denomsAvailable = money.get(denom);
		int denomsInValue = valueInPennies / denomValue;
		boolean worked = false;
		for (int taken = Math.min(denomsAvailable, denomsInValue); taken > -1
				&& !worked; taken--) {
			wallet.add(denom, taken);
			worked = tryToDecompose(wallet, valueInPennies - taken * denomValue,
					denomIdx + 1);
			if (!worked) {
				wallet.remove(denom, taken);
			}
		}

		return worked;
	}

	@Override
	public String toString() {
		long pennyValue = getPennyValue();
		return String.format("Wallet(£%.2f; %dp; %s)", pennyValue / 100.0, pennyValue,
				money.toString());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Wallet other = (Wallet) obj;
		if (money == null) {
			if (other.money != null)
				return false;
		} else if (!money.equals(other.money))
			return false;
		return true;
	}

}
