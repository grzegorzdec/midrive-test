# Android Exercise

In the root of this repo you will find an Android folder, which contains a barebones version of our Learner app. This currently consists of a welcome and login screen. The login screen is functional, and once you log in the user object and auth token will be stored for future API requests.

The app is using [Retrofit](https://square.github.io/retrofit/) for networking and [Realm](https://realm.io/docs/java/latest/) for data storage.

You can log in with:

Username: `dev+techtest@midrive.com`

Password: `techtest1234`

## The Test
After login, we would like you to bring the user to a screen which shows all of their driving lessons.

To achieve this, you will need to:
- Send a GET request to `https://midrive-api-staging.herokuapp.com/learner/lessons`. This will return an array of lessons for that learner.
- Store those lessons in Realm.
- Display all the lessons in a sensible format which shows the start date, start time, duration and status of the lesson.
- Add some style to this screen using your previous experience and creativity to guide you. There is a `colors.xml` file which contains all the Midrive style colours.
- Add pull to refresh on the list of lessons
- Split the lessons into `Previous` and `Upcoming`

### Bonus

If a lesson is tapped on, send the user to a screen which displays the lesson in greater detail.

If the lesson status is incomplete:
- Show a map with the start location
- Display the start location name

If the lesson status is complete:
- Show a map with the lesson route (if there is one) as a polyline with the start and end points marked
- Display the generated notes for the lesson
- Display the notes left by the instructor

## Data

Here is an outline of each lesson object:

|Property  |Type       |Description|
|----------|-----------|-----------|
|location  |String     |Human readable location name|
|status    |Enum:String (incomplete, complete, cancelled)|The current state of the lesson|
|startDate |Date       |ISO8601 representation of a lessons start date|
|geo       |GeoJSON    |An object that contains the coordinates of the start location of the lesson in the form `[lon, lat]`
|route2    |GeoJSON    |GeoJSON representation of the route driven on a lesson. Each coordinate is in the form `[lon, lat]`|
|thisLessonNotes|String|A string which contains some generated notes about the lesson|
|nextLessonNotes|String|A string which contains notes from the instructor about the learner's performance in the lesson|

## Submission

Submit the project in a private repo or in an archive.
