# Servicio backend de prueba. 

En primer lugar debemos arrancar los servicios especificados en: https://github.com/dalogax/backendDevTest

Nos ubicamos en la carpeta del proyecto con el servicio Docker arrancado y ejecutamos: 
docker-compose up -d simulado influxdb grafana
y 
docker-compose run --rm k6 run scripts/test.js

A continuación levantamos el docker, para ello entramos en la carpeta /target y usamos los comandos: 

docker build -t backenddev .
docker run -p 5000:5000 -d backenddev 

Al arrancar el proyecto se despliega en el puerto 5000. Podemos acceder al swagger desde la url: 
http://localhost:5000/swagger-ui.html 

# Swagger

Si accedemos al Swagger vemos el controlador get-product-similar donde se incluye el servicio getProductSimilar
Debemos introducir un valor numérico (número entero que es el id del producto). 

![Diagram](./src/main/resources/capturas/Captura0.jpg "Swagger") 

![Diagram](./src/main/resources/capturas/Captura0b.jpg "Swagger") 

El servicio hará dos llamadas a otros servicios para obtener los id's relacionados y los nombres de los productos. 
Se devolverá una lista datos de cada uno de los productos similares al de que hemos pasado como parámetro. 

Se ha introducido una caché que se resetearía cada media hora. 


# Ejemplos de llamadas
http://localhost:5000/product/1/similar

![Diagram](./src/main/resources/capturas/Captura1.jpg "Respuesta con idProducto 1") 

http://localhost:5000/product/2/similar

![Diagram](./src/main/resources/capturas/Captura2.jpg "Respuesta con idProducto 2") 

http://localhost:5000/product/3/similar

![Diagram](./src/main/resources/capturas/Captura3.jpg "Respuesta con idProducto 3") 

http://localhost:5000/product/4/similar

![Diagram](./src/main/resources/capturas/Captura4.jpg "Respuesta con idProducto 4") 

http://localhost:5000/product/5/similar

![Diagram](./src/main/resources/capturas/Captura5.jpg "Respuesta con idProducto 5") 

http://localhost:5000/product/6/similar

![Diagram](./src/main/resources/capturas/Captura6.jpg "Respuesta con idProducto 6") 


# Ejemplo de test unitario

![Diagram](./src/main/resources/capturas/Captura7.jpg "Test unitario de ejemplo") 



