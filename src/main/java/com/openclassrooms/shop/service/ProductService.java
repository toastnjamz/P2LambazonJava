package com.openclassrooms.shop.service;

import com.openclassrooms.shop.domain.Cart;
import com.openclassrooms.shop.domain.CartLine;
import com.openclassrooms.shop.repository.OrderRepository;
import com.openclassrooms.shop.domain.Product;
import com.openclassrooms.shop.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class ProductService {

	private static final Logger log = LoggerFactory.getLogger(ProductService.class);

	private ProductRepository productRepository;
	private OrderRepository orderRepository;

	@Autowired
	public ProductService(ProductRepository repository, OrderRepository orderRepository) {
		this.productRepository = repository;
		this.orderRepository = orderRepository;
	}

	/**
	 * @return a list of all products from the inventory
	 */
	public List<Product> getAllProducts() {

		// Changed the return type from array to List<T> and propagated the change
		// throughout the application
		List<Product> allProductsList = new ArrayList<Product>();
		allProductsList = productRepository.findAll();
		return allProductsList;
	}

	/**
	 *
	 * @param productId Id of the product
	 * @return a product from the inventory
	 */
	public Product getProductById(Long productId)
	{
		// Implemented the method by converting the list of products in inventory to a stream, 
		// filtering by ID, and returning the first matching product
		Product product = productRepository.getProducts().stream().filter(p -> p.getId() == productId).findFirst().get();
		return product;
	}

	/**
	 * Updates the quantities left for each product in the inventory depending of ordered the quantities
	 * @param productId ID of the product to be updated
	 */
	public void updateProductQuantities(Long productId, int quantity)
	{
		// Retrieves the product by ID, then updates the quantity through the Product class getters and setters 
		Product product = getProductById(productId);
		product.setStock(product.getStock() - quantity);
		
	}
}
