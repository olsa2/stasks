package de.htwsaar.sosa.task8;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class User {
	private String name;
	private String readerId;
	private Set<Book> books = new TreeSet<Book>();
	
	public User(String name, String readerId) {
		this.name = name;
		this.readerId = readerId;
	}
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getReaderId() {
		return readerId;
	}


	public void setReaderId(String readerId) {
		this.readerId = readerId;
	}


	public List<Book> getBooks() {
		Stream<Book> s = books.stream().sorted(Comparator.comparing(Book::getReturnDate));
		return s.collect(Collectors.toList());
		//ArrayList<Book> books2 = new ArrayList<>(books);
		//Collections.sort(books2, Comparator.comparing(Book::getReturnDate));
		//return books2;
	}



	public boolean borrowBook(Book book, LocalDate returnDate) {
		if (!book.isBorrowed()) {
			book.setBorrowed(true);
			book.setReturnDate(returnDate);
			books.add(book);
			return true;
		}
		return false;
	}
	
	public boolean returnBook(Book book) {
		if (!book.isBorrowed()) {
			return false;
		}
		
		if (books.remove(book)) {
			book.setBorrowed(false);
			book.setReturnDate(null);
			return true;
		}
		return false;
	}
	
	
	
}
