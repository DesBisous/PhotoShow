/**
 * Created by asus on 2016/5/10.
 */
$(function(){
    //初始化的时候高度随着宽度变化
    medifyDarenWidthToHeight();
    //  监视游览器的宽度
    {
        $(window).resize(function(){
            //初始化的时候高度随着宽度变化
            medifyDarenWidthToHeight();
        });
    }
    //头像触发事件
    $(".inputImg").find("img").click(function(){
        $(this).next().click();
    });
    //判断头像
    {
        $("input[type=file]").change(function(){
            var filepath=$(this).get(0).files;
            var extStart=new Array();
            var ext=new Array();
            var fileName=new Array();
            var fileSize=new Array();
            for (var i=0;i<filepath.length;i++){
                fileName[i]=filepath[i].name;
                fileSize[i]=filepath[i].size;
                extStart[i]=filepath[i].name.lastIndexOf(".");
                ext[i]=fileName[i].substring(extStart[i],fileName[i].length).toUpperCase();
                if(ext[i]!=".BMP"&&ext[i]!=".PNG"&&ext[i]!=".GIF"&&ext[i]!=".JPG"&&ext[i]!=".JPEG"){
                    $(this).parent().find("img").attr("src","images/register/PersonIMag.jpg");
                    $(this).val("");
                    swal(
                        {   title: "图片只能为BMP、PNG、GIF、JPG、JPEG",
                            type: "warning",
                            confirmButtonColor: "#DD6B55",
                            confirmButtonText: "close"
                        }
                    );
                    return false;
                }else if(fileSize[i]>4194304){
                    //alert(fileName[i]+"文件超过4M");
                    swal(
                        {   title: "图片不能超过4M",
                            type: "warning",
                            confirmButtonColor: "#DD6B55",
                            confirmButtonText: "close"
                        }
                    );
                    $(this).parent().find("img").attr("src","images/register/PersonIMag.jpg");
                    $(this).val("");
                    return false;
                }else{
                    var objUrl = getObjectURL(filepath[i]) ;
                    //文件对象是否存在
                    if( objUrl ){
                        $(this).parent().find("img").attr("src",objUrl);
                    }else{
                        $(this).parent().find("img").attr("src","images/register/PersonIMag.jpg");
                        $(this).val("");
                    }
                }
            }
        });
        //建立一個可存取到該file的url，对应不同游览器有不同获取方法
        function getObjectURL(file) {
            var url = null ;
            if (window.createObjectURL!=undefined) { // basic
                url = window.createObjectURL(file) ;
            } else if (window.URL!=undefined) { // mozilla(firefox)
                url = window.URL.createObjectURL(file) ;
            } else if (window.webkitURL!=undefined) { // webkit or chrome
                url = window.webkitURL.createObjectURL(file) ;
            }
            return url ;
        }
    }

    //重置输入框
    $(".Reset").click(function(){
        $(this).parent().parent().parent().find("input[type=text]").each(function () {
            $(this).val("");
        });
        $(this).parent().parent().parent().find("textarea").each(function () {
            $(this).val("");
        });
        $(this).parent().parent().parent().find("input[type=file]").val("");
    });

    //更新按钮点击事件
    $(".Save1").click(function (){
        //获取信息
        var name = $("#name1").val().trim();
        var title = $("#title1").val().trim();
        var info = $("#textarea1").val().trim();
        var introduce = $("#textarea2").val().trim();
        var inputImeg = $("#inputImeg1").val().trim();
        if( name=="" || title=="" || info=="" || introduce=="" || inputImeg=="" ){
            swal(
                {   title: "请填写完整信息",
                    type: "warning",
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "close"
                }
            );
            return false;
        }
        if( !Limitlength(name,title,info,introduce) ) {
            swal(
                {   title: "长度出错，请按照提示填写",
                    type: "warning",
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "close"
                }
            );
            return false;
        }
        var key1 = new Array("note","order");
        var value1= new Array("Save1","");
        var key2 = new Array("name","nickname","basicinfo","introduce");
        var value2= new Array(name,title,info,introduce);
        var json = jsonStr("graph", key1, value1,"masterinfo", key2, value2);
        uploadDarenInfo("inputImeg1",json);
        return false;
    });
    $(".Save2").click(function (){
        //获取信息
        var name = $("#name2").val().trim();
        var title = $("#title2").val().trim();
        var info = $("#textarea3").val().trim();
        var introduce = $("#textarea4").val().trim();
        var inputImeg = $("#inputImeg2").val().trim();
        if( name=="" || title=="" || info=="" || introduce=="" || inputImeg=="" ){
            swal(
                {   title: "请填写完整信息",
                    type: "warning",
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "close"
                }
            );
            return false;
        }
        if( !Limitlength(name,title,info,introduce) ) {
            swal(
                {   title: "长度出错，请按照提示填写",
                    type: "warning",
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "close"
                }
            );
            return false;
        }
        var key1 = new Array("note","order");
        var value1= new Array("Save2","");
        var key2 = new Array("name","nickname","basicinfo","introduce");
        var value2= new Array(name,title,info,introduce);
        var json = jsonStr("graph", key1, value1,"masterinfo", key2, value2);
        uploadDarenInfo("inputImeg2",json);
        return false;
    });
});
function medifyDarenWidthToHeight(){
    $( ".inputImg").find( "img" ).each(function() {
        $(this).height( $(this).width()*0.75 );
    });
}
function Limitlength(name,title,info,introduce) {
    if(name.length<2 || name.length>3){
        return false;
    }else if(title.length<2 || title.length>5){
        return false;
    }else if(info.length<2 || info.length>100){
        return false;
    }else if(introduce.length<2 || introduce.length>210){
        return false;
    }else return true;
}
function uploadDarenInfo(Id,json){
    $.ajax({
        type : "post",
        data : json,
        url : "/PhotoShow/Graph_GraphDaren.action",
        async : false,
        contentType : "application/json",
        success : function(data) {
            jsonObj = eval("(" + data + ")");
            if (jsonObj.state == "success") {
                // swal("", "success", "success");
                uploadImg(Id);
            }else {
                swal("", "系统异常", "error");
            }
        },
        error:function (data) {
            swal("", "系统出错！", "error");
        }
    });
}
function uploadImg(Id) {
    $.ajaxFileUpload({
        url: "/PhotoShow/upload_uploadImgDaren.action",//用于文件上传的服务器端请求地址
        secureuri: false,//是否需要安全协议，一般设置为false
        fileElementId: Id,//需要上传的文件域的ID，即<input type="file">的ID。
        dataType: 'json',//服务器返回的数据类型。可以为xml,script,json,html。如果不填写，jQuery会自动判断。
        async : false,
        success : function(data) {
            jsonObj = eval("(" + data + ")");
            if (jsonObj.state == "success" ) {
                swal("", "success", "success");
            }
        },
        error:function (data) {
            swal("", "系统出错！", "error");
        }
    });
}
function jsonStr(objectName1, key1, value1,objectName2, key2, value2) {
    var result = "{'" + objectName1 + "':{'";
    for ( var i in key1) {
        if (i < key1.length - 1)
            result += key1[i] + "':'" + value1[i] + "','";
        else
            result += key1[i] + "':'" + value1[i] + "'}";
    }
    result += ",'" + objectName2 + "':{'";
    for ( var i in key2) {
        if (i < key2.length - 1)
            result += key2[i] + "':'" + value2[i] + "','";
        else
            result += key2[i] + "':'" + value2[i] + "'}}";
    }
    return result;
}