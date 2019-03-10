package com.dao;

import com.forms.FeedbackForm;
import java.util.List;

public interface proposalDao {
    /**
     * 获取feedback第一类反馈
     * @return
     */
    public List<FeedbackForm> feedback1();
    /**
     * 获取feedback第二类反馈
     * @return
     */
    public List<FeedbackForm> feedback2();
    /**
     * 获取feedback第三类反馈
     * @return
     */
    public List<FeedbackForm> feedback3();
    /**
     * 获取feedback第四类反馈
     * @return
     */
    public List<FeedbackForm> feedback4();
}
