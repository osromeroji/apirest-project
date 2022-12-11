# apirest-project
Para ejecutar el código de la aplicación necesitaremos Eclipse u otro IDE y una instancia de SQL para persistir los datos.

También podemos ejecutar y probar el API REST sin necesitar de ejecutar el código, pues se ha desplegado en Amazon AWS. La URL del API es:
http://apirestmerca-env.eba-zv8a2j5e.us-east-1.elasticbeanstalk.com
En esta API se han definido varios mapeos:
PRODUCTOS (/api/products)
GET: Obtendremos la información de los productos que hay registrados.
GET products/{id} para obtener la información de un único producto
 
POST: Registraremos un nuevo producto
 
Colección POSTMAN:
{
    "code": "12345",
    "name": "Turron almendra",
    "description": "Turron blando de almendra sin azucar",
    "supplier": {
        "id": 3,
        "code": "8437010",
        "name": "Antiu Xixona"
}
}

PUT: Actualizar un producto ya existente.
 
SUPPLIERS (/api/suppliers)
Operaciones CRUD de igual manera que para los productos. Algunas colecciones para POSTMAN serían:
{
    "code": "8437010",
    "name": "Antiu Xixona"
}

{
    "code": "8437009",
    "name": "Bosque verde"
}

Ejemplos:
GET /api/suppliers

POST /api/suppliers

OBTENER INFORMACIÓN DE UN CÓDIGO EAN (/api/search/{EAN})

Se mostrará la información del proveedor, del producto y del destino del código EAN.


Además de esto, se realiza control de errores, devolviendo un mensaje de error.
-	Mensaje de error cuando el proveedor del código EAN no existe:
 


-	Mensaje de error cuando el producto del código EAN no existe:
 
-	Mensaje cuando el código EAN no está compuesto por 13 dígitos:
 
-	Mensaje cuando el código EAN contiene letras u otros caracteres:

 
El código se adjunta al email enviado, pero, además se ha subido a GitHub. La URL del repositorio es:

