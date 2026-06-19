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
1fe8ed5c-d4f5-4538-b387-401d8b86fb1d	7753557066	SAVINGS	0	2026-06-19 15:39:36.576587+00	7428c3b9-2926-49a0-9bf2-44d46a169d9e	t
2a2a6119-610a-4cf7-8600-fed2eac9f799	3189105790	SAVINGS	403	2026-06-19 14:27:10.311305+00	7428c3b9-2926-49a0-9bf2-44d46a169d9e	f
da161564-e98b-42d2-9665-381586ce9ebb	6407194854	SAVINGS	1	2026-06-19 15:53:05.519732+00	\N	f
\.

-- Datos para la tabla movement
COPY movement (id, account_id, amount, creation_date) FROM stdin;
6f56ad15-1714-44a4-9697-03d4e3fb6d35	2a2a6119-610a-4cf7-8600-fed2eac9f799	100	2026-06-19 14:27:10.40972+00
9ea359d8-a1d3-42f6-8760-400e267d6570	2a2a6119-610a-4cf7-8600-fed2eac9f799	101	2026-06-19 14:49:33.8225+00
279e88ee-5cf1-4448-9966-1a90e754703b	da161564-e98b-42d2-9665-381586ce9ebb	100	2026-06-19 15:53:05.589245+00
7c491a71-7cd3-4da7-b92b-a0d67d4d66e8	da161564-e98b-42d2-9665-381586ce9ebb	-99	2026-06-19 15:54:15.345287+00
\.
