insert into users (username,first_name, last_name, password, enabled) values
('admin','Kristijan', 'Mihaljinac', '$2a$10$rC14zOuWa0NiFB9.TIm.d.4YH9wFwm9oG8dpvvL03HvYTTwhHQD0u', 1),
('user','Marko', 'Markic', '$2a$10$rC14zOuWa0NiFB9.TIm.d.4YH9wFwm9oG8dpvvL03HvYTTwhHQD0u', 1);

insert into authorities (username, authority) values
('admin', 'ROLE_ADMIN'),
('admin', 'ROLE_USER'),
('user', 'ROLE_USER');

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
    ('TASK-1', 'Learn css', 'admin', 'admin', 1, 1, '2021-12-12'),
    ('TASK-2', 'Learn C#', 'admin', 'admin', 2, 1, '2021-12-12'),
    ('TASK-3', 'Learn Java', 'admin', 'admin', 3, 1, '2021-12-12'),
    ('TASK-4', 'Learn Kotlin', 'user', 'admin', 1, 1, '2021-12-12'),
    ('TASK-5', 'Learn HTML', 'user', 'admin', 2, 1, '2021-12-12'),
    ('TASK-6', 'Learn Python', 'user', 'admin', 2, 1, '2021-12-12'),
    ('TASK-7', 'Learn SQL', 'user', 'admin', 2, 1, '2021-12-12');