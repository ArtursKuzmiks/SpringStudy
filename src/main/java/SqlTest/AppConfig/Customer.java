package SqlTest.AppConfig;

import javax.persistence.Entity;


/**
 * @author Artur Kuzmik on 18.29.5
 */

@Entity
public class Customer {

    private int ID;
    private String name;
    private String surname;
    private String orderDate;
    private float cost;
    private float paid;

    public Customer() {
    }

    public Customer(int ID, String name, String surname, String orderDate,
                    float cost, float paid) {
        super();
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.orderDate = orderDate;
        this.cost = cost;
        this.paid = paid;

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public float getPaid() {
        return paid;
    }

    public void setPaid(float paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {
        return String.format("%-5s\t%-15s\t%-15s\t%-15s\t%.2f\t%.2f",
                ID, name, surname,orderDate,cost, paid);
    }
}
