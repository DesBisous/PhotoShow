package com.dao;

import java.util.List;
import com.beans.Album;
import com.beans.Suggestionbox;


public interface MemberDao {
    /**
     * 分页查询
     * @param hql  查询条件
     * @param offset  开始记录
     * @param length  一次查询几条记录
     * @return 查询的记录集合
     */
    public List<Album> queryForPage(final String hql, final int offset, final int length);
    /**
     * 用户建议分页查询
     * @param hql  查询条件
     * @param offset  开始记录
     * @param length  一次查询几条记录
     * @return 查询的记录集合
     */
    public List<Suggestionbox> queryForPageSuggest(final String hql, final int offset, final int length);
    /**
     * 查询所有的记录数
     * @param hql 查询条件
     * @return 总记录数
     */
    public int getAllRowCount(String hql);

}
