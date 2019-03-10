package com.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forms.ImgForm;
import com.forms.UserAndAlbumForm;
import com.forms.UserForm;
import com.opensymphony.xwork2.ActionSupport;
import com.service.AdminHomeManager;
import com.service.AlbumService;
import com.service.UserManager;
import org.apache.struts2.ServletActionContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ruihe on 16-5-21.
 */
public class AdminHomeAction extends ActionSupport {

    private UserManager userManager;
    private AdminHomeManager adminHomeManager;
    private AlbumService albumService;
    private SessionAction sessionAction=new SessionAction();
    private String result;

    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public AdminHomeManager getAdminHomeManager() {
        return adminHomeManager;
    }

    public void setAdminHomeManager(AdminHomeManager adminHomeManager) {
        this.adminHomeManager = adminHomeManager;
    }

    public AlbumService getAlbumService() {
        return albumService;
    }

    public void setAlbumService(AlbumService albumService) {
        this.albumService = albumService;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String onLine() throws Exception{
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        String isOnLine=sessionAction.online();
        System.out.println("isOnLine===>"+isOnLine);
        long isAllUser=0;
        try{
            isAllUser=userManager.allUserNum();
        }catch (Exception e){
            e.printStackTrace();
            map.put("state","error");
            result=objectMapper.writeValueAsString(map);
            return "error";
        }
        if(isOnLine==null){
            isOnLine="0";
        }
        map.put("state","success");
        map.put("online",isOnLine);
        map.put("allUser",isAllUser);
        result=objectMapper.writeValueAsString(map);
        return "online";
    }

    public String sortsOfAlbum() throws Exception{
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        long tour=0;
        long life=0;
        long natural=0;
        long camp=0;
        long party=0;
        long other=0;
        try{
            tour=adminHomeManager.numOfAlbum("%旅游%");
            life=adminHomeManager.numOfAlbum("%生活%");
            natural=adminHomeManager.numOfAlbum("%自然%");
            camp=adminHomeManager.numOfAlbum("%野营%");
            party=adminHomeManager.numOfAlbum("%聚会%");
            other=adminHomeManager.numOfAlbum("%其他%");
            map.put("tour",tour);
            map.put("life",life);
            map.put("natural",natural);
            map.put("camp",camp);
            map.put("party",party);
            map.put("other",other);
        }catch (Exception e){
            e.printStackTrace();
            map.put("state","error");
            result=objectMapper.writeValueAsString(map);
            return "error";
        }
        map.put("state","success");
        result=objectMapper.writeValueAsString(map);
        return "sortsOfAlbum";
    }

    public String maxNumOrderBy() throws Exception{
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        try{
            List<UserAndAlbumForm> list=adminHomeManager.maxNumOrderBy();
            for( int i=0;i<list.size();i++ ){
                //获取当前相册的相对路径Path
                String Path = ServletActionContext.getServletContext()
                        .getRealPath("/imgs/"+list.get(i).getId()+"/"+list.get(i).getTitle()+"/");
                //随机获取当前相册中的展示图片的名字
                list.get(i).setUserHeadImg(ImgForm.getImgeName( Path ));
            }
            for(int i=0;i<list.size();i++){
                map.put(i,list.get(i));
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("state","error");
            result=objectMapper.writeValueAsString(map);
            return "error";
        }
        map.put("state","success");
        result=objectMapper.writeValueAsString(map);
        return "maxNumOrderBy";
    }

    public String betterUser() throws Exception{
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        try{
            List<UserForm> list=adminHomeManager.betterUser();
            map.put("data",list);
        }catch (Exception e){
            e.printStackTrace();
            map.put("state","error");
            result=objectMapper.writeValueAsString(map);
            return "error";
        }
        map.put("state","success");
        result=objectMapper.writeValueAsString(map);
        return "betterUser";
    }

    //主页其他和非其他相册统计
    public String TwoTypeAlbum() throws Exception{
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        String Hql = "FROM Album WHERE theme = '其他' ";
        int OtherTheme = albumService.getAllRow(Hql);
        Hql = "FROM Album";
        int NoOtherTheme =  albumService.getAllRow(Hql)-OtherTheme;
        map.put("state","success");
        map.put("OtherTheme",OtherTheme);
        map.put("NoOtherTheme",NoOtherTheme);
        result=objectMapper.writeValueAsString(map);
        return "twoTypeAlbum";
    }
}
