create table if not exists users (
    username varchar(20) not null primary key,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    password varchar(100) not null,
    enabled bit not null
    );

create table if not exists authorities (
    username varchar(20) not null,
    authority varchar(20) not null,
    constraint fk_authorities_users foreign key(username) references users(username)
    );

create table if not exists priorities (
    id bigint primary key ,
    code varchar(20) not null,
    name varchar(50) not null,
    css_class varchar(500) null,
    icon varchar(20) null
    );

create  table if not exists  statuses (
    id bigint primary  key ,
    code varchar(20) not null,
    name varchar(50) not null,
    css_class varchar(500) null
);

create table if not exists tasks (
    id identity,
    subject varchar(50) not null,
    description varchar(2000) not null,
    user_assigned_to varchar(20)  null,
    user_assigned_by varchar (20) not null,
    status_id bigint not null,
    priority_id bigint null,
    deadline timestamp not null,
    constraint fk_user_assigned_to foreign key(user_assigned_to) references users(username),
    constraint fk_user_assigned_by foreign key(user_assigned_by) references users(username)
);

create view if not exists v_tasks
as
select
       t.id,
       t.subject,
       t.description,
       t.user_assigned_to as assigned_to_username,
       uato.first_name as assigned_to_first_name,
       uato.last_name as assigned_to_last_name,
       t.user_assigned_by as assigned_by_username,
       uaby.first_name as assigned_by_first_name,
       uaby.last_name as assigned_by_last_name,
       t.status_id,
       st.code as status_code,
       st.name as status_name,
       st.css_class as status_css_class,
       t.priority_id,
       p.code as priority_code,
       p.name as priority_name,
       p.css_class as priority_css_class,
       p.icon as priority_icon,
       t.deadline
from tasks t
left join users uato on t.user_assigned_to = uato.username
left join users uaby on t.user_assigned_by = uaby.username
left join statuses st on t.status_id = st.id
left join priorities p on t.priority_id = p.id
