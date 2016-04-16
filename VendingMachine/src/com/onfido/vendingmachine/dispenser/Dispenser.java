package com.onfido.vendingmachine.dispenser;

public class Dispenser {

	/**
	 * Number of slots in the dispenser. Every slot holds a number of units of
	 * the same product. The user selects the slot number that corresponds to
	 * the product that she wants to buy.
	 */
	public static final int NUM_PRODUCTS_SLOTS = 10;

	/**
	 * Max number of units of a specific product that can be placed in a
	 * specific slot of the dispenser.
	 */
	public static final int SPACES_PER_SLOT = 15;

	private static final Dispenser INSTANCE = new Dispenser();

	private final Product[] machineProducts;

	private Dispenser() {
		machineProducts = new Product[NUM_PRODUCTS_SLOTS];
	}

	/**
	 * Gets the singleton instance of the {@link Dispenser}.
	 * 
	 * @return dispenser instance.
	 */
	public static final Dispenser getInstance() {
		return INSTANCE;
	}

	/**
	 * Sets the product for the selected slot.
	 * 
	 * @param slotNewProduct
	 *            The new product that will be placed in the given slot. It
	 *            replaces any previous product that was placed before in the
	 *            slot.
	 * @param selectedSlot
	 *            The slot number which will be associated with the given
	 *            product.
	 */
	public void setProduct(Product slotNewProduct, int selectedSlot) {
		if (slotNewProduct == null)
			throw new IllegalArgumentException("Cannot set a slot with a null product.");

		machineProducts[selectedSlot] = slotNewProduct;
	}

	/**
	 * Gets the product at a specific slot in the dispenser.
	 * 
	 * @param seletedSlot
	 *            Number of the slot which its product will be returned.
	 * @return Product or <code>null</code> if no product was set at all in the
	 *         given slot.
	 */
	public Product getProduct(int selectedSlot) {
		return machineProducts[selectedSlot];
	}

	/**
	 * Re-fills more units of a product in a specific slot. A product must be
	 * set first.
	 * 
	 * @param selectedSlot
	 *            Number of slot whose product will be re-stocked.
	 * 
	 * @throws IllegalStateException
	 *             if no product was set in the given slot.
	 * 
	 * @see Dispenser#setProduct(Product, int)
	 */
	public void restockProduct(int selectedSlot) {
		Product p = getProduct(selectedSlot);
		if (p == null) {
			throw new IllegalStateException(
					"No product found. You must set product first at slot " + selectedSlot);
		}

		p.restock();
	}

	/**
	 * Dispenses an available unit of the product in the selected slot.
	 * 
	 * @param selectedSlot
	 *            Number of the slot corresponding to the product that users
	 *            wants to buy.
	 * @return product name.
	 * 
	 * @throws IllegalStateException
	 *             if the slot has no product or no available units.
	 */
	public String dispenseProduct(int selectedSlot) {
		Product p = getProduct(selectedSlot);
		if (p == null || !p.isAvailable()) {
			throw new IllegalStateException(
					String.format("Cannot dispense %s at slot number %d", p, selectedSlot));
		}

		p.takeOne();
		return p.getProductName();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Dispenser_BEG");
		for (int i = 0; i < machineProducts.length; i++) {
			sb.append(String.format("\n  @%02d --> %s,", i, machineProducts[i]));
		}
		sb.append("\nDispenser_END");
		return sb.toString();
	}
}
