package edu.harvard.fas.zfeledy.fiximulator.dao;

import edu.harvard.fas.zfeledy.fiximulator.core.Execution;
import edu.harvard.fas.zfeledy.fiximulator.core.FIXimulator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class ExecutionDAO {

    private String url;
    private String driver;
    private String user;
    private String pass;
    private static ExecutionDAO instance;

    private ExecutionDAO() {
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

    public static ExecutionDAO getInstance() {
        if (instance == null) {
            synchronized (ExecutionDAO.class) {
                if (instance == null) {
                    instance = new ExecutionDAO();
                }
            }
        }
        return instance;
    }

    public void addExecution(Execution execution) {
        System.out.println("Add Execution " + execution.toString());
        Connection connection = getConnection();
        if (connection == null) {
            return;
        }
        try(PreparedStatement statement = connection.prepareStatement(SQLConstants.ADD_EXECUTION)) {
            statement.setString(1, execution.getID());
            statement.setString(2, execution.getOrder().getID());
            statement.setString(3, execution.getExecType());
            statement.setString(4, execution.getExecTranType());
            statement.setDouble(5, execution.getLastShares());
            statement.setDouble(6, execution.getLastPx());
            statement.setDouble(7, execution.getLeavesQty());
            statement.setDouble(8, execution.getCumQty());
            statement.setDouble(9, execution.getAvgPx());
            statement.setTimestamp(10, Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC"))));
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

}
