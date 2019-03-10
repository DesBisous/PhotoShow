package com.daoImpl;

import com.beans.Suggestionbox;
import com.dao.MyServiceRecordDao;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MyServiceRecordDaoImpl extends BeasDaoImpl implements MyServiceRecordDao {

    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 把字符串转换成时间格式

    @Override
    public List<Suggestionbox> MyRecord1(int userid) {
        String hql = "SELECT new list(s.id,s.userid,s.suggestionType,s.suggestionTitle,s.sugContent,s.sugTime,s.csId,s.csContent,s.csTime) " +
                "FROM Suggestionbox s WHERE userid="+userid+" AND suggestionType=1";
        List<Suggestionbox> Suggestionboxs = backSuggestionboxs (hql);
        return Suggestionboxs;
    }

    @Override
    public List<Suggestionbox> MyRecord2(int userid) {
        String hql = "SELECT new list(s.id,s.userid,s.suggestionType,s.suggestionTitle,s.sugContent,s.sugTime,s.csId,s.csContent,s.csTime) " +
                "FROM Suggestionbox s WHERE userid="+userid+" AND suggestionType=2";
        List<Suggestionbox> Suggestionboxs = backSuggestionboxs (hql);
        return Suggestionboxs;
    }

    @Override
    public List<Suggestionbox> MyRecord3(int userid) {
        String hql = "SELECT new list(s.id,s.userid,s.suggestionType,s.suggestionTitle,s.sugContent,s.sugTime,s.csId,s.csContent,s.csTime) " +
                "FROM Suggestionbox s WHERE userid="+userid+" AND suggestionType=3";
        List<Suggestionbox> Suggestionboxs = backSuggestionboxs (hql);
        return Suggestionboxs;
    }

    @Override
    public List<Suggestionbox> MyRecord0(int userid) {
        String hql = "SELECT new list(s.id,s.userid,s.suggestionType,s.suggestionTitle,s.sugContent,s.sugTime,s.csId,s.csContent,s.csTime) " +
                "FROM Suggestionbox s WHERE userid="+userid+" AND suggestionType=0";
        List<Suggestionbox> Suggestionboxs = backSuggestionboxs (hql);
        return Suggestionboxs;
    }

    @Override
    public Suggestionbox listToSuggestionbox(List list) {
        Suggestionbox Suggestionbox = null;
        try {
            Suggestionbox = new Suggestionbox();
            Suggestionbox.setId((int) list.get(0));
            Suggestionbox.setUserid((int) list.get(1));
            Suggestionbox.setSuggestionType((int) list.get(2));
            Suggestionbox.setSuggestionTitle((String) list.get(3));
            Suggestionbox.setSugContent((String) list.get(4));
            Suggestionbox.setSugTime(new Timestamp(format.parse(list.get(5).toString()).getTime()));
            Suggestionbox.setCsId((String) list.get(6));
            Suggestionbox.setCsContent((String) list.get(7));
            if(list.get(8) == null || list.get(8) == "") Suggestionbox.setCsTime(null);
            else Suggestionbox.setCsTime(new Timestamp(format.parse(list.get(8).toString()).getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ;
        return Suggestionbox;
    }

    @Override
    public List<Suggestionbox> backSuggestionboxs (String hql) {
        List<List> lists = getSession().createQuery(hql).list();
        List<Suggestionbox> Suggestionboxs = new ArrayList<Suggestionbox>();
        for( List list: lists){
            Suggestionbox Suggestionbox = listToSuggestionbox(list);
            if( Suggestionbox == null ) continue;
            Suggestionboxs.add(Suggestionbox);
        }
        return Suggestionboxs;
    }
}
