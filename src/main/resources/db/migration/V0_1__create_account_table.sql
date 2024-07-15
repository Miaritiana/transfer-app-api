create extension if not exists "uuid-ossp";

CREATE TABLE IF NOT EXISTS "account" (
    id  VARCHAR     CONSTRAINT user_pk  PRIMARY KEY     DEFAULT uuid_generate_v4(),
    username    VARCHAR     NOT NULL,
    first_name    VARCHAR     NOT NULL,
    last_name    VARCHAR     NOT NULL,
    telephone    VARCHAR     NOT NULL    CONSTRAINT user_telephone_unique   UNIQUE,
    balance     INTEGER     NOT NULL,
    currency    VARCHAR     NOT NULL,
    password    VARCHAR     NOT NULL
);