package com.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionSupport;
import com.service.UserManager;
import com.forms.UserForm;

import java.util.HashMap;
import java.util.Map;

public class ModifyThemeAction extends ActionSupport {
    private String url;
    private String result;
    private UserManager userManager;
    private SessionAction sessionAction=new SessionAction();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    @Override
    public String execute() throws Exception {
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        int id=sessionAction.judgeSession();
        if( id == 0 ){
            map.put("state","error");
            map.put("mgs","请先登录");
        }else{
            UserForm userInfo=userManager.showInfoManager(id);
            if ( userManager.updateTheme(url,userInfo.getUserId()) ){
                map.put("state","success");
                map.put("mgs","成功");
            }else{
                map.put("state","error");
                map.put("mgs","更新失败");
            }
        }
        result=objectMapper.writeValueAsString(map);
        return "success";
    }
}
