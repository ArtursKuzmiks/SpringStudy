package SqlTest.Dao;

import SqlTest.AppConfig.Customer;
import SqlTest.Crypto.AesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Artur Kuzmik on 18.29.5
 */

@Repository
@Qualifier("customerDao")
public class CustomerDaoImpl implements CustomerDao {


    private JdbcTemplate jdbcTemplate;
    private AesUtil aesUtil;

    @Autowired
    public CustomerDaoImpl(JdbcTemplate jdbcTemplate, AesUtil aesUtil) {
        this.jdbcTemplate = jdbcTemplate;
        this.aesUtil = aesUtil;
    }


    @Override
    public void addCustomer(Customer customer) {
        try {
            jdbcTemplate.update("INSERT INTO md_2DB (Name,Surname,orderDate,cost,paid) VALUES (?,?,?,?,?)",
                    aesUtil.encrypt(customer.getName()), aesUtil.encrypt(customer.getSurname()),
                    aesUtil.encrypt(customer.getOrderDate()), customer.getCost(), customer.getPaid());
        } catch (Exception e) {
            System.out.println("Encrypting error");
        }

        System.out.println("Customer Added!");
    }

    @Override
    public void editCustomer(Customer customer, int customerId) {
        try {
            jdbcTemplate.update("REPLACE INTO md_2DB (id,Name,Surname,orderDate,cost,paid) VALUES(?,?,?,?,?,?)",
                    customerId, aesUtil.encrypt(customer.getName()), aesUtil.encrypt(customer.getSurname()),
                    aesUtil.encrypt(customer.getOrderDate()), customer.getCost(), customer.getPaid());
        } catch (Exception e) {
            System.out.println("Encrypting error");
        }

        System.out.println("Customer Updated!");

    }

    @Override
    public void deleteCustomer(int customerId) {
        jdbcTemplate.update("DELETE from md_2DB WHERE id = ?", customerId);

        System.out.println("Customer Delete!");

    }

    @Override
    public List<Customer> sortDateSurname() {
        return listDecrypt(jdbcTemplate.query("SELECT * FROM md_2DB ORDER by orderDate,Surname ASC",
                new BeanPropertyRowMapper<>(Customer.class)));
    }

    @Override
    public List<Customer> debtors() {
        return listDecrypt(jdbcTemplate.query("SELECT * FROM md_2DB WHERE paid < cost",
                new BeanPropertyRowMapper<>(Customer.class)));
    }

    @Override
    public Customer find(int customerId) {
        try {
            return customerDecrypt(jdbcTemplate.queryForObject("SELECT * FROM md_2DB WHERE id = ?",
                    new Object[]{customerId}, new BeanPropertyRowMapper<>(Customer.class)));

        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    @Override
    public List<Customer> findAll() {
        return listDecrypt(jdbcTemplate.query("SELECT * FROM md_2DB",
                new BeanPropertyRowMapper<>(Customer.class)));

    }

    private List<Customer> listDecrypt(List<Customer> customers){
        for (Customer aCustomers : customers) {
            try {

                aCustomers.setName(aesUtil.decrypt(aCustomers.getName()));
                aCustomers.setSurname(aesUtil.decrypt(aCustomers.getSurname()));
                aCustomers.setOrderDate(aesUtil.decrypt(aCustomers.getOrderDate()));

            } catch (Exception e) {
                System.out.println("Decrypting error");
            }
        }

        return customers;
    }

    private Customer customerDecrypt(Customer customer){
        try {

            customer.setName(aesUtil.decrypt(customer.getName()));
            customer.setSurname(aesUtil.decrypt(customer.getSurname()));
            customer.setOrderDate(aesUtil.decrypt(customer.getOrderDate()));

        } catch (Exception e) {
            System.out.println("Decrypting error");
        }
        return customer;
    }
}
