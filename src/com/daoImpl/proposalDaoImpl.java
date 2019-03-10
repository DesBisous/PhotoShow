package com.daoImpl;

import com.beans.Feedback;
import com.forms.FeedbackForm;
import com.dao.proposalDao;
import org.springframework.beans.BeanUtils;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.List;

public class proposalDaoImpl extends HibernateDaoSupport implements proposalDao {

    List<FeedbackForm> feedbackForms = null;
    FeedbackForm feedbackForm = null;

    @Override
    public List<FeedbackForm> feedback1() {
        String sqlCheck="from Feedback where fbradio=1";
        return FeedbackFormsUtils(sqlCheck);
    }

    @Override
    public List<FeedbackForm> feedback2() {
        String sqlCheck="from Feedback where fbradio=2";
        return FeedbackFormsUtils(sqlCheck);
    }

    @Override
    public List<FeedbackForm> feedback3() {
        String sqlCheck="from Feedback where fbradio=3";
        return FeedbackFormsUtils(sqlCheck);
    }

    @Override
    public List<FeedbackForm> feedback4() {
        String sqlCheck="from Feedback where fbradio=4";
        return FeedbackFormsUtils(sqlCheck);
    }
    public List<FeedbackForm> FeedbackFormsUtils( String sqlCheck ){
        feedbackForms =new ArrayList<FeedbackForm>();
        try {
            ArrayList<Feedback> feedbacks = (ArrayList<Feedback>) getHibernateTemplate().find(sqlCheck);
            if( feedbacks == null || feedbacks.size()<=0 ) return null;
            else {
                for( int i = 0 ; i<feedbacks.size() ; i++ ){
                    feedbackForm =new FeedbackForm();
                    BeanUtils.copyProperties(feedbacks.get(i),feedbackForm);
                    feedbackForms.add(i,feedbackForm);
                }
            }
        }catch (HibernateException e){
            e.printStackTrace();
            return null;
        }
        return feedbackForms;
    }
}
