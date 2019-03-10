package com.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forms.GraphForm;
import com.forms.MasterinfoForm;
import com.service.AdminManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Benson on 2016/5/23.
 */
public class GraphAction {
    private GraphForm graph;
    private MasterinfoForm masterinfo;
    private SessionAction sessionAction=new SessionAction();
    private AdminManager adminManager;
    private String result;

    public GraphForm getGraph() {
        return graph;
    }

    public void setGraph(GraphForm graph) {
        this.graph = graph;
    }

    public MasterinfoForm getMasterinfo() {
        return masterinfo;
    }

    public void setMasterinfo(MasterinfoForm masterinfo) {
        this.masterinfo = masterinfo;
    }

    public SessionAction getSessionAction() {
        return sessionAction;
    }

    public void setSessionAction(SessionAction sessionAction) {
        this.sessionAction = sessionAction;
    }

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

    public String GraphInfo()throws Exception{
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        try {
            sessionAction.addGraphSession(graph);
            map.put("state","success");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state","error");
        }
        result=objectMapper.writeValueAsString(map);
        return "success";
    }

    public String GraphDaren()throws Exception{
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        if( graph.getNote().equals("Save1") ){
            masterinfo.setId(1);
        }else{
            masterinfo.setId(2);
        }
        if( adminManager.DarenManager(masterinfo) ){
            sessionAction.addGraphSession(graph);
            map.put("state","success");
        }else{
            map.put("state","error");
        }
        result=objectMapper.writeValueAsString(map);
        return "success";
    }
}
