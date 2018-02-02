# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table airport (
  id                            bigint auto_increment not null,
  ident                         varchar(255),
  type                          varchar(255),
  name                          varchar(255),
  latitude_deg                  varchar(255),
  longitude_deg                 varchar(255),
  elevation_ft                  varchar(255),
  continent                     varchar(255),
  iso_region                    varchar(255),
  municipality                  varchar(255),
  scheduled_service             varchar(255),
  gps_code                      varchar(255),
  iata_code                     varchar(255),
  local_code                    varchar(255),
  home_link                     varchar(255),
  wikipedia_link                varchar(255),
  keywords                      varchar(255),
  iso_country                   bigint not null,
  constraint pk_airport primary key (id)
);

create table country (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  code                          varchar(255),
  continent                     varchar(255),
  wikipedia_link                varchar(255),
  keywords                      varchar(255),
  constraint pk_country primary key (id)
);

alter table airport add constraint fk_airport_iso_country foreign key (iso_country) references country (id) on delete restrict on update restrict;
create index ix_airport_iso_country on airport (iso_country);


# --- !Downs

alter table airport drop foreign key fk_airport_iso_country;
drop index ix_airport_iso_country on airport;

drop table if exists airport;

drop table if exists country;

