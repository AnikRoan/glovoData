CREATE TABLE IF NOT EXISTS orders(
    id SERIAL PRIMARY KEY,
    date DATE,
    cost NUMERIC
);

CREATE TABLE IF NOT EXISTS product(

    id SERIAL PRIMARY KEY,
    orders INTEGER references orders(id),
    orders_key integer,
    name TEXT,
    cost NUMERIC
);