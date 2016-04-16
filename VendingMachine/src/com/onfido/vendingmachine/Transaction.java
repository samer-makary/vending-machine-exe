package com.onfido.vendingmachine;

import com.onfido.vendingmachine.change.CashSafe;
import com.onfido.vendingmachine.change.Wallet;

/**
 * This class holds all the information about an ongoing transaction. This
 * information includes the selected product and the cash inserted. </br>
 * The class is also responsible for executing the transaction itself if the
 * cash logic holds.
 * 
 * @author Samer Makary
 *
 */
public class Transaction {

	public enum Response {
		/**
		 * Execution response in case the inserted cash is less than the cost.
		 */
		INSUFF_FUNDS,
		/**
		 * Execution response in case the safe will not be able to provided the
		 * remaining change back to the user.
		 */
		INSUFF_CHANGE,
		/**
		 * Execution response in case transaction was performed successfully.
		 * Any changes can be collected after receiveing this response.
		 */
		TRANS_SUCCESSFUL;
	}

	private final int selectedProductSlotNumber;
	private final float transactionCost;
	private final Wallet insertedCash;
	private Wallet change;

	public Transaction(int selectedProductSlotNumber, float transactionCost) {
		this.selectedProductSlotNumber = selectedProductSlotNumber;
		this.transactionCost = transactionCost;
		this.insertedCash = new Wallet();
	}

	public int getSelectedProductSlotNumber() {
		return selectedProductSlotNumber;
	}

	public void addMoreFunds(Wallet moreCash) {
		insertedCash.add(moreCash);
	}

	public Wallet getChange() {
		return change;
	}

	public Wallet getInsertedFunds() {
		return insertedCash;
	}

	public Response execute() {
		if (insertedCash.getPoundValue() < transactionCost)
			return Response.INSUFF_FUNDS;
		else {
			// try executing the transaction.
			CashSafe safe = CashSafe.getInstance();
			// TODO modify Wallet class to return float instead of double.
			float changeInPennies = (float) (insertedCash.getPoundValue()
					- transactionCost);

			// reload the inserted funds into the safe.
			safe.reload(insertedCash);

			// attempt to retrieve the change from the sage.
			change = safe.unloadIfPossible(changeInPennies);

			if (change != null) {
				return Response.TRANS_SUCCESSFUL;
			} else {
				// not enough change in the safe. Roll-back the inserted cash.
				safe.unload(insertedCash);
				return Response.INSUFF_CHANGE;
			}
		}
	}

	@Override
	public String toString() {
		return "Transaction [selectedProductSlotNumber=" + selectedProductSlotNumber
				+ ", transactionCost=" + transactionCost + ", insertedCash="
				+ insertedCash + ", change=" + change + "]";
	}

}
