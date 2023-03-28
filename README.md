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

## Intalación de docker.
Docker se utilizará como contenedor de base de datos.
Esto es necesario para no depender de un servidor externo que nos provea el servicio de bases de
datos y el proyecto pueda ser ejecutado desde cualquier máquina con las herramientas necesarias.

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
En caso que al momento de intentar levantar el servicio de docker nos de un error; puedes ingresar
a éste [link](https://bbs.archlinux.org/viewtopic.php?id=194087) y seguir los pasos que se indican
(recuerda que debes de reiniciar tu equipo).

## Levantar el proyecto.
### Habilitar el contenedor de docker.

**Nota**: Si se está usando un servidor de bases de datos externo, se puede omitir este paso.
Solo se deberán de modificar los archivos ```application.properties``` de los proyectos para
definirle los datos de conexión.

Para levantar el contenedor de docker, se realizó un archivo llamado ```docker-compose.yml```, el 
cual contiene la información necesaria para el contenedor.   
Este archivo está localizado en la ruta raíz del proyecto, por lo que se deberá navergar hasta la
ruta del proyecto y ejecutar el siguiente comando:
``` console
# docker-compose -f ./docker-compose.yml up -d
```

### Levantar el proyecto del apirest.
Para esto, es necesario estar en la ruta raíz del proyecto y ejecutar el siguiente comando:
``` console
$ cd apirest
$ ./mvnw spring-boot:run
```

## Bajar el proyecto.
### Deshabilitar el contenedor de docker.
**Nota**: Si se está usando un servidor de bases de datos externo, se puede omitir este paso.
Para deshabilitar el contenedor, se deberán ejecutar los siguientes comandos:
``` console
# docker-compose down
# docker-compose stop
```
Y para asegurarnos que se deshabilito correctamente, se deberá de corroborar con el comando:
```console
# docker-compose ps
```