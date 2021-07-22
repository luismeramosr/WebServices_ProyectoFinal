use almacen;


// Roles
insert into role (id,name) values (1, "Administrador");
insert into role (id,name) values (2, "Operario");

// Horarios
insert into schedule (id, start, stop) values (1, "00:00:00", "23:59:59");
insert into schedule (id, start, stop) values (2, "10:00:00", "19:00:00");
insert into schedule (id, start, stop) values (3, "13:00:00", "22:00:00");
insert into schedule (id, start, stop) values (4, "16:00:00", "01:00:00");
insert into schedule (id, start, stop) values (5, "23:00:00", "08:00:00");

// Usuarios
INSERT INTO user (id, dni, email, first_name, id_role, last_name, password, phone, username, active, id_schedule) VALUES(1, 98765432, 'luismeramosr@gmail.com', 'Luis', 1, 'Ramos', '$2a$10$GSeYUCLs6kwEm6MMXdj7EOLzF7TOMVcxMLksldDv6/81OLh6qXlwa', 123456789, 'admin', 1, 1);
INSERT INTO user (id, dni, email, first_name, id_role, last_name, password, phone, username, active, id_schedule) VALUES(2, 98765432, 'anotheradmin@gmail.com', 'Admin', 1, 'abc', '$2a$10$GSeYUCLs6kwEm6MMXdj7EOLzF7TOMVcxMLksldDv6/81OLh6qXlwa', 123456789, 'admin2', 1, 1);
INSERT INTO user (id,active,dni,email,first_name,id_role,id_schedule,last_name,password,phone,username) VALUES (7,1,'75693102','juanp@gmail.com','Juan',2,1,'Perez','$2a$10$FOFt.0i/6AP0UjeVKxmZIeFAGQF9HzybyRPaj/3zSEwRvBBT87dIq','987654321','JuaPer102');
INSERT INTO user (id, dni, email, first_name, id_role, last_name, password, phone, username, active, id_schedule) VALUES(4, 87391291, 'jorgecasas@mail.com', 'Jorge', 2, 'Casas', '$2a$10$yxcqbI41PNsrz9fM9K7Ene.LwkiU3vy6LgGmQ1vk9vBEuJ6ZZzCr6', 998873121, 'jorcas291', 1, 2);
INSERT INTO user (id,active,dni,email,first_name,id_role,id_schedule,last_name,password,phone,username) VALUES (5,1,'64891291','carlos221@gmail.com','Carlos',2,5,'Carrera','$2a$10$RGkJPMNIB9q7.IYhA2HR1O6D1i.95A5FbqiB17CzBhN2OI72GdbWe','912317472','CarCar291');
INSERT INTO user (id,active,dni,email,first_name,id_role,id_schedule,last_name,password,phone,username) VALUES (6,1,'56328745','pollo_pio@hotmail.com','El pollo',2,4,'Pío','$2a$10$zlz7llf/vTVRt35Z8wKVyOR0NBmGB3gYz0F.E3KpD5n7MuXFiNDju','948261200','El Pío745');
// Proveedores
INSERT INTO provider (id,name,phone) VALUES (1,'Adidas',937829015);
INSERT INTO provider (id,name,phone) VALUES (2,'Nike',964326022);
INSERT INTO provider (id,name,phone) VALUES (3,'Platanitos',982762390);
INSERT INTO provider (id,name,phone) VALUES (4,'Jhon Holden',945593056);
INSERT INTO provider (id,name,phone) VALUES (5,'Gamarra - Gucci',956285322);
INSERT INTO provider (id,name,phone) VALUES (6,'Gamarra - Jeans',975273058);
INSERT INTO provider (id,name,phone) VALUES (7,'inmedlife',912867021);

// Items (Productos)
INSERT INTO item (barcode,brand,description,name,price,stock,stock_min,provider_id) VALUES ('7757913000018','inmedlife','Caja de mascarillas quirúrgicas tipo II-R','Mascarilla Quirúrgica Facial',20.5,130,25,7);
INSERT INTO item (barcode,brand,description,name,price,stock,stock_min,provider_id) VALUES ('7757913002019','OldSpice','Desodorante OldSpice champion','OldSpice champion',12.6,100,10,5);
INSERT INTO item (barcode,brand,description,name,price,stock,stock_min,provider_id) VALUES ('7757913002020','OldSpice','Desodorante OldSpice fresh','OldSpice fresh',11.5,100,10,6);


// Tipos de solicitud
INSERT INTO request_type (id,`type`) VALUES (1,'Despacho');
INSERT INTO request_type (id,`type`) VALUES (2,'Ingreso');
INSERT INTO request_type (id,`type`) VALUES (3,'Inventario');