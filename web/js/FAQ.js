$(function(){
    $(".panel").click(function(){
        var facaretdown = $(this).children("div:first-child").children("i.fa1").attr("class");
        var facaretleft = $(this).children("div:first-child").children("i.fa1").attr("class");
        if( facaretdown.indexOf("fa-caret-down".toString())!=-1 ){
            $(this).children("div:first-child").children("i.fa1").removeClass("fa-caret-down");
            $(this).children("div:first-child").children("i.fa1").addClass("fa-caret-left");
        }else{
            $(this).children("div:first-child").children("i.fa1").removeClass("fa-caret-left");
            $(this).children("div:first-child").children("i.fa1").addClass("fa-caret-down");
        }
        
    });
    $(".FAQ-mid-foot>button").click(function(){
       $(".FAQ-mid-foot>button:eq(0)").addClass("MyDiaplay");
       $(".FAQ-mid-foot>button:eq(1)").addClass("MyDiaplay");
       if($(this).html()=="Yes"){
            $(".FAQ-mid-foot").find("p:first").html("提交成功！非常感谢您的反馈，我们会继续努力做到更好！");
       }else{
           $(".FAQ-mid-foot").find("p:first").html("为了我们更有效，针对性的改善我们的服务，我们很需要您进一步的反馈信息：");
           $(".FAQ-mid-foot .UserBack").removeClass("MyDiaplay");
       }
    });
    
    $(".FAQ-mid-foot .UserBack>button").click(function(){
        var bool = 0;
        if($(this).html()=="提交"){
            if($("input[type=radio]:checked").val()!=null||$("input[type=radio]:checked").val()>0){
                $(".FAQ-mid-foot").find("p:first").html("提交成功！非常感谢您的反馈，我们会继续努力做到更好！");
                $(".FAQ-mid-foot .UserBack").addClass("MyDiaplay");
                bool = 1;

            }else{
                if( $("#feedbackText").val() == null||$("#feedbackText").val() == "" ){
                    $("#ErrorBack").removeClass("MyDiaplay");
                    $("#ErrorBack").css("display","inline");
                    bool = 0;
                }else{
                    $(".FAQ-mid-foot").find("p:first").html("提交成功！非常感谢您的反馈，我们会继续努力做到更好！");
                    $(".FAQ-mid-foot .UserBack").addClass("MyDiaplay");
                    bool = 1;
                }
            }
            if( bool == 1 ){
                // ajax发送数据到后台
                {
                    // 获取radio fbtext
                    var fbradio = $("input[type=radio]:checked").val().toString();
                    var fbtext = $("#feedbackText").val().toString();
                    var json = "{'fbradio':'"+fbradio+"','fbtext':'"+fbtext+"'}";
                    $.ajax({
                        type : "post",
                        url : "/PhotoSow/SC_feedback_FeedBack.action",
                        contentType : "application/json;charset=utf-8",
                        data:json
                    });
                }
            }
        }else{
            $(".FAQ-mid-foot").find("p:first").html("以上是否解决了您疑问？");
            $(".FAQ-mid-foot .UserBack").addClass("MyDiaplay");
            $(".FAQ-mid-foot>button:eq(0)").removeClass("MyDiaplay");
            $(".FAQ-mid-foot>button:eq(1)").removeClass("MyDiaplay");
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
    //只有登录后才能访问意见箱页面
    $(".FAQ-top-nav").children().find("li:last").prev().children().click(function(){
        var jsonObj;
        $.ajax({
            type : "post",
            url : "/PhotoShow/session_existSession.action",
            async : false, // 注意此处需要同步，
            success:function (data){
                jsonObj=eval("("+data+")");
                if(jsonObj.state=="success"){
                }else{
                    swal("", "请先登录后才能进入意见箱！", "error");
                    return false;
                }
            }
        });
        if(jsonObj.state=="error"){
            return false;
        }
    });
});


