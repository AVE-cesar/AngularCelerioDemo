-----------------------------------------------------------------------------------
-----------------------------------------------------------------------------------
-----------------------------------------------------------------------------------
-- Sample part
-----------------------------------------------------------------------------------
-----------------------------------------------------------------------------------
-----------------------------------------------------------------------------------

INSERT INTO "book" ("id", "title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('serial_1', 'Les misérables', 'histoire de cozette', null, 3, 12.25, /*null,*/ null);
INSERT INTO "book" ("id", "title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('serial_2', 'A la recherche de temps perdu', '...', null, 4, 12.25, /*null,*/ null);
INSERT INTO "book" ("id", "title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('serial_3', 'Sodome et gomorrhe', '...', null, 4, 12.25, /*null,*/ null);
INSERT INTO "book" ("id", "title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('serial_4', 'Du côté de chez Swann', '...', null, 4, 12.25, /*null,*/ null);
INSERT INTO "book" ("id", "title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('serial_5', 'Germinal', '...', null, 5, 12.25, /*null,*/ null);
INSERT INTO "book" ("id", "title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('serial_6', 'Au bonheur des dames', '...', null, 5, 12.25, /*null,*/ null);
INSERT INTO "book" ("id", "title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('serial_7', 'Les fleurs du mal', '...', null, 6, 12.25, /*null,*/ null);
INSERT INTO "book" ("id", "title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('serial_8', 'Le spleen de Paris', '...', null, 6, 12.25, /*null,*/ null);
INSERT INTO "book" ("id", "title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('serial_9', 'La peste', '...', null, 2, 12.25, /*null,*/ null);
INSERT INTO "book" ("id", "title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('serial_10', 'L''étranger', '...', null, 2, 12.25, /*null,*/ null);
INSERT INTO "book" ("id", "title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('serial_11', 'La nausée', '...', null, 7, 12.25, /*null,*/ null);
INSERT INTO "book" ("id", "title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('serial_12', 'Madame Bovary', '...', null, 8, 12.25, /*null,*/ null);
INSERT INTO "book" ("id", "title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('serial_13', 'L''éducation sentimentale', '...', null, 8, 12.25, /*null,*/ null);
INSERT INTO "book" ("id", "title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('serial_14', 'Les confessions', '...', null, 9, 12.25, /*null,*/ null);
INSERT INTO "book" ("id", "title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('serial_15', 'Phèdre', '...', null, 10, 12.25, /*null,*/ null);
INSERT INTO "book" ("id", "title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('serial_16', 'Andromaque', '...', null, 10, 12.25, /*null,*/ null);
INSERT INTO "book" ("id", "title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('serial_17', 'Lettres de mon moulin', '...', null, 11, 12.25, /*null,*/ null);
INSERT INTO "book" ("id", "title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('serial_18', 'Tartarin de Tarascon', '...', null, 11, 12.25, /*null,*/ null);
INSERT INTO "book" ("id", "title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('serial_19', 'La religieuse', '...', null, 12, 12.25, /*null,*/ null);
INSERT INTO "book" ("id", "title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('serial_20', 'Le paysan de Paris', '...', null, 13, 12.25, /*null,*/ null);
INSERT INTO "book" ("id", "title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('serial_21', 'Harry Potter à l''école des sorciers', '...', null, 14, 25.01, /*null,*/ null);
INSERT INTO "book" ("id", "title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('serial_22', 'Harry Potter et la Chambre des secrets', '...', null, 14, 21, /*'serial_21',*/ null);



-----------------------------------------------------------------------------------
-----------------------------------------------------------------------------------
-----------------------------------------------------------------------------------
-- Admin part
-----------------------------------------------------------------------------------
-----------------------------------------------------------------------------------
-----------------------------------------------------------------------------------



-- add admin user
INSERT INTO "app_user" ("id", "login", "password", "email", "language", "first_name", "last_name", "enabled")  VALUES (-1, 'admin', 'admin', 'admin@admin.com', 'FR', 'admin', 'admin', 1);

-- add admin role (lower case)
INSERT INTO "app_authority" ("id", "name") VALUES (-1, 'admin');

-- link admin role to admin user
INSERT INTO "app_user_authority" ("app_user_id", "app_authority_id") VALUES (-1, -1);
