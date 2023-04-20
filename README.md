# Life in ~BIKE~ (Backend)
Documentación sobre el backend de la aplicación de Life in ~BIKE~.

## Requerimientos y consejos para ejecutar el proyecto.
**NOTA:** Esta documentación está basada bajo comando de linux. A su vez, los comandos pertenecen
totalmente a las distribuciones basadas en Arch linux.  
Si el proyecto se desa correr bajo otra distribución, será necesario buscar los comandos
correspondientes para esa distribución.

Requerimientos opcionales:
* Correrse bajo una distribución linux (preferentemente una basada en Arch).
* Uso del SGDB de PostgreSQL.
* Procesador con soporte de virtualización (esto solo si se desea usar docker en lugar de un
servidor externo de base de datos).

Requerimientos necesarios:
* OpenJDK 17.
* Procesador de 2 núcleos mayor a 1.8 GHz.
* 4 GB de RAM DDR3 a 1333 MHz o mayor.
* Almacenamiento mayor a 128 GB.

## Instalación del openJDK 17.
* Archlinux:   
    Actualizar los repositorios.
    ``` console
    # pacman -Syu
    ```
    Instalar el openjdk 17:
    ``` console
    # pacman -S jdk17-openjdk jre17-openjdk jre17-openjdk-headless
    ```
    En caso que ya se tenga una versión de java instalado, verificar que se esté usando la versión 17:
    ``` console
    $ java --version
    ```
    Si la versión que esta muestre no es la 17, se deberá de seleccionar esa versión. Para ello,
    será necesario ejecutar los siguientes comandos:
    ``` console
    # archlinux-java status
    ```
    El comando anterior deberá de mostrar las versiones de java instaladas:
    ``` console
    Available Java environments:
        java-17-openjdk
        java-19-openjdk (default)
        java-8-openjdk/jre
    ```
    Esta mostrará con una etiqueta ```(default)``` la versión que está seleccionada por defecto.   
    Para este caso, será necesario cambiarla a la versión del jdk 17. Para ello, se deberá de
    ejecutar el comando ```archlinux-java set <jdk>```, remplazando la etiqueta ```<jdk>``` por el
    nombre del jdk a utilizar de la lista de versiones instaladas. En este casó sería así:
    ``` console
    # archlinux-java set java-17-openjdk
    ```
    Y se corrobora nuevamente la versión con el comando ```java --version```.

## Intalación de docker.
Docker se utilizará como contenedor de base de datos.
Esto es necesario para no depender de un servidor externo que nos provea el servicio de bases de
datos y el proyecto pueda ser ejecutado desde cualquier máquina con las herramientas necesarias.

* Archlinux:   
    Se deberá de instalar el paquete de docker:
    ```console
    # pacman -S docker docker-compose
    ```
    **Nota**: es necesario reiniciar una vez se haya instalado docker.   
    Posterior a esto, se deberá habilitar el srvicio de docker:
    ```console
    # systemctl start docker.service
    # systemctl enable docker.service
    ```
    En caso que al momento de intentar levantar el servicio de docker nos de un error; puedes
    ingresar a éste [link](https://bbs.archlinux.org/viewtopic.php?id=194087) y seguir los pasos
    que se indican (recuerda que debes de reiniciar tu equipo).

## Levantar el proyecto.
### Habilitar el contenedor de docker.

**Nota**: Si se está usando un servidor de bases de datos externo, se puede omitir este paso.
Solo se deberán de modificar los archivos ```application.properties``` de los proyectos para
definirle los datos de conexión.

Para levantar el contenedor de docker, se realizó un archivo llamado ```docker-compose.yml```, el 
cual contiene la información necesaria para el contenedor.   
Este archivo está localizado en la ruta raíz del proyecto, por lo que se deberá navergar hasta la
ruta del proyecto y ejecutar los pasos correspondientes:
* Archlinux:   
    Crear el contenedor de docker en base al archivo ```docker-compose.yml```:
    ``` console
    # docker-compose -f ./docker-compose.yml up -d
    ```

    En caso que ya se hubiera creado el contenedor y se requiera levantar el docker, solo es
    necesario correr el siguiente comando:
    ``` console
    # docker-compose up
    ```

### Correr los test
El testeo es necesario para corroborar que si se tenga conexión a la base de datos desde el
proyecto; así como realizar insersiónes y selecciones de datos de manera automática para comprobar
que las tablas están creadas de manera adecuada y la base de datos está preparada para el proyecto.
Para ejecutar los test será necesario realizar el siguiente comando:
``` console
$ ./mvnw test
```

### Correr el proyecto
Para esto, es necesario estar en la ruta raíz del proyecto y ejecutar el siguiente comando:
``` console
$ ./mvnw spring-boot:run
```

### Acceder a la documentación del api.
En esta parte se podrá encontrar la documentación de absolutamente todos los endpoints del proyeco.
Esto nos facilita la visualización de todos los endpoints disponibles, la forma en la que se
utilizan, que atributos recibe y su retorno.   
Para ello deberá de estar corriendo el proyecto y entrar al siguiente link [swagger-ui](http://localhost:8080/swagger-ui.html)

## Bajar el proyecto.
### Deshabilitar el contenedor de docker.
**Nota**: Si se está usando un servidor de bases de datos externo, se puede omitir este paso.
* Archlinux:   
    Para deshabilitar el contenedor, se deberán ejecutar los siguientes comandos:
    ``` console
    # docker-compose down
    # docker-compose stop
    ```
    Y para asegurarnos que se deshabilito correctamente, se deberá de corroborar con el comando:
    ```console
    # docker-compose ps
    ```
