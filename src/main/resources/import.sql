use almacen;


// Roles
insert into `role` (id,`name`) values (1, "Administrador");
insert into `role` (id,`name`) values (2, "Operario");

// Horarios
insert into `schedule` (id, start, stop) values (1, "00:00:00", "23:59:59");
insert into `schedule` (id, start, stop) values (2, "10:00:00", "19:00:00");
insert into `schedule` (id, start, stop) values (3, "13:00:00", "22:00:00");
insert into `schedule` (id, start, stop) values (4, "16:00:00", "01:00:00");
insert into `schedule` (id, start, stop) values (5, "23:00:00", "08:00:00");

// Usuarios
INSERT INTO `user` (id, dni, email, first_name, id_role, last_name, password, phone, username, active, id_schedule) VALUES(1, 98765432, 'luismeramosr@gmail.com', 'Luis', 1, 'Ramos', '$2a$10$GSeYUCLs6kwEm6MMXdj7EOLzF7TOMVcxMLksldDv6/81OLh6qXlwa', 123456789, 'admin', 1, 1);
INSERT INTO `user` (id, dni, email, first_name, id_role, last_name, password, phone, username, active, id_schedule) VALUES(2, 75693102, 'juanperez@mail.com', 'Juan', 2, 'Pérez', '$2a$10$GSeYUCLs6kwEm6MMXdj7EOLzF7TOMVcxMLksldDv6/81OLh6qXlwa', 987654321, 'juaper102', 1, 1);
INSERT INTO `user` (id, dni, email, first_name, id_role, last_name, password, phone, username, active, id_schedule) VALUES(3, 87391291, 'jorgecasas@mail.com', 'Jorge', 2, 'Casas', '$2a$10$GSeYUCLs6kwEm6MMXdj7EOLzF7TOMVcxMLksldDv6/81OLh6qXlwa', 998873121, 'jorcas291', 1, 2);