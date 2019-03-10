package com.daoImpl;

import com.beans.Feedback;
import com.dao.FeedbackDao;


public class FeedbackDaoImpl extends BeasDaoImpl implements FeedbackDao {

    private Feedback feedback = null;

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    @Override
    public void savefeedback(int fbradio,String fbtext) {
        feedback = new Feedback();
        feedback.setFbradio(fbradio);
        feedback.setFbtext(fbtext);
        getSession().save(feedback);
    }
}
