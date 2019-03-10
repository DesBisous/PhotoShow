package com.action;

import com.opensymphony.xwork2.ActionSupport;
import com.forms.SuggestionForm;
import com.service.ServiceCentre;

import java.util.Date;
import java.sql.Timestamp;


public class SuggestionBoxAction extends ActionSupport {
    private SuggestionForm suggestionForm = null;
    private ServiceCentre serviceCentre = null;
    private SessionAction sessionAction = null;

    public SuggestionForm getSuggestionForm() {
        return suggestionForm;
    }

    public void setSuggestionForm(SuggestionForm suggestionForm) {
        this.suggestionForm = suggestionForm;
    }

    public void setServiceCentre(ServiceCentre serviceCentre) {
        this.serviceCentre = serviceCentre;
    }

    public void SuggestionBox(){
//      获取已登录用户的ID
        sessionAction = new SessionAction();
        int userId= sessionAction.judgeSession();
        getSuggestionForm().setUserId(userId);
//      意见投递时间
        getSuggestionForm().setSugTime(new Timestamp(new Date().getTime()));
//        serviceCentre.saveSuggestion(suggestionForm);
        serviceCentre.saveSuggestion(suggestionForm);
    }
}
