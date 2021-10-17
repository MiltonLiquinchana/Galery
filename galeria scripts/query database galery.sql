DROP DATABASE IF EXISTS GALERY;
CREATE DATABASE GALERY;

USE GALERY;

#Tabla que guarda los tipos de archivos permitidos
DROP TABLE IF EXISTS TypeFile;
CREATE TABLE TypeFile(
PK_TYPEFILE INT AUTO_INCREMENT PRIMARY KEY,
typeFile varchar(10) UNIQUE NOT NULL
);


#Tabla que guarda informacion de la imagen, video, etc;
DROP TABLE IF EXISTS FileData;
CREATE TABLE FileData(
PK_FILEDATA INT AUTO_INCREMENT PRIMARY KEY,
routeFile nvarchar(500),
nameFile nvarchar(500),
FK_TYPEFILE INT NOT NULL,
FOREIGN KEY(FK_TYPEFILE) REFERENCES TypeFile(PK_TYPEFILE)
);

select * from TypeFile order by PK_TYPEFILE asc;
select * from FileData order by PK_FILEDATA asc;

select * from FileData group by FK_TYPEFILE
#comentario


