package datasource.model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    int id;
    String name;
    int age;
    double termFee;
    double paidFee;
    int attendance;

    public Student(int id, String name, int age, double termFee, double paidFee, int attendance) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.termFee = termFee;
        this.paidFee = paidFee;
        this.attendance = attendance;
    }
    
    
    
    public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}



	public double getTermFee() {
		return termFee;
	}



	public void setTermFee(double termFee) {
		this.termFee = termFee;
	}



	public double getPaidFee() {
		return paidFee;
	}



	public void setPaidFee(double paidFee) {
		this.paidFee = paidFee;
	}



	public int getAttendance() {
		return attendance;
	}



	public void setAttendance(int attendance) {
		this.attendance = attendance;
	}



	@Override
    public String toString() {
        return id + " - " + name + " | Age: " + age +
               " | Term Fee: " + termFee +
               " | Paid Fee: " + paidFee +
               " | Attendance: " + attendance + "%";
    }
}
