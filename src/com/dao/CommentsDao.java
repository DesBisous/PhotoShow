package com.dao;

import com.forms.SuggestionForm;

import java.util.List;

public interface CommentsDao {
    /**
     * 获取第一类Suggestion
     * @return
     */
    public List<SuggestionForm> SuggestionForm1();
    /**
     * 获取第二类Suggestion
     * @return
     */
    public List<SuggestionForm> SuggestionForm2();
    /**
     * 获取第三类Suggestion
     * @return
     */
    public List<SuggestionForm> SuggestionForm3();
    /**
     * 获取第四类Suggestion
     * @return
     */
    public List<SuggestionForm> SuggestionForm0();
}
