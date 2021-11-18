create table if not exists users (
    username varchar(20) not null primary key,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    password varchar(100) not null,
    enabled boolean not null
    );

create table if not exists authorities (
    username varchar(20) not null,
    authority varchar(20) not null,
    constraint fk_authorities_users foreign key(username) references users(username)
    );

create table if not exists priorities (
    id identity,
    code varchar(20) not null,
    name varchar(50) not null,
    css_class varchar(20) null,
    icon varchar(20) null
    );

create  table if not exists  statuses (
    id identity ,
    code varchar(20) not null,
    name varchar(50) not null,
    css_class varchar(20) null,
    is_default bit not null
);

create table if not exists tasks (
    id identity,
    subject varchar(50) not null,
    description varchar(2000) not null,
    user_assigned_to varchar(20) null,
    user_assigned_by varchar (20) not null,
    status_id bigint not null,
    priority_id bigint null,
    deadline timestamp not null,
    constraint fk_user_assigned_to foreign key(user_assigned_to) references users(username),
    constraint fk_user_assigned_by foreign key(user_assigned_by) references users(username)
);

create view if not exists v_tasks
as
select t.* from tasks t
left join users uato on t.user_assigned_to = uato.username
left join users uaby on t.user_assigned_to = uaby.username
left join statuses st on t.status_id = st.id
