#script de procedimientos almacenados
##Procedimiento que busca todos los typos de archivos y los archivos asociados
DROP PROCEDURE IF EXISTS finTypes;
Delimiter $$
CREATE PROCEDURE finTypes()
BEGIN 
	SELECT PK_TYPEFILE,typeFile FROM TypeFile;
END$$
Delimiter $$; 

call finTypes();