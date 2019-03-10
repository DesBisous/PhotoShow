var num=1;
$(function(){
    personalAlbumPage(1)
    // 动态html绑定click
    $(document).on('click','.goGallery',function () {
        //alert($(this).attr("title"));
        getPersonlGalleryAddress($(this).attr("title"));
    });
    //对点击首页 上一页 下一页 尾页 进行计算当前页
    {
        $(".pagination a").click(function(){
            var CurrentPage = Number($(".CurrentPage").text());
            if( $(this).html() == "首页" ) CurrentPage = 1;
            else if( $(this).html() == "上一页" ) CurrentPage = CurrentPage - 1;
            else if( $(this).html() == $(".CurrentPage").html() ) CurrentPage = CurrentPage;
            else if( $(this).html() == "下一页" ) CurrentPage = CurrentPage + 1;
            else if( $(this).html() == "尾页" ) CurrentPage = 9999;
            personalAlbumPage(CurrentPage);
            return false;
        });
    }






});


