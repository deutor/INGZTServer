package com.gmail.deutor1361.ing;
import java.util.*;

public class ATMRegion {
        ATMRequest atmRequest;

        HashMap<Integer, ATMRequest> requests2 = new HashMap<>();
        Integer region;

        ATMRegion(int region) {
                this.region = region;
        }
        public Integer getRegion() {
                return region;
        }

        public void addRequest(String requestType, int atmId, int order) {

                atmRequest = requests2.get(atmId);
                if(atmRequest == null) {
                        atmRequest = new ATMRequest(atmId, requestType, order);
                        requests2.put(atmId, atmRequest);
                } else {
                        atmRequest.checkAndUpgrade(requestType);
                }


                /*atmRequest = new ATMRequest(atmId, requestType, order);

                int index = Collections.binarySearch(requests, atmRequest, ATMRequest.comparatorById);
                if (index < 0) {
                        index = -index - 1;
                        requests.add(index, atmRequest);
                } else {
                        atmRequest = requests.get(index);
                        atmRequest.checkAndUpgrade(requestType);
                } */
        }

        public ArrayList<ATMRequest> sortRequests() {

                ArrayList<ATMRequest> listOfValues = new ArrayList<>( requests2.values() );
                listOfValues.sort(ATMRequest.comparatorByPri);
                return listOfValues;
        }

        public void clearRequests() {
                requests2.clear();
        }
}
