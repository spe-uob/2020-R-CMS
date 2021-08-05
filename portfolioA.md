# Portfolio A

## Overview 
The aim of the project is to create a simple Content Management System (CMS) that manages information about the different projects within the Software Engineering Project unit hosted by the University of Bristol’s Computer Science Department. This information includes all the project data, such as client name and contact, project title, project brief, group members, and the URL of the GitHub repository for the project. Our vision is to create a system that will be part of new tooling that provides easy access to information about all the SPE projects. 
Our client is Dr Dan Schien, one of the unit directors of the SPE unit. With the help of our system he will be able to track down and find specific details about each project easier. 
Some core objectives of our system are to have an accessible web user interface where the client is able to view and edit the data of individual projects, as well as upload information about any new projects, a usable API that serves read requests from other systems, and database functionality for holding and interrogating structured representations of each project involved with the system.


## Requirements
A simple content management system that contains the project data, such as client name, and contact, project title and brief, group members, github repository URL that provides an API to read and write the data.

### user stories:
#### SPEAC

#### Clients

#### MS Forms Users

### Functional requirements
|  | Description|
| :--- | :--- |
|	1|Authenticated API for other services |
| 1.1|getAllProjects|
|1.2|getProjectDetails|
|1.3|getProjectName|
|1.4|getClientName|
|1.5|getClientEmail|
|1.6|getProjectURL|
|1.7|table/tables|
|	2|A web based UI to edit data|
|2.1|users can log into the web in seconds|
|2.2|users can input and save personal information|
|	2.3|The data is easily editable|
| 2.4|The webpage could add and remove columns|
| 2.5|New columns must not break the API |
|2.6| users can use this web appication on all browsers|
|2.7| users can creat password|
|	3|Import of excel workbooks from MS Forms|
|3.1| users could easily import Excel with eye-catching functional area|
|3.2| users can import excel into CMS in seconds|
|4| Export into Excel|
|4.1| users can export data to Excel easily|
|4.2|users could easily export data with eye-catching functional area|
### Non-functional requirements


## Personal Data, Privacy, Security and Ethics Management
In the CMS project, personal data (name, email etc.) will be collected for sorting files. 
## Architecture


## Development Testing
jUNIT
| Function |Explaination| Example|
| :---         |     :---:      |          ---: |
|	Authenticated API for other services |||
| A web based UI to edit data |DB schema needs to be easily modifiable, by adding and removing columns||
|	Import/Export |Project data initially is provided by clients via an MS Form. MS Form provides easy exports to Excel. This excel data will be imported into the CMS||


## Reflection
