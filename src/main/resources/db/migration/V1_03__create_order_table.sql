DROP TABLE IF EXISTS back.orders;
DROP TABLE IF EXISTS back.order_dishes;

CREATE TABLE IF NOT EXISTS back.orders(
    id          SERIAL PRIMARY KEY ,
    created     timestamp DEFAULT now(),
    changed     timestamp,
    status      varchar(16) DEFAULT 'REGISTERED' NOT NULL,
    user_id        INTEGER DEFAULT 0,
    ready_time  timestamp,
    in_work     BOOLEAN DEFAULT FALSE,
    worker      INTEGER,
    total_sum   NUMERIC(10 , 2),
    dop_info    text
);

CREATE TABLE IF NOT EXISTS back.order_dishes(
    id          SERIAL PRIMARY KEY ,
    order_id    INTEGER REFERENCES back.orders(id),
    dish_id     INTEGER REFERENCES back.dishes(id)
);