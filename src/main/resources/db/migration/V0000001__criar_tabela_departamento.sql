CREATE TABLE departamento (
	id bigint NOT NULL auto_increment, 
	descricao varchar(100) NOT NULL,
	ativo tinyint(1) NOT NULL,  
	
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;