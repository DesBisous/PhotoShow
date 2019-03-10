package com.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forms.AdminForm;
import com.opensymphony.xwork2.ActionSupport;
import com.service.AdminManager;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class AdminAction extends ActionSupport {

    private AdminForm adminForm;
    private AdminManager adminManager;
    private SessionAction sessionAction=new SessionAction();
    private String result;

    public AdminForm getAdminForm() {
        return adminForm;
    }

    public void setAdminForm(AdminForm adminForm) {
        this.adminForm = adminForm;
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

    /**
     * 注册管理员
     * @return
     * @throws Exception
     */
    public String register() throws Exception {
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        //先判断账号是否已被注册
        if( adminManager.loginManager(adminForm) == null ){
            if(adminManager.registerManager(adminForm)){
                sessionAction.addAdminSession(adminManager.loginManager(adminForm));
                map.put("state", "success");
                map.put("mgs", "注册成功");
            }
        }else{
            map.put("state","error");
            map.put("mgs","账号已被注册");
        }
        result=objectMapper.writeValueAsString(map);
        return "register";
    }
    /**
     * 登录管理员
     * @return
     * @throws Exception
     */
    public String login() throws  Exception{
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        AdminForm loginSession=null;
        try {
            loginSession = adminManager.loginManager(adminForm);
        }catch (Exception e){
            e.printStackTrace();
            loginSession=null;
        }
        if(loginSession!=null){
            sessionAction.addAdminSession(loginSession);
            loginSession=null;
            loginSession=sessionAction.haveAdminSession();
            map.put("state", "success");
            map.put("userId",loginSession.getAdminid());
            map.put("id",Integer.toString(loginSession.getId()));
            result=objectMapper.writeValueAsString(map);
            System.out.println(result);
            return "login";
        }
        map.put("state","error");
        result=objectMapper.writeValueAsString(map);
        return "error";
    }
    /**
     * 突出管理员登录
     * @return
     * @throws Exception
     */
    public String exitAdmin() throws  Exception{
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        sessionAction.exitAdminSession();
        map.put("state","success");
        result=objectMapper.writeValueAsString(map);
        return "success";
    }
    /**
     * 修改管理员密码
     * @return
     * @throws Exception
     */
    public String modify() throws  Exception{
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        AdminForm adminform = null;
        String Hql = "from Admin where adminid='"+adminForm.getAdminid()+"'";
        adminform = adminManager.HqlFindManager(Hql);
        //先判断账号是否已存在
        if( adminform != null ){
            adminform.setPasswd(adminForm.getPasswd());
            if(adminManager.modifyManager(adminform)){
                map.put("state", "success");
                map.put("mgs", "修改成功");
            }
        }else{
            map.put("state","error");
            map.put("mgs","账号不存在");
        }
        result=objectMapper.writeValueAsString(map);
        return "success";
    }
    /**
     * 返回已登录管理员信息
     * @return
     * @throws Exception
     */
    public String find() throws  Exception{
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        adminForm = sessionAction.haveAdminSession();
        map.put("state", "success");
        map.put("adminForm", adminForm);
        result=objectMapper.writeValueAsString(map);
        return "success";
    }
    /**
     * 修改管理员信息
     * @return
     * @throws Exception
     */
    public String modifyInfo() throws  Exception{
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        AdminForm adminform = null;
        adminform = sessionAction.haveAdminSession();
        //先判断账号是否已存在
        if( adminform != null ){
            System.out.println(adminForm.toString());
            if( !adminForm.getName().equals("null") ) adminform.setName(adminForm.getName());
            if( !adminForm.getPhone().equals("null") ) adminform.setPhone(adminForm.getPhone());
            if( !adminForm.getEmail().equals("null") ) adminform.setEmail(adminForm.getEmail());
            if( !adminForm.getAddress().equals("null") ) adminform.setAddress(adminForm.getAddress());
            if( !adminForm.getPasswd().equals("null") ) adminform.setPasswd(adminForm.getPasswd());
            if(adminManager.modifyManager(adminform)){
                map.put("state", "success");
                map.put("mgs", "修改成功");
            }
        }else{
            map.put("state","error");
            map.put("mgs","账号不存在");
        }
        result=objectMapper.writeValueAsString(map);
        return "success";
    }

    /**
     * 随机返回5个管理员信息
     * @return
     * @throws Exception
     */
    public String backFiveAdmin() throws  Exception{
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        String Hql = "from Admin";
        ArrayList<AdminForm> adminForms = null;
        adminForms = adminManager.HqlFindAllManager(Hql);
        if( adminForms == null ) {
            map.put("state", "error");
        }else{
            ArrayList<Integer> MathNum = new ArrayList<>();
            MathNum.add((int)(Math.random()*adminForms.size()));
            for( int i=0;i<5;i++ ){
                int n = (int)(Math.random()*adminForms.size());
                for( int j=0;j<=MathNum.size();j++ ){
                    if( j == MathNum.size() ) {
                        MathNum.add(n);
                        break;
                    }
                    if( MathNum.get(j) == n ){
                        i--;
                        break;
                    }
                }
            }
            ArrayList<AdminForm> adminForms_copy = new ArrayList<>();
            for( int i=0;i<MathNum.size()&&i<5;i++ ){
                adminForms_copy.add(adminForms.get(MathNum.get(i)));
            }
            map.put("state", "success");
            map.put("adminForms", adminForms_copy);
        }
        result=objectMapper.writeValueAsString(map);
        return "success";
    }
}
