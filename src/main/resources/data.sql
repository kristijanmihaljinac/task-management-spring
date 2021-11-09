insert into users (username, password, enabled) values
('admin', '$2a$10$rC14zOuWa0NiFB9.TIm.d.4YH9wFwm9oG8dpvvL03HvYTTwhHQD0u', 1),
('user', '$2a$10$rC14zOuWa0NiFB9.TIm.d.4YH9wFwm9oG8dpvvL03HvYTTwhHQD0u', 1);

insert into authorities (username, authority) values
('admin', 'ROLE_ADMIN'),
('admin', 'ROLE_USER'),
('user', 'ROLE_USER');