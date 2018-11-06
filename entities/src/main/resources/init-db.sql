INSERT INTO users(id, name, surname, username, password, email) VALUES (1, 'John', 'Doe', 'john', 'john', 'john@doe');
INSERT INTO users(id, name, surname, username, password, email) VALUES (2, 'Samson', 'Peterson', 'samson', 'samson', 'samson@peterson');
INSERT INTO users(id, name, surname, username, password, email) VALUES (3, 'Kelsey', 'Brown', 'kelsey', 'kelsey', 'kelsey@brown');
INSERT INTO users(id, name, surname, username, password, email) VALUES (4, 'Myranda', 'Rome', 'myranda', 'myranda', 'myranda@rome');

ALTER SEQUENCE users_id_seq RESTART WITH 5;