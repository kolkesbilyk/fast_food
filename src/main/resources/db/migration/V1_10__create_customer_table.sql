DROP TABLE IF EXISTS back.customer;

CREATE TABLE IF NOT EXISTS back.customer(
    id              SERIAL PRIMARY KEY,
    name            varchar(64) NOT NULL,
    login           varchar(64) NOT NULL,
    password        varchar(255) NOT NULL,
    created         timestamp DEFAULT now(),
    deactivated     timestamp
);

INSERT INTO back.customer(name, login, password)
VALUES ('demo', 'demo', 'demo');