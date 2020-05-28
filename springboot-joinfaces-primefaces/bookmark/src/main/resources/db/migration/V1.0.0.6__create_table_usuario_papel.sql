create table usuario_papel (
	usp_usu_id uuid not null,
	usp_pap_id uuid not null,
	constraint pk_usuario_papel primary key (usp_usu_id, usp_pap_id),
	constraint fk_usp_usu_id foreign key (usp_usu_id) references usuario(usu_id) on update cascade on delete cascade,
	constraint fk_usp_pap_id foreign key (usp_pap_id) references papel(pap_id) on update cascade
);
