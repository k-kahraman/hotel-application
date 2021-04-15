# Hotel Reservation App
- Everything you see inside the Application written in pure Java and Designed by me.
- You can check out JavaDocs generated for documentation.

## Main Components

There are three major components in this project: 

1. CLI for the User Interface. I used the Command Line Interface or CLI for the user interface. The user can enter commands to search for available rooms, book rooms, and so on.
2. Java code. The second main component is the Java code itself—this is where I added business logic for the app.
3. Java collections. Finally, I used Java collections for in-memory storage of the data we need for the app, such as the users' names, room availability, and so on.

## Application Architecture
Applications architecture is also designed and written by me.

I used layering for code maintainability and to make it more human readable. The app will be separated into the following layers:

- User interface (UI), including a main menu for the users who want to book a room, and an admin menu for administrative functions.
- AppManager to take user inputs from UI (CLI) and parse them to wanted/required type.
- Resources will act as our Application Programming Interface (API) to our UI.
- Services will communicate with our resources, and each other, to build the business logic necessary to provide feedback to our UI.
- Data models will be used to represent the domain that we're using within the system (e.g., rooms, reservations, and customers).

###### Hotel Reservation Application created and designed entirely by Kaan Kahrama.
