package ca.jrvs.apps.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExecutor {

    static final Logger logger = LoggerFactory.getLogger(JDBCExecutor.class);

    public static void main(String[] args){

        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost", "hplussport", "postgres", "password");
        try{
            Connection connection = dcm.getConnection();
            CustomerDAO customerDAO = new CustomerDAO(connection);
            Customer customer = new Customer();
            customer.setFirstName("George");
            customer.setLastName("Washington");
            customer.setEmail("george.washington@wh.gov");
            customer.setPhone("(555) 555-6543");
            customer.setAddress("1234 Main St");
            customer.setCity("Mount Vernon");
            customer.setState("VA");
            customer.setZipCode("22121");

            customerDAO.create(customer);
        }catch(SQLException e){
            JDBCExecutor.logger.error("Error: Cannot make a connection", e);
            e.printStackTrace();
        }
    }
}