package com.daoImpl;

import com.beans.Suggestionbox;
import com.forms.SuggestionForm;
import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import com.dao.CommentsDao;

import java.util.ArrayList;
import java.util.List;

public class CommentsDaoImpl extends HibernateDaoSupport implements CommentsDao{
    List<SuggestionForm> suggestionForms= null;
    SuggestionForm suggestionForm= null;
    @Override
    public List<SuggestionForm> SuggestionForm1() {
        String sqlCheck="from Suggestionbox where suggestionType=1";
        return SuggestionFormUtils(sqlCheck);
    }

    @Override
    public List<SuggestionForm> SuggestionForm2() {
        String sqlCheck="from Suggestionbox where suggestionType=2";
        return SuggestionFormUtils(sqlCheck);
    }

    @Override
    public List<SuggestionForm> SuggestionForm3() {
        String sqlCheck="from Suggestionbox where suggestionType=3";
        return SuggestionFormUtils(sqlCheck);
    }

    @Override
    public List<SuggestionForm> SuggestionForm0() {
        String sqlCheck="from Suggestionbox where suggestionType=0";
        return SuggestionFormUtils(sqlCheck);
    }
    public List<SuggestionForm> SuggestionFormUtils(String sqlCheck ){
        suggestionForms =new ArrayList<SuggestionForm>();
        try {
            ArrayList<Suggestionbox> suggestionboxs = (ArrayList<Suggestionbox>) getHibernateTemplate().find(sqlCheck);
            if( suggestionboxs == null || suggestionboxs.size()<=0 ) return null;
            else {
                for( int i = 0 ; i<suggestionboxs.size() ; i++ ){
                    suggestionForm =new SuggestionForm();
                    BeanUtils.copyProperties(suggestionboxs.get(i),suggestionForm);
                    suggestionForms.add(suggestionForm);
                }
            }
        }catch (HibernateException e){
            e.printStackTrace();
            return null;
        }
        return suggestionForms;
    }
}
