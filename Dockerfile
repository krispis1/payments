From openjdk:8
copy ./target/payments-1.0.jar payments-1.0.jar
CMD ["java","-jar","payments-1.0.jar"]