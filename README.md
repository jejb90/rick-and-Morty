# rick-and-Morty
## API Documentation

## Database Information

This API uses the H2 database (`org.h2.Driver`) to store and manage its data.

## Endpoints

## Get Characters

### Endpoint
`GET /api/characters`

Retrieve a list of characters.

#### Parameters

- `name` (optional): Filter characters by name.
- `gender` (optional): Filter characters by gender.
- `status` (optional): Filter characters by status.
- `page` (optional): Page number for pagination.

#### Example Request
GET /api/characters?name=Rick&gender=Male&page=1

#### Example Response
HTTP/1.1 200 OK
Content-Type: application/json
```json
[
{
"id": 1,
"name": "Rick Sanchez",
"gender": "Male",
"status": "Alive"
},
{
"id": 2,
"name": "Morty Smith",
"gender": "Male",
"status": "Alive"
}
]
```
## Save Character
### Endpoint
`POST /api/characters`

Create a new character.

#### Request Body

```json
{
  "name": "Cowboy Morty",
  "gender": "Male",
  "status": "Alive",
  "image": "https://rickandmortyapi.com/api/character/avatar/77.jpeg"
}
```
name (required): Name of the character.
gender (required): Gender of the character.
status (required): Status of the character.
image (required): Image URL of the character.
Example Request

POST /api/characters
```json
{
  "name": "Cowboy Morty",
  "gender": "Male",
  "status": "Alive",
  "image": "https://rickandmortyapi.com/api/character/avatar/77.jpeg"
}
```
Example Response

HTTP/1.1 201 Created
Content-Type: application/json
```json
{
  "id": 77,
  "name": "Cowboy Morty",
  "gender": "Male",
  "status": "Alive",
  "image": "https://rickandmortyapi.com/api/character/avatar/77.jpeg"
}
```
## Get Logs

### Endpoint
`GET /api/logs`

Retrieve system logs.

#### Example Request
GET /api/logs


#### Example Response
HTTP/1.1 200 OK
Content-Type: application/json
```json
[
{
"id": 1,
"timestamp": "2023-08-07T21:03:29.731187",
"method": "GET",
"url": "/api/characters/search/",
"requestBody": "",
"responseBody": null,
"error": false,
"errorMessage": null
},
{
"id": 2,
"timestamp": "2023-08-07T21:03:30.611509",
"method": "GET",
"url": "/api/characters/search/",
"requestBody": null,
"responseBody": null,
"error": false,
"errorMessage": null
}
]
```