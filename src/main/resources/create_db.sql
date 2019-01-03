create database exchangedb;

use exchangedb;

drop table exc_transaction;
create table exc_transaction(
 transaction_id int PRIMARY KEY AUTO_INCREMENT,
 transaction_date datetime not null,
 from_currency varchar(5),
 to_currency varchar(5),
 sell_amount decimal(15,2),
 buy_amount decimal(15,2)
);

insert into exc_transaction (
 transaction_id,
 transaction_date,
 from_currency,
 to_currency,
 sell_amount,
 buy_amount
) values (1,
'2019-01-01 20:00:00',
'TRY',
'USD',
10000,
1980
);

insert into exc_transaction (
 transaction_id,
 transaction_date,
 from_currency,
 to_currency,
 sell_amount,
 buy_amount
) values (2,
'2019-01-01 21:00:00',
'TRY',
'USD',
50000,
9000
);

select * from exc_transaction;