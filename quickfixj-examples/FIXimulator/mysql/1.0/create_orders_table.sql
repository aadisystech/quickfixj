USE quickfix;

DROP TABLE IF EXISTS orders;

CREATE TABLE quickfix.orders (
order_id varchar(20) NOT NULL,
order_side varchar(4) NOT NULL,
order_type varchar(10) NOT NULL,
order_tif varchar(10) NOT NULL,
order_status varchar(20) NOT NULL,
order_client_id varchar(20) NOT NULL,
order_orig_client_id varchar(20),
order_symbol varchar(12),
order_security_id varchar(12),
order_idsource varchar(10),
order_quantity decimal(10,2) NOT NULL,
order_open_quantity decimal(10,2),
order_executed_quantity decimal(10,2),
order_limit_price decimal(8,2),
order_avg_price decimal(8,2),
PRIMARY KEY pk_order_id (order_id)
) engine=InnoDB;