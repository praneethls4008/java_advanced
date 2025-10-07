package datasource.model;

import java.time.LocalDate;
import java.util.List;

public class Order {
	private int id;
    private LocalDate date;
    private List<Item> items;

    public Order(int id, LocalDate date, List<Item> items) {
        this.id = id;
        this.date = date;
        this.items = items;
    }

    
    public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public LocalDate getDate() { return date; }
    public List<Item> getItems() { return items; }

    @Override
    public String toString() {
        return "Order " + id + " [" + date + "] Items: " + items;
    }
}