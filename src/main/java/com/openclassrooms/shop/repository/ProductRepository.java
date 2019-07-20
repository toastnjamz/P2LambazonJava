package com.openclassrooms.shop.repository;

import com.openclassrooms.shop.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**

 * @author stanlick
 *
 */

@Repository
public class ProductRepository {
    private static List<Product> products;

    public ProductRepository()
    {
        products = new ArrayList<>();
        generateProductData();
    }

    /**
     * Generate the default list of products
     */
    private void generateProductData()
    {
        long id = 0;
        products.add(new Product(++id, 10, 92.50, "Echo Dot", "(2nd Generation) - Black"));
        products.add(new Product(++id, 20, 9.99, "Anker 3ft / 0.9m Nylon Braided", "Tangle-Free Micro USB Cable"));
        products.add(new Product(++id, 30, 69.99, "JVC HAFX8R Headphone", "Riptidz, In-Ear"));
        products.add(new Product(++id, 40, 32.50, "VTech CS6114 DECT 6.0", "Cordless Phone"));
        products.add(new Product(++id, 50, 895.00, "NOKIA OEM BL-5J", "Cell Phone "));
    }

    /**
     * @return public getter to return Private ArrayList of products for use in ProductService.java
     */
    // TODO remove
    public List<Product> getProducts() {
    	return products;
    }
    
    /**
     * Converts list of products to a stream, filters and compares on the sequence of objects, then converts back to a list
     * @return All products in a list from the inventory
     */
    public List<Product> findAll()
    {
    	List<Product> allProductsList = products.stream().filter(p -> p.getStock() > 0).sorted(Comparator.comparing(Product::getName)).collect(Collectors.toList());
    	return allProductsList;
    }

    /**
     * @param productId ID of the getProductById
     * @param quantityToRemove Quantity of the getProductById
     */
    public void updateProductStocks(int productId, int quantityToRemove) {
        Product product = products.stream().filter(p -> p.getId() == productId).findFirst().get();
        product.setStock(product.getStock() - quantityToRemove);

        if (product.getStock() == 0){
            products.remove(product);
        }
    }
}
