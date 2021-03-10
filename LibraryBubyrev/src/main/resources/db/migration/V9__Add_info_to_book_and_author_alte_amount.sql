drop table if exists bookamount;

alter table if exists author ADD COLUMN info varchar(2048);

alter table if exists book ADD COLUMN info varchar(2048);

alter table if exists book_amount
    ADD COLUMN book_amount_reserved  int4,
    ADD COLUMN book_amount_at_users  int4;



