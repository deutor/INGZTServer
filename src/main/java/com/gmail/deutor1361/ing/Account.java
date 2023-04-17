package com.gmail.deutor1361.ing;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Account {
    String accountNumber;
    int debitCount;
    int creditCount;
    BigDecimal balance;

    Account(String accountNumber) {
        this.accountNumber = accountNumber;
        debitCount =0;
        creditCount = 0;
        balance =  new BigDecimal(0);
        balance.setScale(2, RoundingMode.HALF_UP);
    }

    BigDecimal deposit(BigDecimal amount) {
        creditCount++;
        balance = balance.add(amount);
        return balance;
    }

    BigDecimal withdraw(BigDecimal amount) {
        debitCount++;
        balance = balance.subtract(amount);
        return balance;
    }

    int getDebitCount() {
        return debitCount;
    }

    int getCreditCount() {
        return creditCount;
    }

    BigDecimal getBalance() {
        return balance;
    }
}
