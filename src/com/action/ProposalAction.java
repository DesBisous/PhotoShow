package com.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forms.FeedbackForm;
import com.service.AdminManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProposalAction {
    private AdminManager adminManager;
    private String result;

    public AdminManager getAdminManager() {
        return adminManager;
    }

    public void setAdminManager(AdminManager adminManager) {
        this.adminManager = adminManager;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String ProposalInfo()throws Exception{
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        List<FeedbackForm> FeedbackForms1 = adminManager.proposalManager(1);
        List<FeedbackForm> FeedbackForms2 = adminManager.proposalManager(2);
        List<FeedbackForm> FeedbackForms3 = adminManager.proposalManager(3);
        List<FeedbackForm> FeedbackForms4 = adminManager.proposalManager(4);
        map.put("state","success");
        map.put("FeedbackForms1",FeedbackForms1);
        map.put("FeedbackForms2",FeedbackForms2);
        map.put("FeedbackForms3",FeedbackForms3);
        map.put("FeedbackForms4",FeedbackForms4);
        result=objectMapper.writeValueAsString(map);
        return "success";
    }

}
