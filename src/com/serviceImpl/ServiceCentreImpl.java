package com.serviceImpl;

import com.beans.Suggestionbox;
import com.forms.SuggestionForm;
import com.service.ServiceCentre;
import com.daoImpl.*;

import java.util.List;


public class ServiceCentreImpl implements ServiceCentre {

    private FeedbackDaoImpl feedbackDaoImpl = null;
    private SuggestionDaoImpl suggestionDaoImpl = null;
    private MyServiceRecordDaoImpl MyServiceRecordDaoImpl = null;

    public void setFeedbackDaoImpl(FeedbackDaoImpl feedbackDaoImpl){
         this.feedbackDaoImpl = feedbackDaoImpl;
     }

    public void setSuggestionDaoImpl(SuggestionDaoImpl suggestionDaoImpl) {
        this.suggestionDaoImpl = suggestionDaoImpl;
    }

    public void setMyServiceRecordDaoImpl(com.daoImpl.MyServiceRecordDaoImpl myServiceRecordDaoImpl) {
        this.MyServiceRecordDaoImpl = myServiceRecordDaoImpl;
    }

    @Override
    public void savefeedback(int fbradio , String fbtext) {
        feedbackDaoImpl.savefeedback(fbradio,fbtext);
    }

    @Override
    public void saveSuggestion(SuggestionForm suggestionForm) {
        suggestionDaoImpl.saveSuggestion(suggestionForm);
    }

    @Override
    public List<Suggestionbox> queryMyServiceRecord(int userid, int type) {
        List<Suggestionbox> Suggestionboxs = null;
        switch (type){
            case 1: Suggestionboxs = MyServiceRecordDaoImpl.MyRecord1(userid); break;
            case 2: Suggestionboxs = MyServiceRecordDaoImpl.MyRecord2(userid); break;
            case 3: Suggestionboxs = MyServiceRecordDaoImpl.MyRecord3(userid); break;
            case 0: Suggestionboxs = MyServiceRecordDaoImpl.MyRecord0(userid); break;
        }
        return Suggestionboxs;
    }


}
