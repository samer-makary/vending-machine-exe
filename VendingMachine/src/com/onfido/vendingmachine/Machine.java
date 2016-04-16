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
		vm.getTextFieldReloadPenny1().setText("");
		reload.add(Denomination.PENNY_2,
				Machine.getIntOrZero(vm.getTextFieldReloadPenny2().getText()));
		vm.getTextFieldReloadPenny2().setText("");
		reload.add(Denomination.PENNY_5,
				Machine.getIntOrZero(vm.getTextFieldReloadPenny5().getText()));
		vm.getTextFieldReloadPenny5().setText("");
		reload.add(Denomination.PENNY_10,
				Machine.getIntOrZero(vm.getTextFieldReloadPenny10().getText()));
		vm.getTextFieldReloadPenny10().setText("");
		reload.add(Denomination.PENNY_20,
				Machine.getIntOrZero(vm.getTextFieldReloadPenny20().getText()));
		vm.getTextFieldReloadPenny20().setText("");
		reload.add(Denomination.PENNY_50,
				Machine.getIntOrZero(vm.getTextFieldReloadPenny50().getText()));
		vm.getTextFieldReloadPenny50().setText("");
		reload.add(Denomination.POUND_1,
				Machine.getIntOrZero(vm.getTextFieldReloadPound1().getText()));
		vm.getTextFieldReloadPound1().setText("");
		reload.add(Denomination.POUND_2,
				Machine.getIntOrZero(vm.getTextFieldReloadPound2().getText()));
		vm.getTextFieldReloadPound2().setText("");

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

	public void handleStartOrContinueTransaction(VendingMachine vm, int selectedSlotIdx) {
		Product selectedProduct = dispenser.getProduct(selectedSlotIdx);
		if (selectedProduct == null || !selectedProduct.isAvailable()) {
			vm.displayMsg(String.format(
					"The selected slot [%d] has no available product.",
					selectedSlotIdx + 1));
			vm.clearSelectedProduct();
			return;
		}

		if (ongoingTransaction != null) {
			if (ongoingTransaction.getSelectedProductSlotNumber() != selectedSlotIdx) {
				throw new IllegalStateException(
						"Selected product should NOT have changed."
								+ "Ongoing Transaction: " + ongoingTransaction
								+ " and received product from UI: " + selectedSlotIdx);
			}

		} else {
			ongoingTransaction = new Transaction(selectedSlotIdx,
					selectedProduct.getUnitCost());
		}

		Wallet insertedCash = collectBuyCash(vm);
		ongoingTransaction.addMoreFunds(insertedCash);
		attemptTransactionExecution(vm, selectedProduct);
	}

	private void attemptTransactionExecution(VendingMachine vm, Product selectedProduct) {
		Transaction.Response transResponse = ongoingTransaction.execute();
		switch (transResponse) {
		case TRANS_SUCCESSFUL:
			dispenser.dispenseProduct(ongoingTransaction.getSelectedProductSlotNumber());
			outputChange(vm, ongoingTransaction.getChange());
			vm.displayMsg("Enjoy!.\n"
					+ "Please collect any change then click [Clear]");
			ongoingTransaction = null;
			vm.clearSelectedProduct();
			break;

		case INSUFF_FUNDS:
			vm.displayMsg(String.format(
					"Insufficient funds; deposited £%.2f, while cost is £%.2f.\n"
							+ "Please insert more money then click [Buy] "
							+ "or abort by clicking [Cancel]",
					ongoingTransaction.getInsertedFunds().getPoundValue(),
					selectedProduct.getUnitCost()));
			break;

		case INSUFF_CHANGE:
			outputChange(vm, ongoingTransaction.getInsertedFunds());
			vm.displayMsg("Machine cannot output change. Transaction aborted.\n"
					+ "Please collect any cash that you have already inserted then click [Clear]");
			ongoingTransaction = null;
			vm.clearSelectedProduct();
			break;

		default:
			throw new RuntimeException("Unknow transaction response: " + transResponse);
		}
	}

	private Wallet collectBuyCash(VendingMachine vm) {
		Wallet buyWallet = new Wallet();
		buyWallet.add(Denomination.PENNY_1,
				Machine.getIntOrZero(vm.getTextFieldInPenny1().getText()));
		vm.getTextFieldInPenny1().setText("");
		buyWallet.add(Denomination.PENNY_2,
				Machine.getIntOrZero(vm.getTextFieldInPenny2().getText()));
		vm.getTextFieldInPenny2().setText("");
		buyWallet.add(Denomination.PENNY_5,
				Machine.getIntOrZero(vm.getTextFieldInPenny5().getText()));
		vm.getTextFieldInPenny5().setText("");
		buyWallet.add(Denomination.PENNY_10,
				Machine.getIntOrZero(vm.getTextFieldInPenny10().getText()));
		vm.getTextFieldInPenny10().setText("");
		buyWallet.add(Denomination.PENNY_20,
				Machine.getIntOrZero(vm.getTextFieldInPenny20().getText()));
		vm.getTextFieldInPenny20().setText("");
		buyWallet.add(Denomination.PENNY_50,
				Machine.getIntOrZero(vm.getTextFieldInPenny50().getText()));
		vm.getTextFieldInPenny50().setText("");
		buyWallet.add(Denomination.POUND_1,
				Machine.getIntOrZero(vm.getTextFieldInPound1().getText()));
		vm.getTextFieldInPound1().setText("");
		buyWallet.add(Denomination.POUND_2,
				Machine.getIntOrZero(vm.getTextFieldInPound2().getText()));
		vm.getTextFieldInPound2().setText("");

		return buyWallet;
	}

	private void outputChange(VendingMachine vm, Wallet changeWallet) {
		vm.getTextFieldOutPenny1().setText(Integer
				.toString(changeWallet.getDenominationCount(Denomination.PENNY_1)));
		vm.getTextFieldOutPenny2().setText(Integer
				.toString(changeWallet.getDenominationCount(Denomination.PENNY_2)));
		vm.getTextFieldOutPenny5().setText(Integer
				.toString(changeWallet.getDenominationCount(Denomination.PENNY_5)));
		vm.getTextFieldOutPenny10().setText(Integer
				.toString(changeWallet.getDenominationCount(Denomination.PENNY_10)));
		vm.getTextFieldOutPenny20().setText(Integer
				.toString(changeWallet.getDenominationCount(Denomination.PENNY_20)));
		vm.getTextFieldOutPenny50().setText(Integer
				.toString(changeWallet.getDenominationCount(Denomination.PENNY_50)));
		vm.getTextFieldOutPound1().setText(Integer
				.toString(changeWallet.getDenominationCount(Denomination.POUND_1)));
		vm.getTextFieldOutPound2().setText(Integer
				.toString(changeWallet.getDenominationCount(Denomination.PENNY_2)));
	}

	public void handleCancel(VendingMachine vm) {
		if (ongoingTransaction != null) {
			outputChange(vm, ongoingTransaction.getInsertedFunds());
			vm.displayMsg("Transaction is cancelled.\n"
					+ "Please collect any change that you have inserted then click [Clear]");
			ongoingTransaction = null;
			vm.clearSelectedProduct();
		}
	}

}
