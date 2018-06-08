package SqlTest.Service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * @author Artur Kuzmik on 18.29.5
 */

@Entity
@Component
@Scope("prototype")
@Table(name = "md_2DB")
public class Customer {

    private @Id @GeneratedValue Long ID;

    @Column(name = "Name")
    private String name;

    @Column(name = "Surname")
    private String surname;

    @Column(name = "orderDate")
    private String orderDate;

    @Column(name = "cost")
    private double cost;

    @Column(name="paid")
    private double paid;

    public Customer() {
    }

    public Customer(String name, String surname, String orderDate,
                    double cost, double paid) {
        super();
        this.name = name;
        this.surname = surname;
        this.orderDate = orderDate;
        this.cost = cost;
        this.paid = paid;

    }

    Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getSurname() {
        return surname;
    }

    void setSurname(String surname) {
        this.surname = surname;
    }

    String getOrderDate() {
        return orderDate;
    }

    void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    double getCost() {
        return cost;
    }

    void setCost(double cost) {
        this.cost = cost;
    }

    double getPaid() {
        return paid;
    }

    void setPaid(double paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {
        return String.format("%-5s\t%-15s\t%-15s\t%-15s\t%.2f\t%.2f",
                ID, name, surname,orderDate,cost, paid);
    }
}
