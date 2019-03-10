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
                    swal("5秒后回到主页", "请先登录后才能查看服务记录！", "error")
                    window.setTimeout(function(){
                        location.href = "/index3.html";
                        return false;
                    },5000);
                }
            }
        });
        if(jsonObj.state=="error"){
            swal("5秒后回到主页", "请先登录后才能查看服务记录！", "error")
            window.setTimeout(function(){
                location.href = "/index3.html";
                return false;
            },5000);
        }
    }

// ajax像后台获取数据
    {
        if(jsonObj.state=="success"){
            var Title = $("head").children("title").html();
            var top = 1;
            if( Title == "我的服务记录/网站问题"){
                top = 1;
            }
            if( Title == "我的服务记录/照片共享问题"){
                top = 2;
            }
            if( Title == "我的服务记录/账户问题/投诉建议"){
                top = 3;
            }
            if( Title == "我的服务记录/其他"){
                top = 0;
            }
            var json = "{'top':'"+top+"'}";
            $.ajax({
                type : "post",
                url : "/PhotoShow/SC_MyServiceRecord_MyServiceRecord.action",
                async : false, // 注意此处需要同步，
                dataType: 'json',//服务器返回的数据类型。可以为xml,script,json,html。如果不填写，
                data : json,
                contentType : "application/json",
                success:function (data){
                    jsonObj=eval("("+data+")");
                    if(jsonObj.state=="success"){
                        $.each(jsonObj.Suggestionboxs, function(n,value,Name,HeadImg) {
                            insertElement(n,value,jsonObj.Name,jsonObj.userHeadImg);
                        });
                        return false;
                    }
                }
            });
        }
    }
});
function insertElement(n,value,Name,HeadImg) {
    var medialist = $("<ul class='media-list'></ul>");
    var mediali = $("<li class='media'></li>");
    var medialeft = $("<div class='media-left'></div>");
    var medialeftA = $("<a><div class='media-left-img'><img class='media-object img-thumbnail' src='imgs/"+value['userid']+"/"+HeadImg+"' alt=''/></div></a>");
    var mediabody = $("<div class='media-body'></div>");
    var mediaUserinfoStrong = $("<strong>会员:</strong>");
    var mediaUserinfoSpan = $("<span>"+Name+"</span>");
    var mediaUserinfoI = $("<i>"+value['sugTime']+"</i>");
    var mediaUserinfoH4 = $("<h4 class='media-heading'>"+value['suggestionTitle']+"</h4>");
    var mediaUserinfoSug = $("<div class='media-body-sug'><p class='edia-body-sug-p'>"+limitLength( value['sugContent'] )+"</p></div>");
    var CSmediadiv = $("<div class='media'><div>");
    var CSmedialeft = $("<div class='media-left'><div>");
    var CSmediabody = $("<div class='media-body'><div>");
    var CSmediinfoStrong;
    var CSmediainfoSpan;
    var CSmediainfoI;
    var CSmediainfoSug;
    if( value['csId'] == "null" || value['csId'] == null){
        CSmediinfoStrong = $("<strong></strong>");
        CSmediainfoSpan = $("<span>请耐心静候客服的回复:</span>");
        CSmediainfoI = $("<i></i>");
        CSmediainfoSug = $("<div class='media-body-sug'><p class='edia-body-sug-p'></p></div>");
    }else {
        CSmediinfoStrong = $("<strong>客服</strong>");
        CSmediainfoSpan = $("<span>"+value['csId']+"回复:</span>");
        CSmediainfoI = $("<i>"+value['csTime']+"</i>");
        CSmediainfoSug = $("<div class='media-body-sug'><p class='edia-body-sug-p'>"+limitLength( value['csContent'] )+"</p></div>");
    }
    //
    medialeft.append(medialeftA);
    CSmediabody.append(CSmediinfoStrong,CSmediainfoSpan,CSmediainfoI,CSmediainfoSug);
    CSmediadiv.append(CSmedialeft,CSmediabody);
    mediabody.append(mediaUserinfoStrong,mediaUserinfoSpan,mediaUserinfoI,mediaUserinfoH4,mediaUserinfoSug,CSmediadiv);
    mediali.append(medialeft,mediabody);
    medialist.append(mediali);
    $(".MSR-li-body").append(medialist);
}
function limitLength( Content ){
    //   MAX=95
    var PLength = Content.length;
    if( PLength>=92 ){
        Content = Content.substring(0,90);
        Content = Content+"...";
    }
    return Content;
}