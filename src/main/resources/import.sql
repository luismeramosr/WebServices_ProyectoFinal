use almacen;


insert into `role` (id,`name`) values (1, "Administrador");
insert into `role` (id,`name`) values (2, "Operario");

INSERT INTO almacen.`user` (id, dni, email, first_name, id_role, last_name, password, phone, username, active) VALUES('A00001', 98765432, 'luismeramosr@gmail.com', 'Luis', 1, 'Ramos', '$2a$10$GSeYUCLs6kwEm6MMXdj7EOLzF7TOMVcxMLksldDv6/81OLh6qXlwa', 123456789, 'admin', 1);
INSERT INTO almacen.`user` (id, dni, email, first_name, id_role, last_name, password, phone, username, active) VALUES('O00001', 98765432, 'juanperez@mail.com', 'Juan', 2, 'Pérez', '$2a$10$GSeYUCLs6kwEm6MMXdj7EOLzF7TOMVcxMLksldDv6/81OLh6qXlwa', 987654321, 'operario', 1);
