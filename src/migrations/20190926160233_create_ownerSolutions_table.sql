create table if not exists ownerSolutions (
	id integer auto_increment primary key,
	id_challenge integer,
	user_id integer,
	source varchar (10000),
	created_at DATETIME,
	updated_at DATETIME
);
	