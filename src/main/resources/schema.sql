create table client(
    id int auto_increment,
    email varchar(45) NOT NULL,
    name varchar(100) NOT NULL,
    apiKey varchar(36) NOT NULL,
    PRIMARY KEY (id)
);

create table position(
    id int auto_increment,
    title varchar(50) NOT NULL,
    location varchar(50) NOT NULL,
    PRIMARY KEY (id)
);