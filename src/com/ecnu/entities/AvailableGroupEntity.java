package com.ecnu.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "available_group", schema = "trivia")
public class AvailableGroupEntity
{
    private int id;//NOPMD

    public AvailableGroupEntity()
    {
    }

    public AvailableGroupEntity(final int id)//NOPMD
    {
        this.id = id;
    }

    @Id
    @Column(name = "id")
    public int getId()
    {
        return id;
    }

    public void setId(final int id)//NOPMD
    {//NOPMD
        this.id = id;
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
        final AvailableGroupEntity that = (AvailableGroupEntity) obj;
        return id == that.id;
    }

    @Override
    public int hashCode()
    {

        return Objects.hash(id);
    }
}
