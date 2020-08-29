CREATE TABLE person (
    id VARCHAR(11) PRIMARY KEY,
    name TEXT,
    email TEXT,
    password TEXT,
    birth_date TIMESTAMP
);

CREATE TABLE account (
    id VARCHAR(26) PRIMARY KEY,
    balance NUMERIC,
    person_id VARCHAR(11) REFERENCES person(id) NOT NULL
);

CREATE TABLE transaction (
    id VARCHAR(26) PRIMARY KEY,
    value NUMERIC NOT NULL,
    time TIMESTAMP NOT NULL,
    type TEXT,
    account_id VARCHAR(26) REFERENCES account(id) NOT NULL
);

CREATE TABLE goal (
    id VARCHAR(26) PRIMARY KEY,
    title TEXT NOT NULL,
    total_goal NUMERIC NOT NULL,
    person_id VARCHAR(11) REFERENCES person(id)
);

CREATE TABLE payment (
    id VARCHAR(26) PRIMARY KEY,
    value NUMERIC NOT NULL,
    parcel INTEGER,
    date TIMESTAMP,
    liquidation_date TIMESTAMP NOT NULL,
    cashback NUMERIC NOT NULL,
    micro_invest NUMERIC NOT NULL,
    store_id VARCHAR(14) NOT NULL,
    person_id VARCHAR(11) REFERENCES person(id) NOT NULL
);