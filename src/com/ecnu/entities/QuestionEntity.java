package com.ecnu.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "question", schema = "trivia")
public class QuestionEntity {
    private int id;//NOPMD
    private String statement;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answer;

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
    @Column(name = "statement")
    public String getStatement() {
        return statement;
    }

    public void setStatement(final String statement)
    {
        this.statement = statement;
    }

    @Basic
    @Column(name = "option_a")
    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(final String optionA)
    {
        this.optionA = optionA;
    }

    @Basic
    @Column(name = "option_b")
    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(final String optionB)
    {
        this.optionB = optionB;
    }

    @Basic
    @Column(name = "option_c")
    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(final String optionC)
    {
        this.optionC = optionC;
    }

    @Basic
    @Column(name = "option_d")
    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(final String optionD)
    {
        this.optionD = optionD;
    }

    @Basic
    @Column(name = "answer")
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(final String answer)
    {
        this.answer = answer;
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
        final QuestionEntity that = (QuestionEntity) obj;
        return id == that.id &&
                Objects.equals(statement, that.statement) &&
                Objects.equals(optionA, that.optionA) &&
                Objects.equals(optionB, that.optionB) &&
                Objects.equals(optionC, that.optionC) &&
                Objects.equals(optionD, that.optionD) &&
                Objects.equals(answer, that.answer);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, statement, optionA, optionB, optionC, optionD, answer);
    }
}
