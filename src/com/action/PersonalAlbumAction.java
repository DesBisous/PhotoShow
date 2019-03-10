package com.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forms.*;
import com.opensymphony.xwork2.ActionSupport;
import com.service.AlbumService;
import com.service.UploadManager;
import com.service.UserManager;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PersonalAlbumAction extends ActionSupport {
    private AlbumService albumService;
    private pageForm pageForm;
    private AddressForm addressForm;
    private UserManager userManager;
    private UploadManager uploadManager;
    private SessionAction sessionAction=new SessionAction();
    private String result;

    public AlbumService getAlbumService() {
        return albumService;
    }

    public void setAlbumService(AlbumService albumService) {
        this.albumService = albumService;
    }

    public com.forms.pageForm getPageForm() {
        return pageForm;
    }

    public void setPageForm(com.forms.pageForm pageForm) {
        this.pageForm = pageForm;
    }

    public AddressForm getAddressForm() {
        return addressForm;
    }

    public void setAddressForm(AddressForm addressForm) {
        this.addressForm = addressForm;
    }

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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    /**
     *以分页形式带回相册表数据
     * @return
     * @throws Exception
     */
    public String personalAlbumShow() throws Exception{
        ObjectMapper objectMapper=new ObjectMapper();
        List<PersonalForm> PersonalForms =new ArrayList<>();
        Map map=new HashMap<>();
        pageForm isPage=new pageForm();
        try {
            int id=sessionAction.judgeSession();
            String hql="from Album where userId="+id;
            isPage=albumService.queryForPage(4, pageForm.getCurrentPage(),hql);
            String ImgeName = null; //展示图片的名字
            String ImgePath = null; //展示图片的路径
            for(int i=0;i<isPage.getList().size();i++){
                PersonalForm personalForm=new PersonalForm();
                BeanUtils.copyProperties(isPage.getList().get(i),personalForm);
                //获取当前相册的相对路径Path
                String Path = ServletActionContext.getServletContext()
                        .getRealPath("/imgs/"+personalForm.getUserId()+"/"+personalForm.getTitle()+"/");
                //随机获取当前相册中的展示图片的名字
                ImgeName = ImgForm.getImgeName( Path );
                //将图片名字封装为搜索页面所需的路径
                if( ImgeName == null ) ImgePath = "";
                else ImgePath = "imgs/"+personalForm.getUserId()+"/"+personalForm.getTitle()+"/"+ImgeName;
                personalForm.setImge(ImgePath);
                PersonalForms.add(personalForm);
            }
        }catch (Exception e){
            map.put("state","error");
            result=objectMapper.writeValueAsString(map);
            return "error";
        }
        map.put("currentPage",isPage.getCurrentPage());
        map.put("PersonalForms",PersonalForms);
        result=objectMapper.writeValueAsString(map);
        return "personalAlbumShow";
    }

    /**
     * 带回一个相册中所有相片的地址
     * @return
     * @throws Exception
     */
    public String showAnAlbum() throws Exception{
        List<AddressForm> addrList=new ArrayList<>();
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        AddressForm addressForm1=new AddressForm();
        addressForm1.setAddress(addressForm.getAddress());
        if("1".equals(addressForm1.getAddress())){
            addressForm1.setAddress(null);
        }
        AddressForm addr=sessionAction.haveAddressSession();
        if(addr.getAddress()==null){
            sessionAction.addAddressSession(addressForm1);
        }else if(addressForm1.getAddress()!=null){
            sessionAction.deletAddressSession();
            sessionAction.addAddressSession(addressForm1);
        }
        try{
            int id=sessionAction.judgeSession();
            addr=sessionAction.haveAddressSession();
            String path= ServletActionContext.getServletContext().getRealPath("/imgs/"+id+"/"+addr.getAddress()+"/");
            //System.out.println(path);
            File file=new File(path);
            File[] files=file.listFiles();
            //System.out.println(files.length);
            for(int i=0;i<files.length;i++){
                //System.out.println(files.length);
                AddressForm address=new AddressForm();
                if(files[i].isFile()){
                    String relativePath="/imgs/"+id+"/"+addr.getAddress()+"/"+files[i].getName();
                    address.setAddress(relativePath);
                    addrList.add(i,address);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("state","error");
            result=objectMapper.writeValueAsString(map);
            return "error";
        }
        result=objectMapper.writeValueAsString(addrList);
        return "showAnAlbum";
    }

    /**
     * 获取个人数据及其一个相册数据
     * @return
     * @throws Exception
     */
    public String anAlbumInfo() throws Exception{
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        try{
            AddressForm addr=sessionAction.haveAddressSession();
            int id=sessionAction.judgeSession();
            System.out.println(id);
            UserForm userForm=userManager.showInfoManager(id);
            AlbumForm albumForm=uploadManager.findAlbumByTitle(id,addr.getAddress());
            map.put("userId",userForm.getUserId());
            map.put("title",albumForm.getTitle());
            map.put("albumIntroduction",albumForm.getAlbumIntroduction());
            map.put("good",albumForm.getGood());
            String time=albumForm.getCreateTime().toString();
            time=time.substring(0,10);
            map.put("createTime",time);
        }catch (Exception e){
            e.printStackTrace();
            map.put("state","error");
            result=objectMapper.writeValueAsString(map);
            return "error";
        }
        result=objectMapper.writeValueAsString(map);
        return "anAlbumInfo";
    }
}
