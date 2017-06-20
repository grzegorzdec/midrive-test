# miDrive Front End Exercise

Build a simple web interface to display the data in the `data.json` file in the root of this repository.

At miDrive we use the following technologies to build our frontend systems.

- React
- Redux
- Webpack

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
Include the `data.json` file directly in your project. You don't need to use a database.

Your interface should display the lesson data in a sensible format. Use your experience and creativity to guide your design process.

**Some nice to have features**
- Display lesson route information on a map
- Filtering options

If you have any other ideas for features feel free to add them.

## Submission
Submit via private repo or project archive.
