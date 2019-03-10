/**
 * Created by asus on 2016/5/9.
 */
$(function(){
    //头像触发事件
    $("#inputImeg").prev().click(function(){
        $("#inputImeg").click();
    });
    //判断头像
    {
        $("#inputImeg").change(function(){
            var filepath=$("#inputImeg").get(0).files;
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
                    $("#inputImeg").prev().attr("src","images/register/PersonIMag.jpg");
                    $("#inputImeg").val("");
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
                    $("#inputImeg").prev().attr("src","images/register/PersonIMag.jpg");
                    $("#inputImeg").val("");
                    return false;
                }else{
                    var objUrl = getObjectURL(filepath[i]) ;
                    //文件对象是否存在
                    if( objUrl ){
                        $("#inputImeg").prev().attr("src",objUrl);
                    }else{
                        $("#inputImeg").prev().attr("src","images/register/PersonIMag.jpg");
                        $("#inputImeg").val("");
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
    //提交事件
    $("button[type='submit']").click(function(){
        //获取注册信息
        var Name = $(".Name").val().toString().trim();
        var Phone = $(".Phone").val().toString().trim();
        var Email = $(".Email").val().toString().trim();
        var Address = $(".Address").val().toString().trim();
        var Adminid = $(".Adminid").val().toString().trim();
        var Pwd1 = $(".Pwd1").val().toString().trim();
        if( Name=="" || Phone=="" || Email=="" || Address=="" || Adminid=="" || Pwd1=="" ){
            swal(
                {   title: "请填写完整信息",
                    type: "warning",
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "close"
                }
            );
            return false;
        }





        //判断联系电话
        var isMobile=/^(?:13\d|15\d|18\d)\d{5}(\d{3}|\*{3})$/; //手机号码验证规则
        var isPhone=/^((0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;   //座机验证规则
        if( !isMobile.test($(".Phone").val()) && !isPhone.test($(".Phone").val()) ){
            swal(
                {   title: "请填写正确的联系电话",
                    type: "warning",
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "close"
                }
            );
            return false;
        }
        //判断邮箱
        var isMobile=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/; //邮箱验证规则
        if( !isMobile.test($(".Email").val())  ){
            swal(
                {   title: "请填写正确的邮箱",
                    type: "warning",
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "close"
                }
            );
            return false;
        }
        //判断头像是否已选择了
        if($("#inputImeg").val()==null||$("#inputImeg").val()==""){
            swal(
                {   title: "请选择头像",
                    type: "warning",
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "close"
                }
            );
            return false;
        }
        //判断密码和确认密码是否一致
        var Pwd1 = $(".Pwd1").val().trim();
        var Pwd2 = $(".Pwd2").val().trim();
        if( Pwd1!=Pwd2 ) {
            swal(
                {   title: "两次密码不一致",
                    type: "warning",
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "close"
                }
            );
            return false;
        }
        if(!$("input[type='checkbox']").is(':checked')){swal(
            {   title: "请同意下方解释权",
                type: "warning",
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "close"
            }
        );
            return false;
        }
        //进行URL访问
        registerInfo();
        return false;
    });
});
function registerInfo(){
    var Name = $(".Name").val().toString().trim();
    var Phone = $(".Phone").val().toString().trim();
    var Email = $(".Email").val().toString().trim();
    var Address = $(".Address").val().toString().trim();
    var Adminid = $(".Adminid").val().toString().trim();
    var Pwd1 = $.md5($(".Pwd1").val().toString().trim());
    var imgName = "head"+$("#inputImeg").val().substring($("#inputImeg").val().lastIndexOf("."),$("#inputImeg").val().length);
    var key = new Array("name","phone","email","address","headImg","adminid","passwd");
    var value = new Array(Name,Phone,Email,Address,imgName,Adminid,Pwd1);
    var json = jsonStr("adminForm", key, value);
    var jsonObj;
    $.ajax({
        type : "post",
        data : json,
        url : "/PhotoShow/admin_register.action",
        async : false,
        contentType : "application/json",
        success : function(data) {
            jsonObj = eval("(" + data + ")");
            if (jsonObj.state == "success") {
                uploadImg();
            }else {
                swal("", jsonObj.mgs, "error");
            }
        },
        error:function (data) {
            swal("", "系统出错！", "error");
        }
    });
    return false;
}
function uploadImg() {
    $.ajaxFileUpload({
        url: "/PhotoShow/upload_uploadImgAdmin.action",//用于文件上传的服务器端请求地址
        secureuri: false,//是否需要安全协议，一般设置为false
        fileElementId: 'inputImeg',//需要上传的文件域的ID，即<input type="file">的ID。
        dataType: 'json',//服务器返回的数据类型。可以为xml,script,json,html。如果不填写，jQuery会自动判断。
        success : function(data) {
            jsonObj = eval("(" + data + ")");
            if (jsonObj.state == "success") {
                swal(
                    {   title: jsonObj.mgs,
                        type: "success",
                        confirmButtonText: "确定",
                        closeOnConfirm: false
                    },
                    function(){
                        location.href = "index.html";
                    }
                );
            }else {
                swal("", jsonObj.mgs, "error");
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