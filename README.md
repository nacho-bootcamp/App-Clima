# Weather API - Technical Test for Backend Developer

## Descripcion del proyecto

Este proyecto constituye una API que proporciona datos meteorológicos mediante la integración con [OpenWeatherMap](https://openweathermap.org/). Su funcionalidad incluye la obtención de información meteorológica en tiempo real y pronósticos para ciudades específicas. Destaca por las siguientes características:

- Cache de Respuestas: Implementa un sistema de caché para optimizar el rendimiento, permitiendo respuestas más rápidas al almacenar y recuperar datos previamente solicitados. Esto mejora la experiencia del usuario al reducir los tiempos de espera para consultas recurrentes.

- Limitación de Peticiones: Se ha establecido un límite de 50 peticiones por hora para gestionar el acceso y garantizar un uso responsable de la API. Esta medida contribuye a mantener la estabilidad del servicio y prevenir posibles abusos.

- Integración con OpenWeatherMap: Utiliza la plataforma OpenWeatherMap para garantizar la calidad y precisión de los datos meteorológicos proporcionados. Esta integración ofrece una amplia gama de información, desde la temperatura actual hasta pronósticos a futuro.

## Requisitos Previos

### Asegurarse de tener instalado:

- [Docker](https://www.docker.com/products/docker-desktop/)
- [Java Development(JDK)](https://www.oracle.com/ar/java/technologies/downloads/)
- [Postman](https://www.postman.com/downloads/)

## Endpoints Disponibles

#### La aplicacion ofrece los siguientes endpoints para la gestion de usuario , autenticacion y peticiones a la API de Weather

## Registro de Usuario (POST)

### Ruta: `/api/auth/signup`

**Ejemplo de Cuerpo de Solicitud:**

```json
{
  "email": "tuEmail@Example.com",
  "password": "tuContraseña",
  "name": "TuNombre",
  "lastName": "TuApellido"
};
```

## Autenticacion de Usuarios(POST)

### Ruta:`/api/auth/signin`

**Ejemplo de Cuerpo de Solicitud:**

```json
{
  "email": "tuEmail@Example.com",
  "password": "tuContraseña",
};
```

**Respuesta Exitosa:**

```json
{
  "token": "TuTokenDeAcceso",
  "expiresIn": "xxxx"
};
```

## Obtener Informacion Meteorológica Actual (GET)

### Ruta: `/api/weather/current`

### Parametro de consulta:

- `cityName`: Nombre de la ciudad para la cual se desea obtener la informacion meteorológica.

### Respuesta Exitosa:

```json
"name": "Uruguay",
    "main": {
        "temp": 288.82,
        "humidity": 64
    },
    "wind": {
        "speed": 1.43
    },
    "weather": [
        {
            "description": "few clouds"
        }
    ]
```

## Obtener Pronostico del Tiempo de 5 días para una Ciudad Designada(GET)

### Ruta: `/api/weather/forecast`

### Parametro de consulta:

- `cityName`: Nombre de la ciudad para la cual se desea obtener el pronóstico del tiempo.

### Respuesta Exitosa:

```json
  "main": {
                "temp": 282.58,
                "feelsLike": 0.0,
                "pressure": 1008,
                "humidity": 90,
                "temp_min": 282.58,
                "temp_max": 282.6,
                "sea_level": 1008,
                },
                //  ...
                {"dt_txt": "2023-11-20 03:00:00",
                }
```

## Acceder a datos de contaminación del aire actual para una ciudad seleccionada (GET)

### Ruta: `/api/weather/air-pollution`

### Parametro de consulta:

- `Longitud y Latitud`: Longitud y Latitud de la ciudad para la cual se desea obtener la informacion actual de la contaminacion del aire.

### Respuesta Exitosa:

```json
"list": [
        {
            "main": {
                "aqi": 1
            },
            "components": {
                "co": 303.75,
                "no": 0.01,
                "no2": 15.08,
                "o3": 53.64,
                "so2": 1.42,
                "pm2_5": 1.87,
                "pm10": 3.38,
                "nh3": 0.93
            }
        }
    ]
```

## Pasos para Levantar el Proyecto

**Sigue estos pasos para levantar la aplicacion Sprint Boot y ejecutarla:**

#### Correr la aplicaciocion manualmente:

```bash
# Para construir y empaquetar la aplicación (omitir pruebas)
./mvnw clean package -DskipTests

# Para construir los contenedores de Docker
docker-compose build

# Para iniciar la aplicación y los servicios relacionados en Docker
docker-compose up
```

**Asegúrate de estar en el directorio raíz de tu proyecto Spring Boot al ejecutar estos comandos. Con estos comandos, podrás automatizar fácilmente la construcción y ejecución de tu aplicación Dockerizada.**
