# EPayment
Epayment System Similar to Fawry System include services with form data which can be extended , discounts can be added based on category , many ways to pay credit card , wallet and cash on delivery , system list all users transactions , user can make refund request in any complete transactions.
# Build deatails
Project was built with java 19 with the help of IntelliJ Enterprise 2022.3 and Spring boot 2.7.8( Snapshot ) to help exposing it as RESTful API.
# How to clone the Project
You can clone it easily with Intellij or VScode make sure you have spplication server like Apache server Tomcat and import project as maven project.
# Error Message
If there was something wrong with your request that is different from the logic of the application, you will receive error message like this
```json
{
    "status": false,
    "message": "error details",
    "object": null
}
```
# Registration
You need to send POST request, Application exept the request to have body like this

POST /sign-up

register the user if the username is uniqe
```json
{
    "email" : "email",
    "username" : "name",
    "password" : "password"
}
```

