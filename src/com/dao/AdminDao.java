package com.dao;

import com.beans.Admin;
import com.forms.AdminForm;

import java.util.ArrayList;


public interface AdminDao {
    public boolean saveAdmin(Admin admin);
    public AdminForm findAdmin(Admin admin);
    public AdminForm HqlFindAdmin(String Hql);
    public ArrayList<AdminForm> HqlFindAdminAll(String Hql);
    public boolean updateAdmin(Admin admin);
}
