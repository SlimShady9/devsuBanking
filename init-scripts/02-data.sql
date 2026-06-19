-- Datos para la tabla person
COPY person (id, age, gender, name) FROM stdin;
7428c3b9-2926-49a0-9bf2-44d46a169d9e	40	MALE	Peter Doe
\.

-- Datos para la tabla customer
COPY customer (password, state, id) FROM stdin;
$2a$12$hszOQgiQG.bTw/PWUciQBuSgYtlzHU83vKe3QBuSa9to/0LUPmkcC	t	7428c3b9-2926-49a0-9bf2-44d46a169d9e
\.

-- Datos para la tabla account
COPY account (id, account_number, account_type, balance, creation_date, customer_id, state) FROM stdin;
1fe8ed5c-d4f5-4538-b387-401d8b86fb1d	7753557066	SAVINGS	1000	2026-06-19 15:39:36.576587+00	7428c3b9-2926-49a0-9bf2-44d46a169d9e	t
\.

-- Datos para la tabla movement
COPY movement (id, account_id, amount, creation_date) FROM stdin;
6f56ad15-1714-44a4-9697-03d4e3fb6d35	1fe8ed5c-d4f5-4538-b387-401d8b86fb1d	100	2026-06-19 14:27:10.40972+00
9ea359d8-a1d3-42f6-8760-400e267d6570	1fe8ed5c-d4f5-4538-b387-401d8b86fb1d	-200	2026-06-19 14:49:33.8225+00
\.
