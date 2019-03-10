/**
 * Created by dairuihe on 2016/4/20.
 */

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
function uploadGallery() {
    var str = stringToHex($("#Upload_textarea").val().trim());
    var str_br = stringToHex("<br>");
    str = str.replace(/\\u00a/g, str_br);
    str = str.replace(/\\u0027/g, "\\u2019");
    str = hexToString(str);
    var key = new Array("theme","title","albumIntroduction");
    var value = new Array($("input[name=threme_radio]:checked").val(),$("#inputTitle1").val().trim(),str);
    var json = jsonStr("albumForm", key, value);
    var jsonObj;
    $.ajax({
        type : "post",
        data : json,
        url : "/PhotoShow/upload_uploadInfo.action",
        contentType : "application/json",
        sync:false,
        success : function(data) {
        },
        error: function (data) {
            swal("","系统出错！","error");
            // location.reload();
            return false;
        }
    });

    $.ajaxFileUpload({
        url: "/PhotoShow/upload_uploadAlbum.action",
        secureuri: false,
        fileElementId: 'inputImeg1',
        dataType: 'json',
        sync:false,
        success: function (data, status) {
            jsonObj=eval("("+data+")");
            if(jsonObj.state=="success"){
                swal(
                    {   title: "上传相册成功",
                        type: "success",
                        confirmButtonText: "确定",
                        closeOnConfirm: false
                    },
                    function(){
                        location.reload();
                    }
                );
            }else {
                swal("","上传相册失败","error");
            }
        },
        error: function (data, status, e) {
            swal("","系统出错！","error");
            location.reload();
        }
    });
}
//字符串转换成16进制
function stringToHex(str) {
    var arr = [];
    for (var i = 0; i < str.length; i++) {
        arr[i] = ("00" + str.charCodeAt(i).toString(16)).slice(-4);
    }
    return "\\u" + arr.join("\\u");
}
//将16进制转换成字符串
function hexToString(str) {
    return unescape(str.replace(/\\/g, "%"));
}