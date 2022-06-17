----------------------------------------------------------
------------ Ejecutar con el usuario postgres ------------
----------------------------------------------------------

DROP DATABASE IF EXISTS inventario_db;
create  DATABASE inventario_db;
DROP ROLE IF EXISTS inventario_usuario;
CREATE ROLE inventario_usuario WITH 
	LOGIN ENCRYPTED PASSWORD 'kr4g3r'
	SUPERUSER
	NOCREATEDB
	CREATEROLE
	INHERIT
	NOREPLICATION
	NOBYPASSRLS
	CONNECTION LIMIT -1;

----------------------------------------------------------
------------ Ejecutar con usuario inventario_usuario -----
----------------------------------------------------------

DROP SCHEMA IF EXISTS inventario CASCADE;
CREATE SCHEMA inventario AUTHORIZATION inventario_usuario;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA inventario TO inventario_usuario;

----------------------------------------------------------
------------ Ejecutar con usuario inventario_usuario -----
------------            Creación de tablas           -----
----------------------------------------------------------

--- VACUNA ---

DROP TABLE IF EXISTS inventario.vacuna;
CREATE TABLE inventario.vacuna (
	id_vacuna serial4 NOT NULL,
	nombre_vacuna varchar(255) NULL,
	CONSTRAINT vacuna_pkey PRIMARY KEY (id_vacuna)
);

--- EMPLEADO ---

DROP TABLE IF EXISTS inventario.empleado;
CREATE TABLE inventario.empleado (
	cedula varchar(255) NOT NULL,
	nombre varchar(255) NULL,
	apellido varchar(255) NULL,
	correo_electronico varchar(255) NULL,
	fecha_nacimiento date NULL,
	direccion varchar(255) NULL,
	celular varchar(255) NULL,	
	estado_vacunacion varchar(255) NULL,
	id_vacuna int4 NOT NULL,
	numero_dosis int4 NULL,
	fecha_vacunacion date NULL,
	CONSTRAINT empleado_pkey PRIMARY KEY (cedula)	
);

--- usr_rol ---

DROP TABLE IF EXISTS inventario.usr_rol;
CREATE TABLE inventario.usr_rol (
	usr_id int4 NOT NULL,
	rol_id int4 NOT NULL,
	CONSTRAINT usr_rol_pkey PRIMARY KEY (usr_id, rol_id)
);

--- rol ---

DROP TABLE IF EXISTS inventario.rol;
CREATE TABLE inventario.rol (
	rol_id serial4 NOT NULL,
	rol varchar(255) NULL,
	CONSTRAINT rol_pkey PRIMARY KEY (rol_id)
);


--- usr ---

DROP TABLE IF EXISTS inventario.usr;
CREATE TABLE inventario.usr (
	usr_id serial4 NOT NULL,
	username varchar(255) NULL,
	password varchar(255) NULL,
	cedula varchar(255) NULL,
	CONSTRAINT usr_pkey PRIMARY KEY (usr_id)
);

ALTER TABLE inventario.empleado ADD CONSTRAINT fk_empleado_vacuna FOREIGN KEY (id_vacuna) REFERENCES inventario."vacuna"(id_vacuna) ON DELETE CASCADE;;
ALTER TABLE inventario.usr_rol  ADD CONSTRAINT fk_usr_rol_user FOREIGN KEY (usr_id) REFERENCES inventario."usr"(usr_id) ON DELETE CASCADE;
ALTER TABLE inventario.usr_rol  ADD CONSTRAINT fk_usr_rol_rol  FOREIGN KEY (rol_id) REFERENCES inventario."rol"(rol_id) ON DELETE CASCADE;
ALTER TABLE inventario.usr      ADD CONSTRAINT fk_usr_empleado FOREIGN KEY (cedula) REFERENCES inventario.empleado(cedula) ON DELETE CASCADE;

----------------------------------------------------------
------- DATOS PARA INICIALIZAR LA APLICACION -------------
------- Usuario: admin Password: admin       -------------
------- Roles: ADMIN,USER                    -------------
----------------------------------------------------------

INSERT INTO inventario."rol" (rol_id, "rol") VALUES(1, 'ADMIN');
INSERT INTO inventario."rol" (rol_id, "rol") VALUES(2, 'USER');

----------------------------------------------------------------------
---- Para el password se coloca el hash bcrypt de la palabra admin---- 
----------------------------------------------------------------------
INSERT INTO inventario.usr( "username", "password", "cedula") VALUES( 'admin', '$2y$10$jrCkUTTGGdrOO/rjnEN4oenYD2eAL5un3LthdMHCV7.ul978P10Ki', null);

---------------------------------------------
---- Asignar roles (ADMIN, USER) a admin ----
---------------------------------------------

INSERT INTO inventario.usr_rol (usr_id, rol_id) VALUES((select usr_id from inventario."usr" u where u.username = 'admin'), 1);
INSERT INTO inventario.usr_rol (usr_id, rol_id) VALUES((select usr_id from inventario."usr" u where u.username = 'admin'), 2);

--- Vacunas ---

INSERT INTO inventario.vacuna (id_vacuna, nombre_vacuna) VALUES(1, 'Ninguna');
INSERT INTO inventario.vacuna (id_vacuna, nombre_vacuna) VALUES(2, 'Sputnik');
INSERT INTO inventario.vacuna (id_vacuna, nombre_vacuna) VALUES(3, 'AstraZeneca');
INSERT INTO inventario.vacuna (id_vacuna, nombre_vacuna) VALUES(4, 'Pfizer');
INSERT INTO inventario.vacuna (id_vacuna, nombre_vacuna) VALUES(5, 'Jhonson&Jhonson');