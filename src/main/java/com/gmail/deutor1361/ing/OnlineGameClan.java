package com.gmail.deutor1361.ing;

import java.util.Comparator;

public class OnlineGameClan {

    Integer numberOfPlayers;
    Integer points;
    Integer creationOrder;

    static Comparator<OnlineGameClan> comparator = (u1, u2) -> {
        int result = - (u1.points - u2.points);
        if(result == 0) {
            result = u1.numberOfPlayers - u2.numberOfPlayers;
        }

        if(result == 0) {
            result = u1.creationOrder - u2.creationOrder;
        }

        return result;
    };

    OnlineGameClan(int numberOfPlayers, int points, int creationOrder) {
        this.numberOfPlayers = numberOfPlayers;
        this.points = points;
        this.creationOrder = creationOrder;
    }
    public Integer getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public Integer getPoints() {
        return points;
    }
}
