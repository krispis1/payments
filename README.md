# payments

Deployment with Docker commands (exec in root project dir):

1. systemctl start docker
2. docker network create payments-mysql
3. docker container run --name mysqldb --network payments-mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=payments -d mysql:8
4. gradle wrapper --gradle-version 7.5
5. ./gradlew bootJar
6. docker image build -t payments-jdbc .
7. docker container run --network payments-mysql --name payments-jdbc-container -p 8080:8080 -d payments-jdbc

Example api:

1. curl --location --request POST 'http://localhost:8080/payment/create' \
   --form 'paymentType="ONE"' \
   --form 'amount="12"' \
   --form 'debtorIban="xxx"' \
   --form 'creditorIban="yyy"' \
   --form 'details="all ok"'
2. curl --location --request POST 'http://localhost:8080/payment/cancel' \
   --form 'paymentId="2"'
3. curl --location --request GET 'http://localhost:8080/payment/processed/desc'
4. curl --location --request GET 'http://localhost:8080/payment/processed/asc'
5. curl --location --request GET 'http://localhost:8080/payment/get?paymentId=2'