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

* Server (second terminal):
    * from root of project: `mvn clean install`
    * go to server folder: `cd rest` 
    * run server: `mvn cargo:run`
    
 * Client (first terminal):
     * go to client folder: `cd web-app` 
     * install client: `npm install`
     * install angular-cli: `npm install -g @angular/cli`
     * run client: `ng serve --open` 

# Prepopulating data
* In dashboard view, click on button to prepopulate data

# Notes:
* Server is running on port 8080, client on 4200: Ensure that you freed this ports!
* For client to work, client and server must run simultaneously
* Ensure that you have `npm` ingit stastalled, if not: [Download it here](https://nodejs.org/en/download/)  