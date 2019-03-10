$(function () {
    //  监视游览器的宽度
    {
        $(window).resize(function(){
            //让.hotoGallery-Imag高度随着宽度变化
            HotoGalleryImagWidthToHeight();
        });
    }
    var title=$.cookie("address");
    goToPhotoGallery(title);
    window.MyPage = 1;
    //点击换页
    $(".PreviousAndNext").click(function(){
        if( $(this).html() == "Previous" ) MyPage = Number($(".MyPage").html())-1;
        else MyPage = Number($(".MyPage").html())+1;
        $(".PhotoGallery-body").children("div").each(function(){
            $(this).remove();
        });
        goToPhotoGallery(title);
        return false;
    });

    //图案片预览代码
    {

        $('.Panorama-Img').fullscreenslides();

        var $container = $('#fullscreenSlideshowContainer');

        $container.bind("init", function() {
                $container
                    .append('<div class="ui" id="fs-close">&times;</div>')
                    .append('<div class="ui" id="fs-loader">Loading...</div>')
                    .append('<div class="ui" id="fs-prev">&lt;</div>')
                    .append('<div class="ui" id="fs-next">&gt;</div>')
                    .append('<div class="ui" id="fs-caption"><span></span></div>');

                $('#fs-prev').click(function(){
                    $container.trigger("prevSlide");
                });

                $('#fs-next').click(function(){
                    $container.trigger("nextSlide");
                });

                $('#fs-close').click(function(){
                    $container.trigger("close");
                });

            })

            .bind("startLoading", function() {
                $('#fs-loader').show();
            })

            .bind("stopLoading", function() {
                $('#fs-loader').hide();
            })

            .bind("startOfSlide", function(event, slide) {

                $('#fs-caption span').text(slide.title);
                $('#fs-caption').show();
            })

            .bind("endOfSlide", function(event, slide) {
                $('#fs-caption').hide();
            });
    }

});
function goToPhotoGallery(title) {
    var json ="{'addressForm':{'address':'"+title+"'}}";
    console.log(json);
    var jsonObj;
    var Strat;
    var End;
    var Allpage;
    $.ajax({
        type : "post",
        data : json,
        url : "/PhotoShow/home_showAlbumFromHome.action",
        contentType : "application/json",
        async:true,
        success : function(data) {
            jsonObj=eval("("+data+")");
            console.log(jsonObj);

            $(".userId").text(jsonObj.userId);
            $(".createTime").text(jsonObj.createTime);
            $(".title").text(jsonObj.title);
            $(".good").text(jsonObj.good);
            $(".albumIntroduction").html(jsonObj.albumIntroduction);

            //showPhoto方式
            if( jsonObj.address.length % 11 == 0 ) Allpage = jsonObj.address.length/11;
            else Allpage = jsonObj.address.length/11+1;
            Allpage = Math.floor(Allpage);
            if( MyPage >= Allpage ) MyPage = Allpage;
            else if( MyPage <= 0 ) MyPage = 1;
            else MyPage = MyPage;
            $(".MyPage").html(MyPage);
            Strat = (MyPage-1)*11;
            End = MyPage*11;
            if( End >= jsonObj.address.length ) End = jsonObj.address.length;
            theWayShowPhoto(jsonObj.address,Strat,End);
        },
        error: function (data) {
            swal("", "系统出错！", "error");
        }
    });
}

function theWayShowPhoto(jsonObj,start,end) {
    for(var i=start+1;i<=end;i++){
        if((i-start)%7<5 && (i-start)%7!=0){
            createPhotoHome1(jsonObj[i-1].address);
        }
        if((i-start)%7>=5 || (i-start)%7==0){
            createPhotoHome2(jsonObj[i-1].address);
        }
    }
    HotoGalleryImagWidthToHeight();
    $(".PhotoGallery-Imag").find("a").click(function() {
        $(".PhotoGallery-Imag").find("img").fullscreenslides();
        return false;
    });
}

function createPhotoHome1(img){
    var photoHtml= '<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">'+
                        '<div class="PhotoGallery-Imag">'+
                            '<a href="'+img+'" rel="gallery" title="">'+
                                '<img src="'+img+'" alt="" />'+
                                '<div class="PhotoGallery-Imag-p">'+
                                    '<h4>全屏查看</h4>'+
                                    '<p><i class="fa fa-search-plus" aria-hidden="true" ></i></p>'+
                                '</div>'+
                            '</a>'+
                        '</div>'+
                    '</div>';
    $(".PhotoGallery-body").append(photoHtml);
}
function createPhotoHome2(img){
    var photoHtml= '<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">'+
                        '<div class="PhotoGallery-Imag">'+
                            '<a href="'+img+'" rel="gallery" title="">'+
                                '<img src="'+img+'"" alt="" />'+
                                '<div class="PhotoGallery-Imag-p">'+
                                    '<h4>全屏查看</h4>'+
                                    '<p><i class="fa fa-search-plus" aria-hidden="true" ></i></p>'+
                                '</div>'+
                            '</a>'+
                        '</div>'+
                    '</div>';
    $(".PhotoGallery-body").append(photoHtml);
}
function HotoGalleryImagWidthToHeight(){
    //初始化的时候让.hotoGallery-Imag高度随着宽度变化
    $(".PhotoGallery-Imag").each(function() {
        $(this).height( $(this).width()*0.56 );
    });
}