CREATE TABLE usuario(
cedula VARCHAR(30) PRIMARY KEY NOT NULL,
nombre VARCHAR(15) NOT NULL,
apellido VARCHAR(15) NOT NULL,
correo VARCHAR(25) NOT NULL,
contrasena VARCHAR(30) NOT NULL
);

CREATE TABLE pruducto(
codigo INT PRIMARY KEY NOT NULL,
nombbre VARCHAR(15) NOT NULL,
cantida TINYINT NOT NULL,
descrpcion VARCHAR(40) NOT NULL,
categoria VARCHAR(30) NOT NULL
);


/*Como se crea una funcion en postgres!!*/
/*CREATE OR REPLACE FUNCTION modificarUsuario(cedula INT,nombre VARCHAR,apellidos VARCHAR,correo VARCHAR,contrasena VARCHAR) RETURNS void AS
    'UPDATE USUARIO SET nombre=$2,apellidos=$3,correo=$4,password=$5  WHERE cedula=$1;'
    language sql;*/

