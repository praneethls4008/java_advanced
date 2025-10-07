package datasource.model;

// Book class
public class Book {
	private int id;
    private String title;
    private String author;

    // Constructor
    public Book(int id, String title, String author) {
        this.id = id;
    	this.title = title;
        this.author = author;
    }

    // Getters
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getId() { return id; }

    public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
    public String toString() {
        return "["+ id +"]'" + title + "' by " + author;
    }
}
