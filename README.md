# PA165 team project - D&D Troops

A Java EE school team project.

Team members: 
* Boris Jaduš
* Ján Válka
* Alena Bednaříková
* Michal Čaniga

Wiki: https://github.com/445343/PA165-DnDProject/wiki

# Running the project
* You need two command line instances 
    * First for server (REST)
    * Second for client

* Server (first terminal):
    * from root of project: `mvn clean install`
    * go to server folder: `cd rest` 
    * run server: `mvn cargo:run`
    
 * Client (second terminal):
     * go to client folder: `cd web-app` 
     * install client: `npm install`
     * install angular-cli: `npm install -g @angular/cli`
     * run client: `ng serve --open` 

# Prepopulating data
* Client fetches dummy data from server automatically from start of app

# Login Credentials 
* Admin account 
    * Username: admin
    * Password: admin

* Regular account 1
    * Username: user1
    * Password: user1
    
* Regular account 2
    * Username: user2
    * Password: user2 

# Permissions
* Admin
    * Can do everything except delete other admins
    
* Regular user
    * See dashboard
    * Create and manage his heroes
    * See roles (can't modify)
    * See troops with related heroes (can't modify)
    * Logout

# Notes:
* Server is running on port 8080, client on 4200: Ensure that you freed these ports!
* For client to work, client and server must run simultaneously
* Ensure that you have `npm` installed, if not: [Download it here](https://nodejs.org/en/download/)  