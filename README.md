# EPayment #
Epayment System Similar to Fawry System include services with form data which can be extended , discounts can be added based on category , many ways to pay credit card , wallet and cash on delivery , system list all users transactions , user can make refund request in any complete transactions.
## Build details ##
Project was built with java 19 with the help of IntelliJ Enterprise 2022.3 and Spring boot 2.7.8( Snapshot ) to help exposing it as RESTful API.
## How to clone the Project ##
You can clone it easily with Intellij or VScode make sure you have spplication server like Apache server Tomcat and import project as maven project.
## Error message ##
If there was something wrong with your request that is different from the logic of the application, you will receive error message like this
```json
{
    "status": false,
    "message": "error details",
    "object": null
}
```
## Endpoints ##

### Registration ###

POST `/sign-up`

Register new customer.

The request body needs to be in JSON format and include the following properties:

 - `email` - String - Required
 - `username` - String - Required
 - `password` - String - Required

Example
```
POST /sign-up/
{
    "email" : "mazen@lgh.com",
    "username" : "mazen",
    "password" : "123456"
}
```

