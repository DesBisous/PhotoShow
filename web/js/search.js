$(function(){
    // 获取cookie的搜索关键词,如搜索词为空，则查找所有相册
    var searchVal = $.cookie("searchVal");
    //获取当前页，默认为1
    var page = $.cookie("page");
    //绑定beforeunload事件，刷新页面的时候 page标签不改变，也就是刷新页面时不回到第一页
    $(window).bind('beforeunload',function(){
        $.cookie("page",$(".CurrentPage").html());
        alert($.cookie("page"));
    });
    //获取搜索结果的总页数，默认最大,定义为全局变量
    window.totalPage = 9999;
    //ajax请求搜索数据
    ajaxPostInfo( page, searchVal );

    //对点击首页 上一页 下一页 尾页 进行计算当前页
    {
        $(".pager a").click(function(){
            var CurrentPage = Number($(".CurrentPage").html());
            if( $(this).html() == "首页" ) CurrentPage = 1;
            else if( $(this).html() == "上一页" ) CurrentPage = CurrentPage - 1;
            else if( $(this).html() == $(".CurrentPage").html() ) CurrentPage = CurrentPage;
            else if( $(this).html() == "下一页" ) CurrentPage = CurrentPage + 1;
            else if( $(this).html() == "尾页" ) CurrentPage = totalPage;
            // 删除原先的元素
            $(".sear-container").find(".media-list").remove();
            ajaxPostInfo( CurrentPage, searchVal );
            return false;
        });
    }

    
//    控制搜索标签
    {
        $(".title-body").mouseover(function(){
            $(".title").css("top","55px");
            $(this).children(".title-p").children("p").removeClass("visibility-hidden");
            $(this).children(".title-p").children("strong").removeClass("visibility-hidden");
            $(this).children(".title-p").children(".angle-double-down").find(".fa").removeClass("fa-angle-double-down");
            $(this).children(".title-p").children(".angle-double-down").find(".fa").addClass("fa-angle-double-up");
        });
        $(".title-body").mouseout(function(){
            $(".title").css("top","-30px");
            $(".title-p").children("p").addClass("visibility-hidden");
            $(".title-p").children("strong").addClass("visibility-hidden");
            $(".title-p").children(".angle-double-down").find(".fa").removeClass("fa-angle-double-up");
            $(".title-p").children(".angle-double-down").find(".fa").addClass("fa-angle-double-down");
        });
    }

    //点击进入相册
    $(".media-list").find("a").click(function(){
        $.cookie("address",$(this).find("img").attr("src"),{path:"/"});
    });
    //点击进入相册
    $(".GodTitle").click(function(){
        $.cookie("address",$(this).parent().parent().prev().find("img").attr("src"),{path:"/"});
    });

});
function insertElement(n, value){
    var medialist = $("<ul class='media-list'></ul>");
    var mediali = $("<li class='media'></li>");
    var medialeft = $("<div class='media-left'></div>");
    // 判断当前相册是否含有图片
    var ImagPath = "images/register/PersonIMag.jpg";
    if( value['imge'] != null && value['imge'] != "" ) ImagPath = value['imge'];
    var medialeftA = $("<a href='HomePhotoGallery.html'><div class='media-left-img'><img class='media-object' src='"+ImagPath+"' alt=''/> <div class='media-left-img-text'> <h4 class='gal'>进入相册</h4> <p class='gal1'><i class='fa fa-folder-open' aria-hidden='true' style='color: #FFF; font-size: 3em'></i></p> </div> </div> </a>");
    var mediabody = $("<div class='media-body'></div>");
    var mediaHeading = $("<div class='media-heading'> <a href='HomePhotoGallery.html' class='GodTitle'><h3 >"+value['title']+"</h3></a> <p><strong>主题：</strong><span>"+value['theme']+"</span></p> </div>");
    var mediaBodySug = $("<div class='media-body-sug'> <p class='edia-body-sug-p'>"+limitLength( value['albumIntroduction'] )+"</p> </div>");
    var mediaBodyStrong = $("<strong>创建者:</strong>");
    var mediaBodySpan = $("<span>"+value['owner']+"</span>");
    var mediaBodyCreateTime = $("<div class='CreateTime'> <strong >创建时间：</strong><i>"+value['createTime']+"</i> </div>");

    medialeft.append(medialeftA);
    mediabody.append(mediaHeading,mediaBodySug,mediaBodyStrong,mediaBodySpan,mediaBodyCreateTime);
    mediali.append(medialeft,mediabody);
    medialist.append(mediali);
    $(".sear-container").append(medialist);


}
function limitLength( Content ){
    //   MAX=210
    var PLength = Content.length;
    if( PLength>=210 ){
        Content = Content.substring(0,208);
        Content = Content+"...";
    }
    return Content;
}

function ajaxPostInfo( page, searchVal ) {
    {
        var json = "{'page':'"+page+"','searchVal':'"+searchVal+"'}";
        $.ajax({
            type : "post",
            url : "/PhotoShow/Search_SearchAction.action",
            async : false, // 注意此处需要同步，
            dataType: 'json',//服务器返回的数据类型。可以为xml,script,json,html。如果不填写，
            data : json,
            contentType : "application/json;charset=utf-8",
            success:function (data){
                jsonObj=eval("("+data+")");
                if(jsonObj.state=="success"){
                    // 更新搜索词
                    if(searchVal == null || searchVal == "") $("#SearchWords").html("所有".trim());
                    else $("#SearchWords").html(searchVal.substring(0,6).trim());
                    // 更新页标
                    $(".CurrentPage").html(jsonObj.currentPage.toString().trim());
                    // 更新总页数
                    totalPage = jsonObj.totalPage.toString().trim();
                    // 遍历相册
                    $.each(jsonObj.SearchForms, function(n,value) {
                        insertElement(n,value);
                    });
                }else{
                    swal(
                        {   title: "没有搜索到结果",
                            type: "warning",
                            confirmButtonColor: "#DD6B55",
                            confirmButtonText: "close"
                        }
                    );
                }
            },
            error:function(data){
                swal(
                    {   title: "服务器异常",
                        type: "warning",
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "返回主页",
                        closeOnConfirm: false
                    },
                    function(){
                        location.href = "/index3.html";
                    }
                );
            }
        });
    }
}