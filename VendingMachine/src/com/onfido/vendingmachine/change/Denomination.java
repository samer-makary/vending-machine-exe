package com.onfido.vendingmachine.change;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Class that represents the denominations available in the system.
 * 
 * @author Samer Makary
 *
 */
public enum Denomination {

	// Change denominations 1p, 2p, 5p, 10p, 20p, 50p, £1, £2.

	PENNY_1(1, 1, "p", false),
	PENNY_2(2, 1, "p", false),
	PENNY_5(5, 1, "p", false),
	PENNY_10(10, 1, "p", false),
	PENNY_20(20, 1, "p", false),
	PENNY_50(50, 1, "p", false),
	POUND_1(1, 100, "£", true),
	POUND_2(2, 100, "£", true);

	private int value;
	private int scale;
	private String sym;
	private boolean placeBefore;

	Denomination(int value, int scale, String sym, boolean placeBefore) {
		this.value = value;
		this.sym = sym;
		this.placeBefore = placeBefore;
		this.scale = scale;
	}

	public int pennyValue() {
		return this.value * this.scale;
	}

	@Override
	public String toString() {
		String val = Integer.toString(value);
		return (placeBefore) ? sym + val : val + sym;
	}

	/**
	 * Gives an array of all available denominations sorted in ascending or
	 * descending order of their value.
	 * 
	 * @param ascending
	 *            <code>true</code> to select ascending, or <code>false</code>
	 *            for descending.
	 * @return Array of all denominations.
	 */
	public static Denomination[] getOrdered(final boolean ascending) {
		Denomination[] denoms = Denomination.values();
		Arrays.sort(denoms, new Comparator<Denomination>() {

			@Override
			public int compare(Denomination d1, Denomination d2) {
				int diff = d1.pennyValue() - d2.pennyValue();
				return (ascending) ? diff : -diff;
			}
		});
		return denoms;
	}

}