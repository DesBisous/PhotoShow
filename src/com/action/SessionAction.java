package com.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forms.*;
import com.opensymphony.xwork2.ActionContext;

import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.Map;

import static org.apache.struts2.ServletActionContext.getServletContext;


public class SessionAction {
    public String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    /**
     * 判断用户是否登录
     * @return
     */
    public int judgeSession(){
        LoginForm loginSession=null;
        try{
            if(ActionContext.getContext().getSession().get("user")==null){
                return 0;
            }
            loginSession= (LoginForm) ActionContext.getContext().getSession().get("user");
        }catch (Exception e){
            return 0;
        }
        return loginSession.getId();
    }
    /**
     * 判断管理员是否登录
     * @return
     */
    public int judgeAdminSession(){
        AdminForm loginSession=null;
        try{
            if(ActionContext.getContext().getSession().get("admin")==null){
                return 0;
            }
            loginSession= (AdminForm) ActionContext.getContext().getSession().get("admin");
        }catch (Exception e){
            return 0;
        }
        return loginSession.getId();
    }

    /**
     * 把登录用户部分信息保存到名为user/admin的session中
     * @param loginSession
     */
    public void addSession(LoginForm loginSession){
        ActionContext.getContext().getSession().put("user",loginSession);
    }
    public void addAdminSession(AdminForm adminSession){
        ActionContext.getContext().getSession().put("admin",adminSession);
    }
    /**
     * 读取session对象user
     * @return
     */
    public LoginForm haveSession(){
        LoginForm loginSession= (LoginForm) ActionContext.getContext().getSession().get("user");
        return loginSession;
    }
    public AdminForm haveAdminSession(){
        AdminForm adminSession= (AdminForm) ActionContext.getContext().getSession().get("admin");
        return adminSession;
    }
    public String online(){
        ServletContext context= getServletContext();
        Integer count=(Integer)context.getAttribute("count");
        if(count==null){
            count=0;
        }
        return Integer.toString(count);
    }
    /**
     * 从session中删除user对象
     * @return
     * @throws Exception
     */
    public String exitSession() throws Exception{
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        ActionContext.getContext().getSession().remove("user");
        map.put("state", "success");
        result=objectMapper.writeValueAsString(map);
        return "exitSession";
    }
    /**
     * 从session中删除admin对象
     * @return
     * @throws Exception
     */
    public boolean exitAdminSession(){
        ActionContext.getContext().getSession().remove("admin");
        return true;
    }

    /**
     * 向浏览器发送是否已登录的信息
     * @return
     * @throws Exception
     */
    public String existSession()throws Exception{
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        if(this.judgeSession()!=0){
            LoginForm loginSession=this.haveSession();
            map.put("state", "success");
            map.put("userId",loginSession.getUserId());
            map.put("userName",loginSession.getUserName());
            result=objectMapper.writeValueAsString(map);
            return "existSession";
        }
        map.put("state","error");
        result=objectMapper.writeValueAsString(map);
        return "error";
    }

    /**
     * 保存相册基本信息到session
     * @param albumForm
     * @throws Exception
     */
    public void addUploadSession(AlbumForm albumForm) throws Exception{
        ActionContext.getContext().getSession().put("albumForm", albumForm);
    }

    /**
     * 从session中删除相册基本信息
     * @throws Exception
     */
    public void deleteUploadSession() throws Exception{
        ActionContext.getContext().getSession().remove("albumForm");
    }

    /**
     * 判断session中是否存在相册基本信息
     * @return
     * @throws Exception
     */
    public AlbumForm haveUploadSession() throws Exception{
        AlbumForm albumForm=(AlbumForm) ActionContext.getContext().getSession().get("albumForm");
        return albumForm;
    }

    /**
     * 保存相册名到session中
     * @param addressForm
     * @throws Exception
     */
    public void addAddressSession(AddressForm addressForm) throws Exception{
        ActionContext.getContext().getSession().put("addressForm", addressForm);
    }

    /**
     * 从Session中删除相册名
     * @throws Exception
     */
    public void deletAddressSession() throws Exception{
        ActionContext.getContext().getSession().remove("addressForm");
    }

    /**
     * 判断session中是否存在相册名
     * @return
     * @throws Exception
     */
    public AddressForm haveAddressSession(){
        AddressForm addressForm = new AddressForm();
        try{
            if(ActionContext.getContext().getSession().get("addressForm")==null){
                addressForm.setAddress("null");
                return addressForm;
            }
            addressForm= (AddressForm) ActionContext.getContext().getSession().get("addressForm");
        }catch (Exception e){
            addressForm.setAddress(null);
            return  addressForm;
        }
        return addressForm;
    }
    /**
     * 保存Graph到session中
     * @param graph
     * @throws Exception
     */
    public void addGraphSession(GraphForm graph) throws Exception{
        ActionContext.getContext().getSession().put("Graph", graph);
    }

    /**
     * 从Session中删除Graph
     * @throws Exception
     */
    public void deletGraphSession() throws Exception{
        ActionContext.getContext().getSession().remove("Graph");
    }

    /**
     * 判断session中是否存在Graph
     * @return Graph
     * @throws Exception
     */
    public GraphForm haveGraphSession() throws Exception{
        GraphForm graph=(GraphForm) ActionContext.getContext().getSession().get("Graph");
        return graph;
    }
}
