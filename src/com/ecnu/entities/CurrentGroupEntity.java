package com.ecnu.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "current_group", schema = "trivia")
public class CurrentGroupEntity {
    private int id;//NOPMD
    private Integer playerSum;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(final int id)
    {//NOPMD
        this.id = id;
    }

    @Basic
    @Column(name = "player_sum")
    public Integer getPlayerSum() {
        return playerSum;
    }

    public void setPlayerSum(final Integer playerSum)
    {
        this.playerSum = playerSum;
    }

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
        final CurrentGroupEntity that = (CurrentGroupEntity) obj;
        return id == that.id &&
                Objects.equals(playerSum, that.playerSum);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, playerSum);
    }
}
