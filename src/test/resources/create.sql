
CREATE SEQUENCE hibernate_sequence START WITH 1000;

CREATE TABLE APP_PARAMETER (
    ID                  int not null  IDENTITY,
    DOMAIN				varchar(250) not null,
    `key`               varchar(1000) not null,
    `VALUE` 	        varchar(4000),
    
    primary key (ID)
);

