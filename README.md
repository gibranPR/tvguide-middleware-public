# tvguide-middleware

API de guía televisiva. Recibe las peticiones del cliente, consulta [TV Maze](https://www.tvmaze.com/api) y responde un contrato propio. El detalle de un show se guarda en MongoDB. Los comentarios viven en otra colección y se adjuntan al responder.

## Requisitos

- Java 21
- Una base en MongoDB Atlas
- La cadena de conexión en la variable de entorno `MONGODB_URI`

La cadena lleva el nombre de la base antes del `?`:

```text
mongodb+srv://usuario:password@cluster.xxxxx.mongodb.net/tvguide?retryWrites=true&w=majority
```

Esa cadena no se guarda en el repositorio. `application.properties` solo tiene `spring.mongodb.uri=${MONGODB_URI}`.

## Arranque

Desde la raíz del proyecto, en la misma terminal:

```powershell
$env:MONGODB_URI = "tu-cadena-de-atlas"
.\gradlew.bat bootRun
```

La aplicación escucha en el puerto 8087.

## Endpoints

### Buscar shows

`GET /shows/search?search_query=girls`

Siempre consulta TV Maze. Después agrega los comentarios guardados para cada show.

```json
[
  {
    "id": 139,
    "name": "Girls",
    "channel": "HBO",
    "summary": "<p>...</p>",
    "genres": ["Drama", "Romance"],
    "comments": [
      { "comment": "Muy buena", "rating": 5 }
    ]
  }
]
```

`channel` sale de `network.name`. Si no hay network, sale de `webChannel.name`. Si un show no tiene comentarios, `comments` es `[]`.

### Detalle de un show

`GET /shows/139`

Si el id ya está en MongoDB, responde desde ahí. Si no está, consulta TV Maze, guarda el show y responde. Los comentarios se leen de MongoDB antes de devolver el resultado.

La respuesta es un solo objeto, con la misma forma que cada elemento de la búsqueda.

### Crear un comentario

`POST /comments`

```json
{
  "show_id": 139,
  "comment": "Muy buena",
  "rating": 5
}
```

`rating` va de 0 a 5. Si el cuerpo es válido, responde `201`:

```json
{ "status": 201 }
```

Si falta el comentario, falta `show_id` o `rating` está fuera de rango, responde `400` y no guarda nada.

```powershell
Invoke-RestMethod -Uri http://localhost:8087/comments -Method Post -ContentType "application/json" -Body '{"show_id":139,"comment":"Muy buena","rating":5}'
```

## Datos

| Colección   | Contenido                                      |
|-------------|------------------------------------------------|
| `shows`     | id, name, channel, summary, genres             |
| `comments`  | showId, comment, rating                        |

Los comentarios no se guardan dentro del documento del show.
