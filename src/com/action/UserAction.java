package com.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forms.LoginForm;
import com.forms.UserForm;
import com.opensymphony.xwork2.ActionSupport;
import com.service.UserManager;

import java.util.HashMap;
import java.util.Map;


public class UserAction extends ActionSupport{
    private static final long serialVersionUID = 1L;
    private UserForm userForm;
    private LoginForm loginForm;
    private UserManager userManager;
    private SessionAction sessionAction=new SessionAction();
    private String result;

    public UserForm getUserForm() {
        return userForm;
    }

    public void setUserForm(UserForm userForm) {
        this.userForm = userForm;
    }

    public LoginForm getLoginForm() {
        return loginForm;
    }

    public void setLoginForm(LoginForm loginForm) {
        this.loginForm = loginForm;
    }

    public UserManager getUserManager() {
        return userManager;
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

    /**
     * 注册用户
     * @return
     * @throws Exception
     */
    public String register() throws Exception {
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        LoginForm loginSession=new LoginForm();
        loginSession.setUserId(userForm.getUserId());
        loginSession.setUserPwd(userForm.getUserPwd());
        if(userManager.registerManager(userForm)){
            try {
                loginSession = userManager.loginManager(loginSession);
            }catch (Exception e){
                e.printStackTrace();
                loginSession=null;
            }
            if(loginSession!=null) {
                sessionAction.addSession(loginSession);
                loginSession = null;
                loginSession = sessionAction.haveSession();
                userManager.saveTheme("images/PersonalWeb/banner-bg-about-2.png",loginSession.getUserId());
                map.put("state", "success");
                map.put("userId", loginSession.getUserId());
                map.put("userName", loginSession.getUserName());
                result = objectMapper.writeValueAsString(map);
                return "register";
            }
        }
        map.put("state","error");
        result=objectMapper.writeValueAsString(map);
        return "error";
    }
    /**
     * 登录用户
     * @return
     * @throws Exception
     */
    public String login() throws  Exception{
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        LoginForm loginSession=null;
        try {
            loginSession = userManager.loginManager(loginForm);
        }catch (Exception e){
            e.printStackTrace();
            loginSession=null;
        }
        if(loginSession!=null){
            sessionAction.addSession(loginSession);
            loginSession=null;
            loginSession=sessionAction.haveSession();
            map.put("state", "success");
            map.put("userId",loginSession.getUserId());
            map.put("userName",loginSession.getUserName());
            result=objectMapper.writeValueAsString(map);
            return "login";
        }
        map.put("state","error");
        result=objectMapper.writeValueAsString(map);
        return "error";
    }

    /**
     * 显示用户信息
     * @return
     * @throws Exception
     */
    public String showInfo() throws Exception{
        int id=sessionAction.judgeSession();
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        UserForm userInfo=null;
        String url = "";
        try{
            userInfo=userManager.showInfoManager(id);
            url = userManager.findTheme(userInfo.getUserId());
        }catch (Exception e){
            e.printStackTrace();
            userInfo=null;
        }
        if (id!=0) {
            map.put("userName",userInfo.getUserName());
            map.put("userId",userInfo.getUserId());
            map.put("userEmail",userInfo.getUserEmail());
            map.put("userPhNum",userInfo.getUserPhNum());
            map.put("userAddress",userInfo.getUserAddress());

            map.put("userHeadImg","imgs/"+id+"/"+userInfo.getUserHeadImg());
            map.put("userAlbumNum",userInfo.getUserAlbumNum());
            map.put("url",url);
            result=objectMapper.writeValueAsString(map);
            return "showInfo";
        }
        map.put("state","error");
        result=objectMapper.writeValueAsString(map);;
        return "error";
    }

    /**
     * 更改个人基本信息
     * @return
     * @throws Exception
     */
    public String updateInfo() throws Exception{
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        int id=sessionAction.judgeSession();
        if (id!=0){
            userForm.setId(id);
            if(userManager.updateManager(userForm)){
                map.put("state", "success");
                result=objectMapper.writeValueAsString(map);
                return "updateInfo";
            }
        }
        map.put("state","error");
        result=objectMapper.writeValueAsString(map);;
        return "error";
    }

    /**
     * 更改密码
     * @return
     * @throws Exception
     */
    public  String updatePwd() throws Exception{
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        int id=sessionAction.judgeSession();
        if (id!=0){
            loginForm.setId(id);
            if(userManager.updatePwdManager(loginForm)){
                map.put("state", "success");
                result=objectMapper.writeValueAsString(map);
                sessionAction.exitSession();
                return "updatePwd";
            }
        }
        map.put("state","error");
        result=objectMapper.writeValueAsString(map);;
        return "error";
    }
}