package edu.harvard.fas.zfeledy.fiximulator.dao;

public interface SQLConstants {

    String ADD_ORDER = "INSERT INTO orders (order_id, order_side, order_type, order_tif, order_status, " +
            "order_client_id, order_orig_client_id, order_symbol, order_security_id, order_idsource, order_quantity, " +
            "order_open_quantity, order_executed_quantity, order_limit_price, order_avg_price) " +
            "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    String UPDATE_ORDER = "UPDATE orders set order_status = ? WHERE order_id = ?;";
}
