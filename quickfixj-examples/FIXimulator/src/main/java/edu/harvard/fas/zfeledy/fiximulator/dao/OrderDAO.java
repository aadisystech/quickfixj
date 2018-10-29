package edu.harvard.fas.zfeledy.fiximulator.dao;

import edu.harvard.fas.zfeledy.fiximulator.core.FIXimulator;
import edu.harvard.fas.zfeledy.fiximulator.core.Order;

import java.sql.*;

public class OrderDAO {

    private String url;
    private String driver;
    private String user;
    private String pass;
    private static OrderDAO instance;

    private OrderDAO() {
        try {
            url = FIXimulator.getApplication().getSettings()
                    .getString("JdbcURL");
            driver = FIXimulator.getApplication().getSettings()
                    .getString( "JdbcDriver");
            user = FIXimulator.getApplication().getSettings()
                    .getString("JdbcUser");
            pass = FIXimulator.getApplication().getSettings()
                    .getString("JdbcPassword");
        } catch (Exception e) {

        }
    }

    public static OrderDAO getInstance() {
        if (instance == null) {
            synchronized (OrderDAO.class) {
                if (instance == null) {
                    instance = new OrderDAO();
                }
            }
        }
        return instance;
    }

    public void addOrder(Order order) {
        System.out.println("Add Order " + order.toString());
        Connection connection = getConnection();
        if (connection == null) {
            return;
        }
        try(PreparedStatement statement = connection.prepareStatement(SQLConstants.ADD_ORDER)) {
            statement.setString(1, order.getID());
            statement.setString(2, order.getSide());
            statement.setString(3, order.getType());
            statement.setString(4, order.getTif());
            statement.setString(5, order.getStatus());
            statement.setString(6, order.getClientID());
            statement.setString(7, order.getOrigClientID());
            statement.setString(8, order.getSymbol());
            statement.setString(9, order.getSecurityID());
            statement.setString(10, order.getIdSource());
            statement.setDouble(11, order.getQuantity());
            statement.setDouble(12, order.getOpen());
            statement.setDouble(13, order.getExecuted());
            statement.setDouble(14, order.getLimit());
            statement.setDouble(15, order.getAvgPx());

            boolean result = statement.execute();
            System.out.println("result " + result);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(driver).newInstance();
            connection = DriverManager.getConnection(url, user, pass);
        }
        catch(Exception e) {
            System.out.println("Could not initialize the database.");
            e.printStackTrace();
        }
        return connection;
    }

    public void updateOrder(Order order) {
        System.out.println("Update " + order.toString());
        Connection connection = getConnection();
        if (connection == null) {
            return;
        }
        try(PreparedStatement statement = connection.prepareStatement(SQLConstants.UPDATE_ORDER)) {
            statement.setString(1, order.getStatus());
            statement.setDouble(2, order.getOpen());
            statement.setDouble(3, order.getExecuted());
            statement.setString(4, order.getID());
            int result = statement.executeUpdate();
            System.out.println("result " + result);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
