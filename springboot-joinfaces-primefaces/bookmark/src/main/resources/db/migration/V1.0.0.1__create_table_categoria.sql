create table categoria (
	cat_id uuid not null,
	cat_nome varchar(255) not null,
	constraint pk_categoria primary key (cat_id)
);