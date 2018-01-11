package com.ecnu.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "player", schema = "trivia")
public class PlayerEntity {
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 用户所在房间的ID
     */
    private Integer groupId;
    /**
     * 金币数量
     */
    private Integer coins = 0;
    /**
     * 骰子所在棋盘的位置
     */
    private Integer position = 0;
    /**
     * 是否正在回答问题
     */
    private Integer isAnswering = 0;
    /**
     * 用户在房间中的序号
     */
    private Integer idInGroup;
    /**
     * 用户是否在禁闭室中
     */
    private Integer isPunished = 0;

    /**
     * Constructor
     */
    public PlayerEntity(String userId, Integer groupId, Integer coins, Integer position, Integer isAnswering, Integer idInGroup, Integer isPunished) {
        this.userId = userId;
        this.groupId = groupId;
        this.coins = coins;
        this.position = position;
        this.isAnswering = isAnswering;
        this.idInGroup = idInGroup;
        this.isPunished = isPunished;
    }

    /**
     * Constructor with user_id
     */
    public PlayerEntity(String userId)
    {
        this.userId = userId;
    }

    /**
     * EmptyConstructor
     */
    public PlayerEntity() {

    }

    /**
     * 获得用户ID
     */
    @Id
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     */
    public void setUserId(final String userId)
    {
        this.userId = userId;
    }

    /**
     * 获得用户所在房间的ID
     */
    @Basic
    @Column(name = "group_id")
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * 设置用户所在房间的ID
     */
    public void setGroupId(final Integer groupId)
    {
        this.groupId = groupId;
    }

    /**
     * 获得用户金币数量
     */
    @Basic
    @Column(name = "coins")
    public Integer getCoins() {
        return coins;
    }

    /**
     * 设置用户金币数量
     */
    public void setCoins(final Integer coins)
    {
        this.coins = coins;
    }

    /**
     * 获得骰子所在棋盘的位置
     */
    @Basic
    @Column(name = "position")
    public Integer getPosition() {
        return position;
    }

    /**
     * 设置骰子所在棋盘的位置
     */
    public void setPosition(final Integer position)
    {
        this.position = position;
    }

    /**
     * 得知用户是否正在回答问题
     */
    @Basic
    @Column(name = "is_answering")
    public Integer getIsAnswering() {
        return isAnswering;
    }

    /**
     * 设置用户是否正在回答问题
     */
    public void setIsAnswering(final Integer isAnswering)
    {
        this.isAnswering = isAnswering;
    }

    /**
     * 获得用户在房间中的序号
     */
    @Basic
    @Column(name = "id_in_group")
    public Integer getIdInGroup() {
        return idInGroup;
    }

    /**
     * 设置用户在房间中的序号
     */
    public void setIdInGroup(final Integer idInGroup)
    {
        this.idInGroup = idInGroup;
    }

    /**
     * 判断两个用户是否相等
     */
    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }
        final PlayerEntity that = (PlayerEntity) obj;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(groupId, that.groupId) &&
                Objects.equals(coins, that.coins) &&
                Objects.equals(position, that.position) &&
                Objects.equals(isAnswering, that.isAnswering) &&
                Objects.equals(idInGroup, that.idInGroup) &&
                Objects.equals(isPunished, that.isPunished);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, groupId, coins, position, isAnswering, idInGroup);
    }

    @Basic
    @Column(name = "is_punished")
    public Integer getIsPunished() {
        return isPunished;
    }

    public void setIsPunished(final Integer isPunished)
    {
        this.isPunished = isPunished;
    }
}