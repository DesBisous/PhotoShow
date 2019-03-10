package com.forms;

import java.io.File;
import java.util.List;


public class ImgForm {

    private List<File> upload;
    private List<String> uploadContentType;
    private List<String> uploadFileName;

    public List<File> getUpload() {
        return upload;
    }

    public void setUpload(List<File> upload) {
        this.upload = upload;
    }

    public List<String> getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(List<String> uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public List<String> getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(List<String> uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    //获取某相册中随机一张图片名字
    public  static  String getImgeName( String Path ){
        String ImgeName = null;
        int n = 0;
        //判断是否为文件夹
        File file=new File(Path);
        if(!file.exists()){
            ImgeName = null;
        }else {
            //获取当前相册中的所有文件
            File[] listFiles =  file.listFiles();
            if( listFiles.length<=0 ) return null;
            //获取图片名，出错重复执行，最多执行5次
            for( int i = 0 ; i < 5 ; i++ ){
                n = (int)(Math.random() * listFiles.length); //0-100以内的随机数，用Matn.random()方式
                ImgeName = listFiles[n].getName();
                int len = ImgeName.lastIndexOf(".");
                String type = ImgeName.substring(len);
                //判断获取到的文件是不是图片类型
                if( type.equals(".BMP")&&type.equals(".PNG")&&type.equals(".GIF")&&type.equals(".JPG")&&type.equals(".JPEG")&&
                        type.equals(".bmp")&&type.equals(".png")&&type.equals(".gif")&&type.equals(".jpg")&&type.equals(".jpeg" ) ){
                    ImgeName = null;
                    continue;
                }else{
                    break;
                }
            }
        }
        return ImgeName;
    }
}
