**Microservices:**

**Notes**
  -- To connect to hookdeck CLI -> Go to /tmp folder and enter **hookdeck version** to confirm and then **hookdeck login** to login to the console (ps: I've moved the hookdeck folder to /opt/usr/local)
  -- hookdeck's URL -> https://console.hookdeck.com/

**Commit Changes:**

1. **_Before Cloud Config Server_** -> separate Configuration files for every microservices. All configurations are placed inside an application.yml files

2. **_After Cloud Config Server_** -> I've added a "**Spring Cloud Config Server**" as a microservice and registered all other microservices as clients in the cloud-config server. 
Also, I've moved all configuration properties into my configuration server. We can have these properties in the classpath, filepath, or GitHub repo.

3. **_File Based Config Properties System_** -> Here I've stored the configuration properties in the folder inside my system and read config files from that folder

4. **_After Auto Config Properties System_** -> I've moved to a Github Repo Config Concept and also added "**Spring Cloud Bus**" So even if we change the properties file we do not need to restart the microservices
We need to restart the bus in that particular microservice. So we can dynamically change the properties without restarting microservices.

5. **_After Bus Refresh Properties System_** -> I've used "**RabbitMQ**" for refreshing the properties file for all microservices if you refresh in any one of the services.
 I've installed and run the rabbitMQ inside docker in port _5672_. So using this rabbitMQ, all **microservices will be refreshed** if we refresh the bus in any microservices.

6. **_Docker for ConfigServer and Rabbitmq_** -> Started writing docker-compose.yml for config server and rabbitmq in the project. Added docker-compose folder for different environment configurations.
  
7. **_Updating DockerCompose Configuration_** -> I've updated the **docker-compose.yml** file and added a **common-config.yml** file for common configurations and environmental variables.
   Created docker images and pushed them through the docker hub.
   
8. **_Updating Common Config for RabbitMq_** -> I've updated the rabbitmq host from localhost to rabbit container using environment variables. Also added **Webhook** for config-server in GitHub using **hook deck** console.
   Login to hook deck console and forward requests to _/monitor_ and also connection label as _localhost_. So whenever you commit changes in GitHub it'll forward the request to localhost using webhook. So when we refresh the bus
   the configuration changes will be updated.

9. **_Updating Cloud Config Monitor for RabbitMq_** -> I've added two dependencies cloud-config-monitor and cloud-starter-bus-ampq for enabling **/monitor** endpoint for auto-refresh configuration using webhook. Also when enabling webhook
    make sure to give **application/json** as the request body to send a request to webhook.
   
10. **_Updating Docker-Compose for other files_** -> I've updated the docker-compose file for prod and qa profiles. Changed the common config.yml _spring.profiles.active as prod and qa_ for their respective profiles. 
