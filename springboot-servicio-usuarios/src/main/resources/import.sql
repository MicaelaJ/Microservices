INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email) VALUES ('andres','$2a$10$CJ/6XP2juTePwe86zVPj..NKbbhghgRy4r0Z5jfVuTSzVbHEqjWFu',1, 'Andres', 'Guzman', 'profesor@bolsadeideas.com');
INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email) VALUES ('admin','$2a$10$x5rFcuB9wNYgbjYBwvnYPOaP.WHUSQN9Lf4CXiauk7baMPKursAXa',1, 'John', 'Doe', 'john.doe@bolsadeideas.com');

INSERT INTO `roles` (nombre) VALUES ('ROLE_USER');
INSERT INTO `roles` (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (1, 1);
INSERT INTO `usuarios_roles`(usuario_id, role_id) VALUES (2, 2);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2, 1);