-- Inserimento ruoli
INSERT INTO `ticket-platform`.role (role)VALUES ('ADMIN'),('USER');

-- Inserimento utenti (incluso l'admin)
INSERT INTO `ticket-platform`.users (username, email, password, availability)VALUES ('admin', 'admin@example.com', '{noop}password', 1),('user1', 'user1@example.com', '{noop}password', 1),('user2', 'user2@example.com', '{noop}password', 1);

-- Inserimento ticket
INSERT INTO `ticket-platform`.ticket (title, description, created_at, category, status, user_id)VALUES ('Problema di accesso', 'Non riesco ad accedere al sistema', '2024-09-29','Livello 1', 'completed', 2),('Errore nel report', 'Il report mensile non si carica correttamente', '2024-09-29','Livello 2', 'toDo', 3),('Problema di esecuzione', 'Non riesco ad eseguire il programma', '2024-09-29','Livello 1', 'current', 2),('Errore di stampa', 'La stampa del report mensile non viene eseguita correttamente', '2024-09-29','Livello 2', 'toDo', 3);

-- Inserimento note/commenti associati ai ticket
INSERT INTO `ticket-platform`.note (created_at,ticket_id,note,author) VALUES('2024-09-29',1,'problema passato al livello 2','user1'),('2024-09-29',2,'problema da verificare con il cliente','user2'),('2024-09-29',1,'problema urgente','user1'),('2024-09-29',2,'da rivedere con un supervisore','user2'),('2024-09-29',3,'passato al livello 3','user1'),('2024-09-29',4,'da verificare con il cliente','user2'),('2024-09-29',3,'da rivedere con programmatore','user1'),('2024-09-29',4,'prenotare ispezione in loco periferica','user2');

-- Assegnazione ruoli agli utenti
INSERT INTO `ticket-platform`.users_roles (user_id, roles_id)VALUES (1, 1),(2, 2),(3, 2);