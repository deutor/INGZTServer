package com.gmail.deutor1361.ing;

import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.*;

public class ATMHandler implements IRequestHandler {
    HashMap<Integer, ATMRegion> regions = new HashMap<>();

    @Override
    public String handleRequest(spark.Request request, spark.Response sparkResponse) {
        regions.clear();

        processRequestJson(request.body());
        JSONArray  responseJSONArr = constructResponseJson();

        return responseJSONArr.toString(2).replace("\n", "\r\n") + "\r\n";
    }

    private JSONArray constructResponseJson() {
        JSONArray  responseJSONArr = new JSONArray();

        ArrayList<Integer> lofRegions = new ArrayList<>(regions.keySet());
        Collections.sort(lofRegions);



        for(Integer regionId: lofRegions) {
            ATMRegion region = regions.get(regionId);
            ArrayList<ATMRequest> requests = region.sortRequests();

            for(ATMRequest atmRequest : requests) {
                JSONObject responseJSONObj = new JSONObject();
                responseJSONObj.put("region", region.getRegion());
                responseJSONObj.put("atmId", atmRequest.getAtmId());

                responseJSONArr.put(responseJSONObj);
            }
            region.clearRequests();
        }

        return responseJSONArr;
    }

    private void processRequestJson(String body) {
        ATMRegion atmRegion;

        JSONArray  requestJSONArr = new JSONArray(body);

        for (int i = 0; i < requestJSONArr.length(); i++)
        {
            int regionId = requestJSONArr.getJSONObject(i).getInt("region");
            String requestType = requestJSONArr.getJSONObject(i).getString("requestType");
            int atmId = requestJSONArr.getJSONObject(i).getInt("atmId");

            atmRegion = regions.get(regionId);

            if (atmRegion == null) {
                atmRegion = new ATMRegion(regionId);
                regions.put(regionId, atmRegion);
            }

            atmRegion.addRequest(requestType, atmId, i);
        }
    }
}
