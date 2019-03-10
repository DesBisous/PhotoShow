package com.serviceImpl;

import com.dao.MemberDao;
import com.forms.pageForm;
import com.service.AlbumService;

import java.util.List;


public class AlbumServiceImpl implements AlbumService{
    private MemberDao memberDao = null;
    private pageForm pageForm = null;

    public MemberDao getMemberDao() {
        return memberDao;
    }

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    /**
     * 分页查询
     * @param pageSize  每页显示多少记录
     * @param page 当前页
     * @param hql 搜索关键词
     * @return 封装了分页信息的bean
     */
    @Override
    public pageForm queryForPage(int pageSize, int page, String hql) {
        int allRow = memberDao.getAllRowCount(hql);  //总记录数
        int totalPage = pageForm.countTatalPage(pageSize, allRow); //总页数
        final int currentPage = pageForm.countCurrentPage(page,totalPage); // 当前页
        final int offset = pageForm.countOffset(pageSize, currentPage); //当前页开始记录
        final int length = pageSize; // 每页记录数
        List list = memberDao.queryForPage(hql, offset, length); //
        //把分页信息保存到Bean当中
        pageForm pageForm  = new pageForm();
        pageForm.setPageSize(pageSize);
        pageForm.setCurrentPage(currentPage);
        pageForm.setAllRow(allRow);
        pageForm.setTotalPage(totalPage);
        pageForm.setList(list);
        pageForm.init();
        return pageForm;
    }
    /**
     * 查询所有的记录数
     * @param hql 查询条件
     * @return 总记录数
     */
    @Override
    public int getAllRow(String hql){
        return memberDao.getAllRowCount(hql);  //总记录数
    }
}
