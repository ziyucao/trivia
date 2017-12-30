package com.ecnu.entity;

public class Player {
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 用户所在房间的ID
     */
    private int groupId;
    /**
     * 金币数量
     */
    private int coins;
    /**
     * 骰子所在棋盘的位置
     */
    private int position;
    /**
     * 是否正在回答问题
     */
    private byte isAnswering;
    /**
     * 用户在房间中的序号
     */
    private int idInGroup;

    /**
     * Constructor
     */
    public Player(String userId, int groupId, int coins, int position, byte isAnswering, int idInGroup) {
        this.userId = userId;
        this.groupId = groupId;
        this.coins = coins;
        this.position = position;
        this.isAnswering = isAnswering;
        this.idInGroup = idInGroup;
    }

    /**
     * EmptyConstructor
     */
    public Player() {

    }

    /**
     * 获得用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获得用户所在房间的ID
     */
    public int getGroupId() {
        return groupId;
    }

    /**
     * 设置用户所在房间的ID
     */
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    /**
     * 获得用户金币数量
     */
    public int getCoins() {
        return coins;
    }

    /**
     * 设置用户金币数量
     */
    public void setCoins(int coins) {
        this.coins = coins;
    }

    /**
     * 获得骰子所在棋盘的位置
     */
    public int getPosition() {
        return position;
    }

    /**
     * 设置骰子所在棋盘的位置
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * 得知用户是否正在回答问题
     */
    public byte getIsAnswering() {
        return isAnswering;
    }

    /**
     * 设置用户是否正在回答问题
     */
    public void setIsAnswering(byte isAnswering) {
        this.isAnswering = isAnswering;
    }

    /**
     * 获得用户在房间中的序号
     */
    public int getIdInGroup() {
        return idInGroup;
    }

    /**
     * 设置用户在房间中的序号
     */
    public void setIdInGroup(int idInGroup) {
        this.idInGroup = idInGroup;
    }
}
