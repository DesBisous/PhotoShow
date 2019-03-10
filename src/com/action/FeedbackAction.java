package com.action;

import com.opensymphony.xwork2.ActionSupport;
import com.service.ServiceCentre;

public class FeedbackAction extends ActionSupport{
    private String fbradio = null;
    private String fbtext = null;
    private ServiceCentre serviceCentre = null;

    public void setFbradio(String fbradio){
        this.fbradio = fbradio;
    }
    public void setfbtext(String fbtext){
        this.fbtext = fbtext;
    }
    public void setServiceCentre(ServiceCentre serviceCentre){
        this.serviceCentre = serviceCentre;
    }

    public void FeedBack(){
        serviceCentre.savefeedback( Integer.parseInt(fbradio),fbtext );
    }


}
