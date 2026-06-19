-- 1. CREACIÓN DE TABLAS
CREATE TABLE person (
    id uuid NOT NULL,
    age integer,
    gender character varying(255),
    name character varying(255)
);

CREATE TABLE customer (
    password character varying(255),
    state boolean,
    id uuid NOT NULL
);

CREATE TABLE account (
    id uuid NOT NULL,
    account_number character varying(255),
    account_type character varying(255),
    balance double precision,
    creation_date timestamp(6) with time zone,
    customer_id uuid,
    state boolean
);

CREATE TABLE movement (
    id uuid NOT NULL,
    account_id uuid,
    amount double precision,
    creation_date timestamp(6) with time zone
);

-- 2. CONFIGURACIÓN DE LLAVES PRIMARIAS
ALTER TABLE ONLY person ADD CONSTRAINT person_pkey PRIMARY KEY (id);
ALTER TABLE ONLY customer ADD CONSTRAINT customer_pkey PRIMARY KEY (id);
ALTER TABLE ONLY account ADD CONSTRAINT account_pkey PRIMARY KEY (id);
ALTER TABLE ONLY movement ADD CONSTRAINT movement_pkey PRIMARY KEY (id);

-- 3. CONFIGURACIÓN DE LLAVES FORÁNEAS
ALTER TABLE ONLY customer 
    ADD CONSTRAINT fkr9okrktxscw3omxi1wm7cg18u FOREIGN KEY (id) REFERENCES person(id);
