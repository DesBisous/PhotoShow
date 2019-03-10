package com.service;

import com.beans.Suggestionbox;
import com.forms.SuggestionForm;

import java.util.List;


public interface ServiceCentre {
    /**
     * 保存网站反馈
     * @param fbradio
     * @param fbtext
     */
    public void savefeedback(int fbradio, String fbtext);

    /**
     * 保存意见箱
     * @param suggestionBoxForm
     */
    public void saveSuggestion(SuggestionForm suggestionBoxForm);

    /**
     * 查询我的服务记录
     * @param userid
     * @param type
     */
    public List<Suggestionbox> queryMyServiceRecord(int userid, int type);
}
