# Levantar la API

Primero, nos movemos al fichero donde esta la API

    cd ..\..\IdeaProjects\constratista

Para poder empaquetar la aplicacion en un archivo ejecutable (jarro o archivo de guerra) se
construye la aplicación
usando el comando de compilación

    gradlew clean build

Como resultado, el archivo ejecutable generado se colocará en el directorio de construcción/libs.

Después de construir la aplicación, podemos ejecutarla usando el comando java -jar en el archivo de
tarro ejecutable
generado.

    java -jar build/libs/constratista-0.0.1-SNAPSHOT.jar