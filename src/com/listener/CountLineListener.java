package com.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Created by ruihe on 16-5-21.
 */
public class CountLineListener implements HttpSessionAttributeListener{
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        ServletContext context=event.getSession().getServletContext();
        Integer count=(Integer)context.getAttribute("count");
        if(count==null){
            count=new Integer(1);
        }else{
            int co = count.intValue();
            count= new Integer(co+1);
        }
        System.out.println("当前用户人数："+count);
        context.setAttribute("count", count);//保存人数
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        ServletContext context=event.getSession().getServletContext();
        Integer count=(Integer)context.getAttribute("count");
        int co=count.intValue();
        count=new Integer(co-1);
        context.setAttribute("count", count);
        System.out.println("当前用户人数："+count);
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        System.out.println("replace!");
    }
}
