CREATE TABLE ACTOR (
    id                  int not null,
    lastname            varchar2(100) not null,
    firstname            varchar2(100) not null,
	
    primary key (id)
);

CREATE OR REPLACE TRIGGER ACTOR_identity 
BEFORE INSERT ON ACTOR
FOR EACH ROW
WHEN (new.id IS NULL)
BEGIN
  :new.id := hibernate_sequence.NEXTVAL;
END;

-- calcul des stats
exec dbms_stats.gather_schema_stats(ownname => 'demo1', estimate_percent => DBMS_STATS.AUTO_SAMPLE_SIZE, granularity => 'ALL', method_opt => 'FOR ALL INDEXED COLUMNS SIZE AUTO', degree =>1, options => 'GATHER AUTO', cascade => TRUE );