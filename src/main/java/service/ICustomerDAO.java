package service;

import model.Customer;

import java.sql.SQLException;
import java.util.List;


public interface ICustomerDAO {
    public void add(Customer customer) throws SQLException;

    public Customer findById(int id);

    public List<Customer> findAll();
    public List<Customer> orderByAge();

    public boolean delete(int id) throws SQLException;

    public boolean update(Customer user) throws SQLException;
}
