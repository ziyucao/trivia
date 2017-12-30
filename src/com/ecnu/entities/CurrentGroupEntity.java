package com.ecnu.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "current_group", schema = "trivia")
public class CurrentGroupEntity {
    private int id;
    private Integer playerSum;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "player_sum")
    public Integer getPlayerSum() {
        return playerSum;
    }

    public void setPlayerSum(Integer playerSum) {
        this.playerSum = playerSum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrentGroupEntity that = (CurrentGroupEntity) o;
        return id == that.id &&
                Objects.equals(playerSum, that.playerSum);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, playerSum);
    }
}
