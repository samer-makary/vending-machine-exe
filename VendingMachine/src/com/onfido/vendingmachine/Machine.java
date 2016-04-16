package com.onfido.vendingmachine;

import com.onfido.vendingmachine.change.CashSafe;
import com.onfido.vendingmachine.change.Denomination;
import com.onfido.vendingmachine.change.Wallet;
import com.onfido.vendingmachine.dispenser.Dispenser;
import com.onfido.vendingmachine.dispenser.Product;
import com.onfido.vendingmachine.gui.VendingMachine;

/**
 * The general controller of the vending machine.
 * 
 * @author Samer Makary
 *
 */
public class Machine {

	private Transaction ongoingTransaction;
	private final Dispenser dispenser;
	private final CashSafe safe;

	private Machine() {
		this.dispenser = Dispenser.getInstance();
		this.safe = CashSafe.getInstance();
	}

	private static final Machine INSTANCE = new Machine();

	public static Machine getInstance() {
		return INSTANCE;
	}

	private static int getIntOrZero(String string) {
		try {
			return Integer.parseInt(string);
		} catch (Exception e) {
			return 0;
		}
	}

	public void handleCashReload(VendingMachine vm) {
		Wallet reload = new Wallet();
		reload.add(Denomination.PENNY_1,
				Machine.getIntOrZero(vm.getTextFieldReloadPenny1().getText()));
		vm.getTextFieldInPenny1().setText("");
		reload.add(Denomination.PENNY_2,
				Machine.getIntOrZero(vm.getTextFieldReloadPenny2().getText()));
		vm.getTextFieldInPenny2().setText("");
		reload.add(Denomination.PENNY_5,
				Machine.getIntOrZero(vm.getTextFieldReloadPenny5().getText()));
		vm.getTextFieldInPenny5().setText("");
		reload.add(Denomination.PENNY_10,
				Machine.getIntOrZero(vm.getTextFieldReloadPenny10().getText()));
		vm.getTextFieldInPenny10().setText("");
		reload.add(Denomination.PENNY_20,
				Machine.getIntOrZero(vm.getTextFieldReloadPenny20().getText()));
		vm.getTextFieldInPenny20().setText("");
		reload.add(Denomination.PENNY_50,
				Machine.getIntOrZero(vm.getTextFieldReloadPenny50().getText()));
		vm.getTextFieldInPenny50().setText("");
		reload.add(Denomination.POUND_1,
				Machine.getIntOrZero(vm.getTextFieldReloadPound1().getText()));
		vm.getTextFieldInPound1().setText("");
		reload.add(Denomination.POUND_2,
				Machine.getIntOrZero(vm.getTextFieldReloadPound2().getText()));
		vm.getTextFieldInPound2().setText("");

		safe.reload(reload);
	}

	public void handleRestockSlot(VendingMachine vm, int i) {
		float cost = Machine.getIntOrZero(vm.getTextFieldSlotProductPounds().getText());
		vm.getTextFieldSlotProductPounds().setText("");
		cost += Machine.getIntOrZero(vm.getTextFieldSlotProductPennies().getText())
				/ 100.0f;
		vm.getTextFieldSlotProductPennies().setText("");
		Product newProduct = new Product(
				vm.getTextFieldSlotProductName().getText().trim(), cost);
		vm.getTextFieldSlotProductName().setText("");
		dispenser.setProduct(newProduct, i);
		dispenser.restockProduct(i);
		String displayStr = String.format("<html><center>%s<br>@ £%s</center></html>",
				newProduct.getProductName(), Float.toString(newProduct.getUnitCost()));
		vm.getBtnProductAt(i).setText(displayStr);
	}

}
