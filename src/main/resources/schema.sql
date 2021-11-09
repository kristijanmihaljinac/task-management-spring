create table if not exists users (
    username varchar(20) not null primary key,
    password varchar(100) not null,
    enabled boolean not null
    );

create table if not exists authorities (
    username varchar(20) not null,
    authority varchar(20) not null,
    constraint fk_authorities_users foreign key(username) references users(username)
    );