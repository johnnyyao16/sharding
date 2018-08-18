-- auto-generated definition
create table t_fee_0
(
  ent_code            varchar(50) not null,
  fee_code            varchar(50) not null
    primary key,
  fee_amount          decimal     null,
  user_code           varchar(50) null,
  reimburse_data_code varchar(50) null
)
  charset = utf8;

create index idx_ent_code
  on t_fee_0 (ent_code);

create index idx_reimburse_data_code
  on t_fee_0 (reimburse_data_code);

-- auto-generated definition
create table t_fee_1
(
  ent_code            varchar(50) not null,
  fee_code            varchar(50) not null
    primary key,
  fee_amount          decimal     null,
  user_code           varchar(50) null,
  reimburse_data_code varchar(50) null
)
  charset = utf8;

create index idx_ent_code
  on t_fee_1 (ent_code);

create index idx_reimburse_data_code
  on t_fee_1 (reimburse_data_code);

-- auto-generated definition
create table t_fee_allocation_0
(
  ent_code            varchar(50) not null,
  fee_code            varchar(50) not null,
  fee_allocation_code varchar(50) not null
    primary key,
  amount              decimal     null
)
  charset = utf8;


-- auto-generated definition
create table t_fee_allocation_1
(
  ent_code            varchar(50) not null,
  fee_code            varchar(50) not null,
  fee_allocation_code varchar(50) not null
    primary key,
  amount              decimal     null
)
  charset = utf8;


-- auto-generated definition
create table t_reimburse
(
  ent_code            varchar(50) not null,
  reimburse_name      varchar(50) null,
  reimburse_data_code varchar(50) not null
    primary key
)
  charset = utf8;

create index idx_ent_code
  on t_reimburse (ent_code);

create index idx_reimburse_data_code
  on t_reimburse (reimburse_data_code);

