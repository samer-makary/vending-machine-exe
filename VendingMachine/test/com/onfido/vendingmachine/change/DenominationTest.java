package com.onfido.vendingmachine.change;

import static com.onfido.vendingmachine.change.Denomination.PENNY_1;
import static com.onfido.vendingmachine.change.Denomination.PENNY_10;
import static com.onfido.vendingmachine.change.Denomination.PENNY_2;
import static com.onfido.vendingmachine.change.Denomination.PENNY_20;
import static com.onfido.vendingmachine.change.Denomination.PENNY_5;
import static com.onfido.vendingmachine.change.Denomination.PENNY_50;
import static com.onfido.vendingmachine.change.Denomination.POUND_1;
import static com.onfido.vendingmachine.change.Denomination.POUND_2;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class DenominationTest {

	// Change denominations 1p, 2p, 5p, 10p, 20p, 50p, £1, £2.

	@Test
	public void testAscOrdering() {
		Denomination[] ascOrder = new Denomination[] { PENNY_1, PENNY_2, PENNY_5,
				PENNY_10, PENNY_20, PENNY_50, POUND_1, POUND_2 };
		Denomination[] actuals = Denomination.getOrdered(true);
		assertArrayEquals(ascOrder, actuals);
	}

	@Test
	public void testDescOrdering() {
		Denomination[] descOrder = new Denomination[] { POUND_2, POUND_1, PENNY_50,
				PENNY_20, PENNY_10, PENNY_5, PENNY_2, PENNY_1 };
		Denomination[] actuals = Denomination.getOrdered(false);
		assertArrayEquals(descOrder, actuals);
	}

}
