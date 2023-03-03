# Servicio backend de prueba. 

En primer lugar debemos arrancar los servicios especificados en: https://github.com/dalogax/backendDevTest

Nos ubicamos en la carpeta del proyecto con el servicio Docker arrancado y ejecutamos: 
docker-compose up -d simulado influxdb grafana
y 
docker-compose run --rm k6 run scripts/test.js

A continuación levantamos el docker, para ello entramos en la carpeta /target y usamos los comandos: 

docker build -t backenddev .
docker run -p 5000:5000 -d backenddev 

Al arrancar el proyecto se despliega en el puerto 5000. Podemos acceder al swagger desde la url. 

# Swagger
http://localhost:5000/swagger-ui.html


Si accedemos al Swagger vemos el controlador get-product-similar donde se incluye el servicio getProductSimilar
Debemos introducir un valor numérico (número entero que es el id del producto). 
El servicio hará dos llamadas a otros servicios para obtener los id's relacionados y los nombres de los productos. 
Se devolverá una lista datos de cada uno de los productos similares al de que hemos pasado como parámetro. 

Se ha introducido una caché que se resetearía cada media hora. 


# Ejemplos de llamadas
http://localhost:5000/product/1/similar

http://localhost:5000/product/2/similar