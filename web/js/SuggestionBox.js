$(function(){
    //   判断是否已登录，这种情况是为了防止用户直接调用该页面的URL地址
    {
        var jsonObj;
        $.ajax({
            type : "post",
            url : "/PhotoShow/session_existSession.action",
            async : false, // 注意此处需要同步，
            success:function (data){
                jsonObj=eval("("+data+")");
                if(jsonObj.state=="success"){
                }else{
                    swal("5秒后回到主页", "请先登录后才能进入意见箱！", "error")
                    window.setTimeout(function(){
                        location.href = "/index3.html";
                        return false;
                    },5000);
                }
            }
        });
        if(jsonObj.state=="error"){
            swal("5秒后回到主页", "请先登录后才能进入意见箱！", "error")
            window.setTimeout(function(){
                location.href = "/index3.html";
                return false;
            },5000);
        }
    }



   $("#form-Sign").click(function(){
       {
           var jsonObj;
           var url = "/PhotoShow/session_existSession.action";
           $.post(url, {}, function(data) {
               jsonObj=eval("("+data+")");
               if(jsonObj.state=="success"){
                   var feedbackError1 = 0;
                   if( $("input[name=feedback_radio]:checked").val()==null ||
                       $("input[name=feedback_radio]:checked").val()=="" ){
                       feedbackError1 = 0;
                   }else{
                       if( $("#inputTitle1").val()==null || $("#inputTitle1").val()=="" ){
                           feedbackError1 = 0;
                       }else{
                           if( $("#feedback_textarea").val()==null || $("#feedback_textarea").val()=="" ){
                               feedbackError1 = 0;
                           }else{
                               feedbackError1 = 1;
                               swal("","投递成功，感谢您的反馈!","success");
                               var sugradio = $("input[name=feedback_radio]:checked").val().toString();
                               var sugtitle = $("#inputTitle1").val().toString();
                               var sugtext = $("#feedback_textarea").val().toString();
                               var key = new Array("sugradio","sugtitle","sugtext");
                               var value = new Array(sugradio,sugtitle,sugtext);
                               var json = jsonStr("suggestionForm", key, value);
                               $.ajax({
                                   type : "post",
                                   data : json,
                                   url : "/PhotoShow/SC_SuggestionBox_SuggestionBox.action",
                                   contentType : "application/json"
                               });
                               return false;
                           }
                       }
                   }
                   if( feedbackError1==0 ){
                       $("#feedbackError1").removeClass("MyDiaplay");
                       $("#feedbackError1").css("display","inline");
                       return false;
                   }

               }else{
                   swal("", "请您先登录在投递意见", "error");
                   return false;
               }
           }, "json");
           return false;
       }
   });
//只有登录后才能访问服务记录页面
    $(".FAQ-top-nav").children().find("li:last").children().click(function(){
        var jsonObj;
        $.ajax({
            type : "post",
            url : "/PhotoShow/session_existSession.action",
            async : false, // 注意此处需要同步，
            success:function (data){
                jsonObj=eval("("+data+")");
                if(jsonObj.state=="success"){
                }else{
                    swal("", "请先登录后才能查看服务记录！", "error");
                    return false;
                }
            }
        });
        if(jsonObj.state=="error"){
            return false;
        }
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

