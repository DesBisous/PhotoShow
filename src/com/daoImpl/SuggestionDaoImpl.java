package com.daoImpl;

import com.action.SessionAction;
import com.dao.SuggestionDao;
import com.forms.SuggestionForm;
import com.beans.Suggestionbox;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class SuggestionDaoImpl extends BeasDaoImpl implements SuggestionDao{
    @Override
    public void saveSuggestion(SuggestionForm suggestionForm) {
//      创建持久化类
        Suggestionbox suggestionbox = new Suggestionbox();
//      赋值给持久化类
        suggestionbox.setUserid(suggestionForm.getUserId());
        suggestionbox.setSuggestionType(suggestionForm.getSugradio());
        suggestionbox.setSuggestionTitle(suggestionForm.getSugtitle());
        suggestionbox.setSugContent(suggestionForm.getSugtext());
        suggestionbox.setSugTime(suggestionForm.getSugTime());
        getSession().save(suggestionbox);
    }

    @Override
    public Suggestionbox  findSuggestionForId(int id) {
        return (Suggestionbox)getSession().get(Suggestionbox.class,id);
    }

    @Override
    public void deleteSuggestion(int id) {
        //查找用户建议
        Suggestionbox suggestionbox = findSuggestionForId(id);
        getSession().delete(suggestionbox);
    }

    @Override
    public void replaySuggestion(int id, String replay) {
        //查找用户建议
        Suggestionbox suggestionbox = findSuggestionForId(id);
        SessionAction sessionAction=new SessionAction();
        String adminId = String.valueOf(sessionAction.haveAdminSession().getId());
        suggestionbox.setCsId(adminId);
        suggestionbox.setCsContent(replay);
        suggestionbox.setCsTime(new Timestamp(new Date().getTime()));
    }
}
