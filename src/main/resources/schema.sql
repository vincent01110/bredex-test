CREATE TABLE client (
    id INT AUTO_INCREMENT,
    email VARCHAR(45) NOT NULL,
    name VARCHAR(100) NOT NULL,
    apiKey VARCHAR(36) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE position (
    id INT AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL,
    location VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);
