-- add admin user
INSERT INTO "app_user" ("id", "login", "password", "email", "language", "firstName", "lastName", "enabled")  VALUES (-1, 'admin', 'admin', 'admin@admin.com', 'FR', 'admin', 'admin', 1);

-- add admin role (lower case)
INSERT INTO "app_authority"  VALUES (-1, 'admin');

-- link admin role to admin user
INSERT INTO "app_user_authority"  VALUES (-1, -1, -1);
