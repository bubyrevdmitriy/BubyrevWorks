drop table book_amount;

create table book_amount (
    id int8 not null,
    book_id int8,
    book_amount_at_library  int4
  );

alter table if exists book_amount
    add constraint book_to_bookAmount_fk
        foreign key (book_id) references book;



