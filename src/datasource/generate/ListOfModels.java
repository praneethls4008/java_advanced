package datasource.generate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import datasource.model.*;

public class ListOfModels {

	public static List<Student> getStudents(){
    	List<Student> students = new ArrayList<>();
    	students.add(new Student(1, "Aarav", 19, 69712.49, 43207.72,  85));
        students.add(new Student(2, "Vivaan", 25, 69359.86, 46105.09,  80));
        students.add(new Student(3, "Aditya", 18, 65966.39, 44325.82,  88));
        students.add(new Student(4, "Vihaan", 23, 62117.41, 44010.11,  73));
        students.add(new Student(5, "Arjun", 24, 71899.64, 59307.02,  57));
        students.add(new Student(6, "Sai", 22, 70123.11, 49763.56,  91));
        students.add(new Student(7, "Krishna", 20, 73829.44, 68192.33, 76));
        students.add(new Student(8, "Ishaan", 21, 68901.77, 63245.12, 83));
        students.add(new Student(9, "Rohan", 25, 72566.89, 53422.45, 64));
        students.add(new Student(10, "Kabir", 19, 56677.33, 47599.55, 95));
        students.add(new Student(11, "Ayaan", 23, 63422.90, 43255.10, 79));
        students.add(new Student(12, "Dhruv", 24, 75120.66, 42190.22, 88));
        students.add(new Student(13, "Aryan", 20, 43200.11, 38999.42, 67));
        students.add(new Student(14, "Ansh", 18, 65433.78, 52344.99, 72));
        students.add(new Student(15, "Rudra", 22, 77555.12, 60123.45, 90));
        students.add(new Student(16, "Atharv", 25, 61100.66, 38900.23, 85));
        students.add(new Student(17, "Shivansh", 19, 48001.20, 31000.50, 60));
        students.add(new Student(18, "Kian", 21, 74500.90, 61234.70, 98));
        students.add(new Student(19, "Parth", 23, 43220.40, 40110.20, 84));
        students.add(new Student(20, "Yuvraj", 24, 59999.99, 51222.88, 70));
        students.add(new Student(21, "Reyansh", 22, 71222.30, 69111.20, 92));
        students.add(new Student(22, "Harsh", 20, 49000.80, 45555.40, 87));
        students.add(new Student(23, "Manav", 21, 50111.25, 50111.25, 66));
        students.add(new Student(24, "Siddharth", 25, 78000.00, 75222.10, 93));
        students.add(new Student(25, "Om", 18, 47000.60, 39888.40, 61));
        students.add(new Student(26, "Raghav", 19, 59000.33, 50012.11, 75));
        students.add(new Student(27, "Pranav", 22, 61000.44, 42222.55, 89));
        students.add(new Student(28, "Laksh", 23, 70012.22, 50012.99, 78));
        students.add(new Student(29, "Tejas", 20, 64888.10, 47888.20, 82));
        students.add(new Student(30, "Mihir", 21, 69999.99, 69999.99, 96));
        return students;
    }

	public static List<Book> getBooks(){
		List<Book> books = new ArrayList<>();
		books.add(new Book(1, "The Great Gatsby", "F. Scott Fitzgerald"));
        books.add(new Book(2, "1984", "George Orwell"));
        books.add(new Book(3, "To Kill a Mockingbird", "Harper Lee"));
        books.add(new Book(4, "Moby Dick", "Herman Melville"));
        books.add(new Book(5, "Pride and Prejudice", "Jane Austen"));
        books.add(new Book(6, "War and Peace", "Leo Tolstoy"));
        books.add(new Book(7, "The Hobbit", "J.R.R. Tolkien"));
        books.add(new Book(8, "Harry Potter", "J.K. Rowling"));
        books.add(new Book(9, "The Catcher in the Rye", "J.D. Salinger"));
        books.add(new Book(10, "The Alchemist", "Paulo Coelho"));
        books.add(new Book(11, "Clean Code", "Robert C. Martin"));
        books.add(new Book(12, "Java in Depth", "Joshua Bloch"));
        books.add(new Book(13, "Design Patterns", "Erich Gamma"));
        books.add(new Book(14, "Crime and Punishment", "Fyodor Dostoevsky"));
        books.add(new Book(15, "Brave New World", "Aldous Huxley"));
        books.add(new Book(16, "The Lord of the Rings", "J.R.R. Tolkien"));
        books.add(new Book(17, "Don Quixote", "Miguel de Cervantes"));
        books.add(new Book(18, "The Kite Runner", "Khaled Hosseini"));
        books.add(new Book(19, "Thinking, Fast and Slow", "Daniel Kahneman"));
        books.add(new Book(20, "Sapiens", "Yuval Noah Harari"));
        books.add(new Book(21, "Anna Karenina", "Leo Tolstoy"));
        books.add(new Book(22, "Great Expectations", "Charles Dickens"));
        books.add(new Book(23, "The Divine Comedy", "Dante Alighieri"));
        books.add(new Book(24, "Meditations", "Marcus Aurelius"));
        books.add(new Book(25, "Wuthering Heights", "Emily Brontë"));
        books.add(new Book(26, "Les Misérables", "Victor Hugo"));
        books.add(new Book(27, "The Brothers Karamazov", "Fyodor Dostoevsky"));
        books.add(new Book(28, "The Picture of Dorian Gray", "Oscar Wilde"));
        books.add(new Book(29, "Ulysses", "James Joyce"));
        books.add(new Book(30, "Fahrenheit 451", "Ray Bradbury"));
        return books;
	}
	
