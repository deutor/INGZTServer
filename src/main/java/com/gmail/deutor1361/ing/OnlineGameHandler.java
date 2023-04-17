package com.gmail.deutor1361.ing;

import java.util.ArrayList;
import org.json.*;


public class OnlineGameHandler implements IRequestHandler {
    ArrayList<OnlineGameClan> clans = new ArrayList<>();
    int groupCount;


    @Override
    public String handleRequest(spark.Request request, spark.Response sparkResponse) {
        clans.clear();

        processRequestJson(request.body());
        JSONArray  responseJSONArr = constructResponseJson();

        return responseJSONArr.toString(2).replace("\n", "\r\n") + "\r\n";
    }

    JSONArray constructResponseJson() {
        JSONArray  responseJSONArr = new JSONArray();

        clans.sort(OnlineGameClan.comparator);



        // while there are still clans in list
        while ( clans.size() > 0) {
            int remainingCapacity = groupCount;
            JSONArray groupJson = new JSONArray();

            int clansSize = clans.size();

            // first element is always added to list regardless of its size so even if input data contained too big
            // groups those groups will be contained in output
            boolean first = true;

            // build group
            for(int i = 0; i<clansSize;i++) {
                OnlineGameClan clan = clans.get(i);
                if( (clan.getNumberOfPlayers() <= remainingCapacity) || first) {
                    first = false;
                    clans.remove(clan);
                    i--;
                    clansSize--;

                    JSONObject clanJson = new JSONObject();
                    clanJson.put("numberOfPlayers", clan.getNumberOfPlayers());
                    clanJson.put("points", clan.getPoints());
                    groupJson.put(clanJson);

                    remainingCapacity = remainingCapacity - clan.getNumberOfPlayers();
                    if (remainingCapacity <= 0) break;
                }
            }
            responseJSONArr.put(groupJson);
        }

        return responseJSONArr;
    }

    private void processRequestJson(String body) {
        OnlineGameClan onlineGameClan;

        JSONObject requestJSONObj = new JSONObject(body);
        groupCount = requestJSONObj.getInt("groupCount");

        JSONArray  requestJSONArr = requestJSONObj.getJSONArray("clans");

        for (int i = 0; i < requestJSONArr.length(); i++)
        {
            int numberOfPlayers = requestJSONArr.getJSONObject(i).getInt("numberOfPlayers");
            int points = requestJSONArr.getJSONObject(i).getInt("points");
            onlineGameClan = new OnlineGameClan(numberOfPlayers, points, i);
            clans.add(onlineGameClan);
        }
    }
}
