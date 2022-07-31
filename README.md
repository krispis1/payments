# payments

Make sure docker and gradle are installed on your unix machine. As well as Java 8.

Deployment with Docker commands (exec in root project dir):

Run:
1. chmod +x deploy
2. sudo ./deploy

If it fails then run commands one by one:

1. sudo systemctl start docker
2. sudo docker network create payments-mysql
3. sudo docker container run --name mysqldb --network payments-mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=payments -d mysql:8
4. sudo gradle wrapper --gradle-version 7.5
5. sudo ./gradlew bootJar
6. sudo docker image build -t payments-jdbc .
7. sudo docker container run --network payments-mysql --name payments-jdbc-container -p 8080:8080 -d payments-jdbc

Example api:

1. curl --location --request POST 'http://localhost:8080/payment/create' \
   --form 'paymentType="ONE"' \
   --form 'amount="12"' \
   --form 'debtorIban="LT674673967581434764"' \
   --form 'creditorIban="LT674673967581434764"' \
   --form 'details="all ok"'
2. curl --location --request POST 'http://localhost:8080/payment/cancel' \
   --form 'paymentId="1"'
3. curl --location --request GET 'http://localhost:8080/payment/processed/desc'
4. curl --location --request GET 'http://localhost:8080/payment/processed/asc'
5. curl --location --request GET 'http://localhost:8080/payment/get?paymentId=1'