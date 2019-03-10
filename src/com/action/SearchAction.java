package com.action;

import com.beans.Album;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.forms.ImgForm;
import com.forms.UserForm;
import com.forms.pageForm;
import com.forms.SearchForm;
import com.opensymphony.xwork2.ActionSupport;
import com.service.AlbumService;
import com.service.UserManager;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SearchAction extends ActionSupport{
    private AlbumService albumService = null;
    private UserManager userManager = null;
    private pageForm pageForm = null;
    List<Album> Albums = null;
    List<SearchForm> SearchForms = null;
    private String page = null; //当前需要显示的页
    private String searchVal = null; //搜索关键词
    private String result = null;

    public AlbumService getAlbumService() {
        return albumService;
    }

    public void setAlbumService(AlbumService albumService) {
        this.albumService = albumService;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
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

    public List<SearchForm> getSearchForms() {
        return SearchForms;
    }

    public void setSearchForms(List<SearchForm> searchForms) {
        SearchForms = searchForms;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
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

    public String execute()throws Exception{
        //构建json
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        //设置jackson的ObjectMapper对时间类型的处理方式
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        objectMapper.setDateFormat(fmt);
        //查询语句
        final String hql = "FROM Album WHERE theme like '%"+searchVal+"%' OR" +
                " title like '%"+searchVal+"%' OR" +
                " albumIntroduction like '%"+searchVal+"%'" ;
        //获取封装了分页信息和数据的pageBean
        this.pageForm = albumService.queryForPage(4, Integer.parseInt(page), hql);
        this.Albums = this.pageForm.getList(); //获取数据
        //判断是否有搜索结果
        if( Albums == null || Albums.size()<=0 ){
            map.put("state", "error");
            result = objectMapper.writeValueAsString(map);
            return "error";
        }else{
            //重新封装Albums,使得符合search页面的需求
            SearchForms = AlbumsToSearchForm( Albums );
            map.put("state", "success");
            map.put("currentPage",pageForm.getCurrentPage() );
            map.put("totalPage",pageForm.getTotalPage() );
            map.put("SearchForms", SearchForms);
            result = objectMapper.writeValueAsString(map);
//            System.out.println(result);
            return "success";
        }
    }

    public List<SearchForm> AlbumsToSearchForm( List<Album> Albums ){
        List<SearchForm> SearchForms = new ArrayList<SearchForm>();
        SearchForm searchForm = null;
        UserForm userForm = null;
        String Name = null; //相册拥有着的名称
        String ImgeName = null; //展示图片的名字
        String ImgePath = null; //展示图片的路径
        for( Album album : Albums ){
            searchForm = new SearchForm();
            //获取当前相册拥有者的用户信息,通过用户id从数据库中获取用户的全部信息
            userForm = userManager.showInfoManager(album.getUserId());
            //检查该用户是否有昵称
            if(userForm.getUserName() == null || userForm.getUserName() == ""){
                Name = userForm.getUserId();
            }else{ Name = userForm.getUserName(); }
            //获取当前相册的相对路径Path
            String Path = ServletActionContext.getServletContext()
                    .getRealPath("/imgs/"+album.getUserId()+"/"+album.getTitle()+"/");
            //随机获取当前相册中的展示图片的名字
            ImgeName = ImgForm.getImgeName( Path );
            //将图片名字封装为搜索页面所需的路径
            if( ImgeName == null ) ImgePath = "";
            else ImgePath = "imgs/"+album.getUserId()+"/"+album.getTitle()+"/"+ImgeName;
            //开始重新封装Albums
            searchForm.setTheme(album.getTheme());
            searchForm.setTitle(album.getTitle());
            searchForm.setAlbumIntroduction(album.getAlbumIntroduction());
            searchForm.setGood(album.getGood());
            searchForm.setCreateTime(album.getCreateTime());
            searchForm.setOwner(Name);
            searchForm.setImge(ImgePath);
            //结束封装Albums，albumForm压入AlbumForms中
            SearchForms.add(searchForm);
        }
        return SearchForms;
    }
}
