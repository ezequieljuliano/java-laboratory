create table bookmark (
	boo_id uuid not null,
	boo_descricao varchar(255) not null,
	boo_link varchar(255) not null, 
	boo_cat_id uuid not null,
	constraint pk_bookmark primary key (boo_id),
	constraint fk_boo_cat_id foreign key (boo_cat_id) references categoria(cat_id) on update cascade
);