INSERT INTO person (id, name, gender, age) VALUES (RANDOM_UUID(), 'Alice Green', 'MALE', 25);
INSERT INTO customer (id, state, password) VALUES ((SELECT id FROM person WHERE name = 'Alice Green'), true, '123456');

-- REGISTRO 2 --
INSERT INTO person (id, name, gender, age) VALUES (RANDOM_UUID(), 'Bob Blue', 'FEMALE', 30);
INSERT INTO customer (id, state, password) VALUES ((SELECT id FROM person WHERE name = 'Bob Blue'), true, '123456');