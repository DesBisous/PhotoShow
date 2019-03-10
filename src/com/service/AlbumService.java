package com.service;

import com.forms.pageForm;


public interface AlbumService {
    /**
     * 分页查询
     * @param pageSize  每页显示多少记录
     * @param page 当前页
     * @return 封装了分页信息的bean
     */
    public pageForm queryForPage(int pageSize, int page, String hql);
    /**
     * 查询所有的记录数
     * @param hql 查询条件
     * @return 总记录数
     */
    public int getAllRow(String hql);
}
