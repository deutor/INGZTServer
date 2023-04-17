package com.gmail.deutor1361.ing;

public class RequestManager {
    IRequestHandler atmHandler;
    IRequestHandler onlineGameHandler;
    IRequestHandler transactionHandler;
    RequestManager() {
        atmHandler = new ATMHandler();
        onlineGameHandler = new OnlineGameHandler();
        transactionHandler = new TransactionHandler();
    }

    IRequestHandler getRequestHandler(String reqType) {
        switch(reqType) {
            case "atm": return atmHandler;
            case "onlinegame": return onlineGameHandler;
            case "transaction": return transactionHandler;
        }

        throw new IllegalArgumentException("Invalid request type: " + reqType);
    }

    public String processRequest(String reqType, spark.Request request, spark.Response response) {
        String result;
        try {
            IRequestHandler reqHandler = getRequestHandler(reqType);
            result = reqHandler.handleRequest(request, response);
        } catch (Exception ex) {
            response.status(500);
            result = ex.toString();
        }

        return result;
    }
}
