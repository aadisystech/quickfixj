USE quickfix;

DROP TABLE IF EXISTS executions;

CREATE TABLE executions (
exe_exec_id varchar(20),
exe_order_id varchar(20),
exe_exec_type varchar(15),
exe_tran_type varchar(15),
exe_last_shares decimal(10,2),
exe_last_price decimal(8,2),
exe_leaves_quantity decimal(10,2),
exe_cum_quantity decimal(10,2),
exe_avg_price decimal(8,2),
PRIMARY KEY (exe_exec_id),
foreign key fk_order_id (exe_order_id)
references orders(order_id)
ON update cascade
on delete restrict) engine=InnoDB;