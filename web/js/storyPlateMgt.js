/**
 * Created by asus on 2016/5/10.
 */
$(function(){
    //头像触发事件
    $(".inputImg").find("img").click(function(){
        $(this).next().click();
    });
    $(".inputImg").next("button").click(function(){
        $(this).prev().find("input[type=file]").click();
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
                    $(this).parent().find("img").attr("src","images/modify/image1.jpg");
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
                    $(this).parent().find("img").attr("src","images/modify/image1.jpg");
                    $(this).val("");
                    return false;
                }else{
                    var objUrl = getObjectURL(filepath[i]) ;
                    //文件对象是否存在
                    if( objUrl ){
                        $(this).parent().find("img").attr("src",objUrl);
                    }else{
                        $(this).parent().find("img").attr("src","images/modify/image1.jpg");
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
        $(this).parent().parent().find("input[type=text]").each(function () {
            $(this).val("");
        });
        $(this).parent().parent().find("textarea").each(function () {
            $(this).val("");
        });
    });

    //更新按钮点击事件
    $(".Submit").click(function (){
        //获取信息
        var title = $("#title1").val().trim();
        var info = $("#textarea1").val().trim();
        var inputImeg = $("#inputImeg1").val().trim();
        if( title=="" || info=="" || inputImeg=="" ){
            swal(
                {   title: "请填写完整信息",
                    type: "warning",
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "close"
                }
            );
            return false;
        }
        if( !Limitlength(title,info) ) {
            swal(
                {   title: "长度出错，请按照提示填写",
                    type: "warning",
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "close"
                }
            );
        }
    });
});
function Limitlength(title,info) {
    if(title.length<1 || title.length>10){
        return false;
    }else if(info.length<2 || info.length>220){
        return false;
    }else return true;
}