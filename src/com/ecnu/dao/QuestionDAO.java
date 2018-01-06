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
    public static String getQuestion(final int index)
    {
        String questionInfo = null;

        final Session session = DBConnection.getSession();
        final Query query = session.createQuery("select statement from QuestionEntity where id = ?");
        query.setParameter(0, index);
        final List result = query.list();
        if (result != null && !result.isEmpty())
        {
            questionInfo = (String)result.get(0);
        }

        session.close();
        return questionInfo;
    }

    /**
     * @param index : question index
     * @return question option statements
     */
    public static List<String> getOption(final int index)
    {
        final List<String> options = new ArrayList<>();

        final Session session = DBConnection.getSession();
        final Query query = session.createQuery("from QuestionEntity where id = ?");
        query.setParameter(0, index);
        if (query.list() != null && query.list().size() != 0)
        {
            final QuestionEntity questionEntity = (QuestionEntity) query.list().get(0);
            options.add(questionEntity.getOptionA());
            options.add(questionEntity.getOptionB());
            options.add(questionEntity.getOptionC());
            options.add(questionEntity.getOptionD());
        }

        session.close();
        return options;
    }

    public static boolean checkAnswer(final int index, final String answer)
    {
        boolean re = false;
        final Session session = DBConnection.getSession();
        final Query query = session.createQuery("select answer from QuestionEntity where id = ?");
        query.setParameter(0, index);
        final List result = query.list();
        if (result != null && !result.isEmpty())
        {
            final String questionAnswer = (String) query.list().get(0);
            if (answer.equals(questionAnswer))
            {
                re = true;
            }
        }

        session.close();
        return re;
    }
}
