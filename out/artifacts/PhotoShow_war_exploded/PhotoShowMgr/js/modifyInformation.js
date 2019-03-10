/**
 * Created by asus on 2016/5/13.
 */
$(function(){
    //初始化
    var AdminInfo = backAdminInfo();
    // console.log(AdminInfo);
    Admininfo(AdminInfo);

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
        $(this).parent().parent().find("input[type=password]").each(function () {
            $(this).val("");
        });
        $(this).parent().parent().find("textarea").each(function () {
            $(this).val("");
        });
        $("#inputImeg").parent().find("img").attr("src","/PhotoShowMgr/imgs/"+AdminInfo.adminForm.id+"/"+AdminInfo.adminForm.headImg);
        $("#inputImeg").val("");
    });

    //更新按钮点击事件
    $(".Submit").click(function (){
        //获取信息, 密码 联系地址 邮箱进行检查，
        // 头像的上面已检查，要是不修改头像或添加的不是图片文件等，就判断input[type=file]的val()是不是空，即可知道修补修改。
        var name = $("#name").val().trim();
        var phone = $("#phone").val().trim();
        var email = $("#email").val().trim();
        var address = $("#address").val().trim();
        var pwd1 = $("#pwd1").val().trim();
        var pwd2 = $("#pwd2").val().trim();
        var inputImeg = $("#inputImeg").val();
        var Checkpoint = 1;

        if( phone!=null && phone!="" ){
            //手机填写并通过检查
            if( Phone(phone) ){}
            else Checkpoint = 0;
        }
        if( email!=null && email!="" ){
            //邮箱填写并通过检查
            if( Eamil(email) ){}
            else Checkpoint = 0;
        }
        if( pwd1!=null && pwd1!="" && pwd2!=null && pwd2!="" ){
            //密码填写并通过检查
            if( Pwd(pwd1,pwd2) ){}
            else Checkpoint = 0;
        }

        if( Checkpoint == 0 ){
            //需要验证项未通过检查，不进行更新
        }else{
            //对所有信息进行重写更新（除了头像，头像上面另外处理），（读取该页面时应将当前管理员的信息获取并填到相应的输入框中）
            modifyAdminInfo(name,phone,email,address,pwd1);
            if( inputImeg!=null && inputImeg!="" ){
                //需要修改图片
                uploadImg();
            }
        }

    });
});
function Phone(phone){
    //判断联系电话
    var isMobile=/^(?:13\d|15\d|18\d)\d{5}(\d{3}|\*{3})$/; //手机号码验证规则
    var isPhone=/^((0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;   //座机验证规则
    if( !isMobile.test(phone) && !isPhone.test(phone) ){
        swal(
            {   title: "请填写正确的联系电话",
                type: "warning",
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "close"
            }
        );
        return false;
    }else return true;
}
function Eamil(email){
    //判断邮箱
    var isMobile=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/; //邮箱验证规则
    if( !isMobile.test(email)  ){
        swal(
            {   title: "请填写正确的邮箱",
                type: "warning",
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "close"
            }
        );
        return false;
    }else return true;
}
function Pwd(Pwd1,Pwd2){
    if( Pwd1!=Pwd2 ) {
        swal(
            {   title: "两次密码不一致",
                type: "warning",
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "close"
            }
        );
        return false;
    }else return true;
}
function Admininfo(AdminInfo) {
    $("#name").attr("placeholder",""+AdminInfo.adminForm.name);
    $("#address").attr("placeholder",AdminInfo.adminForm.address);
    $(".storyImg").find("img").attr("src","/PhotoShowMgr/imgs/"+AdminInfo.adminForm.id+"/"+AdminInfo.adminForm.headImg);
}
function modifyAdminInfo(name,phone,email,address,pwd1) {
    //判断参数为不为空
    if( name==null || name=="" ) name = "null";
    if( phone==null || phone=="" ) phone = "null";
    if( email==null || email=="" ) email = "null";
    if( address==null || address=="" ) address = "null";
    if( pwd1==null || pwd1=="" ) pwd1 = "null";
    else pwd1 = $.md5(pwd1);

    var key = new Array("name","phone","email","address","headImg","adminid","passwd");
    var value = new Array(name,phone,email,address,"null","null",pwd1);
    var json = jsonStr("adminForm", key, value);
    var jsonObj;
    $.ajax({
        type : "post",
        data : json,
        url : "/PhotoShow/admin_modifyInfo.action",
        async : false,
        contentType : "application/json",
        success : function(data) {
            jsonObj = eval("(" + data + ")");
            if (jsonObj.state == "success") {
                swal("", "修改成功", "success");
            }else {
                swal("", jsonObj.mgs, "error");
            }
        },
        error:function (data) {
            swal("", "系统出错！", "error");
        }
    });
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
