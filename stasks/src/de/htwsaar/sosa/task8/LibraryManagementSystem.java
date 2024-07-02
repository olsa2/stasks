package de.htwsaar.sosa.task8;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
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
		return allBooks.stream().sorted(Comparator.comparing(Book::getRating)).toList();
	}
}
