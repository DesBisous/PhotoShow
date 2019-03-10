package com.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forms.*;
import com.opensymphony.xwork2.ActionSupport;
import com.service.HomeManager;
import com.service.UploadManager;
import com.service.UserManager;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.text.DecimalFormat;
import java.util.*;


public class HomeAction extends ActionSupport {
    private UserManager userManager;
    private UploadManager uploadManager;
    private HomeManager homeManager;
    private AddressForm addressForm;
    private String result;

    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public UploadManager getUploadManager() {
        return uploadManager;
    }

    public void setUploadManager(UploadManager uploadManager) {
        this.uploadManager = uploadManager;
    }

    public HomeManager getHomeManager() {
        return homeManager;
    }

    public void setHomeManager(HomeManager homeManager) {
        this.homeManager = homeManager;
    }

    public AddressForm getAddressForm() {
        return addressForm;
    }

    public void setAddressForm(AddressForm addressForm) {
        this.addressForm = addressForm;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    /**
     * 主页百分比显示
     * @return
     * @throws Exception
     */
    public String showPercent() throws Exception{
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        DecimalFormat decimalFormat=new DecimalFormat(".00");
        float all=0;
        float america=0;
        float china=0;
        float britain=0;
        float australia=0;
        try {
            all=homeManager.countAddress("%%");
            america=homeManager.countAddress("%美国%");
            china=homeManager.countAddress("%中国%");
            britain=homeManager.countAddress("%印度%");
            australia=homeManager.countAddress("%澳洲%");
            if(all!=america && all!=china && all!=britain && all!=australia){
                america=(america/all)*100;
                china=(china/all)*100;
                britain=(britain/all)*100;
                australia=(australia/all)*100;
            } else {
                america=25;
                china=25;
                britain=25;
                australia=25;
            }
            map.put("america",decimalFormat.format(america));
            map.put("china",decimalFormat.format(china));
            map.put("britain",decimalFormat.format(britain));
            map.put("australia",decimalFormat.format(australia));
        }catch (Exception e){
            map.put("state","error");
            result=objectMapper.writeValueAsString(map);
            return "error";
        }
        map.put("state","success");
        result=objectMapper.writeValueAsString(map);
        return "showPercent";
    }

    /**
     * 显示6个相册
     * @return
     * @throws Exception
     */
    public String showSixAlbum() throws Exception{
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        List<AlbumForm> list=new ArrayList<>();
        List<String> albumStr=new ArrayList<>();
        try {
            list=homeManager.allManAlbum();
            for(int i=0;i<6;i++){
                Random random=new Random();
                int ran=random.nextInt(list.size())%(list.size()+1);
                if(ran>list.size()){
                    i--;
                    continue;
                }else {
                    String address = "/imgs/" + list.get(ran).getUserId() + "/" + list.get(ran).getTitle() + "/";
                    String path = ServletActionContext.getServletContext().getRealPath(address);

                    File file = new File(path);
                    if(!file.exists()){
                        i--;
                        continue;
                    }
                    File[] files = file.listFiles();
                    address = address + files[0].getName();
                    albumStr.add(i, address);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("state","error");
            result=objectMapper.writeValueAsString(map);
            return "error";
        }
        result=objectMapper.writeValueAsString(albumStr);
        return "showSixAlbum";
    }

    /**
     * 用于主页点击查看相册
     * @return
     * @throws Exception
     */
    public String showAlbumFromHome() throws Exception{
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        //System.out.println("fdjsfljdslkfj");
        String str=addressForm.getAddress();
        str=str.substring(1);
        //0,1,2
        String[] s=str.split("/");
        int userId=0;
        System.out.println(s[1]+" "+s[2]);
        UserForm userForm=new UserForm();
        AlbumForm albumForm=new AlbumForm();
        List<AddressForm> addrList=new ArrayList<>();
        try{
            userId= Integer.parseInt(s[1]);
            albumForm=uploadManager.findAlbumByTitle(userId,s[2]);
            userForm=userManager.showInfoManager(userId);
            map.put("userId",userForm.getUserId());
            map.put("title",albumForm.getTitle());
            map.put("albumIntroduction",albumForm.getAlbumIntroduction());
            map.put("good",albumForm.getGood());
            String time=albumForm.getCreateTime().toString();
            time=time.substring(0,10);
            map.put("createTime",time);

            String path= ServletActionContext.getServletContext().getRealPath("/imgs/"+s[1]+"/"+albumForm.getTitle()+"/");
            File file=new File(path);
            File[] files=file.listFiles();
            for(int i=0;i<files.length;i++){
                AddressForm address=new AddressForm();
                if(files[i].isFile()){
                    String relativePath="/imgs/"+s[1]+"/"+albumForm.getTitle()+"/"+files[i].getName();
                    address.setAddress(relativePath);
                    addrList.add(i,address);
                }
            }
            map.put("address",addrList);
        }catch (Exception e){
            e.printStackTrace();
            map.put("state","error");
            result=objectMapper.writeValueAsString(map);
            return "error";
        }
         result=objectMapper.writeValueAsString(map);
         return "showAlbumFromHome";
    }

    /**
     * 获取达人信息
     * @return
     * @throws Exception
     */
    public String Masterinfo()throws Exception{
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        List<MasterinfoForm> masterinfoForms = homeManager.getMasterin();
        if( masterinfoForms != null ){
            map.put("state","success");
            map.put("masterinfoForms",masterinfoForms);
        }else{
            map.put("state","error");
        }
        result=objectMapper.writeValueAsString(map);
        return "Masterinfo";
    }

    public String twoAlbum() throws Exception{
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        List<AlbumForm> albumForm;
        List<TowAlbumForm> towAlbumForm=new ArrayList<>();
        try{
            albumForm=homeManager.towAlbum();
            for(int i=0;i<albumForm.size();i++){
                String path= ServletActionContext.getServletContext().getRealPath("/imgs/"+
                        albumForm.get(i).getUserId()+"/"+albumForm.get(i).getTitle()+"/");
                File file=new File(path);
                File[] files=file.listFiles();
                String relativePath="/imgs/"+albumForm.get(i).getUserId()+"/"+
                        albumForm.get(i).getTitle()+"/"+files[i].getName();
                TowAlbumForm towAlbum=new TowAlbumForm();
                towAlbum.setTheme(albumForm.get(i).getTheme());
                towAlbum.setTitle(albumForm.get(i).getTitle());
                towAlbum.setAlbumIntroduction(albumForm.get(i).getAlbumIntroduction());
                towAlbum.setPath(relativePath);
                towAlbumForm.add(i,towAlbum);
            }
            map.put("data",towAlbumForm);
        }catch (Exception e){
            e.printStackTrace();
            map.put("state","error");
            result=objectMapper.writeValueAsString(map);
            return "error";
        }
        map.put("state","success");
        result=objectMapper.writeValueAsString(map);
        return "twoAlbum";
    }
}
