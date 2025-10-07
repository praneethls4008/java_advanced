package datasource.model;

import java.util.List;

public class Customer {
    private int id;
    private String name;
    private List<Order> orders;

    public Customer(int id, String name, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.orders = orders;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public List<Order> getOrders() { return orders; }

    @Override
    public String toString() {
        return "Customer " + id + " - " + name + ", Orders: " + orders.size();
    }
}