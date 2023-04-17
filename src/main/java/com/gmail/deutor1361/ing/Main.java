//com.sparkjava.spark-core     Apache 2.0  (https://mvnrepository.com/artifact/com.sparkjava/spark-core)
//org.slf4j.slf4j-simple       MIT  (https://mvnrepository.com/artifact/org.slf4j/slf4j-simple)


package com.gmail.deutor1361.ing;

public class Main {
    public static void main(String[] args) {
            RequestManager requestManager = new RequestManager();
            spark.Spark.port(8080);
            spark.Spark.threadPool(8);

            spark.Spark.post("/onlinegame/calculate", (request, response) -> requestManager.processRequest("onlinegame", request, response));
            spark.Spark.post("/atms/calculateOrder", (request, response) ->  requestManager.processRequest("atm", request, response));
            spark.Spark.post("/transactions/report", (request, response) ->  requestManager.processRequest("transaction", request, response));

    }
}