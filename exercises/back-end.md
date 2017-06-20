# miDrive Back End Exercise

Build a simple REST API to serve the data contained within the `data.json` file in the root of this repository.

At miDrive we use the following technologies to build our backend systems.

- NodeJS
- PostgreSQL
- Express

However you may use any technology you are comfortable with.

## Data
The `data.json` file contains 25 random lesson objects describing driving lessons in various states.
Below is a outline of the properties present on each lesson object.

|Property  |Type       |Description|
|----------|-----------|-----------|
|location  |String     |Human readable location name|
|status    |Enum:String (incomplete, complete, cancelled)|The current state of the lesson|
|startDate |Date       |ISO8601 representation of a lessons start date|
|route     |GeoJSON    |GeoJSON representation of the route driven on a lesson|

## Spec
Implement as many of these endpoints as possible

### `GET` /lessons
Retrieve a list of all lessons with routes omitted
### `GET` /lessons/:status
Retrieve a list of all lessons with a specific status
### `GET` /lesson/:id
Retrieve a specific lesson with the route present if available. Also provide some additional statistics about the lesson for example (Distance driven, Time till/since lesson)

### `ANY` Additional
If you still have time or have some ideas of your own, add them in as well.

## Submission

Submit via private repo or project archive.
