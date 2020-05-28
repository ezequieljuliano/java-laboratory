create table usuario (
	usu_id uuid not null,
	usu_username varchar(255) not null,
	usu_password varchar(255) not null, 
	usu_nome varchar(255) not null,
	usu_email varchar(255) not null, 	
	usu_ativo boolean not null,
	usu_administrador boolean not null,
	constraint pk_usuario primary key (usu_id),
	constraint uk_usu_username unique (usu_username),
	constraint uk_usu_email unique (usu_email)
);