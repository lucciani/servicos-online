CREATE TABLE categoria (
	id bigint NOT NULL auto_increment, 
	descricao varchar(100) NOT NULL,
	ajuda MEDIUMTEXT NOT NULL,
	ativo tinyint(1) NOT NULL,
	departamento_id bigint NOT NULL,
	
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

alter table categoria add constraint fk_categoria_departamento foreign key (departamento_id) references departamento (id);
