package SqlTest.Service;

import SqlTest.AppConfig.Customer;
import SqlTest.Dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Artur Kuzmik on 18.29.5
 */

@Service("CustomerService")
public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDao;
    private Customer customer;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao, Customer customer) {
        this.customerDao = customerDao;
        this.customer = customer;
    }

    @Override
    public void addCustomer() throws IOException {

        Date data = null;
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");

        try {

            System.out.println("Add Customer");

            System.out.print("ID: ");
            customer.setID(Integer.parseInt(reader.readLine()));

            System.out.print("Name: ");
            customer.setName(format(reader.readLine()));

            System.out.print("Surname: ");
            customer.setSurname(format(reader.readLine()));

            System.out.print("Date(yyyy-MM-dd):");

            try {

                boolean end = false;
                while (!end) {
                    data = new SimpleDateFormat("yyyy-MM-dd").parse(reader.readLine());
                    if ((data != null ? data.compareTo(new Date()) : 0) < 0) {
                        System.out.println("We do not have a time machine :)");
                        System.out.print("Date(yyyy-MM-dd):");
                    } else {
                        end = true;
                    }
                }

            } catch (ParseException e) {
                System.out.println("Incorrect format");
            }

            customer.setOrderDate(sim.format(data));

            System.out.print("Cost: ");
            customer.setCost(Float.parseFloat(reader.readLine()));

            System.out.print("Paid: ");
            customer.setPaid(Float.parseFloat(reader.readLine()));

            customerDao.addCustomer(customer);


        } catch (IllegalArgumentException e) {
            System.out.println("Input error");
        }

    }

    @Override
    public void editCustomer() throws IOException {
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");

        try {
            System.out.print("Input Customer ID: ");

            customer = find(Integer.parseInt(reader.readLine()));

            if (customer != null) {
                System.out.println("Customer:");
                title();
                System.out.println(customer.toString());
                System.out.println();

                try {
                    System.out.println("Editing menu");
                    System.out.println("1: Name");
                    System.out.println("2: Surname");
                    System.out.println("3: Order date");
                    System.out.println("4: Cost");
                    System.out.println("5: Paid");
                    System.out.println("0: End");
                    loop:
                    for (; ; ) {
                        System.out.print("Menu: ");
                        int menu = Integer.parseInt(reader.readLine());

                        switch (menu) {
                            case 1:
                                System.out.print("Input Name: ");
                                customer.setName(format(reader.readLine()));
                                break;
                            case 2:
                                System.out.print("Input Surname: ");
                                customer.setSurname(format(reader.readLine()));
                                break;
                            case 3:
                                System.out.print("Input date (yyyy-MM-dd): ");

                                try {
                                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(reader.readLine());

                                    if (date.compareTo(new Date()) < 0)
                                        System.out.println("We do not have a time machine :)");
                                    else
                                        customer.setOrderDate(sim.format(date));
                                } catch (ParseException e) {
                                    System.out.println("Incorrect date format");
                                }
                                break;
                            case 4:
                                System.out.print("Input cost: ");
                                customer.setCost(Float.parseFloat(reader.readLine()));
                                break;
                            case 5:
                                System.out.print("Input paid: ");
                                float paid = Float.parseFloat(reader.readLine());

                                if (paid > customer.getCost())
                                    System.out.println("Paid > cost, error");
                                else
                                    customer.setPaid(paid);
                                break;
                            case 0:
                                break loop;
                            default:
                                System.out.println("There is no such option, try again: ");
                        }
                    }

                    customerDao.editCustomer(customer,customer.getID());

                } catch (IllegalArgumentException e) {
                    System.out.println("Input error");
                }

            } else
                System.out.println("There is no such ID");

        } catch (IllegalArgumentException e) {
            System.out.println("Input error");
        }
    }

    @Override
    public void deleteCustomer() throws IOException {

        try {

            System.out.print("Input Customer ID: ");

            int customerId = Integer.parseInt(reader.readLine());

            if (find(customerId) != null)
                customerDao.deleteCustomer(customerId);
            else
                System.out.println("There is no such ID");

        } catch (IllegalArgumentException e) {
            System.out.println("Input error");
        }

    }

    @Override
    public void sortDateSurname() {
        List<Customer> customers = customerDao.sortDateSurname();

        title();

        for (Customer customer : customers) {
            System.out.println(customer.toString());
        }

        System.out.println();

    }

    @Override
    public void debtors() {
        List<Customer> customers = customerDao.debtors();

        title();

        for (Customer customer : customers) {
            System.out.println(customer.toString());
        }

        System.out.println();
    }

    @Override
    public void allPrice() {
        double amount = 0;
        List<Customer> customers = findAll();

        for (Customer customer : customers) amount += customer.getCost();

        System.out.printf("Cost of all orders: %.2f\n", amount);

    }

    @Override
    public Customer find(int customerId) {
        return customerDao.find(customerId);
    }

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Override
    public void printCustomers() {
        List<Customer> customers = findAll();

        title();

        for (Customer customer : customers) {
            System.out.println(customer.toString());
        }
        System.out.println();

    }

    private void title() {
        System.out.printf("\n%-5s\t%-15s\t%-15s\t%-15s\t%-5s\t%s\n",
                "ID", "Name", "Surname", "Date", "Cost", "Paid");

        System.out.printf("%-5s\t%-15s\t%-15s\t%-15s\t%-5s\t%s\n",
                "----", "-------", "--------", "----------", "-----", "-----");
    }

    private String format(String a) {
        String temp = a.trim();
        return temp.substring(0, 1).toUpperCase() + temp.substring(1);

    }

}
