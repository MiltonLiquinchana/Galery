#Query de insercion para llenar datos en las tablas de la bd bmp|gif|jpe?g|jpg|mp4|png|tiff
INSERT INTO TypeFile(typeFile) VALUES('png'),('jpg') ,('jpeg'),('gif'),('svg'), ('mkv'),
('jpe'),('mp4'),('tiff'),('bmp');

INSERT INTO FileData(routeFile, nameFile, FK_TYPEFILE) VALUES ('C:\anime','imagenBorrar',1),('D:\anime','NinjinAnime',6),
('D:\anime','fotoprincipal',2), 
('D:\anime','fotosecundaria',1);