package com.gmail.deutor1361.ing;
import java.util.*;

public class ATMRequest {
    Integer atmId;
    ATMRequestType atmRequestType;

    int creationOrder; // creationOrder

        static Comparator<ATMRequest> comparatorByPri = (u1, u2) -> {
        int compResult = u1.atmRequestType.compareTo(u2.atmRequestType);

        compResult = - compResult; // request start from least significant

        if(compResult == 0) {
            compResult = u1.creationOrder - u2.creationOrder;
        }

        return compResult;
    };


    ATMRequest(int atmId, String requestType, int order) {
        this.atmId = atmId;
        atmRequestType = ATMRequestType.valueOf(requestType);
        creationOrder = order;
    }
    public Integer getAtmId() {
        return atmId;
    }

    public void checkAndUpgrade(String requestType) {
        ATMRequestType tmpAtmRequestType = ATMRequestType.valueOf(requestType);
        if( tmpAtmRequestType.compareTo(atmRequestType) > 0) {
            atmRequestType = tmpAtmRequestType;
        }
    }
}
