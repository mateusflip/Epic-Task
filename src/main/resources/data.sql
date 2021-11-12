/*Arquivo obrigatoriamente deve estar no resource deve ser habilitado no aplication properties*/
CREATE TABLE task(
	id bigint primary key auto_increment,
	title varchar(220),
	description varchar(200),
	points int,
	status int DEFAULT 0
);

INSERT INTO task (title, description, points) VALUES(
	'Criar Banco de dados',
	'Criar Db Oracle na Nuvem',
	300
);

INSERT INTO task (title, description, points) VALUES(
	'Criar Container',
	'Criar Conainter para subida de aplicação',
	100
);

INSERT INTO task (title, description, points) VALUES(
	'Deploy API',
	'Subida de APIS',
	200
);