package com.onfido.vendingmachine;

import com.onfido.vendingmachine.dispenser.Dispenser;

/**
 * The general controller of the vending machine.
 * 
 * @author Samer Makary
 *
 */
public class Machine {

	private Transaction ongoingTransaction;
	private final Dispenser dispenser;

	private Machine() {
		this.dispenser = Dispenser.getInstance();
	}

	private static final Machine INSTANCE = new Machine();

	public static Machine getInstance() {
		return INSTANCE;
	}
	
	

}
