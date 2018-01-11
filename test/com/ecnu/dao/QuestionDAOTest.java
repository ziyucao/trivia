package com.ecnu.dao;

import org.junit.Assert;

import java.util.List;

import static org.junit.Assert.*;

public class QuestionDAOTest
{

    @org.junit.Test
    public void check_question_1_statement()
    {
        String questionStatement = QuestionDAO.getQuestion(1);
        Assert.assertEquals(questionStatement, "1=2?");
    }

    @org.junit.Test
    public void check_question_1_options()
    {
        List<String> options = QuestionDAO.getOption(1);
        Assert.assertEquals(options.get(0), "True");
        Assert.assertEquals(options.get(1), "False");
        Assert.assertEquals(options.get(2), "Unknown");
        Assert.assertEquals(options.get(3), "None of above");

    }

    @org.junit.Test
    public void check_question_1_answer()
    {
        boolean resultTrue = QuestionDAO.checkAnswer(1, "b");
        boolean resultFalse = QuestionDAO.checkAnswer(1, "a");
        Assert.assertEquals(resultTrue, true);
        Assert.assertEquals(resultFalse, false);
    }
}