	public static List<Library> getLibraries(){
		Book b1  = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald");
        Book b2  = new Book(2, "1984", "George Orwell");
        Book b3  = new Book(3, "To Kill a Mockingbird", "Harper Lee");
        Book b4  = new Book(4, "Moby Dick", "Herman Melville");
        Book b5  = new Book(5, "Pride and Prejudice", "Jane Austen");
        Book b6  = new Book(6, "War and Peace", "Leo Tolstoy");
        Book b7  = new Book(7, "The Hobbit", "J.R.R. Tolkien");
        Book b8  = new Book(8, "Harry Potter", "J.K. Rowling");
        Book b9  = new Book(9, "The Catcher in the Rye", "J.D. Salinger");
        Book b10 = new Book(10, "The Alchemist", "Paulo Coelho");
        Book b11 = new Book(11, "Clean Code", "Robert C. Martin");
        Book b12 = new Book(12, "Java in Depth", "Joshua Bloch");
        Book b13 = new Book(13, "Design Patterns", "Erich Gamma");
        Book b14 = new Book(14, "Crime and Punishment", "Fyodor Dostoevsky");
        Book b15 = new Book(15, "Brave New World", "Aldous Huxley");
        Book b16 = new Book(16, "The Lord of the Rings", "J.R.R. Tolkien");
        Book b17 = new Book(17, "Don Quixote", "Miguel de Cervantes");
        Book b18 = new Book(18, "The Kite Runner", "Khaled Hosseini");
        Book b19 = new Book(19, "Thinking, Fast and Slow", "Daniel Kahneman");
        Book b20 = new Book(20, "Sapiens", "Yuval Noah Harari");
        Book b21 = new Book(21, "Anna Karenina", "Leo Tolstoy");
        Book b22 = new Book(22, "Great Expectations", "Charles Dickens");
        Book b23 = new Book(23, "The Divine Comedy", "Dante Alighieri");
        Book b24 = new Book(24, "Meditations", "Marcus Aurelius");
        Book b25 = new Book(25, "Wuthering Heights", "Emily Brontë");
        Book b26 = new Book(26, "Les Misérables", "Victor Hugo");
        Book b27 = new Book(27, "The Brothers Karamazov", "Fyodor Dostoevsky");
        Book b28 = new Book(28, "The Picture of Dorian Gray", "Oscar Wilde");
        Book b29 = new Book(29, "Ulysses", "James Joyce");
        Book b30 = new Book(30, "Fahrenheit 451", "Ray Bradbury");
        
		Library libA = new Library("Library A");
        Library libB = new Library("Library B");
        Library libC = new Library("Library C");
        Library libD = new Library("Library D");
        Library libE = new Library("Library E");

        // Assign 6 books to each library (manually)
        libA.addBook(b1); libA.addBook(b2); libA.addBook(b3);
        libA.addBook(b4); libA.addBook(b5); libA.addBook(b6);

        libB.addBook(b7); libB.addBook(b8); libB.addBook(b9);
        libB.addBook(b10); libB.addBook(b11); libB.addBook(b12);

        libC.addBook(b13); libC.addBook(b14); libC.addBook(b15);
        libC.addBook(b16); libC.addBook(b17); libC.addBook(b18);

        libD.addBook(b19); libD.addBook(b20); libD.addBook(b21);
        libD.addBook(b22); libD.addBook(b23); libD.addBook(b24);

        libE.addBook(b25); libE.addBook(b26); libE.addBook(b27);
        libE.addBook(b28); libE.addBook(b29); libE.addBook(b30);

        List<Library> libraries  = new ArrayList<>();
        libraries.add(libA);
        libraries.add(libB);
        libraries.add(libC);
        libraries.add(libD);
        libraries.add(libE);
        return libraries;
	}
	
	public static List<Customer> genrateCustomersData() {
	    Item i1 = new Item(1, "Laptop", 1200);
        Item i2 = new Item(2, "Phone", 800);
        Item i3 = new Item(3, "Headphones", 150);
        Item i4 = new Item(4, "Monitor", 300);
        Item i5 = new Item(5, "Keyboard", 100);
        Item i6 = new Item(6, "Mouse", 80);
        Item i7 = new Item(7, "Tablet", 500);
        Item i8 = new Item(8, "Smartwatch", 250);
        Item i9 = new Item(9, "Printer", 200);
        Item i10 = new Item(10, "Camera", 900);

        // Create Orders with different dates
        Order o1 = new Order(1, LocalDate.of(2025, 9, 29), Arrays.asList(i1, i2));
        Order o2 = new Order(2, LocalDate.of(2024, 2, 5), Arrays.asList(i3, i4, i5));
        Order o3 = new Order(3, LocalDate.of(2025, 9, 29), Arrays.asList(i1, i2, i3, i4, i5, i6, i7));  // older than 7 days
        Order o4 = new Order(4, LocalDate.of(2025, 8, 12), Arrays.asList(i8, i9, i10));
        Order o5 = new Order(5, LocalDate.of(2024, 5, 16), Arrays.asList(i3, i7));
        Order o6 = new Order(6, LocalDate.of(2025, 4, 17), Arrays.asList(i3, i7));

        // Create Customers with Orders
        Customer c1 = new Customer(1, "Alice", Arrays.asList(o1, o2));
        Customer c2 = new Customer(2, "Bob", Arrays.asList(o3));
        Customer c3 = new Customer(3, "Charlie", Arrays.asList(o4, o5));
        Customer c4 = new Customer(4, "Alon", Arrays.asList(o6));

        return Arrays.asList(c1, c2, c3, c4);
	}
	
}
