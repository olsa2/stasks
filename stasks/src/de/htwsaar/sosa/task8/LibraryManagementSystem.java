package de.htwsaar.sosa.task8;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.LinkedList;

public class LibraryManagementSystem {
	Set<Book> allBooks = new TreeSet<>();
	HashMap<String, User> users = new HashMap<>();
	HashMap<LocalDate, List<Book>> borrowedBooks = new HashMap<>();

	// return previous user
	public User addUser(User user) {
		return users.put(user.getReaderId(), user);
	}

	public boolean addBook(Book book) {
		return allBooks.add(book);
	}

	public boolean borrowBook(String readerId, Book book, LocalDate returnDate) {
		User user = users.get(readerId);
		if (user == null) {
			return false;
		}

		if (!allBooks.contains(book)) {
			return false;
		}

		if(!user.borrowBook(book, returnDate)) {
			return false;
		}

		List<Book> booksByDate = borrowedBooks.get(book.getReturnDate());
		if (booksByDate == null) {
			booksByDate = new LinkedList<>();
			borrowedBooks.put(book.getReturnDate(), booksByDate);
		}
		booksByDate.add(book);
		return true;
	}
	
	public Collection<Book> getAllBooks() {
		return allBooks;
	}

	public void displayBooksBorrowedByReader(String readerId) {
		User user = users.get(readerId);
		if (user != null) {
			user.getBooks().stream().forEach(System.out::println);

		}
	}
	
	public void displayAllBorrowedBooks() {
		borrowedBooks.forEach((date,books)->{ System.out.println(date);books.forEach(System.out::println);});
	}
	
	public List<Book> filterByYear(int year) {
		return allBooks.stream().filter(book->book.getYear()>=year).toList();
	}
	
	public List<Book> sortBooksByPage() {
		return allBooks.stream().sorted(Comparator.comparing(Book::getPages)).toList();
	}
	
	public List<Book> sortBooksByRating() {
		return allBooks.stream().sorted(Comparator.comparing(Book::getRating).reversed()).toList();
	}
	
	public List<Book> filterAndSortBooks(Predicate<Book>filter,Comparator<Book> sorter) {
		return allBooks.stream().filter(filter).sorted(sorter).toList();
	}
	
	public int getTotalPages() {
		return allBooks.stream().map(book->book.getPages()).reduce(0,Integer::sum);
	}
	
	public List<Book> getTopRatedBooks() {
		Comparator<Book> c = Comparator.comparing(Book::getRating).reversed();
		return allBooks.stream().sorted(c).limit(3).toList();
	}
	
	public List<String> getAuthorsMostBooks() {
	    Map<String,List<Book>> byAuthor = allBooks.stream().collect(Collectors.groupingBy(Book::getAuthor));
	    return byAuthor.entrySet().stream().sorted((e1,e2)->e2.getValue().size() - e1.getValue().size()).limit(3).map(e->e.getKey()).toList();
	    
		
	}
}
