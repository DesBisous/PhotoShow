package com.dao;

import com.beans.Suggestionbox;
import com.forms.SuggestionForm;

import java.util.List;


public interface SuggestionDao {
    //存储用户意见信息
    public void saveSuggestion(SuggestionForm suggestionForm);
    //查询用户意见信息
    public Suggestionbox findSuggestionForId(int id);
    //删除用户意见信息
    public void deleteSuggestion(int id);
    //回复用户意见信息
    public void replaySuggestion(int id,String replay);
}
