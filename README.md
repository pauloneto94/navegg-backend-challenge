# Navegg Back-end Challenge API

CRUD Rest Api implemented using spring-boot Maven Project.


## How to Run
### Locally
-   Download the zip or clone the Git repository.
-   Unzip the zip file (if you downloaded one)
-   Open Command Prompt and Change directory (cd) to folder containing pom.xml
 - Run the service by using the following command:

    `./mvnw spring-boot:run`


### Web

 - Access [https://navegg-challenge.herokuapp.com/](https://navegg-challenge.herokuapp.com/)

## Rest Api Endpoints

### List all active register

    GET localhost:8080/item/
    Accept: application/json
    Content-Type: application/json
    
### List one register

    GET localhost:8080/item/[id]
    Accept: application/json
    Content-Type: application/json
	 
    
    
### Create new register

    POST localhost:8080/item/
    Accept: application/json
    Content-Type: application/json
	{
	"name": "Site Name",
	"urlList":[{
			"url": "url string"
			}],
	"categoryList":[{
			"category": "category string"
			}],
	"status": "status"				
	}
    
### Modify register

    PATCH localhost:8080/item/[id]
    Accept: application/json
    Content-Type: application/json
	{
	"id": "id",
	"name": "Site Name",
	"urlList":[{
			"url": "url string"
			}],
	"categoryList":[{
			"category": "category string"
			}],
	"status": "status"				
	}

### Delete register

    DELETE localhost:8080/item/[id]
    Accept: application/json
    Content-Type: application/json

## Time to get done

	About 10h coding

## To Add/Change

- Add more endpoints like get by name.
- Add Angular front-end.
- Change status to boolean