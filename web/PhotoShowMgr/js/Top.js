/**
 * Created by asus on 2016/5/28.
 */
$(function(){
    var AdminInfo = backAdminInfo();
    AdminHeadInfo(AdminInfo);
    Top1();
    Top2();
    //登出事件
    $(".fa-sign-out").parent().click(function () {
        exitAdmin();
    });

});
function Top1(){
    json = "{'proposal':'true'}";
    // console.log(json);
    $.ajax({
        type : "post",
        data : json,
        url : "/PhotoShow/Proposal_ProposalInfo.action",
        async : false,
        contentType : "application/json",
        success : function(data) {
            jsonObj = eval("(" + data + ")");
            var num1 = jsonObj.FeedbackForms1==null?0:jsonObj.FeedbackForms1.length;
            var num2 = jsonObj.FeedbackForms2==null?0:jsonObj.FeedbackForms2.length;
            var num3 = jsonObj.FeedbackForms3==null?0:jsonObj.FeedbackForms3.length;
            var num4 = jsonObj.FeedbackForms4==null?0:jsonObj.FeedbackForms4.length;
            $(".badge:eq(0)").text(num1+num2+num3+num4);
        },
        error:function (data) {
            swal("", "系统出错！", "error");
        }
    });
    return false;
}
function Top2(){
    json = "{'StatisticsSuggestion':'true'}";
    // console.log(json);
    $.ajax({
        type : "post",
        data : json,
        url : "/PhotoShow/Comments_StatisticsSuggestion.action",
        async : false,
        contentType : "application/json",
        success : function(data) {
            jsonObj = eval("(" + data + ")");
            if(jsonObj.state=="success"){
                var num1 = jsonObj.SuggestionForm1==null?0:jsonObj.SuggestionForm1;
                var num2 = jsonObj.SuggestionForm2==null?0:jsonObj.SuggestionForm2;
                var num3 = jsonObj.SuggestionForm3==null?0:jsonObj.SuggestionForm3;
                var num0 = jsonObj.SuggestionForm0==null?0:jsonObj.SuggestionForm0;
                $(".badge:eq(1)").text(num1+num2+num3+num0);
            }else{
                swal("", "系统异常！", "error");
            }
        },
        error:function (data) {
            swal("", "系统出错！", "error");
        }
    });
    return false;
}
function backAdminInfo(){
    json = "{'AdminInfo':'true'}";
    var result;
    $.ajax({
        type : "post",
        data : json,
        url : "/PhotoShow/admin_find.action",
        async : false,
        contentType : "application/json",
        success : function(data) {
            jsonObj = eval("(" + data + ")");
            if(jsonObj.state=="success"){
                // console.log(data);
                if( jsonObj.adminForm == null ){
                    swal(
                            {   title: "请先登录您的管理员身份",
                                type: "error",
                                confirmButtonText: "返回登录界面",
                                closeOnConfirm: false
                            },
                            function(){
                                location.href = "login.html";
                            }
                        );
                }
                else result = jsonObj;
            }
        },
        error:function (data) {
            swal(
                {   title: "系统异常，请稍后再试",
                    type: "error",
                    confirmButtonText: "返回登录界面",
                    closeOnConfirm: false
                },
                function(){
                    location.href = "login.html";
                }
            );
        }
    });
    return result;
}
function exitAdmin(){
    json = "{'exitAdmin':'true'}";
    $.ajax({
        type : "post",
        data : json,
        url : "/PhotoShow/admin_exitAdmin.action",
        async : false,
        contentType : "application/json",
        success : function(data) {
            jsonObj = eval("(" + data + ")");
            if(jsonObj.state=="success"){
                location.href = "login.html";
            }
        },
        error:function (data) {
            swal(
                {   title: "系统异常，请稍后再试",
                    type: "error",
                    confirmButtonText: "返回登录界面",
                    closeOnConfirm: false
                },
                function(){
                    location.href = "login.html";
                }
            );
        }
    });
}
function AdminHeadInfo(AdminInfo){
    $(".logged-user").find("img").attr("src","/PhotoShowMgr/imgs/"+AdminInfo.adminForm.id+"/"+AdminInfo.adminForm.headImg);
    $(".logged-user").find("h4").text(AdminInfo.adminForm.name);
    $(".dropdown-toggle").find("span:eq(0)").text(AdminInfo.adminForm.name);
    $(".dropdown-toggle").find("img").attr("src","/PhotoShowMgr/imgs/"+AdminInfo.adminForm.id+"/"+AdminInfo.adminForm.headImg);
}