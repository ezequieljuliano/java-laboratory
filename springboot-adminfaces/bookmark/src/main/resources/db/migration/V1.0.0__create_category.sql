create table category (
  cat_id       bigint not null,
  cat_name     varchar(60) not null,
  cat_created  timestamp without time zone not null,
  cat_updated  timestamp without time zone,
  cat_version  bigint not null,
  constraint pk_category primary key (cat_id)
);