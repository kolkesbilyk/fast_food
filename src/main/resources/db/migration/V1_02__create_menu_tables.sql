DROP TABLE IF EXISTS back.dishes;
DROP TABLE IF EXISTS back.dir_menu;


CREATE TABLE IF NOT EXISTS back.dir_menu(
    id SERIAL PRIMARY KEY ,
    name varchar(64) NOT NULL CONSTRAINT "name_UK"
        UNIQUE,
    image varchar(255),
    created timestamp DEFAULT now(),
    deactivated timestamp,
    description varchar(255)
);

INSERT INTO back.dir_menu(name)
VALUES ('Drinks'),
       ('Desserts'),
       ('Alcohols'),
       ('Burgers'),
       ('Menu');

CREATE TABLE IF NOT EXISTS back.dishes(
    id SERIAL PRIMARY KEY ,
    id_menu INTEGER REFERENCES back.dir_menu(id),
    name varchar(64),
    created timestamp DEFAULT now(),
    deactivated timestamp,
    image varchar(255),
    prize numeric(10, 2),
    description varchar(255)
);

INSERT INTO back.dishes(id_menu, name, image, prize, description)
VALUES (1, 'Coffee', '', 30.00, 'Coffee'),
       (1, 'Tea', '', 25.00, 'Tea'),
       (1, 'Coca-cola', '', 35.00, 'Cola'),
       (2, 'Cake', '', 70.00, 'Cake'),
       (2, 'Cheese-Cake', '', 100.00, NULL),
       (3, 'Baltica', '', 45.00, 'Beer'),
       (3, 'Obolon', '', 40.00, 'Beer'),
       (4, 'Big-Mac', '', 105.00, 'burger'),
       (4, 'CheeseBurger', '', 90.00, 'burger'),
       (4, 'GymBurger', '', 95.00, 'burger'),
       (5, 'Big Mac Menu', '', 205.00, 'menu');
