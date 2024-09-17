# Carrito de compras en kotlin

Aplicación de consola sobre un carrito de compras hecha en kotlin

## Integrantes

- Alas Linares Alejandro Antonio AL192188
- De Paz Velásquez Vicente Daniel DV192307
- Duran Meléndez Gilberto Emmanuel DM192201
- Gutiérrez Borja Rafael Armando GB192205

## Intrucciones de ejecución

Para poder ejecutar el proyecto se debe tener instaladas las siguientes herramientas: `java`, `kotlin` y `gradle`.
Estas herramientas pueden ser instaladas fácilmente en sistemas **UNIX** utilizando [SDKMan](https://sdkman.io/).

Si se está usando un sistema UNIX se puede ejecutar el script bash que se encuentra en el `root` del proyecto para ejecutar el proyecto de manera sencilla:

```bash
sh dev.sh
```

Si no, también se puede ejecutar de la siguiente manera:

- Compilar el archivo jar con el comando:

```bash
./gradlew clean shadowJar
```

- Se creará un archivo fatjar dentro del directorio `build` que se puede ejecutar con java:

```bash
java -jar ./app/build/libs/app-all.jar
```

## Instrucciones de desarrollo

Los archivos utilizados por el programa se encuentran en la ruta `app/src/main/kotlin/org/example`, allí se pueden crear todos los archivos necesarios como clases o funciones y son automáticamente importados en los demás archivos para ser usados.

El archivo principal se llama `App.kt`.