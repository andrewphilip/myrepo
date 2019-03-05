create table panel(
id number(5,0) not null AUTO_INCREMENT,
panel_code varchar2(10) not null,
created_date timestamp not null default CURRENT_TIMESTAMP(),
primary key (id)
);