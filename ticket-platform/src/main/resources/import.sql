-- Inserimento ruoli
INSERT INTO `ticket-platform`.role (role)VALUES ('ADMIN'),('USER');

-- Inserimento utenti (incluso l'admin)
INSERT INTO `ticket-platform`.users (username, email, password, availability)VALUES ('admin', 'admin@example.com', '{noop}password', true),('user1', 'user1@example.com', '{noop}password', true),('user2', 'user2@example.com', '{noop}password', true);

-- Inserimento ticket
INSERT INTO `ticket-platform`.ticket (`title`, description, created_at, status, user_id)VALUES ('Problema di accesso', 'Non riesco ad accedere al sistema', '2024-09-29', false, 2),('Errore nel report', 'Il report mensile non si carica correttamente', '2024-09-29', true, 3);

-- Inserimento note/commenti associati ai ticket
INSERT INTO `ticket-platform`.note (created_at,ticket_id,note) VALUES('2024-09-29',1,'problema passato al livello 2'),('2024-09-29',2,'problema da verificare con il cliente');

-- Assegnazione ruoli agli utenti
INSERT INTO `ticket-platform`.users_roles (user_id, roles_id)VALUES (1, 1),(2, 2),(3, 2);