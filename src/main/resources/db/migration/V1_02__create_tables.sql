CREATE TABLE IF NOT EXISTS back.dir_roles(
    id SERIAL PRIMARY KEY,
    name varchar(64) NOT NULL ,
    created timestamp DEFAULT now(),
    deactivated timestamp,
    description varchar(255)
);

INSERT INTO back.dir_roles(name, description)
VALUES ('ADMIN', 'адмін'),
       ('KASA', 'касир'),
       ('CHEF_KITCHEN', 'шеф повар'),
       ('KITCHEN', 'повар');


CREATE TABLE IF NOT EXISTS back.employer(
    id SERIAL PRIMARY KEY ,
    first_name varchar(64) NOT NULL ,
    last_name varchar(64) NOT NULL ,
    login varchar(64) NOT NULL,
    password varchar(255) NOT NULL ,
    email varchar(64),
    created timestamp DEFAULT now(),
    deactivated timestamp,
    author INTEGER,
    parent_id INTEGER
);

INSERT INTO back.employer(first_name, last_name, login, password)
VALUES ('SYSTEM', 'SYSTEM', 'system', 'system');