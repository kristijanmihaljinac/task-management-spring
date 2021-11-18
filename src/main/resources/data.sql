insert into users (username,first_name, last_name, password, enabled) values
('admin','Kristijan', 'Mihaljinac', '$2a$10$rC14zOuWa0NiFB9.TIm.d.4YH9wFwm9oG8dpvvL03HvYTTwhHQD0u', 1),
('user','Marko', 'Markic', '$2a$10$rC14zOuWa0NiFB9.TIm.d.4YH9wFwm9oG8dpvvL03HvYTTwhHQD0u', 1);

insert into authorities (username, authority) values
('admin', 'ROLE_ADMIN'),
('admin', 'ROLE_USER'),
('user', 'ROLE_USER');

insert into priorities (code, name, css_class, icon) values
                                                                       ('LOW', 'Low', '#0000FF', NULL),
                                                                       ('LOWEST', 'Lowest', '#0000FF',NULL),
                                                                       ('MEDIUM', 'Medium', '#FFA500', NULL);

insert into statuses(code, name, css_class, is_default) values
                                                               ('TODO', 'To-Do', 'badge', 1),
                                                               ('IN_PROGRESS', 'In progress', 'badge',0),
                                                               ('DONE', 'Done', 'badge', 0);

insert into tasks(subject, description, user_assigned_to, user_assigned_by, status_id, priority_id, deadline) values
    ('Subject', 'Description', 'user', 'admin', 1, 1, '2021-10-10')