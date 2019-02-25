-- Create table
CREATE table APP_USER
(
  USER_ID           BIGINT not null,
  USER_NAME         VARCHAR(36) not null,
  USER_EMAIL         VARCHAR(36) not null,
  ENCRYTED_PASSWORD VARCHAR(128) not null,
  ENABLED           Int not null 
) ;
--  
alter table APP_USER
  add constraint APP_USER_PK primary key (USER_ID);
 
alter table APP_USER
  add constraint APP_USER_UK unique (USER_NAME);
 
 alter table APP_USER
  add constraint APP_USER_UK unique (USER_EMAIL);
  
-- Create table
CREATE table APP_ROLE
(
  ROLE_ID   BIGINT not null,
  ROLE_NAME VARCHAR(30) not null
) ;
--  
alter table APP_ROLE
  add constraint APP_ROLE_PK primary key (ROLE_ID);
 
alter table APP_ROLE
  add constraint APP_ROLE_UK unique (ROLE_NAME);
 
 
-- Create table
CREATE table USER_ROLE
(
  USER_ID BIGINT not null,
  ROLE_ID BIGINT not null
);
--  
alter table USER_ROLE
  add constraint USER_ROLE_UK unique (USER_ID, ROLE_ID);
 
alter table USER_ROLE
  add constraint USER_ROLE_FK1 foreign key (USER_ID)
  references APP_USER (USER_ID);
 
alter table USER_ROLE
  add constraint USER_ROLE_FK2 foreign key (ROLE_ID)
  references APP_ROLE (ROLE_ID);
 
-- Create table

CREATE table APP_ORDER
(
	OWNER_ID BIGINT not null,
	ORDER_ID BIGINT not null,
	WEIGHT INTEGER,
	DESTINATION_POINT VARCHAR (36)
);
-- 
alter table APP_ORDER
  add constraint APP_ORDER_PK primary key (ORDER_ID);
 
alter table APP_ORDER
  add constraint APP_ORDER_FK1 foreign key (OWNER_ID)
  references APP_USER (USER_ID); 

  
  
/*-- Used by Spring Remember Me API.  
CREATE TABLE Persistent_Logins (
 
    username varchar(64) not null,
    series varchar(64) not null,
    token varchar(64) not null,
    last_used timestamp not null,
    PRIMARY KEY (series)
     
);*/
  
--------------------------------------
 
INSERT into APP_USER (USER_ID, USER_NAME, USER_EMAIL, ENCRYTED_PASSWORD, ENABLED)
values (2, 'dbuser1', 'test@mail.ru','$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
 
INSERT into APP_USER (USER_ID, USER_NAME, USER_EMAIL, ENCRYTED_PASSWORD, ENABLED)
values (1, 'dbadmin1', 'test2@mail.ru','$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
 
---
 
INSERT into APP_ROLE (ROLE_ID, ROLE_NAME)
values (1, 'ROLE_ADMIN');
 
INSERT into APP_ROLE (ROLE_ID, ROLE_NAME)
values (2, 'ROLE_USER');
 
---
 
INSERT into USER_ROLE (USER_ID, ROLE_ID)
values (1, 1);
 
INSERT into USER_ROLE (USER_ID, ROLE_ID)
values (1, 2);
 
INSERT into USER_ROLE (USER_ID, ROLE_ID)
values (2, 2);
---

INSERT into APP_ORDER (OWNER_ID, ORDER_ID, WEIGHT, DESTINATION_POINT)
values (1, 1, 9, 'Самара');
 
INSERT into APP_ORDER (OWNER_ID, ORDER_ID, WEIGHT, DESTINATION_POINT)
values (1, 2, 11, 'Торонто');
 
INSERT into APP_ORDER (OWNER_ID, ORDER_ID, WEIGHT, DESTINATION_POINT)
values (2, 3, 12, 'Пекин');
---
Commit;