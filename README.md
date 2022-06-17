# Inventario del estado de vacunación de los empleados
Proyecto del inventario de vacunas de los empleados
Se realizó la aplicación con la base de datos PostgreSQL.
Adjunto se encuentra el script Inventario.sql que contiene:
	-	Creación de base de datos 
	- 	Creación de esquema
	-	Creación de usuario para el esquema
	- 	Creación de tablas
	- 	Creación de relaciones entre las tablas
	-	Ingreso de información en las diferentes tablas creadas.
Se adjunta también el Diagrama Entidad Relación de la base de datos.

# Swagger
Se uso el swagger para la documentación de APIS
Para la generación del token se debe ingrear el usuario admin y la contraseña admin.

# IDE (Eclipse)
Para el desarrollo se uso java 11. En eclipse se debe importar como proyecto maven existente.

# Proyecto
Se desarrollo una aplicación para llevar un registro del inventario del estado de vacunación de los empleados.
La aplicación cuenta con dos roles: Administrador (ADMIN) y Empleado (USER).
Con el Rol de Administrador usted puede realizar:
	- Registrar empleados
	- Editar empleados
	- Listar empleados
	- Eliminar empleados 
	- Filtrar:
		- Por estado de vacunación
		- Por tipo de vacuna
		- Por rango de fecha de vacunación
Con el Rol de Empleado usted puede realizar visualizar y actualizar su información.
