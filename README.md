**Microservices:**

**Commit Changes:**

1. _Before Cloud Config Server_ -> separate Configuration files for every microservices. All configurations are placed inside an application.yml files

2. _After Cloud Config Server_ -> I've added a "**Spring Cloud Config Server**" as a microservice and registered all other microservices as clients in the cloud-config server. 
Also, I've moved all configuration properties into my configuration server. We can have these properties in the classpath, filepath, or GitHub repo.

3. _File Based Config Properties System_ -> Here I've stored the configuration properties in the folder inside my system and read config files from that folder

4. _After Auto Config Properties System_ -> I've moved to a Github Repo Config Concept and also added "**Spring Cloud Bus**" So even if we change the properties file we do not need to restart the microservices
We need to restart the bus in that particular microservice. So we can dynamically change the properties without restarting microservices.

5. _After Bus Refresh Properties System_ -> I've used "**RabbitMQ**" for refreshing the properties file for all microservices if you refresh in any one of the services.
 I've installed and run the rabbitMQ inside docker in port _5672_. So using this rabbitMQ, if we refresh the bus in any of the microservices all microservices will be refreshed.
