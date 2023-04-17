package com.gmail.deutor1361.ing;

import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;


public class TransactionHandler implements IRequestHandler {
    HashMap<String, Account> accounts = new HashMap<>();

    @Override
    public String handleRequest(spark.Request request, spark.Response sparkResponse) {
        accounts.clear();

        processRequestJson(request.body());
        JSONArray  responseJSONArr = constructResponseJson();

        return responseJSONArr.toString(2).replace("\n", "\r\n") + "\r\n";
    }

    private JSONArray constructResponseJson() {
        JSONArray  responseJSONArr = new JSONArray();

        ArrayList<String> lofAccounts = new ArrayList<>(accounts.keySet());
        Collections.sort(lofAccounts);


        for(String accountNo: lofAccounts) {
            Account account = accounts.get(accountNo);
            JSONObject accountJson = new JSONObject();

            accountJson.put("account", accountNo);
            accountJson.put("debitCount", account.getDebitCount());
            accountJson.put("creditCount", account.getCreditCount());
            accountJson.put("balance", account.getBalance());

            responseJSONArr.put(accountJson);
        }

        return responseJSONArr;
    }

    private void processRequestJson(String body) {
        JSONArray  requestJSONArr = new JSONArray(body);

        for (int i = 0; i < requestJSONArr.length(); i++) {
            String debitAccount = requestJSONArr.getJSONObject(i).getString("debitAccount");
            String creditAccount = requestJSONArr.getJSONObject(i).getString("creditAccount");
            BigDecimal amount = requestJSONArr.getJSONObject(i).getBigDecimal("amount");

            Account account = accounts.get(debitAccount);
            if (account == null) {
                account = new Account(debitAccount);
                accounts.put(debitAccount, account);
            }
            account.withdraw(amount);

            account = accounts.get(creditAccount);
            if (account == null) {
                account = new Account(creditAccount);
                accounts.put(creditAccount, account);
            }
            account.deposit(amount);
        }
    }
}
