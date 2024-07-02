package de.htwsaar.sosa.task8;

import java.time.LocalDate;
import java.util.Objects;

public class Book implements Comparable<Book> {
	private String title;
	private String author;
	private int year;
	private int pages;
	private String genre;
	private double rating;
	private boolean borrowed;
	private LocalDate returnDate;

	public Book(String title, String author, int year, int pages, String genre, double rating, boolean borrowed,
			LocalDate returnDate) {
		super();
		this.title = title;
		this.author = author;
		this.year = year;
		this.pages = pages;
		this.genre = genre;
		this.rating = rating;
		this.borrowed = borrowed;
		this.returnDate = returnDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public boolean isBorrowed() {
		return borrowed;
	}

	public void setBorrowed(boolean borrowed) {
		this.borrowed = borrowed;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", year=" + year + ", pages=" + pages + ", genre="
				+ genre + ", rating=" + rating + ", borrowed=" + borrowed + ", returnDate=" + returnDate + "]";
	}

	@Override
	public int compareTo(Book o) {

		if (o.getTitle() == null && getTitle() == null) {
			return 0;
		}
		if (getTitle() == null) {
			return -1;
		}
		return getTitle().compareTo(o.getTitle());
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, title, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(author, other.author) && Objects.equals(title, other.title) && year == other.year;
	}
	

}
