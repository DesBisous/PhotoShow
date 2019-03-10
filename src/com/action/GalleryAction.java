package com.action;

import com.beans.Album;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.forms.GalleryForm;
import com.forms.ImgForm;
import com.forms.pageForm;
import com.opensymphony.xwork2.ActionSupport;
import com.service.AlbumService;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GalleryAction extends ActionSupport {
    private AlbumService albumService = null;
    private pageForm pageForm = null;
    List<Album> Albums = null;
    List<GalleryForm> GalleryForms = null;
    private String pageSize = null;//当前页需要的记录数
    private String searchVal = null; //搜索关键词
    private String result = null;

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

    public List<Album> getAlbums() {
        return Albums;
    }

    public void setAlbums(List<Album> albums) {
        Albums = albums;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getSearchVal() {
        return searchVal;
    }

    public void setSearchVal(String searchVal) {
        this.searchVal = searchVal;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<GalleryForm> getGalleryForms() {
        return GalleryForms;
    }

    public void setGalleryForms(List<GalleryForm> galleryForms) {
        GalleryForms = galleryForms;
    }

    public String execute() throws Exception{
        //构建json
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        //查询语句
        final String hql = "FROM Album WHERE theme like '%"+searchVal+"%'";
        // 获取总记录数
        int allRow = albumService.getAllRow(hql);
        // 获取总页数
        int totalPage = pageForm.countTatalPage(Integer.parseInt( pageSize ), allRow);
        //防止选择了最后一页，而最一页只有几个相册的情况
        if( totalPage!=1 ){
            if( allRow-pageForm.countOffset(Integer.parseInt( pageSize ),totalPage)<Integer.parseInt( pageSize ) ){
                totalPage = totalPage-1;
            }
        }
        // 获取当前随机显示页码，即模拟相册更新随机选取
        int page = (int)(Math.random() * totalPage+1); //用Matn.random()方式,大于等于 0.0 且小于 1.0
        //获取封装了分页信息和数据的pageBean
        this.pageForm = albumService.queryForPage(Integer.parseInt( pageSize ), page, hql);
        //获取数据
        this.Albums = this.pageForm.getList();
        //判断是否有搜索结果
        if( Albums == null || Albums.size()<=0 ){
            map.put("state", "error");
            result = objectMapper.writeValueAsString(map);
            return "error";
        }else{
            //重新封装Albums,使得符合Gallery页面的需求
            GalleryForms = AlbumsToGalleryForm( Albums );
            map.put("state", "success");
            map.put("GalleryForms", GalleryForms);
            result = objectMapper.writeValueAsString(map);
//            for( GalleryForm GalleryForm : GalleryForms ){
//                System.out.println(GalleryForm.toString());
//            }
//            System.out.println(result);
            return "success";
        }
    }
    public List<GalleryForm> AlbumsToGalleryForm( List<Album> Albums ){
        List<GalleryForm> GalleryForms = new ArrayList<GalleryForm>();
        String ImgeName = null; //展示图片的名字
        String ImgePath = null; //展示图片的路径
        GalleryForm galleryForm = null;
        for( Album album : Albums ){
            galleryForm = new GalleryForm();
            //获取当前相册的相对路径Path
            String Path = ServletActionContext.getServletContext()
                    .getRealPath("/imgs/"+album.getUserId()+"/"+album.getTitle()+"/");
            //随机获取当前相册中的展示图片的名字
            ImgeName = ImgForm.getImgeName( Path );
            //将图片名字封装为搜索页面所需的路径
            if( ImgeName == null ) ImgePath = "";
            else ImgePath = "imgs/"+album.getUserId()+"/"+album.getTitle()+"/"+ImgeName;
            //开始重新封装galleryForm
            galleryForm.setUserId(album.getUserId());
            galleryForm.setTitle(album.getTitle());
            galleryForm.setImgePath(ImgePath);
            GalleryForms.add(galleryForm);
        }
        return GalleryForms;
    }
}
