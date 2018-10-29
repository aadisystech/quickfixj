USE quickfix;

DROP TABLE IF EXISTS executions;

CREATE TABLE executions (
exe_exec_id varchar(20) not null,
exe_order_id varchar(20) not null,
exe_exec_type varchar(15) not null,
exe_tran_type varchar(15) not null,
exe_last_shares decimal(10,2),
exe_last_price decimal(8,2),
exe_leaves_quantity decimal(10,2),
exe_cum_quantity decimal(10,2),
exe_avg_price decimal(8,2),
exe_rec_create_time TIMESTAMP not null,
PRIMARY KEY (exe_exec_id),
foreign key fk_order_id (exe_order_id)
references orders(order_id)
ON update cascade
on delete restrict) engine=InnoDB;