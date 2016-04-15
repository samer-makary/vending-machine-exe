package com.onfido.vendingmachine.dispenser;

/**
 * An instance of this class represents a specific product placed in the spaces
 * of a specific slot in the {@link Dispenser}. </br>
 * This instance holds the product name, unit-cost, and number of currently
 * available units.</br>
 * Note that, once created, the product state can only be changed throught the
 * {@link Dispenser}.
 * 
 * @author Samer Makary
 *
 */
public class Product {

	private final String productName;

	private int availableUnits;

	private double unitCost;

	/**
	 * Creates a new vending machine product by setting its name and
	 * unit-cost.</br>
	 * Note that the number of available units of this product is initially set
	 * to {@link Dispenser#SPACES_PER_SLOT}.
	 * 
	 * @param productName
	 *            The product name.
	 */
	public Product(String productName, double unitCost) {
		if (productName == null || productName.isEmpty())
			throw new IllegalArgumentException("New product name cannot be " + productName);
		if (Double.compare(unitCost, 0) <= 0)
			throw new IllegalArgumentException("New product unit-cost cannot be " + unitCost);

		this.productName = productName;
		this.unitCost = unitCost;
		this.availableUnits = Dispenser.SPACES_PER_SLOT;
	}

	/**
	 * Gets the given product name.
	 * 
	 * @return product name.
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * Gets the cost of a single unit of the product.
	 * 
	 * @return product unit-cost.
	 */
	public double getUnitCost() {
		return unitCost;
	}

	/**
	 * Sets the number of available units of the product to be equal to the
	 * maximum number of spaces available in the dispenser slot.
	 * {@link Dispenser#SPACES_PER_SLOT}
	 */
	/* package */ void restock() {
		this.availableUnits = Dispenser.SPACES_PER_SLOT;
	}

	/**
	 * Checks whether there is any available units of the product of not.
	 * 
	 * @return <code>true</code> if available, <code>false</code> otherwise.
	 */
	public boolean isAvailable() {
		return availableUnits > 0;
	}

	/**
	 * Decrements the number of available units of this product by 1, i.e. one
	 * unit is taken.
	 * 
	 * @throws IllegalStateException
	 *             if no product unit is available.
	 */
	/* package */ void takeOne() {
		if (isAvailable())
			availableUnits--;
		else
			throw new IllegalStateException("No product units availabe!");
	}

	@Override
	public String toString() {
		return String.format("Product[name=%s, unit-cost=%.2f, avail-units=%d]", productName,
				unitCost, availableUnits);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equalsIgnoreCase(other.productName))
			return false;
		if (Math.abs(unitCost - other.unitCost) < 1E-4)
			return false;
		return true;
	}

}
