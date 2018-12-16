# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

-- init script create procs
-- Inital script to create stored procedures etc for mysql platform
DROP PROCEDURE IF EXISTS usp_ebean_drop_foreign_keys;

delimiter $$
--
-- PROCEDURE: usp_ebean_drop_foreign_keys TABLE, COLUMN
-- deletes all constraints and foreign keys referring to TABLE.COLUMN
--
CREATE PROCEDURE usp_ebean_drop_foreign_keys(IN p_table_name VARCHAR(255), IN p_column_name VARCHAR(255))
BEGIN
  DECLARE done INT DEFAULT FALSE;
  DECLARE c_fk_name CHAR(255);
  DECLARE curs CURSOR FOR SELECT CONSTRAINT_NAME from information_schema.KEY_COLUMN_USAGE
    WHERE TABLE_SCHEMA = DATABASE() and TABLE_NAME = p_table_name and COLUMN_NAME = p_column_name
      AND REFERENCED_TABLE_NAME IS NOT NULL;
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

  OPEN curs;

  read_loop: LOOP
    FETCH curs INTO c_fk_name;
    IF done THEN
      LEAVE read_loop;
    END IF;
    SET @sql = CONCAT('ALTER TABLE ', p_table_name, ' DROP FOREIGN KEY ', c_fk_name);
    PREPARE stmt FROM @sql;
    EXECUTE stmt;
  END LOOP;

  CLOSE curs;
END
$$

DROP PROCEDURE IF EXISTS usp_ebean_drop_column;

delimiter $$
--
-- PROCEDURE: usp_ebean_drop_column TABLE, COLUMN
-- deletes the column and ensures that all indices and constraints are dropped first
--
CREATE PROCEDURE usp_ebean_drop_column(IN p_table_name VARCHAR(255), IN p_column_name VARCHAR(255))
BEGIN
  CALL usp_ebean_drop_foreign_keys(p_table_name, p_column_name);
  SET @sql = CONCAT('ALTER TABLE ', p_table_name, ' DROP COLUMN ', p_column_name);
  PREPARE stmt FROM @sql;
  EXECUTE stmt;
END
$$
create table accounts (
  id                            integer auto_increment not null,
  is_active                     tinyint(1) default 0 not null,
  name                          varchar(128) not null,
  description                   varchar(500),
  users_limit                   integer not null,
  created_date                  datetime(6) not null,
  updated_date                  datetime(6) not null,
  version                       bigint not null,
  constraint pk_accounts primary key (id)
);

create table account_project (
  id                            integer auto_increment not null,
  is_active                     tinyint(1) default 0 not null,
  account_id                    integer not null,
  project_id                    integer,
  created_date                  datetime(6) not null,
  updated_date                  datetime(6) not null,
  version                       bigint not null,
  constraint uq_account_project_account_id_project_id unique (account_id,project_id),
  constraint pk_account_project primary key (id)
);

create table products (
  id                            integer auto_increment not null,
  is_active                     tinyint(1) default 0 not null,
  name                          varchar(256) not null,
  description                   varchar(1000),
  created_date                  datetime(6) not null,
  updated_date                  datetime(6) not null,
  version                       bigint not null,
  constraint uq_products_name unique (name),
  constraint pk_products primary key (id)
);

create table projects (
  id                            integer auto_increment not null,
  is_active                     tinyint(1) default 0 not null,
  product_id                    integer not null,
  name                          varchar(256) not null,
  created_date                  datetime(6) not null,
  updated_date                  datetime(6) not null,
  version                       bigint not null,
  constraint uq_projects_product_id_name unique (product_id,name),
  constraint pk_projects primary key (id)
);

create table users (
  type                          varchar(10) not null,
  id                            integer auto_increment not null,
  is_active                     tinyint(1) default 0 not null,
  account_id                    integer not null,
  first_name                    varchar(35) not null,
  last_name                     varchar(35) not null,
  job_title                     varchar(256),
  mobile                        varchar(16),
  email                         varchar(256) not null,
  is_admin                      tinyint(1) default 0 not null,
  password                      varchar(64) not null,
  password_updated              tinyint(1) default 0 not null,
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

create index ix_account_project_account_id on account_project (account_id);
alter table account_project add constraint fk_account_project_account_id foreign key (account_id) references accounts (id) on delete restrict on update restrict;

create index ix_account_project_project_id on account_project (project_id);
alter table account_project add constraint fk_account_project_project_id foreign key (project_id) references projects (id) on delete restrict on update restrict;

create index ix_projects_product_id on projects (product_id);
alter table projects add constraint fk_projects_product_id foreign key (product_id) references products (id) on delete restrict on update restrict;

create index ix_users_account_id on users (account_id);
alter table users add constraint fk_users_account_id foreign key (account_id) references accounts (id) on delete restrict on update restrict;


# --- !Downs

alter table account_project drop foreign key fk_account_project_account_id;
drop index ix_account_project_account_id on account_project;

alter table account_project drop foreign key fk_account_project_project_id;
drop index ix_account_project_project_id on account_project;

alter table projects drop foreign key fk_projects_product_id;
drop index ix_projects_product_id on projects;

alter table users drop foreign key fk_users_account_id;
drop index ix_users_account_id on users;

drop table if exists accounts;

drop table if exists account_project;

drop table if exists products;

drop table if exists projects;

drop table if exists users;

