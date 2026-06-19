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