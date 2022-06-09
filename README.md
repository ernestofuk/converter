# PROJECT NAME

Project_name: API Converter.

> Proyecto que se encarga de convertir arvhicos CSV a JSON.

Se tiene que subir un archivo CSV y su tipo de separación para poder convertir el archivo a JSON.

## Background

Para facilitar la conversión de archivos CSV a JSON.

## Usage
Clona el proyecto y despliega la API para comenzar a utilizarlo.

## API/Component

API Converter:

Se obtiene un archivo CSV y su tipo de separador, donde luego se obtiene los valores de la primera fila para obtener el nombre de los campos y se agregan a una lista, luego separamos las demás filas que vendrían a ser los valores de cada columna y se convierte en una estructura JSON.

## Installation

Prerequisitos:

- Instalar Maven.
- Instalar Java 8 o superior.

```shell
    # Clone or install commands
    git clone https://github.com/ernestofuk/converter/blob/master/LICENSE 
```

```shell
    # test
    mvn test
```

```shell
    # install
    mvn install
```

```shell
    # run the project after the above command
    java -jar target/converter-0.0.1-snapshot.jar
```

## License

Este proyecto está bajo la licencia
[MIT](https://github.com/ernestofuk/converter/blob/master/LICENSE)