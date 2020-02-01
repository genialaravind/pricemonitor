# Project Title
PRICE MONITOR

## Starting Application
spring-boot:run

## Testing Application
mvn clean install

### How Application Works

1. All Sequence generator generates one unique sequence number every 30 seconds , And that Unique Seq No acts as a window.
Every 30 seconds new window a.k.a sequence number will be maintained.

2. Any Data received from the banks will be mapped to the sequence number of the current window.

3. Any Data received from the company also mapped to the sequence number of the current window.

4. At every 31 second Data Verification scheduler comes online and verifies the previous window(seq No) data.

5. If the data from the Bank and company matches no actions will be taken , if any discrepancies then it will alert the bank.

Assumption for the application development / Price monitoring.

1.The 3rd party company send data once every 30 seconds

2.The 3rd party company will send only the latest information

3.The 3rd party company will not send any data if no data sent from the company

