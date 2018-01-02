package com.ecnu.dao;

import com.ecnu.entities.QuestionEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public final class QuestionDAO
{

    private QuestionDAO()
    {
    }

    /**
     * @param index : question index
     * @return question statement
     */
    public static String getQuestion(int index)
    {
        String questionInfo = null;

        Session s = DBConnection.getSession();
        Query q = s.createQuery("select statement from QuestionEntity where id = ?");
        q.setParameter(0, index);
        List result = q.list();
        if (result != null && result.size() != 0)
        {
            questionInfo = (String)result.get(0);
        }

        s.close();
        return questionInfo;
    }

    /**
     * @param index : question index
     * @return question option statements
     */
    public static ArrayList<String> getOption(int index)
    {
        ArrayList<String> options = new ArrayList<>();

        Session s = DBConnection.getSession();
        Query q = s.createQuery("from QuestionEntity where id = ?");
        q.setParameter(0, index);
        if (q.list() != null && q.list().size() != 0)
        {
            QuestionEntity qe = (QuestionEntity) q.list().get(0);
            options.add(qe.getOptionA());
            options.add(qe.getOptionB());
            options.add(qe.getOptionC());
            options.add(qe.getOptionD());
        }

        s.close();
        return options;
    }

    public static boolean checkAnswer(int index, String answer)
    {
        System.out.println(answer);
        boolean re = false;
        Session s = DBConnection.getSession();
        Query q = s.createQuery("select answer from QuestionEntity where id = ?");
        q.setParameter(0, index);
        List result = q.list();
        if (result != null && result.size() != 0)
        {
            String questionAnswer = (String)q.list().get(0);
            if (answer.equals(questionAnswer))
            {
                re = true;
            }
        }

        s.close();
        return re;
    }
}
