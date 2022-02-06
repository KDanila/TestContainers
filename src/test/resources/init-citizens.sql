CREATE TABLE IF NOT EXISTS citizen
(
    ID         SERIAL,
    FIRST_NAME varchar(40) NOT NULL,
    LAST_NAME  varchar(40) NOT NULL,
    ID_NUMBER  varchar(40),
    PRIMARY KEY (id)
);

insert into citizen (id, first_name, last_name, id_number) values (100, 'jam', 'simon', 'adshfla12312');
insert into citizen (id, first_name, last_name, id_number) values (200, 'sam', 'ebet', 'adshfasdfsd7668la12312');
insert into citizen (id, first_name, last_name, id_number) values (300, 'den', 'class', 'sdfeww113');