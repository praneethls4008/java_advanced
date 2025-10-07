package datasource.model;

import java.util.ArrayList;
import java.util.List;

//Library class (HAS-A list of Books)
public class Library {
 private String name;
 private List<Book> books;

 // Constructor
 public Library(String name) {
     this.name = name;
     this.books = new ArrayList<>();
 }
 
 

 public String getName() {
	return name;
}



 public void setName(String name) {
	this.name = name;
 }



 public List<Book> getBooks() {
	return books;
 }



 public void setBooks(List<Book> books) {
	this.books = books;
 }



 // Add book to library
 public void addBook(Book book) {
     books.add(book);
 }

 // Display all books
 public void showBooks() {
     System.out.println("Books in " + name + ":");
     for (Book book : books) {
         System.out.println("- " + book);
     }
 }
}
