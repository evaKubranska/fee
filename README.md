# RUN APPLICATION

1. `docker run -d -p 5672:5672 -p 15672:15672 --name my-rabbit rabbitmq:3-management`
2. `./gradlew bootRun`

# GET FEE VIA REST API

###  Request

get fee based on transaction type

`GET fee/transaction/A`


###  get total count of fee based on transaction types

`POST fee/transaction`

body 
``` 
{
  "trancationType": 1, (1 =  the number of executed transactions)
  "trancationTypeX": 2
}
```


## GET FEE VIA RABBITMQ

**Demonstration of messaging**

On application start messages are sent to rabbit mq broker.
Consumer can be found in com.kubranska.fees.listener.TransactionListener and reads messages from the RabbitMQ broker.
When the TransactionListener receive messages, fee is calculated and result is loged in:
``` 
2022-11-01 16:58:51.359  INFO 8032 --- [ntContainer#1-1] c.k.fees.listener.TransactionListener    : Received transaction type {"A":2,"B":1} , calculated fee is 40.0
2022-11-01 16:58:51.359  INFO 8032 --- [ntContainer#0-1] c.k.fees.listener.TransactionListener    : Received transaction type A , calculated fee is 10.0
```

### Database
 this application is using h2-console database. The table structure is automatically generated through flyway. 
 