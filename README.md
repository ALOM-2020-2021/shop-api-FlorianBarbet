# shop-api

## Publishing url

You could find this API on clevercloud with this url : https://shop-api-florian-barbet.cleverapps.io/


# Lancement du projet
* Run with maven 


Run into project root : ``mvn spring-boot:run``


* Run with jar :

Run into project root : ``mvn clean package install``

then go to target directory and ``java -jar shop-api-0.1.0.jar``

# Properties & Configuration

This project run with spring cloud configurator.
It will use a remote API to take his configuration.

## Change remote configuration server
Actually you could find project configuration on https://configuration-api-florian-barbet.cleverapps.io/shop-api/

You can setup your own remote configuration with a `bootstrap.properties` in `src/main/resources` there you'll can change properties as you wish, e.g :
* spring.cloud.config.uri=https://configuration-api-florian-barbet.cleverapps.io/
* spring.application.name=shop-api

## Properties to run project
Base file :
```
server.port = ${SERVER_PORT_SHOP:8085}
trainer.service.url=${TRAINER_API_URL:http://localhost:8081}
spring.jpa.hibernate.ddl-auto = ${DDL_AUTO:update}

#### Security ####
trainer.service.username=
trainer.service.password=

#### RABBIT MQ ####
spring.rabbitmq.host=
spring.rabbitmq.virtual-host=
spring.rabbitmq.username=
spring.rabbitmq.password=

trainer.api.exchange.name=trainer-api-exchange
shop.api.exchange.name=shop-api-exchange

#### DATABASE ####
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=
spring.datasource.hikari.maximum-pool-size=1

#### POKEBALL CONF (Don't touch if you don't know what you do)####
pokemon.masterballs.id = 144,145,146,150,151
pokemon.hyperballs.id = 147,58,74,95,77,37,109,27,126,63,25,125,66,88,111,100,108,123,127,114,138,140
pokemon.superballs.id = 35,32,29,23,104,118,60,90,39,81,92,102,79,54,124,120,72,132
pokemon.pokeballs.id = 10,13,16,19,41,133,48,43,129,96,52,21,69,46,98,116
pokemon.starters.id = 1,4,7
```
/!\ WARNING /!\

don't change these following properties, 
it could break all your MS Architecture : `trainer.api.exchange.name=trainer-api-exchange`                                                                                 
`shop.api.exchange.name=shop-api-exchange` ; thank you 

/!\ WARNING /!\                                                                                                    

For security reasons I've delete some properties on this base file, first step is to
 change it with your own configuration.

Don't panic I help you, start with ... / stand for ... : 
* trainer.service / security authentication on trainer-api 
which implements Spring Security Configuration !
* spring.datasource / your database information 
to plug your own database ( it should be the same as trainer-api )
* spring.rabbitmq / your RabbitMQ information, 
this project use some messaging queues, it should be the same for each API plug into shop-api

You've seen some props are like : ${SOMETHING:...} it stands for there is an Environment variable 
named as SOMETHING ( in this case ) if you can't find it will use : "..."

Environments variables are : 
* SERVER_PORT_SHOP it's the server port to run shop.
* TRAINER_API_URL it's the URL of your trainer-api.
* DDL_AUTO it could be fill in as "none","update","create-drop" ; 
I let you find yourself what means each configuration !

### Help with AMQP configuration
````
spring.rabbitmq.host=
spring.rabbitmq.virtual-host=
spring.rabbitmq.username=
spring.rabbitmq.password=
```` 
Fill in your host, if you run you RabbitMQ in local it could be `localhost`
If you are in local context you should pass a port so add `spring.rabbitmq.port=` 
and fill in with your RabbitMQ port (by default it's `5672`)

Fill in your virtual-host ( it's a facultative property but if you are 
in vhost context you should read this : https://www.rabbitmq.com/vhosts.html )

At the end, fill in your RabbitMQ username and password.

### Pokeball configuration
```` 
pokemon.masterballs.id = 144,145,146,150,151
pokemon.hyperballs.id = 147,58,74,95,77,37,109,27,126,63,25,125,66,88,111,100,108,123,127,114,138,140
pokemon.superballs.id = 35,32,29,23,104,118,60,90,39,81,92,102,79,54,124,120,72,132
pokemon.pokeballs.id = 10,13,16,19,41,133,48,43,129,96,52,21,69,46,98,116
pokemon.starters.id = 1,4,7 
````
pokemon.XXX.id : if you wish you could change the list of pokemon id 
you could obtain into a XXX ( XXX could be masterballs, superballs, hyperballs, pokeballs or starter )
these properties are important to run this API you have to put them all ! they will be use to instance a singleton 
factory which will produce a List of PokemonId that are required to randomize a selection of pokemon ! 
( Don't touch if you don't know what it does ) !