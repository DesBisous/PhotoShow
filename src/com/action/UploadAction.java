package com.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forms.AlbumForm;
import com.forms.GraphForm;
import com.forms.ImgForm;
import com.opensymphony.xwork2.ActionSupport;
import com.service.UploadManager;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class UploadAction extends ActionSupport{
    private ImgForm imgForm;
    private String result;
    private UploadManager uploadManager;
    private AlbumForm albumForm;
    private SessionAction sessionAction=new SessionAction();

    public ImgForm getImgForm() {
        return imgForm;
    }

    public void setImgForm(ImgForm imgForm) {
        this.imgForm = imgForm;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public UploadManager getUploadManager() {
        return uploadManager;
    }

    public void setUploadManager(UploadManager uploadManager) {
        this.uploadManager = uploadManager;
    }

    public AlbumForm getAlbumForm() {
        return albumForm;
    }

    public void setAlbumForm(AlbumForm albumForm) {
        this.albumForm = albumForm;
    }

    /**
     * 上传单照片并命名为head(用户)
     * @return
     * @throws Exception
     */
    public String uploadImg() throws Exception{
        int id=sessionAction.judgeSession();
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        if(id==0){
            map.put("state","error");
            result=objectMapper.writeValueAsString(map);
        }
        String idStr=String.valueOf(id);
        String relativePath="/imgs/"+idStr+"/";
        String userHeadImg="";
        String path = ServletActionContext.getServletContext().getRealPath(relativePath);
        File file=new File(path);
        if(!file.exists()){
            file.mkdir();
        }
        try {
            for (int i = 0; i < imgForm.getUpload().size(); i++) {
                String name = imgForm.getUploadFileName().get(i);
                int len = name.lastIndexOf(".");
                String type = name.substring(len);
                String head = "head" + type;
                userHeadImg=head;
                if(uploadManager.saveHeadImg(id,userHeadImg)){
                    FileUtils.copyFile(imgForm.getUpload().get(i), new File(file, head));
                }else{
                    map.put("state","error");
                    result=objectMapper.writeValueAsString(map);
                    return "error";
                }
            }
        }catch (Exception e){
            map.put("state","error");
            result=objectMapper.writeValueAsString(map);
            return "error";
        }
        map.put("state","success");
        result=objectMapper.writeValueAsString(map);
        return "uploadImg";
    }
    /**
     * 上传单照片并命名为head(管理员)
     * @return
     * @throws Exception
     */
    public String uploadImgAdmin() throws Exception{
        int id=sessionAction.judgeAdminSession();
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        if(id==0){
            map.put("state","error");
            map.put("mgs","系统异常！1");
            result=objectMapper.writeValueAsString(map);
            return "error";
        }
        String idStr=String.valueOf(id);
        String relativePath="/PhotoShowMgr/imgs/"+idStr+"/";
        String path = ServletActionContext.getServletContext().getRealPath(relativePath);
        File file=new File(path);
        if(!file.exists()){
            file.mkdir();
        }
        try {
            for (int i = 0; i < imgForm.getUpload().size(); i++) {
                String name = imgForm.getUploadFileName().get(i);
                int len = name.lastIndexOf(".");
                String type = name.substring(len);
                String head = "head" + type;
                FileUtils.copyFile(imgForm.getUpload().get(i), new File(file, head));
            }
        }catch (Exception e){
            map.put("state","error");
            map.put("mgs","系统异常！2");
            result=objectMapper.writeValueAsString(map);
            return "error";
        }
        map.put("state","success");
        map.put("mgs","注册成功");
        result=objectMapper.writeValueAsString(map);
        return "uploadImg";
    }
    /**
     * 上传单照片应用Graph
     * @return
     * @throws Exception
     */
    public String uploadImgGraph() throws Exception{
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        GraphForm graph = sessionAction.haveGraphSession();
        String relativePath;
        switch (graph.getNote()){
            case "indexGraph": relativePath="/images/index/"; break;
            case "searchGraph": relativePath="/images/searchImg/LunBO/"; break;
            default: relativePath="/images/index/"; break;
        }
        int order = Integer.parseInt(graph.getOrder());
        if( (order>3 && order<7) || (order>9 && order<13) ) relativePath = relativePath+"small/";
        String path = ServletActionContext.getServletContext().getRealPath(relativePath);
        if( order>0 && order<7 ){
            if( order%3 == 1 )
                FileUtils.copyFile(imgForm.getUpload().get(0), new File(new File(path), "walle.jpg"));
            if( order%3 == 2 )
                FileUtils.copyFile(imgForm.getUpload().get(0), new File(new File(path), "toystory.jpg"));
            if( order%3 == 0 )
                FileUtils.copyFile(imgForm.getUpload().get(0), new File(new File(path), "nemo.jpg"));
        }else{
            if( order%3 == 1 )
                FileUtils.copyFile(imgForm.getUpload().get(0), new File(new File(path), "1.jpg"));
            if( order%3 == 2 )
                FileUtils.copyFile(imgForm.getUpload().get(0), new File(new File(path), "2.jpg"));
            if( order%3 == 0 )
                FileUtils.copyFile(imgForm.getUpload().get(0), new File(new File(path), "3.jpg"));
        }
        map.put("state","success");
        map.put("mgs","success");
        result=objectMapper.writeValueAsString(map);
        return "uploadImg";
    }
    /**
     * 上传单照片应用Daren
     * @return
     * @throws Exception
     */
    public String uploadImgDaren() throws Exception{
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        GraphForm graph = sessionAction.haveGraphSession();
        String relativePath;
        switch (graph.getNote()){
            case "Save1": relativePath="/images/index/Present1.png"; break;
            case "Save2": relativePath="/images/index/Present2.png"; break;
            default: relativePath="/images/index/Present1.png"; break;
        }
        String path = ServletActionContext.getServletContext().getRealPath(relativePath);
        FileUtils.copyFile(imgForm.getUpload().get(0), new File(path));
        map.put("state","success");
        map.put("mgs","success");
        result=objectMapper.writeValueAsString(map);
        return "uploadImg";
    }
    /**
     * 上传相册相关信息
     * @return
     * @throws Exception
     */
    public String uploadInfo() throws Exception{
        int id=sessionAction.judgeSession();
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        if(id==0){
            map.put("state","error");
            result=objectMapper.writeValueAsString(map);
            return "error";
        }
        try {
            Timestamp dateTime=new Timestamp(System.currentTimeMillis());
            albumForm.setUserId(id);
            albumForm.setGood(0);
            albumForm.setCreateTime(dateTime);
            sessionAction.addUploadSession(albumForm);
            boolean isExist=uploadManager.findAlbumByName(id,albumForm.getTitle());
            if(isExist==true){
                uploadManager.updateAlbumByName(albumForm);
                map.put("state","success");
                result=objectMapper.writeValueAsString(map);
                return "uploadAlbum";
            }
            uploadManager.saveGalleryInfo(albumForm);
            sessionAction.addUploadSession(albumForm);
        }catch (Exception e){
            e.printStackTrace();
            map.put("state","error");
            result=objectMapper.writeValueAsString(map);
            return "error";
        }
        map.put("state","success");
        result=objectMapper.writeValueAsString(map);
        return "uploadInfo";
    }

    /**
     * 上传相册
     * @return
     * @throws Exception
     */
    public String uploadAlbum() throws Exception{
        int id=sessionAction.judgeSession();
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        String path="";
        if(id==0){
            map.put("state","error");
            result=objectMapper.writeValueAsString(map);
            return "error";
        }
        String idStr=String.valueOf(id);
        try {
            albumForm =sessionAction.haveUploadSession();
            path = ServletActionContext.getServletContext().getRealPath("/imgs/"+idStr+"/"+ albumForm.getTitle()+"/");
            sessionAction.deleteUploadSession();
            int num=this.realAlbumNum(ServletActionContext.getServletContext().getRealPath("/imgs/"+idStr+"/"));
            uploadManager.updateAlbumNum(id,num);
            File file=new File(path);
            if(!file.exists()){
                file.mkdir();
            }
            for (int i = 0; i < imgForm.getUpload().size(); i++) {
                FileUtils.copyFile(imgForm.getUpload().get(i), new File(file,imgForm.getUploadFileName().get(i)));
            }
            this.deleteAlbum();
        }catch (Exception e){
            e.printStackTrace();
            map.put("state","error");
            result=objectMapper.writeValueAsString(map);
            return "error";
        }
        map.put("state","success");
        result=objectMapper.writeValueAsString(map);
        return "uploadAlbum";
    }

    /**
     * 计算个人相册个数
     * @param path 个人相册绝对地址
     * @return
     * @throws Exception
     */
    public int realAlbumNum(String path) throws Exception{
        File file=new File(path);
        String[] files=file.list();
        int num=files.length;
        return num;
    }

    /**
     * 判断数据库相册地址是否存在，并通过调用其他函数删除
     * @throws Exception
     */
    public void deleteAlbum() throws Exception{
        int id=sessionAction.judgeSession();
        ArrayList<AlbumForm> albumFormsArr;
        String idStr=String.valueOf(id);
        String path="";
        albumFormsArr=uploadManager.haveAlbumAddr(id);
        for (int i=0;i<albumFormsArr.size();i++){
            path=ServletActionContext.getServletContext().getRealPath("/imgs/"+idStr+"/"+albumFormsArr.get(i).getTitle()+"/");
            File file=new File(path);
            if(!file.exists()){
                uploadManager.deleteUnexistsData(albumFormsArr.get(i).getId());
            }
        }
    }
}
