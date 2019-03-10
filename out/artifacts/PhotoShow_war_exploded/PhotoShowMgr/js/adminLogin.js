
$(function () {
    $(".goto-admin").click(function () {
        checkAdmin();
        return false;
    });
    $(".forgetPwd").click(function(){
        if( $("#Pwd").val().length<=0 || $("#User").val()<=0 ){
            swal("", "请填写信息", "warning");
        }else{
            modifyAdminPwd();
        }
        return false;
    });
});

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

function checkAdmin() {
    var key = new Array("adminid","passwd");
    var pwd=$.md5($(".passwd").val());
    var value = new Array($(".adminid").val(),pwd);
    var json = jsonStr("adminForm", key, value);
    var jsonObj;
    $.ajax({
        type : "post",
        data : json,
        url : "/PhotoShow/admin_login.action",
        async : false,
        contentType : "application/json",
        success : function(data) {
            jsonObj = eval("(" + data + ")");
            if (jsonObj.state == "success") {
                swal(
                    {   title: "登录成功",
                        type: "success",
                        confirmButtonText: "确定",
                        closeOnConfirm: false
                    },
                    function(){
                        location.href = "index.html";
                    }
                );
            }else {
                swal("", "登录失败", "error");
            }
        },
        error:function (data) {
            swal("", "系统出错！", "error");
        }
    });
    return false;
}
function modifyAdminPwd() {
    var key = new Array("adminid","passwd");
    var pwd=$.md5($("#Pwd").val());
    var value = new Array($("#User").val(),pwd);
    var json = jsonStr("adminForm", key, value);
    var jsonObj;
    $.ajax({
        type : "post",
        data : json,
        url : "/PhotoShow/admin_modify.action",
        async : false,
        contentType : "application/json",
        success : function(data) {
            jsonObj = eval("(" + data + ")");
            $(".back-pwe").children("strong").html(jsonObj.mgs);
        },
        error:function (data) {
            swal("", "系统出错！", "error");
        }
    });
    return false;
}