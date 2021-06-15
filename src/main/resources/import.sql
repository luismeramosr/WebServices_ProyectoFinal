use almacen;


insert into `role` (id,`name`) values (1, "ADMIN");

INSERT INTO almacen.`user` (id, dni, email, first_name, id_role, last_name, password, phone, username) VALUES('O00001', 98765432, 'test@mail.com', 'Luis', 1, 'Ramos', '$2a$10$GSeYUCLs6kwEm6MMXdj7EOLzF7TOMVcxMLksldDv6/81OLh6qXlwa', 123456789, 'admin');
