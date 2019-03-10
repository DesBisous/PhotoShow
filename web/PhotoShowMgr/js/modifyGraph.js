/**
 * Created by asus on 2016/5/10.
 */
$(function(){
    //初始化的时候让.Gallery-imgObject高度随着宽度变化
    medifyGraphWidthToHeight();
    //  监视游览器的宽度
    {
        $(window).resize(function(){
            var _width = $(window).width();
            if(_width < 991){
                $(".img-lg-1 img").css("margin-top","0");
            }else{
                $(".img-lg-1 img").css("margin-top","100%");
            }
            //初始化的时候让.img-rounded高度随着宽度变化
            medifyGraphWidthToHeight();
        });
    }
    //头像触发事件
    $(".inputImg").find("img").click(function(){
        $(this).parent().find("input[type='file']").click();
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
    //更新按钮点击事件
    window.graphPage = 0;
    $(".modifyGraph-btu1").children("button").click(function (){
        $(".Graph-row1").find("input[type=file]").each(function(){
            if( $(this).val()==null || $(this).val()=="" ) {
                swal(
                    {   title: "全选完图片才能进行更新",
                        type: "warning",
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "close"
                    }
                );
            }
        });
        //URL上传
        graphPage = 0;
        var key = new Array("note","order");
        var value = new Array("indexGraph",graphPage+1+"");
        var json = jsonStr("graph", key, value);
        var Id = "inputImeg"+(graphPage+1)+"";
        $(this).children("span").css("display","inline")
        countDownTime($(this).find("i"));
        uploadImgGraph(Id,json);

    });
    $(".modifyGraph-btu2").children("button").click(function (){
        $(".Graph-row2").find("input[type=file]").each(function(){
            if( $(this).val()==null || $(this).val()=="" ) {
                swal(
                    {   title: "全选完图片才能进行更新",
                        type: "warning",
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "close"
                    }
                );
            }
        });
        //URL上传
        graphPage = 6;
        var key = new Array("note","order");
        var value = new Array("searchGraph",graphPage+1+"");
        var json = jsonStr("graph", key, value);
        var Id = "inputImeg"+(graphPage+1)+"";
        $(this).children("span").css("display","inline")
        countDownTime($(this).find("i"));
        uploadImgGraph(Id,json);
    });
});
function medifyGraphWidthToHeight(){
    //初始化的时候让.img-rounded高度随着宽度变化
    $( ".inputImg").find( ".img-rounded" ).each(function() {
        $(this).height( $(this).parent().width()*0.962949 );
    });
}
function uploadImgGraph(Id,json){
    $.ajax({
        type : "post",
        data : json,
        url : "/PhotoShow/Graph_GraphInfo.action",
        async : false,
        contentType : "application/json",
        success : function(data) {
            jsonObj = eval("(" + data + ")");
            if (jsonObj.state == "success") {
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
        url: "/PhotoShow/upload_uploadImgGraph.action",//用于文件上传的服务器端请求地址
        secureuri: false,//是否需要安全协议，一般设置为false
        fileElementId: Id,//需要上传的文件域的ID，即<input type="file">的ID。
        dataType: 'json',//服务器返回的数据类型。可以为xml,script,json,html。如果不填写，jQuery会自动判断。
        async : false,
        success : function(data) {
            jsonObj = eval("(" + data + ")");
            if (jsonObj.state == "success" ) {
                if( graphPage == 5 || graphPage == 11 ){
                    graphPage = 0;
                }else{
                    graphPage++;
                    var key = new Array("note","order");
                    var value;
                    if( graphPage>=0 && graphPage<6 ){
                        value = new Array("indexGraph",graphPage+1+"");
                    }else{
                        value = new Array("searchGraph",graphPage+1+"");
                    }
                    var json = jsonStr("graph", key, value);
                    var Id = "inputImeg"+(graphPage+1)+"";
                    uploadImgGraph(Id,json);
                }
            }
        },
        error:function (data) {
            swal("", "系统出错！", "error");
        }
    });
}
function jsonStr(objectName, key, value) {
    var result = "{'" + objectName + "':{'";
    for ( var i in key) {
        if (i < key.length - 1)
            result += key[i] + "':'" + value[i] + "','";
        else
            result += key[i] + "':'" + value[i] + "'}}";
    }
    return result;
}
function countDownTime(countDownI){
    var countDownTime=parseInt(5);
    countDown(countDownTime,countDownI);
}
function countDown(countDownTime,countDownI){
    var timer=setInterval(function(){
        if(countDownTime>=0){
            countDownI.html(countDownTime)
            countDownTime--;
        }else{
            clearInterval(timer);
            countDownI.parent().css("display","none");
            swal("", "更改成功", "success");
        }
    },1000);
}