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

# User

### Sign in ###

 POST `/sign-in`

Sign in.

The request body needs to be in JSON format and include the following properties:

 - `email` - String - Required
 - `password` - String - Required

Example
```
POST /sign-in/
{
    "email" : "admin@epay.com",
    "password" : "admin"
}
```

# Customer

### Sign up ###

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

### Add card ### 

Allow you to add credit card.

POST `/customer/add-credit-card`

The request body needs to be in JSON format and include the following properties:

 - `name` - String - Required
 - `number` - String - Required
 - `password` - String - Required

Example
```
POST customer/add-credit-card/
{
    "name" : "Visa" ,
    "number" : "411117" ,
    "password" : "1234"
}
```

### Get cards ###

Allow you to see all the credit cards.

 GET `/customer/show-cards`

### Charge Wallet ###

Allow you to charge your wallet using credit card.

 PUT `/customer/charge-wallet`

The request body needs to be in JSON format and include the following properties:

 - `amount` - Double - Required
 - `cardId` - Integer  - Required
 - `password` - String - Required

Example
```
PUT /customer/charge-wallet
{
    "amount" : 150 ,
    "cardId" : 1 ,
    "password" : "1234"
}
```

### Get Wallet ###

Allow you to see all the credit cards.

 GET `/customer/show-wallet`

### Serch for service ###

Returns a list of matched service.

 GET `/customer/search?q={query}`

The request Parameters needs to include the following prameters:

 - `q` - String - Required

Example
```
GET /customer/search?q=mobile
```

### Get services ###

Allow you to see all services.

 GET `/customer/show-services`

### Get service ###

Allow you to see details for specific service

 GET `/customer/show-service/?id={id}`

The request Parameters needs to include the following prameters:

 - `id` - Integer - Required

Example
```
GET /customer/show-service/?id=1
```
Response Example
```json
{
    "status": true,
    "message": "",
    "object": {
        "container": {
            "supported payments": [
                {
                    "id": 1,
                    "name": "Credit Card"
                },
                {
                    "id": 2,
                    "name": "Wallet"
                }
            ],
            "form": {
                "fields": {
                    "Mobile number": [],
                    "Amount": []
                }
            },
            "name": "Etisalat Mobile Recharge",
            "id": 1
        }
    }
}
```
## Use service

POST `/customer/use-service`

The request body needs to be in JSON format and include the following properties:

 - `serviceId` - Integer  - Required
 - `paymentId` - Integer  - Required
 - `cardId` - Integer  - Optional
 - `password` - String  - Optional
 - `fields` - String - Required

### Pay with credit card ###

Example
```
POST /customer/use-service
{
    "serviceId" : 1,
    "paymentId" :1,
    "cardId" : 1,
    "password" : "1234",
    "Mobile number" : "0111",
    "Amount" : "50"
}
```

### Pay with Wallet ###

Example
```
POST /customer/use-service
{
    "serviceId" : 2,
    "paymentId" : 2,
    "Mobile number" : "0100",
    "Amount" : "72.5"
}
```

### Pay with cash ###

Example
```
POST /customer/use-service
{
    "serviceId" : 12,
    "paymentId" : 3,
    "Address" : "Maddi",
    "Mobile number" : "0111",
    "Monthly Donation" : "2"
}
```

### Get discounts ### 

Allow you to see all discounts.

 GET `/customer/show-discounts`

### Get transactions ### 

Allow you to see all transactions.

 GET `/customer/show-transactions`

### Apply refund ### 

Allow you to submit refund request for some transaction

 POST `/customer/apply-refund?id={id}`

The request Parameters needs to include the following prameters:

 - `id` - Integer - Required

Example
```
POST `/customer/apply-refund?id=1`
```

### Get refunds ### 

Allow you to see all refunds.

 GET `/customer/show-refunds`



 






