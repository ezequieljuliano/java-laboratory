create table papel_permissao (
	ppe_pap_id uuid not null,
	ppe_per_id uuid not null,
	constraint pk_papel_permissao primary key (ppe_pap_id, ppe_per_id),
	constraint fk_ppe_pap_id foreign key (ppe_pap_id) references papel(pap_id) on update cascade on delete cascade,
	constraint fk_ppe_per_id foreign key (ppe_per_id) references permissao(per_id) on update cascade
);
