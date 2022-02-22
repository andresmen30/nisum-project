# nisum-project
Nisum project  - Api Rest

La implementación del proyecto se llevo acabo con Java 8, junto con el framework Sprint boot, también se utiliza la estructura de Base de datos H2, todo bajo la arquitectura hexagonal.

## Tecnologias

### Java 8
### Spring Boot
### JUnit
### Mockito
### Tomcat
### Base de datos H2
### Intellij

## Diagrama

![diagrama architecture hexagonal](https://user-images.githubusercontent.com/18649868/155082595-8e0e7eaf-a4e8-4419-98c3-d3ef60ee3413.png)



## Consumo Servicio /nisum-project

### url: http://localhost:7071/nisum-project/

## Endpoints

### POST /user

Este método se utiliza para la creación de un usuario
El request esta compuesto por la información basica del usuario

El response contiene información sobre fechas de actualización y creación de usuario e información de credenciales.

En dado caso de que falle por mandatoriedad de campos o validación de los mismos lanzara un error.


#### Request exitoso creación de usuario
```
{
   "name":"Andres Mendez",
   "email":"andresmen30@gmail.com",
   "password":"123",
   "phones":[
      {
         "number":"1234567",
         "cityCode":"1",
         "countryCode":"57"
      },
      {
         "number":"3123123",
         "cityCode":"2",
         "countryCode":"54"
      },
      {
         "number":"4234324",
         "cityCode":"2",
         "countryCode":"43"
      }
   ]
}
```

#### Response exitoso creación de usuario
```
{
    "userId": 1,
    "created": "2022-02-21T22:03:42.104",
    "modified": null,
    "lastLogin": "2022-02-21T22:03:42.104",
    "token": "a4053cfa-fddb-40d9-bc30-71d0cd013418",
    "active": true
}
```


#### Request validación mandatoriedad de campos
```
{
   "name":"Andres Mendez",
   "email":"",
   "password":"",
   "phones":[
      {
         "number":"1234567",
         "cityCode":"1",
         "countryCode":"57"
      },
      {
         "number":"3123123",
         "cityCode":"2",
         "countryCode":"54"
      },
      {
         "number":"4234324",
         "cityCode":"2",
         "countryCode":"43"
      }
   ]
}
```

#### Response validación mandatoriedad de campos
```
{
    "code": 400,
    "message": "Validation error",
    "details": [
        "email : no debe estar vacío",
        "password : no debe estar vacío"
    ]
}
```

#### Request validación correo ya existente
```
{
   "name":"Reclutador nisum",
   "email":"nisum@gmail.com",
   "password":"123",
   "phones":[
      {
         "number":"1234567",
         "cityCode":"1",
         "countryCode":"57"
      },
      {
         "number":"3123123",
         "cityCode":"2",
         "countryCode":"54"
      },
      {
         "number":"4234324",
         "cityCode":"2",
         "countryCode":"43"
      }
   ]
}
```

#### Response Error Formato invalido
```
{
    "code": 409,
    "message": "Validation error",
    "details": [
        "El correo ya registrado"
    ]
}
```

### PUT /user/{id}

Este método se utiliza para la actualización de un usuario
El request esta compuesto por la información basica del usuario y en el path ira el {id} para identificar el usuario a actualizar

El response contiene información sobre fechas de actualización y creación de usuario e información de credenciales.

En dado caso de que falle por mandatoriedad de campos, usuario ya existente o validación de los mismos lanzara un error.


#### Request exitoso actualización de usuario
```
{
   "name":"Reclutador nisum",
   "email":"prueba.correo@gmail.com",
   "password":"123",
   "phones":[
      {
         "number":"3108841501",
         "cityCode":"1",
         "countryCode":"57"
      },
      {
         "number":"3123123",
         "cityCode":"2",
         "countryCode":"54"
      }
   ]
}
```

#### Request exitoso actualización de usuario
```
{
    "userId": 1,
    "created": "2022-02-21T22:03:42.104",
    "modified": "2022-02-22T01:10:20.09",
    "lastLogin": "2022-02-21T22:03:42.104",
    "token": "a4053cfa-fddb-40d9-bc30-71d0cd013418",
    "active": true
}
```

#### Response no exitoso actualización de usuario
```
{
    "code": 409,
    "message": "Validation error",
    "details": [
        "El correo ya registrado"
    ]
}
```

### GET /user

Este método se utiliza para obtener todos los usuarios registrados


#### Response exitoso todos los usuarios registrados
```
[
    {
        "userId": 1,
        "name": "Reclutador nisum",
        "email": "prueba.correo@gmail.com",
        "password": "123",
        "created": "2022-02-21T22:03:42.104",
        "modified": "2022-02-22T01:12:11.599",
        "lastLogin": "2022-02-21T22:03:42.104",
        "token": "a4053cfa-fddb-40d9-bc30-71d0cd013418",
        "phones": [
            {
                "phoneId": 13,
                "number": "3108841501",
                "cityCode": "1",
                "countryCode": "57"
            },
            {
                "phoneId": 14,
                "number": "3123123",
                "cityCode": "2",
                "countryCode": "54"
            }
        ],
        "active": true
    },
    {
        "userId": 5,
        "name": "Reclutador nisum",
        "email": "nisum@gmail.com",
        "password": "123",
        "created": "2022-02-22T00:27:54.597",
        "modified": null,
        "lastLogin": "2022-02-22T00:27:54.597",
        "token": "b0b1a6db-085a-4afd-af99-397f4e0911c6",
        "phones": [
            {
                "phoneId": 6,
                "number": "1234567",
                "cityCode": "1",
                "countryCode": "57"
            },
            {
                "phoneId": 7,
                "number": "3123123",
                "cityCode": "2",
                "countryCode": "54"
            },
            {
                "phoneId": 8,
                "number": "4234324",
                "cityCode": "2",
                "countryCode": "43"
            }
        ],
        "active": true
    }
]
```

### GET /user/{id}

Este método se utiliza para obtener los usuarios por {id}


#### Response exitoso usuario por {id}
```
[
    {
        "userId": 1,
        "name": "Reclutador nisum",
        "email": "prueba.correo@gmail.com",
        "password": "123",
        "created": "2022-02-21T22:03:42.104",
        "modified": "2022-02-22T01:12:11.599",
        "lastLogin": "2022-02-21T22:03:42.104",
        "token": "a4053cfa-fddb-40d9-bc30-71d0cd013418",
        "phones": [
            {
                "phoneId": 13,
                "number": "3108841501",
                "cityCode": "1",
                "countryCode": "57"
            },
            {
                "phoneId": 14,
                "number": "3123123",
                "cityCode": "2",
                "countryCode": "54"
            }
        ],
        "active": true
    }
]
```

### DELETE /user/{id}

Este método se utiliza para eliminar usuarios por {id}



#### Response exitoso usuario eliminado por {id}
```
{
    "code": 200,
    "message": "Se ha eliminado existosamente"
}
```




## Compilación del proyecto

##### Clonar del repositorio
```
git clone https://github.com/andresmen30/nisum-project.git
```


##### Compilar

Ubíquese en la ruta /nisum-project
```
mvn clean install
```

##### Desplegar

Ubíquese en la ruta /nisum-project/target y ejecute:
```
java -jar nisum-project-v0.0.1-SNAPSHOT.jar
```

###### Documentación [https://documenter.getpostman.com/view/8454301/UV5aeF7C](https://documenter.getpostman.com/view/8454301/UVkmRHEc).

















