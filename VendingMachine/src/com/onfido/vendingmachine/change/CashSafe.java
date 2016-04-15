package com.onfido.vendingmachine.change;

/**
 * Class that encapsulate the operations performed on the {@link Wallet} of the
 * machine. In other words, this class represents the safe of the vending
 * machine.
 * 
 * @author Samer Makary
 *
 */
public class CashSafe {

	private final Wallet safe;

	private CashSafe() {
		this.safe = new Wallet();
	}

	private static final CashSafe INSTANCE = new CashSafe();

	/**
	 * Gets the singleton instance of the machine safe.
	 * 
	 * @return The vending machine safe.
	 */
	public static CashSafe getInstance() {
		return INSTANCE;
	}

	public void reload(Wallet changeToInsert) {
		safe.add(changeToInsert);
	}

	public void unload(Wallet changeToRemove) {
		safe.remove(changeToRemove);
	}

	/**
	 * Checks if an amount of money can be removed from the safe. If possible,
	 * then unloading will be executed on the safe and the removed change will
	 * be return.
	 * 
	 * @param valueToTryUnloading
	 *            Amount of cash, in Pounds, to try and remove from the safe.
	 * @return Wallet decomposition of the given amount of money,
	 *         <code>null</code> if not possible.
	 */
	public Wallet unloadIfPossible(float valueToTryUnloading) {
		Wallet toUnload = safe.tryGetValue(valueToTryUnloading);
		if (toUnload != null) {
			unload(toUnload);
		}

		return toUnload;
	}

	public double getCashInside() {
		return safe.getPoundValue();
	}
}
