package com.action;

import com.beans.Suggestionbox;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.forms.SuggestionForm;
import com.forms.pageForm;
import com.forms.UserForm;
import com.service.AdminManager;
import com.service.UserManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Benson on 2016/5/26.
 */
public class CommentsAction {
    private AdminManager adminManager;
    private UserManager userManager;
    private String page = "1"; //当前需要显示的页
    private String suggestId; //需要删除的用户建议ID
    private String replay; //回复属性
    List<Suggestionbox> Suggestionboxs = null;
    private pageForm pageForm;
    private String result;

    public AdminManager getAdminManager() {
        return adminManager;
    }

    public void setAdminManager(AdminManager adminManager) {
        this.adminManager = adminManager;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getSuggestId() {
        return suggestId;
    }

    public void setSuggestId(String suggestId) {
        this.suggestId = suggestId;
    }

    public String getReplay() {
        return replay;
    }

    public void setReplay(String replay) {
        this.replay = replay;
    }

    public com.forms.pageForm getPageForm() {
        return pageForm;
    }

    public void setPageForm(com.forms.pageForm pageForm) {
        this.pageForm = pageForm;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    /**
     * 统计各种类建议数量
     * @return
     */
    public String StatisticsSuggestion(){
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        List<SuggestionForm> SuggestionForm1 = adminManager.commentsManager(1);
        List<SuggestionForm> SuggestionForm2 = adminManager.commentsManager(2);
        List<SuggestionForm> SuggestionForm3 = adminManager.commentsManager(3);
        List<SuggestionForm> SuggestionForm0 = adminManager.commentsManager(0);
        map.put("state","success");
        map.put("SuggestionForm1",SuggestionForm1.size());
        map.put("SuggestionForm2",SuggestionForm2.size());
        map.put("SuggestionForm3",SuggestionForm3.size());
        map.put("SuggestionForm0",SuggestionForm0.size());
        try {
            result=objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            map.put("state","error");
        }
        return "success";
    }
    /**
     * 用户建议分页查询
     * @return
     */
    public String QueryForPageSuggest() throws Exception {
        //构建json
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        //设置jackson的ObjectMapper对时间类型的处理方式
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        objectMapper.setDateFormat(fmt);
        //查询语句
        final String hql = "FROM Suggestionbox";
        //获取封装了分页信息和数据的pageBean
        pageForm = adminManager.queryForPageSuggest(12, Integer.parseInt(page), hql);
        Suggestionboxs = this.pageForm.getListSuggestionbox(); //获取数据
        //判断是否有搜索结果
        if( Suggestionboxs == null || Suggestionboxs.size()<=0 ){
            map.put("state", "error");
            result = objectMapper.writeValueAsString(map);
            return "error";
        }else{
            map.put("state", "success");
            map.put("currentPage",pageForm.getCurrentPage() );
            map.put("Suggestionboxs", ChangeInfo(Suggestionboxs));
            result = objectMapper.writeValueAsString(map);
            return "success";
        }
    }

    /**
     * 删除用户建议
     * @return
     * @throws Exception
     */
    public String DeleteUserSuggest() throws Exception{
        //构建json
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        String[] suggestIdArr = suggestId.split("@");
        SuggestionForm suggestionForm = new SuggestionForm();
        for(int i=0;i<suggestIdArr.length;i++){
            adminManager.deleteSuggest(Integer.parseInt(suggestIdArr[i]));
        }
        map.put("state", "success");
        result = objectMapper.writeValueAsString(map);
        return "success";
    }
    /**
     * 回复用户建议
     * @return
     * @throws Exception
     */
    public String ReplayUserSuggest() throws Exception{
        //构建json
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=new HashMap<>();
        String[] replayArr = replay.split("@");
        adminManager.replaySuggest(Integer.parseInt(replayArr[0]),replayArr[1]);
        map.put("state", "success");
        result = objectMapper.writeValueAsString(map);
        return "success";
    }
    public List<SuggestionForm> ChangeInfo(List<Suggestionbox> Suggestionboxs){
        List<SuggestionForm> SuggestionForms = new ArrayList<SuggestionForm>();
        SuggestionForm suggestionForm;
        for(  int i = 0; i < Suggestionboxs.size(); i++  ){
            suggestionForm = new SuggestionForm();
            UserForm userForm = userManager.showInfoManager(Suggestionboxs.get(i).getUserid());
            suggestionForm.setId(Suggestionboxs.get(i).getId());
            suggestionForm.setUserId(Suggestionboxs.get(i).getUserid());
            suggestionForm.setSugradio(Suggestionboxs.get(i).getSuggestionType());
            suggestionForm.setSugtitle(Suggestionboxs.get(i).getSuggestionTitle());
            suggestionForm.setSugtext(Suggestionboxs.get(i).getSugContent());
            suggestionForm.setSugTime(Suggestionboxs.get(i).getSugTime());
            suggestionForm.setCsId(userForm.getUserName());
            suggestionForm.setCstext(userForm.getUserHeadImg());
            suggestionForm.setCsTime(Suggestionboxs.get(i).getCsTime());
            SuggestionForms.add(suggestionForm);
        }
        return SuggestionForms;
    }
}
