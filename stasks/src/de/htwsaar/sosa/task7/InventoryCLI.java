package de.htwsaar.sosa.task7;

import java.util.List;
import java.util.Scanner;

public class InventoryCLI {
	private Scanner scanner = new Scanner(System.in);
	private Inventory inventory = new Inventory();

	public void start() {
		int cmd = 0;
		while (true) {
			showMenu();

			try {
				cmd = scanner.nextInt();

				switch (cmd) {
				case 1:
					addProduct();
					break;

				case 2:
					removeProduct();
					break;

				case 3:
					searchById();
					break;

				case 4:
					displayByCategory();
					break;

				case 5:
					displayAll();
					break;

				case 6:
					sortByName();
					break;

				case 7:
					sortByPrice();
					break;

				case 8:
					displayByLowQuantity();
					break;

				case 9:
					displayByUserFilter();
					break;

				case 10:
					incrementPrice();
					break;

				case 11:
					exit();

				default:
					System.out.println("Invalid command!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}

		}

	}

	private void showMenu() {
		System.out.println();
		System.out.println("1. Add product");
		System.out.println("2. Remove product");
		System.out.println("3. Search by ID");
		System.out.println("4. Display by category");
		System.out.println("5. Display all");
		System.out.println("6. Sort by name");
		System.out.println("7. Sort by price");
		System.out.println("8. Display by low quantity");
		System.out.println("9. Display by user filter");
		System.out.println("10. Increment price by 1 percent");
		System.out.println("11. Exit");
	}

	private String ask(String prompt) {
		System.out.print(prompt + ": ");
		return scanner.next();
	}

	private void addProduct() {
		Product product = new Product(Integer.parseInt(ask("ID")), ask("Name"), ask("Category"),
				Double.parseDouble(ask("Price")), Integer.parseInt(ask("Quantity")));
		inventory.addProduct(product);
		System.out.println("Product added=>" + product);
	}

	private void removeProduct() {
		int id = Integer.parseInt(ask("ID"));
		boolean removed = inventory.removeProduct(id);
		if (removed) {
			System.out.println("Product with ID " + id + " removed");
		} else {
			System.out.println("Product with ID " + id + " not found!");
		}
	}

	private void sortByName() {
		inventory.sortProductsByName();
		System.out.println("-----Sorted by name-----");
		displayAll();
	}

	private void sortByPrice() {
		inventory.sortProductsByPrice();
		System.out.println("-----Sorted by price-----");
		displayAll();

	}

	private void displayAll() {
		inventory.applyToProducts(System.out::println);
	}

	private void searchById() {
		int id = Integer.parseInt(ask("ID"));
		Product p = inventory.findProductById(id);
		if (p != null) {
			System.out.println("Found=>" + p);
		} else {
			System.out.println("Product with ID " + id + " not found!");
		}
	}
	
	private void displayByCategory() {
		String category = ask("Category");
		List<Product> found = inventory.findProductsByCategory(category);
		System.out.println("Category: " + category);
		if (!found.isEmpty()) {
			found.forEach(System.out::println);
		} else {
			System.out.println("No products found!");
		}
	}
	
	private void incrementPrice() {
		inventory.applyToProducts(p->p.setPrice(p.getPrice()*101/100));
		System.out.println("Price incremented by 1%");
		displayAll();
	}
	
	private void displayByUserFilter() {
		final String name = ask("Name contains");
		final String category = ask("Category contains");
		//final double priceLow = Double.parseDouble(ask("Price >="));
		//final double priceHigh = Double.parseDouble(ask("Price <="));
		//final int quantityLow = Integer.parseInt(ask("Quantity >="));
		//final int quantityHigh = Integer.parseInt(ask("Quantity <="));
		List<Product> filtered = inventory.filterProducts(p->p.getName().contains(name) && p.getCategory().contains(category));
		System.out.println("Filtered by user");
		filtered.forEach(System.out::println);
		
	}
	
	private void displayByLowQuantity() {
		final int quantity = Integer.parseInt(ask("Quantity"));
		List<Product> filtered = inventory.filterProducts(p->p.getQuantity()<quantity);
		System.out.println("Filtered by low quantity");
		filtered.forEach(System.out::println);
	}
	private void exit() {
		System.out.println("Bye bye");
		System.exit(0);
	}
}
