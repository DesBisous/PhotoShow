package com.action;

import com.beans.Suggestionbox;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.forms.LoginForm;
import com.forms.UserForm;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.ServiceCentre;
import com.service.UserManager;
import java.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyServiceRecordAction extends ActionSupport {
    private String top = null;
    private LoginForm loginForm = null;
    private ServiceCentre serviceCentre = null;
    private UserManager userManager = null;
    private String result;

    public void setTop(String top) {
        this.top = top;
    }

    public void setServiceCentre(ServiceCentre serviceCentre) {
        this.serviceCentre = serviceCentre;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String MyServiceRecord() throws  Exception {

//      通过Session获取已登录的用户id信息
        loginForm= (LoginForm)ActionContext.getContext().getSession().get("user");
        int userid = loginForm.getId();

//      通过用户id从数据库中获取用户的全部信息
        UserForm userForm = userManager.showInfoManager(userid);

//      获取用户意见箱内容
        List<Suggestionbox> Suggestionboxs = serviceCentre.queryMyServiceRecord(userid,Integer.parseInt(top));

//      构建json
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
//      设置jackson的ObjectMapper对时间类型的处理方式
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        objectMapper.setDateFormat(fmt);
        map.put("state", "success");
//      判断是否有昵称
        if(userForm.getUserName() == null || userForm.getUserName() == ""){
            map.put("Name", userForm.getUserId());
        }else{ map.put("Name", userForm.getUserName()); }
        map.put("state", "success");
        map.put("userHeadImg", userForm.getUserHeadImg());
        map.put("Suggestionboxs", Suggestionboxs);
        result = objectMapper.writeValueAsString(map);
        return "success";
     }
}
