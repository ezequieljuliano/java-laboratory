create table papel (
	pap_id uuid not null,
	pap_nome varchar(255) not null,
	constraint pk_papel primary key (pap_id),
	constraint uk_pap_nome unique (pap_nome)
);