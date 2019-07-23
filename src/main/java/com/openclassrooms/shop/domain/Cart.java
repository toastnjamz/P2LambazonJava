package com.openclassrooms.shop.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Cart {
	
	// Creating a private variable for the cartline list that holds cart products
	private List<CartLine> cartLineList = new ArrayList<>() ;

    /**
     *
     * @return the actual cartline list
     */
    public List<CartLine> getCartLineList() {
    	return cartLineList;
    }

    /**
     * Adds a getProductById in the cart or increment its quantity in the cart if already added
     * @param product getProductById to be added
     * @param quantity the quantity
     */
    public void addItem(Product product, int quantity) {
        // Creates a predicate filter to search for the passed in product by productId
    	// Searches for the first instance of the predicate, otherwise returns null
    	// If the predicate is null or greater than 0, add the new item to cartLineList
    	// Otherwise, if product already exists in the cartLineList, increment quantity by the value passed in
    	final Predicate<CartLine> filterByProductId = cl -> cl.getProduct().getId().longValue() == product.getId().longValue();
		final CartLine cartLine = getCartLineList().stream().filter(filterByProductId).findFirst().orElse(null);
		if (cartLine == null && quantity > 0)
			getCartLineList().add(new CartLine(product, quantity));
		else if (quantity > 0)
			cartLine.setQuantity(cartLine.getQuantity() + quantity);
    }

    /**
     * Removes a getProductById form the cart
     * @param product the getProductById to be removed
     */
    public void removeLine(Product product) {
        getCartLineList().removeIf(l -> l.getProduct().getId().equals(product.getId()));
    }

    /**
     * @return total value of a cart
     */
    public double getTotalValue()
    {
        // For each product in the cartLineList, get the subtotal and add it to the totalValue variable
    	double totalValue = 0.0;
    	for (CartLine item : getCartLineList()) {
        	 totalValue += item.getSubtotal();
         }
        return totalValue;
    }

    /**
     * @return Get average value of a cart
     */
    public double getAverageValue()
    {
        // If the list isn't empty, for each product in the cartLineList, 
    	// get the subtotal and add it to the subTotal variable.
    	// Otherwise return the initial value of the subTotal variable.
    	double subTotal = 0.0;
    	int quantity = 0;
    	if (!getCartLineList().isEmpty()) {
	    	for (CartLine cl : getCartLineList()) {
	    		subTotal += cl.getSubtotal();
	    		quantity += cl.getQuantity();
	    	}
	    	return subTotal / quantity;
    	}
    	return subTotal;
    }

    /**
     * @param productId the getProductById id to search for
     * @return getProductById in the cart if it finds it
     */
    public Product findProductInCartLines(Long productId)
    {
    	// For each product in the cartLineList, check if the productId matches the argument
    	for (CartLine item : getCartLineList()) {
    		if (item.getProduct().getId().equals(productId))
    			return item.getProduct();
    	}
    	return null;
    }

    /**
     *
     * @param index index of the cartLine
     * @return CartLine in that index
     */
    public CartLine getCartLineByIndex(int index)
    {
        return getCartLineList().get(index);
    }

    /**
     * Clears a the cart of all added products
     */
    public void clear()
    {
        List<CartLine> cartLines = getCartLineList();
        cartLines.clear();
    }
}
