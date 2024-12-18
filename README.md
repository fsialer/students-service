## Microservice students
Este microservicio ayudara a gestionar al estudiantes.

## Características

**CRUD completo:** Permite realizar operaciones para crear, leer, actualizar y eliminar estudiantes.

**Arquitectura Hexagonal:** Organiza el código separando las capas de dominio, aplicación y puertos/adaptadores, facilitando el mantenimiento y escalabilidad.

**Spring Boot 3:** Utiliza las últimas características de Spring para un desarrollo ágil y eficiente.

**Interacción con API de Matrículas:** La API se comunica con una API externa para gestionar la matriculación de los estudiantes.

**Interacción con Resource Server:** La API se comunica con una servidor de autorización para securizar los endpoints.

## Endpoints

| Método | Endpoint         | Descripción                        | Cuerpo (Body)                               |
|--------|------------------|------------------------------------|---------------------------------------------|
| GET    | `/students`      | Obtener todos los estudiantes      | N/A                                         |
| GET    | `/students/{id}` | Obtener un estudiante por ID       | N/A                                         |
| POST   | `/students`      | Crear un nuevo estudiante          | `{ "name": "Nombre", "age": 20, ... }`      |
| PUT    | `/students/{id}` | Actualizar un estudiante existente | `{ "name": "NuevoNombre", "age": 21, ... }` |
| DELETE | `/students/{id}` | Eliminar un estudiante por ID      | N/A                                         |



## Instalación

1. Clonar el repositorio:

    ```bash
    git clone https://github.com/tu_usuario/tu_repositorio.git
    ```

2. Acceder al directorio del proyecto:

    ```bash
    cd tu_repositorio
    ```

3. Configurar las propiedades en `src/main/resources/application.yml`:

    ```yaml
    spring:
      datasource:
        url: jdbc:postgres://localhost:5432/mi_base_datos
        username: usuario
        password: contraseña
      jpa:
        hibernate:
          ddl-auto: update
    
    ```

4. Compilar y ejecutar la aplicación:

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```
## Crear contenedores
1. Ejecutar el comando:
 ```
docker-compose up -d --build

 ```
## Testing

Para ejecutar los tests unitarios y de integración:

```bash
mvn test
```
```bash
mvn clean verify sonar:sonar -Dsonar.projectKey=<name_project_sq> -Dsonar.host.url=<host_sq>
```