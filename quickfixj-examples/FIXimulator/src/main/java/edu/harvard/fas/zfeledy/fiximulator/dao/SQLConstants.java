package edu.harvard.fas.zfeledy.fiximulator.dao;

public interface SQLConstants {

    String ADD_ORDER = "INSERT INTO orders (order_id, order_side, order_type, order_tif, order_status, " +
            "order_client_id, order_orig_client_id, order_symbol, order_security_id, order_idsource, order_quantity, " +
            "order_open_quantity, order_executed_quantity, order_limit_price, order_avg_price, order_rec_create_time," +
            "order_rec_update_time) " +
            "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    String UPDATE_ORDER = "UPDATE orders set order_status = ?, order_open_quantity = ?, order_executed_quantity =?," +
            "order_rec_update_time = ? WHERE order_id = ?;";

    String ADD_EXECUTION = "INSERT INTO executions  (exe_exec_id, exe_order_id, exe_exec_type, exe_tran_type, " +
            "exe_last_shares, exe_last_price, exe_leaves_quantity, exe_cum_quantity, exe_avg_price, exe_rec_create_time) " +
            "values (?,?,?,?,?,?,?,?,?,?)";
}
