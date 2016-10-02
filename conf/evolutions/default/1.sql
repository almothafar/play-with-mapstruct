# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table accounts (
  id                            integer auto_increment not null,
  is_active                     tinyint(1) default 0,
  name                          varchar(128) not null,
  notes                         varchar(500),
  users_limit                   integer,
  users_count                   integer,
  created_date                  datetime(6) not null,
  updated_date                  datetime(6) not null,
  version                       bigint not null,
  constraint pk_accounts primary key (id)
);

create table auth_tokens (
  id                            integer auto_increment not null,
  is_active                     tinyint(1) default 0,
  user_id                       integer,
  token                         varchar(255) not null,
  expire_after                  bigint,
  created_date                  datetime(6) not null,
  updated_date                  datetime(6) not null,
  version                       bigint not null,
  constraint uq_auth_tokens_user_id unique (user_id),
  constraint pk_auth_tokens primary key (id)
);

create table users (
  type                          varchar(10) not null,
  id                            integer auto_increment not null,
  is_active                     tinyint(1) default 0,
  account_id                    integer,
  first_name                    varchar(35) not null,
  last_name                     varchar(35) not null,
  job_title                     varchar(256),
  mobile                        varchar(16),
  email                         varchar(256) not null,
  is_admin                      tinyint(1) default 0,
  password                      varchar(64) not null,
  password_updated              tinyint(1) default 0,
  created_date                  datetime(6) not null,
  updated_date                  datetime(6) not null,
  version                       bigint not null,
  twitter_screen_name           varchar(15),
  facebook_user_id              varchar(128),
  constraint uq_users_email unique (email),
  constraint uq_users_twitter_screen_name unique (twitter_screen_name),
  constraint uq_users_facebook_user_id unique (facebook_user_id),
  constraint pk_users primary key (id)
);

alter table auth_tokens add constraint fk_auth_tokens_user_id foreign key (user_id) references users (id) on delete restrict on update restrict;

alter table users add constraint fk_users_account_id foreign key (account_id) references accounts (id) on delete restrict on update restrict;
create index ix_users_account_id on users (account_id);


# --- !Downs

alter table auth_tokens drop foreign key fk_auth_tokens_user_id;

alter table users drop foreign key fk_users_account_id;
drop index ix_users_account_id on users;

drop table if exists accounts;

drop table if exists auth_tokens;

drop table if exists users;

