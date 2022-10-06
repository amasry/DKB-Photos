# DKB-Photos

https://user-images.githubusercontent.com/6030554/194262322-21d07823-0e9d-4deb-821b-566fc09cfab5.mp4


Since the time is limited to 2 hours, I could only finish the first screen and add some unit tests to the `PhotosViewModel`. I decided to submit a fully functioning feature with good quality code and add some unit tests before adding more features to the app. Given more time it would be simple to add the missing screen either as it's own separate activity or do some refactoring to convert the current screen to a fragment and use a single activity pattern for this project.

### Here are some of improvements that I think should be made:  

 - Show a loading state before displaying the photos
 - Add unit tests to the repository and mapper classes
 - Add some espresso tests to make sure that the ui states are rendered correctly
 - Use kotlin in Gradle build files and extract the versions to a separate file

### Things that were soved well:

 - Choosing koin for dependency injection. This project is small, so koin is a better choice here because of it's simplicity and fast setup. However, for bigger projects and projects that require more scalability, I would choose Hilt instead.
 - Creating a working and testable code for the main screen.
