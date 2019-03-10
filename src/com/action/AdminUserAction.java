package com.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forms.KeyWordsForm;
import com.forms.UserForm;
import com.forms.UserPageForm;
import com.forms.pageForm;
import com.opensymphony.xwork2.ActionSupport;
import com.service.AdminUserManager;
import com.service.AlbumService;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ruihe on 16-5-26.
 */
public class AdminUserAction extends ActionSupport {
    private AdminUserManager adminUserManager;
    private AlbumService albumService;
    private UserForm userForm;
    private UserPageForm userPageForm;
    private KeyWordsForm keyWordsForm;
    private String result;

    public AdminUserManager getAdminUserManager() {
        return adminUserManager;
    }

    public void setAdminUserManager(AdminUserManager adminUserManager) {
        this.adminUserManager = adminUserManager;
    }

    public String getResult() {
        return result;
    }

    public AlbumService getAlbumService() {
        return albumService;
    }

    public void setAlbumService(AlbumService albumService) {
        this.albumService = albumService;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public UserForm getUserForm() {
        return userForm;
    }

    public void setUserForm(UserForm userForm) {
        this.userForm = userForm;
    }

    public UserPageForm getUserPageForm() {
        return userPageForm;
    }

    public void setUserPageForm(UserPageForm userPageForm) {
        this.userPageForm = userPageForm;
    }

    public KeyWordsForm getKeyWordsForm() {
        return keyWordsForm;
    }

    public void setKeyWordsForm(KeyWordsForm keyWordsForm) {
        this.keyWordsForm = keyWordsForm;
    }

    public String toDeleteUser() throws Exception{
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        int id=userForm.getId();
        if(id==0){
            map.put("state","error");
            result=objectMapper.writeValueAsString(map);
            return "error";
        }
        try{
            String dir="/imgs/"+id;
            String path = ServletActionContext.getServletContext().getRealPath(dir);
            File file=new File(path);
            boolean state=this.deleteDir(file);
            adminUserManager.deleteUser(id);
        }catch (Exception e){
            e.printStackTrace();
            map.put("state","error");
            result=objectMapper.writeValueAsString(map);
            return "error";
        }
        map.put("state","success");
        result=objectMapper.writeValueAsString(map);
        return "toDeleteUser";
    }

    public String findUser() throws Exception{
        ObjectMapper objectMapper=new ObjectMapper();
        Map map =new HashMap<>();
        List<UserForm> list;
        int startPage=0;
        try{
            String hqlCheck="from User where userName like '%"+keyWordsForm.getKeywords()
                    +"%' or id like '%"+keyWordsForm.getKeywords()
                    +"%' or userId like '%"+keyWordsForm.getKeywords()+"%'";
            list=adminUserManager.userPage(hqlCheck,startPage,10);
            map.put("data",list);
        }catch (Exception e){
            e.printStackTrace();
            map.put("state","error");
            result=objectMapper.writeValueAsString(map);
            return "error";
        }
        map.put("state","success");
        result=objectMapper.writeValueAsString(map);
        return "findUser";
    }

    public String userPage() throws Exception{
        ObjectMapper objectMapper=new ObjectMapper();
        Map map =new HashMap<>();
        List<UserForm> list;
        int page=userPageForm.getPage();
        try{
            String hqlCheck="from User";
            int allRow = albumService.getAllRow(hqlCheck);
            int totalPage = pageForm.countTatalPage(10, allRow); //总页数
            int currentPage = pageForm.countCurrentPage(page,totalPage); // 当前页
            int offset = pageForm.countOffset(10, currentPage); //当前页开始记录
            list=adminUserManager.userPage(hqlCheck,offset,10);
            map.put("data",list);
            map.put("currentPage",currentPage);
        }catch (Exception e){
            e.printStackTrace();
            map.put("state","error");
            result=objectMapper.writeValueAsString(map);
            return "error";
        }
        map.put("state","success");
        result=objectMapper.writeValueAsString(map);
        return "userPage";
    }
    //
    public boolean deleteDir(File dir){
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
}
