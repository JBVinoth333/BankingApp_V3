package model;

import java.time.LocalDateTime;

class Transaction implements ShowDate{
    final int operationID;
    final LocalDateTime time;
    final double money;

    public int getOperationID() {
        return operationID;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public double getMoney() {
        return money;
    }

    public int getModeID() {
        return modeID;
    }

    final int modeID;

    Transaction(int operationID, LocalDateTime time, double money, int modeID) {
        this.operationID = operationID;
        this.time = time;
        this.money = money;
        this.modeID = modeID;
    }

}


