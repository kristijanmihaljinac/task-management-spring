insert into users (username,first_name, last_name, password, enabled) values
('admin','Kristijan', 'Mihaljinac', '$2a$10$Hui0To3ZRYi.K5bWaty3/epMvVj3q98XJj7n4JA/ZZxB8oZSbe1rK', 1),
('mmarkic','Marko', 'Markic', '$2a$10$Hui0To3ZRYi.K5bWaty3/epMvVj3q98XJj7n4JA/ZZxB8oZSbe1rK', 1),
('pperic','Pero', 'Peric', '$2a$10$Hui0To3ZRYi.K5bWaty3/epMvVj3q98XJj7n4JA/ZZxB8oZSbe1rK', 1);

insert into authorities (username, authority) values
('admin', 'ROLE_ADMIN'),
('admin', 'ROLE_USER'),
('mmarkic', 'ROLE_USER'),
('pperic', 'ROLE_USER');
                                                     ;

insert into priorities (id,code, name, css_class, icon) values
(1,'LOW', 'Low', 'badge bg-info text-dark', NULL),
(2,'LOWEST', 'Lowest', 'badge bg-primary',NULL),
(3,'MEDIUM', 'Medium', 'badge bg-success', NULL),
(4,'HIGH', 'High', 'badge bg-warning text-dark', NULL),
(5,'HIGHEST', 'Highest', 'badge bg-danger', NULL);

insert into statuses(id, code, name, css_class) values
(1,'TODO', 'To-Do', 'badge bg-secondary'),
(2,'IN_PROGRESS', 'In progress', 'badge bg-primary'),
(3,'DONE', 'Done', 'badge bg-success');

insert into tasks(subject, description, user_assigned_to, user_assigned_by, status_id, priority_id, deadline) values
    ('TASK-1', 'Learn css', 'admin', 'admin', 1, 1, '2021-12-13'),
    ('TASK-2', 'Learn C#', 'admin', 'admin', 2, 2, '2021-12-14'),
    ('TASK-3', 'Learn Java', 'admin', 'admin', 3, 3, '2021-12-15'),
    ('TASK-4', 'Learn Kotlin', 'mmarkic', 'admin', 1, 4, '2021-12-16'),
    ('TASK-5', 'Learn HTML', 'mmarkic', 'admin', 2, 5, '2021-12-17'),
    ('TASK-6', 'Learn Python', 'pperic', 'admin', 2, 1, '2021-12-18'),
    ('TASK-7', 'Learn SQL', 'pperic', 'admin', 2, 1, '2021-12-19');