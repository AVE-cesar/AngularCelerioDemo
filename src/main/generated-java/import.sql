-- add admin user
INSERT INTO "app_user" ("id", "login", "password", "email", "language", "first_name", "last_name", "enabled")  VALUES (-1, 'admin', 'admin', 'admin@admin.com', 'FR', 'admin', 'admin', 1);

-- add admin role (lower case)
INSERT INTO "app_authority" ("id", "name") VALUES (-1, 'admin');

-- link admin role to admin user
INSERT INTO "app_user_authority" ("app_user_id", "app_authority_id") VALUES (-1, -1);
