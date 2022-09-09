# alizaapp

Prueba tecnica alianza fiduciaria
Esta prueba tecnica tiene que ver con un programa en donde recibirás como parámetro un array de Strings que representan cada fila de una tabla
de (NxN) con una secuencia del ADN. Las letras de los Strings solo pueden ser: (A,T,C,G), las cuales representa cada base nitrogenada del ADN.
para poder reconocer una secuencia de ADN y determinar si un persona es un Mutante (X-Men).

## Como empezar

Este instructivo te permite tener una copia del proyecto subirlo y ejecutarlo en tu maquina local para desarrollar nuevos cambios, probar y refactorizar.
disfrutalo y espero que continues mejorandolo.

### Prerequesitos

Que necesitas para instalar necesitar descargar el repositorio con la instrucción:

```
git clone https://github.com/lvillarrealcool/alizaapp.git
```

### Instalación

Este es un paso a paso de lo que se debe hacer para ejecutar el programa abrir cmd de windows o shell de linux:

```
Dirigete a la ruta del proyecto clonado 
en windows puede ser:
C:\alianzapp
o en linux:
[user]$ cd /home/user/alianzapp 
```
Luego ejecutar el comando:
```
./gradlew clean build

Al terminar de ejecutarse el comando debe aparcer un mensaje como este:
BUILD SUCCESSFUL in 45s
9 actionable tasks: 9 executed
```
Luego ejecutar el comando:
```
en windows:
java -jar build\libs\alianzapp-0.0.1-SNAPSHOT.jar
en linux:
java -jar build/libs/alianzapp-0.0.1-SNAPSHOT.jar
```
Si se presenta este error: 
Error: Unable to access jarfile <path>

Seguir las instrucciones de este enlace: https://www.youtube.com/watch?v=s3s_31dVvHE
  
Si se ejecuta correctamente debera aparecer un mensaje como este:

```
    .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.7.3)

  
  2022-09-09 01:10:56.828  WARN 9944 --- [           main] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
2022-09-09 01:10:58.200  INFO 9944 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2022-09-09 01:10:58.212  INFO 9944 --- [           main] c.a.alianzapp.AlianzappApplication       : Started AlianzappApplication in 5.877 seconds (JVM running for 6.303)
2022-09-09 01:11:18.453  INFO 9944 --- [nio-8080-exec-6] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2022-09-09 01:11:18.453  INFO 9944 --- [nio-8080-exec-6] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2022-09-09 01:11:18.454  INFO 9944 --- [nio-8080-exec-6] o.s.web.servlet.DispatcherServlet        : Completed initialization in 1 ms
2022-09-09 01:11:19.134  INFO 9944 --- [nio-8080-exec-8] o.springdoc.api.AbstractOpenApiResource  : Init duration for springdoc-openapi is: 221 ms
2022-09-09 01:14:20.385  INFO 9944 --- [ionShutdownHook] j.LocalContainerEntityManagerFactoryBean : Closing JPA EntityManagerFactory for persistence unit 'default'
2022-09-09 01:14:20.386  INFO 9944 --- [ionShutdownHook] .SchemaDropperImpl$DelayedDropActionImpl : HHH000477: Starting delayed evictData of schema as part of SessionFactory shut-down'
2022-09-09 01:14:20.388  INFO 9944 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : my-mutants-db - Shutdown initiated...
2022-09-09 01:14:20.390  INFO 9944 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : my-mutants-db - Shutdown completed.
```
Por ultimo validar los siguientes enlaces:

```
* [database](http://localhost:8080/h2-console)
* [servicios-web](http://localhost:8080/swagger-ui/index.html)
* [calidad-del-codigo](http://localhost:63342/alianzapp/build/reports/jacoco/test/html/index.html?_ijt=ep1ioiuhajnhbj5kkb1jp4r5h1)
* [calidad-del-codigo](http://localhost:63342/alianzapp/build/reports/tests/test/index.html?_ijt=ep1ioiuhajnhbj5kkb1jp4r5h1)
```






