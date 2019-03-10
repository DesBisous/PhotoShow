
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
function registerInfo() {
    var key = new Array("userId","userPwd","userEmail","userPhNum");
    var value = new Array($("#inputUser").val(),$("#inputPassword").val(),$("#inputEmail").val(),$("#inputPhone").val());
    var json = jsonStr("userForm", key, value);
    $.ajax({
        type : "post",
        data : json,
        url : "/PhotoShow/user_userAction_register.action",
        contentType : "application/json",
        success : function(data) {
            //console.log(data);
            uploadImg();
        },
        error: function (data) {
           // console.log(data);
            threeForm("error");
        }
    });
}

function uploadImg() {
    $.ajaxFileUpload({
        url: "/PhotoShow/upload_uploadImg.action",//用于文件上传的服务器端请求地址
        secureuri: false,//是否需要安全协议，一般设置为false
        fileElementId: 'inputImeg1',//需要上传的文件域的ID，即<input type="file">的ID。
        dataType: 'json',//服务器返回的数据类型。可以为xml,script,json,html。如果不填写，jQuery会自动判断。
        success: function (data, status) {
            //console.log(data);
            threeForm("success");
        },
        error: function (data, status, e) {
           // console.log(data);
            threeForm("error");
        }
    });
}

function threeForm(data) {
   // var data = "success";
    var fa = $("#fa-tip1");
    var span1 = $(".reg-info-p").children().children();
    if(data == "success"){
        fa.removeClass("fa-times");
        fa.addClass("fa-check");
        span1.html("");
        span1.html("注册成功");
    }else{
        fa.removeClass("fa-check");
        fa.addClass("fa-times");
        fa.css("color","red");
        span1.html("");
        span1.html("注册失败");
    }
    $("#ChoseUl li").removeClass("active");
    $("#ChoseUl li:eq(2)").addClass("active");
    $(".reg-jumbotron2").addClass("reg-jumbotron-hiddle");
    $(".reg-jumbotron3").removeClass("reg-jumbotron-hiddle");
}