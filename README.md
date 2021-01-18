# PALETA DE COLORES

## Descripción
Se implementa un servicio que permite mostrar información de una variedad de colores para ser empleados en el diseño de paginas web. 
Este servicio brinda la siguiente información:
- Lista todo los colores que se tiene en Base Datos.
- Lista mediante un paginado en el cual se le especifica la pagina que se quiere visualizar. El servicio muestra la pagina actual, número de paginas, total de elementos y cantidad de elementos por pagina.
- Muestra el detalle de un color al ser consultado por el id.
- Registra el color que se desea guardar en base de datos, donde para ello realiza ciertas validaciones: no permite valores nulos o vacios y debe cumplir al menos con la cantidad de caracteres para cada campo. No se requiere ingresar el id ya que lo realiza de forma automatica e incremental.
- *Los servicios estan preparados para responder en formato Json o XML de acuerdo a como se le solicite, al no especificarse responde en formato Json por defecto.*

## Tecnología utilizada
Para la implementación de esta aplicación se utilizo los siguiente:
- IDE Eclipses
- Proyecto Maven
- Framework Spring Boot
- Spring Data JPA
- Base Datos H2
- Lombok
- Pruebas Unitarias: JUnit4

## Dependencias y desplegar el proyecto
Las dependencias empleadas se encuentra ubicadas en el archivo pom.xml del proyecto.
Para desplegar el proyecto, descargar las fuentes del repositorio Github, importar en el IDE Eclipse como proyecto Maven y finalmente ejecutar el proyecto. Tener en cuenta que se esta trabajando con una base datos embebida H2 por lo que la data se encuentra almacena en memoria en cuanto el proyecto este corriendo. Existe un script que al ejecutar el proyecto se cargara la data en la tabla correspondiente. Una vez ejecutado el proyecto los servicios pueden ser invocado por el browser o Postman.

------------


Luego de correr el proyecto los servicios expuesto son:

#### Listar todo los colores:
Request: 
http://localhost:8080/integra/colores?formato=json
http://localhost:8080/integra/colores?formato=xml

Response: 
**json**

    [
        {
            "id": 1,
            "name": "cerulean",
            "color": "#98B2D1"
        },
        {
            "id": 2,
            "name": "fuchsia rose",
            "color": "#C74375"
        },
    	. . .
    ]

**xml:**

    <List>
        <item>
            <id>1</id>
            <name>cerulean</name>
            <color>#98B2D1</color>
        </item>
        <item>
            <id>2</id>
            <name>fuchsia rose</name>
            <color>#C74375</color>
        </item>
    	. . .
    </List>

#### Listar colores por paginado
Request: 
http://localhost:8080/integra/colores/paginado/{pagina}?formato=json
http://localhost:8080/integra/colores/paginado/{pagina}?formato=xml

Response:
**json:**

    {
        "paginaActual": 1,
        "numeroPaginas": 3,
        "totalElementos": 12,
        "cantElementoPorPagina": 4,
        "colores": [
            {
                "id": 1,
                "name": "cerulean",
                "color": "#98B2D1"
            },
            {
                "id": 2,
                "name": "fuchsia rose",
                "color": "#C74375"
            },
    		. . .
    }

**xml:**

    <PaginacionResponse>
        <paginaActual>4</paginaActual>
        <numeroPaginas>4</numeroPaginas>
        <totalElementos>13</totalElementos>
        <cantElementoPorPagina>4</cantElementoPorPagina>
        <colores>
            <colores>
                <id>13</id>
                <name>celeste</name>
                <color>#123ABC</color>
            </colores>
        </colores>
    </PaginacionResponse>

#### Consultar color por id
Request:
http://localhost:8080/integra/colores/{id}
http://localhost:8080/integra/colores/{id}?formato=xml

Response:
**json**

    {
        "id": 5,
        "name": "tigerlily",
        "year": "2004",
        "color": "#E2583E",
        "pantoneValue": "17-1456"
    }

**xml:**

    <Color>
        <id>5</id>
        <name>tigerlily</name>
        <year>2004</year>
        <color>#E2583E</color>
        <pantoneValue>17-1456</pantoneValue>
    </Color>

#### Registrar color
Request:
http://localhost:8080/integra/colores
http://localhost:8080/integra/colores?formato=xml

Response:
**json:**

    {
        "name": "celeste",
        "year": "2021",
        "color": "#123ABC",
        "pantoneValue": "17-3333"
    }

**xml:**

    <Color>
        <name>celeste</name>
        <year>2021</year>
        <color>#123ABC</color>
        <pantoneValue>17-3333</pantoneValue>
    </Color>


------------
## Documentación Swagger
Para visualizar la documentación Swagger, ejecutar el proyecto, copiar el siguiente link en el browser:
http://localhost:8080/integra/api-docs/swagger-ui/index.html

En el buscador (Explorer) colocar el parrafo: /integra/v2/api-docs


------------


