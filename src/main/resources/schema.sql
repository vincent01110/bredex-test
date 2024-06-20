create table client(
    id int auto_increment,
    email varchar(45) NOT NULL,
    name varchar(100) NOT NULL,
    api_key varchar(36) NOT NULL
);

create table position(
    title varchar(50) NOT NULL,
    location varchar(50) NOT NULL
);