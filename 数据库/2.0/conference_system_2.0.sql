/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/11/27 10:25:42                          */
/*==============================================================*/
drop database if exists conference;
create database conference;
use conference;

drop table if exists admin_table;

drop table if exists conference;

drop table if exists driver_table;

drop table if exists fleet_table;

drop table if exists hotel_table;

drop table if exists join_conference;

drop table if exists live_room;

drop table if exists organizer_table;

drop table if exists participant_table;

drop table if exists pick_up;

/*==============================================================*/
/* Table: admin_table                                           */
/*==============================================================*/
create table admin_table
(
   admin_id             int not null auto_increment,
   admin_name           varchar(20) not null,
   admin_pass           varchar(20) not null,
   admin_account        varchar(50) not null,
   primary key (admin_id, admin_account),
   unique key AK_Key_2 (admin_account)
);

/*==============================================================*/
/* Table: conference                                            */
/*==============================================================*/
create table conference
(
   conference_id        int not null auto_increment,
   organizer_id         int,
   fleet_id             int,
   hotel_id             int,
   conference_name      varchar(20) not null,
   conference_start     timestamp not null,
   conference_end       timestamp not null,
   conference_location  varchar(20) not null,
   conference_info      varchar(300),
   primary key (conference_id)
);

/*==============================================================*/
/* Table: driver_table                                          */
/*==============================================================*/
create table driver_table
(
   driver_id            int not null auto_increment,
   name                 varchar(20) not null,
   car_number           varchar(20) not null,
   fleet_id             int,
   driver_pass          varchar(20) not null,
   driver_phone         varchar(20) not null,
   is_assign            bool not null,
   primary key (driver_id),
   unique key AK_Key_2 (car_number, driver_phone)
);

/*==============================================================*/
/* Table: fleet_table                                           */
/*==============================================================*/
create table fleet_table
(
   fleet_id             int not null auto_increment,
   fleet_name           varchar(20) not null,
   fleet_pass           varchar(20) not null,
   fleet_phone          varchar(20) not null,
   primary key (fleet_id),
   unique key AK_Key_2 (fleet_phone)
);

/*==============================================================*/
/* Table: hotel_table                                           */
/*==============================================================*/
create table hotel_table
(
   hotel_id             int not null auto_increment,
   hotel_name           varchar(20) not null,
   hotel_location       varchar(20) not null,
   hotel_phone          varchar(20) not null,
   hotel_pass           varchar(20) not null,
   hotel_info           varchar(300),
   primary key (hotel_id),
   unique key AK_Key_2 (hotel_phone)
);

/*==============================================================*/
/* Table: join_conference                                       */
/*==============================================================*/
create table join_conference
(
   participate_id       int not null,
   conference_id        int not null,
   is_putup             smallint not null,
   is_pickup            smallint,
   to_time              timestamp not null,
   return_time          timestamp not null,
   Train_number         varchar(50),
   primary key (participate_id, conference_id)
);

/*==============================================================*/
/* Table: live_room                                             */
/*==============================================================*/
create table live_room
(
   participate_id       int not null,
   hotel_id             int not null,
   room_id              varchar(20),
   primary key (participate_id, hotel_id)
);

/*==============================================================*/
/* Table: organizer_table                                       */
/*==============================================================*/
create table organizer_table
(
   organizer_id         int not null auto_increment,
   organizer_pass       varchar(20) not null,
   organizer_phone      varchar(20) not null,
   organizer_unit       varchar(20) not null,
   email                varchar(50) not null,
   primary key (organizer_id),
   unique key AK_Key_2 (organizer_phone)
);

/*==============================================================*/
/* Table: participant_table                                     */
/*==============================================================*/
create table participant_table
(
   participate_id       int not null auto_increment,
   participant_name     varchar(20) not null,
   job                  varchar(20),
   work_unit            varchar(20),
   email                varchar(50),
   participate_pass     varchar(20) not null,
   participate_phone    varchar(20) not null,
   sex                  varchar(4) not null,
   id_card              varchar(20) not null,
   primary key (participate_id),
   unique key AK_Key_2 (participate_phone)
);

/*==============================================================*/
/* Table: pick_up                                               */
/*==============================================================*/
create table pick_up
(
   participate_id       int not null,
   driver_id            int not null,
   Train_number         varchar(50),
   to_time              timestamp,
   return_time          timestamp,
   is_finish_pickup     bool not null,
   primary key (participate_id, driver_id)
);

alter table conference add constraint FK_organise_conference foreign key (organizer_id)
      references organizer_table (organizer_id) on delete restrict on update restrict;

alter table conference add constraint FK_r_pickup foreign key (fleet_id)
      references fleet_table (fleet_id) on delete restrict on update restrict;

alter table conference add constraint FK_r_putup foreign key (hotel_id)
      references hotel_table (hotel_id) on delete restrict on update restrict;

alter table driver_table add constraint FK_r_dirver_fleet foreign key (fleet_id)
      references fleet_table (fleet_id) on delete restrict on update restrict;

alter table join_conference add constraint FK_join_conference foreign key (participate_id)
      references participant_table (participate_id) on delete restrict on update restrict;

alter table join_conference add constraint FK_join_conference2 foreign key (conference_id)
      references conference (conference_id) on delete restrict on update restrict;

alter table live_room add constraint FK_live_room foreign key (participate_id)
      references participant_table (participate_id) on delete restrict on update restrict;

alter table live_room add constraint FK_live_room2 foreign key (hotel_id)
      references hotel_table (hotel_id) on delete restrict on update restrict;

alter table pick_up add constraint FK_pick_up foreign key (participate_id)
      references participant_table (participate_id) on delete restrict on update restrict;

alter table pick_up add constraint FK_pick_up2 foreign key (driver_id)
      references driver_table (driver_id) on delete restrict on update restrict;

