package com.service;

import com.forms.*;

import java.util.ArrayList;
import java.util.List;


public interface AdminManager {
    //注册
    public boolean registerManager(AdminForm adminFrom);
    //登录
    public AdminForm loginManager(AdminForm adminFrom);
    //查询
    public AdminForm HqlFindManager(String Hql);
    //查询所有
    public ArrayList<AdminForm> HqlFindAllManager(String Hql);
    //修改密码
    public boolean modifyManager(AdminForm adminFrom);
    //修改达人模块
    public boolean DarenManager(MasterinfoForm masterinfoForm);
    //获取用户Feedback
    public List<FeedbackForm> proposalManager(int note);
    //获取分类SuggestionForm
    public List<SuggestionForm> commentsManager(int note);
    //用户建议分页查询
    public pageForm queryForPageSuggest(int pageSize, int page, String hql);
    //删除用户建议
    public boolean deleteSuggest(int id);
    //回复用户建议
    public boolean replaySuggest(int id,String replay);
}
