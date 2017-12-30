package com.ecnu.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "player", schema = "trivia")
public class PlayerEntity {
    private String userId;
    private Integer groupId;
    private Integer coins;
    private Integer position;
    private Byte isAnswering;
    private Integer idInGroup;

    @Id
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "group_id")
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "coins")
    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    @Basic
    @Column(name = "position")
    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Basic
    @Column(name = "is_answering")
    public Byte getIsAnswering() {
        return isAnswering;
    }

    public void setIsAnswering(Byte isAnswering) {
        this.isAnswering = isAnswering;
    }

    @Basic
    @Column(name = "id_in_group")
    public Integer getIdInGroup() {
        return idInGroup;
    }

    public void setIdInGroup(Integer idInGroup) {
        this.idInGroup = idInGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerEntity that = (PlayerEntity) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(groupId, that.groupId) &&
                Objects.equals(coins, that.coins) &&
                Objects.equals(position, that.position) &&
                Objects.equals(isAnswering, that.isAnswering) &&
                Objects.equals(idInGroup, that.idInGroup);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, groupId, coins, position, isAnswering, idInGroup);
    }
}
