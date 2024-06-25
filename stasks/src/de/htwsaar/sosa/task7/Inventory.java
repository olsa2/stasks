package de.htwsaar.sosa.task7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Inventory {
	private LinkedHashMap<Integer, Product> products = new LinkedHashMap<>();

	public void addProduct(Product product) {
		products.put(product.getProductId(), product);
	}

	public boolean removeProduct(int productId) {
		return products.remove(productId) != null ? true : false;
	}

	public Product findProductById(int productId) {
		return products.get(productId);
	}

	public List<Product> findProductsByCategory(String category) {
		final ArrayList<Product> found = new ArrayList<>();
		products.forEach((id, product) -> {
			if (product.getCategory().equals(category))
				found.add(product);
		});
		return found;
	}

	public List<Product> getAllProducts() {
		return new ArrayList<Product>(products.values());
	}

	public void sortProductsByName() {
		sortProductsBy(Comparator.comparing(Product::getName));
	}

	public void sortProductsByPrice() {
		sortProductsBy(Comparator.comparing(Product::getPrice));
	}

	public void sortProductsBy(Comparator<Product> comparator) {
		List<Product> all = getAllProducts();
		Collections.sort(all, comparator);
		products.clear();
		all.forEach(p -> products.put(p.getProductId(), p));
	}
	
	public List<Product> filterProducts(Predicate<Product> predicate) {
		List<Product> all = getAllProducts();
		all.removeIf(predicate.negate());
		return all;
	}
	
	public List<Product> getLowStockProducts(int threshold) {
		return filterProducts(p->p.getQuantity() < threshold);
	}
	
	public void applyToProducts(Consumer<Product> consumer) {
		products.values().forEach(consumer);
	}
}
