/**
 * Created by dairuihe on 2016/4/23.
 */
$(function () {
    //   判断是否已登录，这种情况是为了防止用户直接调用该页面的URL地址
    IsLogin();
    userInfo();
    modifyTheme();
});

function userInfo() {
    var jsonObj;
    $.ajax({
        type : "post",
        url : "/PhotoShow/user_userAction_showInfo.action",
        success : function(data) {
            jsonObj=eval("("+data+")");
            // console.log(jsonObj);
            // console.log(jsonObj.userHeadImg);
            $(".userHeadImg").attr("src",jsonObj.userHeadImg);
            $(".userName").text(jsonObj.userName);
            $(".userAddress").text(jsonObj.userAddress);
            $(".userPhNum").text(jsonObj.userPhNum);
            $(".userAlbumNum").text(jsonObj.userAlbumNum);
            $(".userId").text(jsonObj.userId);
            $(".userEmail").text(jsonObj.userEmail);

            $("#inputUser").val(jsonObj.userName);
            $("#inputEmail").val(jsonObj.userEmail);
            $("#inputPhone").val(jsonObj.userPhNum);
            $("#inputAddress").val(jsonObj.userAddress);
            $(".headImg-top").css({'background-image':'url('+jsonObj.url+')'});
           // $("#inputImeg1").val(jsonObj.userHeadImg);
        },
        error: function (data) {
            location.reload();
        }
    });
}
function updateInfo() {
    var key = new Array("userName","userEmail","userPhNum","userAddress");
    var value = new Array($("#inputUser").val(),$("#inputEmail").val(),$("#inputPhone").val(),$("#inputAddress").val());
    var json = jsonStr("userForm", key, value);
    var jsonObj;
    $.ajax({
        type : "post",
        data : json,
        url : "/PhotoShow/user_userAction_updateInfo.action",
        contentType : "application/json",
        success : function(data) {
            jsonObj=eval("("+data+")");
            if(jsonObj.state=="success"){
                swal("","基本信息修改成功!","success");
                return false;
            }else{
                swal("","修改失败!","error");
                return false;
            }
        },
        error:function (data) {
            swal("","系统出错!","error");
            location.reload();
        }

    });

    if($("#inputImeg1").val()!=null && $("#inputImeg1").val()!=""){
        $.ajaxFileUpload({
            url: "/PhotoShow/upload_uploadImg.action",
            secureuri: false,
            fileElementId: 'inputImeg1',
            dataType: 'json',
            success: function (data, status) {
                location.reload();
            },
            error: function (data, status, e) {
                swal("","系统出错!","error");
                location.reload();
            }
        });
    }
    return false;
}
function updatePwd() {
    var key = new Array("userPwd");
    var value = new Array($("#inputPassword2").val());
    var json = jsonStr("loginForm", key, value);
    var jsonObj;
    $.ajax({
        type: "post",
        data: json,
        url: "/PhotoShow/user_userAction_updatePwd.action",
        contentType: "application/json",
        success: function (data) {
            jsonObj=eval("("+data+")");
            if(jsonObj.state=="success"){
                swal(
                    {   title: "密码修改成功,请重新登录!",
                        type: "success",
                        confirmButtonText: "确定",
                        closeOnConfirm: false
                    },
                    function(){
                        location.href = "index3.html";
                    }
                );
                return false;
            }else{
                swal("","修改失败!","error");
                return false;
            }
            location.reload();
        },
        error:function (data) {
            swal("","系统出错!","error");
            location.reload();
        }
    });
    return false;
}
function modifyTheme(){
    //切换封面
    var orgin
    $(".Cover-divImag").children().click(function(){
   //$(this)获取到的是<a>标签
        orgin = $(".headImg-top").css("background-image");
        orgin = orgin.toString().substring(orgin.length-7,orgin.length-6);

        var New = $(this).children().attr("src");
        New = New.toString().substring(New.length-5,New.length-4);

        var url;
        switch( New )
        {
            case "1":
            {
                url = "images/PersonalWeb/banner-bg-about-1.png";
                $(".headImg-top").css({'background-image':'url(images/PersonalWeb/banner-bg-about-1.png)'});
            }
                break;
            case "2":
            {
                url = "images/PersonalWeb/banner-bg-about-2.png";
                $(".headImg-top").css({'background-image':'url(images/PersonalWeb/banner-bg-about-2.png)'});
            }
                break;
            case "3":
            {
                url = "images/PersonalWeb/banner-bg-about-3.png";
                $(".headImg-top").css({'background-image':'url(images/PersonalWeb/banner-bg-about-3.png)'});
            }
                break;
            case "4":
            {
                url = "images/PersonalWeb/banner-bg-about-4.png";
                $(".headImg-top").css({'background-image':'url(images/PersonalWeb/banner-bg-about-4.png)'});
            }
                break;
            default:
            {
                url = "images/PersonalWeb/banner-bg-about-2.png";
                $(".headImg-top").css({'background-image':'url(images/PersonalWeb/banner-bg-about-2.png)'});
            }
        }
        //保存当前用户选定的主题
        updatausertheme( url );
    });
}
function updatausertheme( themePath ){
    var json = "{'url':'"+themePath+"'}";
    $.ajax({
        type : "post",
        data : json,
        url : "/PhotoShow/Theme.action",
        contentType : "application/json",
        sync:false,
        success : function(data) {

        },
        error: function (data) {
            swal("","系统出错！","error");
            location.reload();
            //return false;
        }
    });
}
function IsLogin(){
    var jsonObj;
    $.ajax({
        type : "post",
        url : "/PhotoShow/session_existSession.action",
        async : false, // 注意此处需要同步，
        success:function (data){
            jsonObj=eval("("+data+")");
            if(jsonObj.state=="success"){
            }else{
                swal("5秒后回到主页", "请先登录后才能进入个人页面！", "error")
                window.setTimeout(function(){
                    location.href = "/index3.html";
                    return false;
                },5000);
            }
        }
    });
    if(jsonObj.state=="error"){
        swal("5秒后回到主页", "请先登录后才能进入个人页面！", "error")
        window.setTimeout(function(){
            location.href = "/index3.html";
            return false;
        },5000);
    }
}