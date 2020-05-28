create table permissao (
	per_id uuid not null,
	per_recurso varchar(255) not null,
	per_acao varchar(255) not null,
	per_descricao varchar(255) not null,
	constraint pk_permissao primary key (per_id),
	constraint uk_per_recurso_acao unique (per_recurso, per_acao)
);