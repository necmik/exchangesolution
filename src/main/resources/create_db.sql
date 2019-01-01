create database exchangedb;

use exchangedb;

create table exc_transaction(
 transaction_id int PRIMARY KEY AUTO_INCREMENT,
 transaction_date date not null,
 from_currency varchar(5),
 to_currency varchar(5),
 sell_amount decimal(15,2),
 buy_amount_amount decimal(15,2)
);

select * from exc_transaction;