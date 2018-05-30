package SqlTest.Dao;

import SqlTest.AppConfig.Customer;
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

    @Autowired
    public CustomerDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void addCustomer(Customer customer) {
        jdbcTemplate.update("INSERT INTO md_2DB (id,Name,Surname,orderDate,cost,paid) VALUES (?,?,?,?,?,?)",
                customer.getID(), customer.getName(), customer.getSurname(),customer.getOrderDate(),
                customer.getCost(), customer.getPaid());

        System.out.println("Customer Added!");
    }

    @Override
    public void editCustomer(Customer customer,int customerId) {
        jdbcTemplate.update("REPLACE INTO md_2DB (id,Name,Surname,orderDate,cost,paid) VALUES(?,?,?,?,?,?)",
                customerId, customer.getName(), customer.getSurname(), customer.getOrderDate(),
                customer.getCost(), customer.getPaid());

        System.out.println("Customer Updated!");

    }

    @Override
    public void deleteCustomer(int customerId) {
        jdbcTemplate.update("DELETE from md_2DB WHERE id = ?", customerId);

        System.out.println("Customer Delete!");

    }

    @Override
    public List<Customer> sortDateSurname() {

        return jdbcTemplate.query("SELECT * FROM md_2DB ORDER by orderDate,Surname ASC",
                new BeanPropertyRowMapper<>(Customer.class));
    }

    @Override
    public List<Customer> debtors() {

        return jdbcTemplate.query("SELECT * FROM md_2DB WHERE paid < cost",
                new BeanPropertyRowMapper<>(Customer.class));
    }

    @Override
    public Customer find(int customerId) {
        try {

            return jdbcTemplate.queryForObject("SELECT * FROM md_2DB WHERE id = ?",
                    new Object[]{customerId}, new BeanPropertyRowMapper<>(Customer.class));

        } catch (EmptyResultDataAccessException e){
            return null;
        }

    }

    @Override
    public List<Customer> findAll() {
        return jdbcTemplate.query("SELECT * FROM md_2DB",
                new BeanPropertyRowMapper<>(Customer.class));
    }
}
