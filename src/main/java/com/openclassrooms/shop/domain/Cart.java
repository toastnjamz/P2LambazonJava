package com.openclassrooms.shop.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart {
	
	// Creating a private variable for the cartline list that holds cart products
	private List<CartLine> cartLineList = new ArrayList<CartLine>() ;

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
        // Search the cartLineList for the product and if found, increment the quantity. If not found, add to cartLineList.
    	for (CartLine item : getCartLineList()) {
    		if (item.getProduct().equals(product)) {
    			item.setQuantity(item.getQuantity() + quantity);
    			return;
    		}
    		cartLineList.add(new CartLine(product, quantity));
    	}
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
    	// get the subtotal and add it to the totalValue variable.
    	// Otherwise return the initial value of the averageValue variable.
    	double averageValue = 0.0;
    	if (!getCartLineList().isEmpty()) {
	    	for (CartLine item : getCartLineList()) {
	    		averageValue += item.getSubtotal();
	         }
	        return averageValue / getCartLineList().size();
    	}
    	return averageValue;
    }

    /**
     * @param productId the getProductById id to search for
     * @return getProductById in the cart if it finds it
     */
    public Product findProductInCartLines(Long productId)
    {
    	// For each product in the cartLineList, check if the productId matches the arguement
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
