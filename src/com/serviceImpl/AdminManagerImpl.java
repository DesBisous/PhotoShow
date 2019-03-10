package com.serviceImpl;

import com.beans.Admin;
import com.beans.Masterinfo;
import com.dao.*;
import com.forms.*;
import com.service.AdminManager;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;


public class AdminManagerImpl implements AdminManager{
    private AdminDao adminDao;
    private DarenDao darenDao;
    private proposalDao proposalDao;
    private CommentsDao commentsDao;
    private MemberDao memberDao;
    private SuggestionDao suggestionDao;
    private pageForm pageForm = null;
    private Admin admin=new Admin();

    public AdminDao getAdminDao() {
        return adminDao;
    }

    public void setAdminDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    public DarenDao getDarenDao() {
        return darenDao;
    }

    public void setDarenDao(DarenDao darenDao) {
        this.darenDao = darenDao;
    }

    public com.dao.proposalDao getProposalDao() {
        return proposalDao;
    }

    public void setProposalDao(com.dao.proposalDao proposalDao) {
        this.proposalDao = proposalDao;
    }

    public CommentsDao getCommentsDao() {
        return commentsDao;
    }

    public void setCommentsDao(CommentsDao commentsDao) {
        this.commentsDao = commentsDao;
    }

    public MemberDao getMemberDao() {
        return memberDao;
    }

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public SuggestionDao getSuggestionDao() {
        return suggestionDao;
    }

    public void setSuggestionDao(SuggestionDao suggestionDao) {
        this.suggestionDao = suggestionDao;
    }

    public com.forms.pageForm getPageForm() {
        return pageForm;
    }

    public void setPageForm(com.forms.pageForm pageForm) {
        this.pageForm = pageForm;
    }

    @Override
    public boolean registerManager(AdminForm adminFrom) {
        BeanUtils.copyProperties(adminFrom,admin);
        return adminDao.saveAdmin(admin);
    }

    @Override
    public AdminForm loginManager(AdminForm adminFrom) {
        BeanUtils.copyProperties(adminFrom,admin);
        return adminDao.findAdmin(admin);
    }
    @Override
    public AdminForm HqlFindManager(String Hql){
        return adminDao.HqlFindAdmin(Hql);
    }

    @Override
    public ArrayList<AdminForm> HqlFindAllManager(String Hql){
        return adminDao.HqlFindAdminAll(Hql);
    }

    @Override
    public boolean modifyManager(AdminForm adminFrom) {
        BeanUtils.copyProperties(adminFrom,admin);
        return adminDao.updateAdmin(admin);
    }

    @Override
    public boolean DarenManager(MasterinfoForm masterinfoForm) {
        Masterinfo masterinfo = new Masterinfo();
        BeanUtils.copyProperties(masterinfoForm,masterinfo);
        return darenDao.updateDaren(masterinfo);
    }

    @Override
    public List<FeedbackForm> proposalManager(int note) {
        List<FeedbackForm> feedbackForms = null;
        switch( note ){
            case 1: feedbackForms = proposalDao.feedback1(); break;
            case 2: feedbackForms = proposalDao.feedback2(); break;
            case 3: feedbackForms = proposalDao.feedback3(); break;
            case 4: feedbackForms = proposalDao.feedback4(); break;
            default: feedbackForms = proposalDao.feedback1(); break;
        }
        return feedbackForms;
    }

    @Override
    public List<SuggestionForm> commentsManager(int note) {
        List<SuggestionForm> suggestionForms = null;
        switch( note ){
            case 1: suggestionForms = commentsDao.SuggestionForm1(); break;
            case 2: suggestionForms = commentsDao.SuggestionForm2(); break;
            case 3: suggestionForms = commentsDao.SuggestionForm3(); break;
            case 0: suggestionForms = commentsDao.SuggestionForm0(); break;
            default: suggestionForms = commentsDao.SuggestionForm1(); break;
        }
        return suggestionForms;
    }

    @Override
    public pageForm queryForPageSuggest(int pageSize, int page, String hql) {
        int allRow = memberDao.getAllRowCount(hql);  //总记录数
        int totalPage = pageForm.countTatalPage(pageSize, allRow); //总页数
        final int currentPage = pageForm.countCurrentPage(page,totalPage); // 当前页
        final int offset = pageForm.countOffset(pageSize, currentPage); //当前页开始记录
        final int length = pageSize; // 每页记录数
        List list = memberDao.queryForPageSuggest(hql, offset, length); //
        //把分页信息保存到Bean当中
        pageForm pageForm  = new pageForm();
        pageForm.setPageSize(pageSize);
        pageForm.setCurrentPage(currentPage);
        pageForm.setAllRow(allRow);
        pageForm.setTotalPage(totalPage);
        pageForm.setListSuggestionbox(list);
        return pageForm;
    }

    @Override
    public boolean deleteSuggest(int id) {
        suggestionDao.deleteSuggestion(id);
        return true;
    }

    @Override
    public boolean replaySuggest(int id,String replay) {
        suggestionDao.replaySuggestion(id,replay);
        return true;
    }
}
