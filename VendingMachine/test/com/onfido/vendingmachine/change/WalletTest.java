package com.onfido.vendingmachine.change;

import static org.junit.Assert.*;
import static com.onfido.vendingmachine.change.Denomination.*;

import org.junit.Test;

public class WalletTest {

	@Test
	public void testTryGetValuePreferHigherDenominations() {
		Wallet original = new Wallet();
		original.add(PENNY_1, 20);
		original.add(POUND_2, 10);

		float value = 10f;

		Wallet expected = new Wallet();
		expected.add(POUND_2, 5);

		Wallet actual = original.tryGetValue(value);

		assertEquals(expected, actual);
	}

	@Test
	public void testTryGetValueWorked() {
		Wallet original = new Wallet();
		original.add(PENNY_1, 20);
		original.add(PENNY_2, 5);
		original.add(PENNY_5, 10);
		original.add(PENNY_10, 3);
		original.add(PENNY_20, 5);
		original.add(PENNY_50, 3);
		original.add(POUND_1, 3);
		original.add(POUND_2, 4);

		float value = 12.36f;

		Wallet expected = new Wallet();
		expected.add(PENNY_1, 1);
		expected.add(PENNY_5, 1);
		expected.add(PENNY_10, 1);
		expected.add(PENNY_20, 1);
		expected.add(PENNY_50, 2);
		expected.add(POUND_1, 3);
		expected.add(POUND_2, 4);

		Wallet actual = original.tryGetValue(value);

		assertEquals(expected, actual);
	}
	
	@Test
	public void testTryGetValueWorkedWithoutPENNY1() {
		Wallet original = new Wallet();
		original.add(PENNY_2, 5);
		original.add(PENNY_5, 10);
		original.add(PENNY_10, 3);
		original.add(PENNY_20, 5);
		original.add(PENNY_50, 3);
		original.add(POUND_1, 3);
		original.add(POUND_2, 4);

		float value = 12.37f;

		Wallet expected = new Wallet();
		expected.add(PENNY_2, 1);
		expected.add(PENNY_5, 1);
		expected.add(PENNY_10, 1);
		expected.add(PENNY_20, 1);
		expected.add(PENNY_50, 2);
		expected.add(POUND_1, 3);
		expected.add(POUND_2, 4);

		Wallet actual = original.tryGetValue(value);

		assertEquals(expected, actual);
	}
	
	@Test
	public void testTryGetValueNotWorked() {
		Wallet original = new Wallet();
		original.add(PENNY_5, 10);
		original.add(PENNY_10, 3);
		original.add(PENNY_20, 5);
		original.add(PENNY_50, 3);
		original.add(POUND_1, 3);
		original.add(POUND_2, 4);

		float value = 12.37f;

		Wallet expected = null;

		Wallet actual = original.tryGetValue(value);

		assertEquals(expected, actual);
	}

}
