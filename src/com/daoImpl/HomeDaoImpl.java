package com.daoImpl;

import com.beans.Album;
import com.beans.Masterinfo;
import com.dao.HomeDao;
import com.forms.AlbumForm;
import com.forms.MasterinfoForm;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.List;


public class HomeDaoImpl extends HibernateDaoSupport implements HomeDao{
    @Override
    public int countAddress(String key) {
        int countNum=0;
        String sqlCheck="from User where userAddress like ?";
        try {
            List<Integer> list= (List<Integer>) getHibernateTemplate().find(sqlCheck,key);
            countNum=list.size();
        }catch (Exception e){
            e.printStackTrace();
            return countNum;
        }
        return countNum;
    }

    @Override
    public List<AlbumForm> allManAlbum() {
        List<AlbumForm> albumForm=new ArrayList<>();
        String sqlCheck="from Album";
        try{
            List<Album> list= (List<Album>) getHibernateTemplate().find(sqlCheck);
            for (int i=0;i<list.size();i++){
                AlbumForm albumF=new AlbumForm();
                BeanUtils.copyProperties(list.get(i),albumF);
                albumForm.add(i,albumF);
            }
        }catch (Exception e){
            e.printStackTrace();
            return albumForm;
        }
        //System.out.println(albumForm.get(0).getTitle());
        return albumForm;
    }

    @Override
    public List<MasterinfoForm> getMasterin() {
        List<MasterinfoForm> masterinfoForms=new ArrayList<MasterinfoForm>();
        String sqlCheck="from Masterinfo";
        try{
            List<Masterinfo> list= (List<Masterinfo>) getHibernateTemplate().find(sqlCheck);
            for (int i=0;i<list.size();i++){
                MasterinfoForm masterinfoForm=new MasterinfoForm();
                BeanUtils.copyProperties(list.get(i),masterinfoForm);
                masterinfoForms.add(masterinfoForm);
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
//        System.out.println(masterinfoForms.toString());
        return masterinfoForms;
    }

    @Override
    public List<AlbumForm> towAlbum() {
        List<AlbumForm> albumForm=new ArrayList<>();
        String sqlCheck="from Album order by good desc";
        try{
            Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
            Query q=session.createQuery(sqlCheck);
            q.setFirstResult(0);
            q.setMaxResults(2);
            List<Album> list= q.list();
            for (int i=0;i<list.size();i++){
                AlbumForm albumF=new AlbumForm();
                BeanUtils.copyProperties(list.get(i),albumF);
                albumForm.add(i,albumF);
            }
            System.out.println(list.get(0).getTheme());
        }catch (Exception e){
            e.printStackTrace();
            return albumForm;
        }
        return albumForm;
    }
}
