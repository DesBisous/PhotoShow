package com.dao;

import com.beans.Suggestionbox;

import java.util.List;


public interface MyServiceRecordDao {
    /**
     * 查找网站问题
     * @return List<Suggestionbox>
     */
    public List<Suggestionbox> MyRecord1(int userid);
    /**
     * 查找照片与共享问题
     * @return List<Suggestionbox>
     */
    public List<Suggestionbox> MyRecord2(int userid);
    /**
     * 查找账户问题/投诉建议"
     * @return List<Suggestionbox>
     */
    public List<Suggestionbox> MyRecord3(int userid);
    /**
     * 查找其他问题
     * @return List<Suggestionbox>
     */
    public List<Suggestionbox> MyRecord0(int userid);
    /**
     * listToSuggestionbox
     * @return Suggestionbox
     */
    public Suggestionbox listToSuggestionbox(List list);
    /**
     * 数据库查询返回意见箱内容
     * @return List<Suggestionbox>
     */
    public List<Suggestionbox> backSuggestionboxs(String hql);
}
