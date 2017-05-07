-----------------------------------------------------------------------------------
-----------------------------------------------------------------------------------
-----------------------------------------------------------------------------------
-- Sample part
-----------------------------------------------------------------------------------
-----------------------------------------------------------------------------------
-----------------------------------------------------------------------------------

INSERT INTO "book" ("title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('Les misérables', 'histoire de cozette', null, 3, 12.25, /*null,*/ null);
INSERT INTO "book" ("title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('A la recherche de temps perdu', '...', null, 4, 12.25, /*null,*/ null);
INSERT INTO "book" ("title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('Sodome et gomorrhe', '...', null, 4, 12.25, /*null,*/ null);
INSERT INTO "book" ("title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('Du côté de chez Swann', '...', null, 4, 12.25, /*null,*/ null);
INSERT INTO "book" ("title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('Germinal', '...', null, 5, 12.25, /*null,*/ null);
INSERT INTO "book" ("title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('Au bonheur des dames', '...', null, 5, 12.25, /*null,*/ null);
INSERT INTO "book" ("title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('Les fleurs du mal', '...', null, 6, 12.25, /*null,*/ null);
INSERT INTO "book" ("title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('Le spleen de Paris', '...', null, 6, 12.25, /*null,*/ null);
INSERT INTO "book" ("title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('La peste', '...', null, 2, 12.25, /*null,*/ null);
INSERT INTO "book" ("title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('L''étranger', '...', null, 2, 12.25, /*null,*/ null);
INSERT INTO "book" ("title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('La nausée', '...', null, 7, 12.25, /*null,*/ null);
INSERT INTO "book" ("title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('Madame Bovary', '...', null, 8, 12.25, /*null,*/ null);
INSERT INTO "book" ("title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('L''éducation sentimentale', '...', null, 8, 12.25, /*null,*/ null);
INSERT INTO "book" ("title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('Les confessions', '...', null, 9, 12.25, /*null,*/ null);
INSERT INTO "book" ("title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('Phèdre', '...', null, 10, 12.25, /*null,*/ null);
INSERT INTO "book" ("title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('Andromaque', '...', null, 10, 12.25, /*null,*/ null);
INSERT INTO "book" ("title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('Lettres de mon moulin', '...', null, 11, 12.25, /*null,*/ null);
INSERT INTO "book" ("title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('Tartarin de Tarascon', '...', null, 11, 12.25, /*null,*/ null);
INSERT INTO "book" ("title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('La religieuse', '...', null, 12, 12.25, /*null,*/ null);
INSERT INTO "book" ("title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('Le paysan de Paris', '...', null, 13, 12.25, /*null,*/ null);
INSERT INTO "book" ("title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('Harry Potter à l''école des sorciers', '...', null, 14, 25.01, /*null,*/ null);
INSERT INTO "book" ("title", "description", "publication_date", "author_id", "price", "barcodeid") VALUES ('Harry Potter et la Chambre des secrets', '...', null, 14, 21, /*'serial_21',*/ null);



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
