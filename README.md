# Práctica 2 – Creación API REST
### Objetivo:
El objetivo de esta práctica es diseñar e implementar una API REST mínima utilizando Spring Boot, siguiendo el estilo arquitectónico request/response.
La API permite realizar las operaciones CRUD (Create, Read, Update, Delete) sobre un recurso denominado Carrito, que modela de forma simplificada el estado de una compra en curso en un sistema de e-commerce.

El objetivo principal no es implementar un sistema de compra completo, sino comprender y aplicar los conceptos básicos del diseño de servicios REST, incluyendo:

- Identificación de recursos mediante URLs
- El uso correcto de los métodos HTTP
- El intercambio de datos en formato JSON
- El manejo de respuestas HTTP y errores




## 1.Descripción de endpoints 


notas aclaratorias:
- El recurso Carrito representa una compra en curso y solo puede contener un único producto.
- El campo precioFinal se considera un valor proporcionado/calculado y se devuelve en las respuestas.
- La API utiliza códigos HTTP estándar para indicar el resultado de cada operación.
- Los errores de validación devuelven un 400 Bad Request con el detalle de los campos incorrectos.


## 2.Ejecución en Postman

## 3. Estructura del Repositorio

## 4. Conclusión




