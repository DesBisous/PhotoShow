/**
 * Created by asus on 2016/5/12.
 */
$(function(){
    proposalInfo();
    //固定块
    Myscroll();
    Myresize();
    $(".NewDate").text("Time："+Dateshow());

});
function Myresize(){
    //获取要定位元素距离浏览器顶部的距离
    var navH = $(".proposal-right").offset().top;
    $(window).resize(function(){
        scrofixed(navH);
    });
}
function Myscroll(){
    //获取要定位元素距离浏览器顶部的距离
    var navH = $(".proposal-right").offset().top;

    //滚动条事件
    $(window).scroll(function(){
        scrofixed(navH);
    })
}
function scrofixed(navH){
    //获取滚动条的滑动距离
    var scroH = $(document).scrollTop();
    //滚动条的滑动距离大于等于定位元素距离浏览器顶部的距离，就固定，反之就不固定
    if(scroH>=navH){
        $(".proposal-right").css({"position":"fixed","top":"51px","width":$(".proposal-right").parent().width()});
    }else if(scroH<navH){
        $(".proposal-right").css({"position":"","width":"100%"});
    }
}
function proposalInfo(){
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
            appearHtml(jsonObj);
        },
        error:function (data) {
            swal("", "系统出错！", "error");
        }
    });
    return false;
}
function appearHtml( json ){
    $(".blog-post").find("strong:eq(1)").text(json.FeedbackForms1==null?0:json.FeedbackForms1.length);
    $(".blog-post").find("strong:eq(3)").text(json.FeedbackForms2==null?0:json.FeedbackForms2.length);
    $(".blog-post").find("strong:eq(5)").text(json.FeedbackForms3==null?0:json.FeedbackForms3.length);
    $(".blog-post").find("strong:eq(7)").text(json.FeedbackForms4==null?0:json.FeedbackForms4.length)

    $(".panel-body").find(".well:eq(0)").find("strong").each(function(n,value){
        if( n+1<=jsonObj.FeedbackForms1.length ){
            $(this).text(limitLength(jsonObj.FeedbackForms1[n].fbtext));
        }else return false;
    });
    $(".panel-body").find(".well:eq(1)").find("strong").each(function(n,value){
        if( n+1<=jsonObj.FeedbackForms2.length ){
            $(this).text(limitLength(jsonObj.FeedbackForms2[n].fbtext));
        }else return false;
    });
    $(".panel-body").find(".well:eq(2)").find("strong").each(function(n,value){
        if( n+1<=jsonObj.FeedbackForms3.length ){
            $(this).text(limitLength(jsonObj.FeedbackForms3[n].fbtext));
        }else return false;
    });
    $(".panel-body").find(".well:eq(3)").find("strong").each(function(n,value){
        if( n+1<=jsonObj.FeedbackForms4.length ){
            $(this).text(limitLength(jsonObj.FeedbackForms4[n].fbtext));
        }else return false;
    });
}
function limitLength( Content ){
    //   MAX=210
    var PLength = Content.length;
    if( PLength>=204 ){
        Content = Content.substring(0,101);
        Content = Content+"...";
    }
    return Content;
}
function Dateshow(){
    var mydate = new Date();
    var str = "" + mydate.getFullYear() + "年";
    str += (mydate.getMonth()+1) + "月";
    str += mydate.getDate()+"日";
    return str;
}