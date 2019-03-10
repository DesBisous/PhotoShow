$(function(){

    //初始化时ajax
    {
        ajaxPostInfo( 9, "旅游" );
    }

    // 设置一个全局变量，用来判断当前是Gallery的哪个页面
    window.GalleryTitle = "Gallery-img01";

    //初始化的时候让.Gallery-imgObject高度随着宽度变化
    GalleryImgObjectWidthToHeight();

    /************** ToolTip *********************/

    function toolTipInit() {
	
	$('.menu li a').tooltip({
		placement: 'right'
	});
    }
    toolTipInit();
//  监视游览器的宽度
{
    $(window).resize(function(){
        var _width = $(window).width(); 
         if(_width < 1200){
//          删除清除浮动块
            $("#Gallery-item05-clearfix").css("display","none");
         }else{
//          添加清除浮动块
            $("#Gallery-item05-clearfix").css("display","block");
         }
        //让.Gallery-imgObject高度随着宽度变化
        GalleryImgObjectWidthToHeight();
    });
}
//主题导航控制
{
	$('.menu-toggle-btn').click(function(){
            $('.responsive_menu').stop(true,true).slideToggle();
        });

        $(".responsive_menu a").click(function(){
            $('.responsive_menu').hide();
        });
}

//更换事件
{
    $( ".Gallery-change" ).find("a").click(function(){
        var arr = GalleryImgTosearchVal();
        ajaxPostInfo( arr[1], arr[0] );
        return false;
    });
}
//  全景
{
    //点击全景触发a标签
    $("#Panorama").click(function(){
        //重新装载需要全景显示的图片
        $( ".Gallery-panorama" ).find("img").fullscreenslides();
        $("#Panorama-Img0").trigger('click');
    });
}
//图案片预览代码
{
	$('.Panorama-Img').fullscreenslides();

	var $container = $('#fullscreenSlideshowContainer');

	$container
	
	.bind("init", function() { 
	
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
//切换主题相册
//切换背景
{
    $(".main_menu a").click(function(){
        $(".Gallery-img01").addClass("disabled-None");
        $(".Gallery-img02").addClass("disabled-None");
        $(".Gallery-img03").addClass("disabled-None");
        $(".Gallery-img04").addClass("disabled-None");
        $(".Gallery-img05").addClass("disabled-None");
        $(".Gallery-img06").addClass("disabled-None");
        var a_class = $(this).attr("class");
        switch( a_class.toString() )
        {
        case "show-1":
            {
                $(".Gallery-img01").removeClass("disabled-None");
                $(".bg-image").fadeOut('slow', function(){
                    $(this).css({
			            'background-image' : 'url(images/Gallery/background/bg-homepage.jpg)',
                    }).fadeIn('slow');
		        });
                GalleryTitle = "Gallery-img01";
                //让.Gallery-imgObject高度随着宽度变化
                GalleryImgObjectWidthToHeight();
                ajaxPostInfo( 9, "旅游" );
            }
          break;
          case "show-2":
            {
                $(".Gallery-img02").removeClass("disabled-None");
                $(".bg-image").fadeOut('slow', function(){
                    $(this).css({
			            'background-image' : 'url(images/Gallery/background/bg-gallery.jpg)',
                    }).fadeIn('slow');
		        });
                GalleryTitle = "Gallery-img02";
                //让.Gallery-imgObject高度随着宽度变化
                GalleryImgObjectWidthToHeight();
                ajaxPostInfo( 10, "生活" );
            }
          break;
          case "show-3":
            {
                $(".Gallery-img03").removeClass("disabled-None");
                $(".bg-image").fadeOut('slow', function(){
                    $(this).css({
			            'background-image' : 'url(images/Gallery/background/bg-contact.jpg)',
                    }).fadeIn('slow');
		        });
                GalleryTitle = "Gallery-img03";
                //让.Gallery-imgObject高度随着宽度变化
                GalleryImgObjectWidthToHeight();
                ajaxPostInfo( 11, "自然" );
            }
          break;
          case "show-4":
            {
                $(".Gallery-img04").removeClass("disabled-None");
                $(".bg-image").fadeOut('slow', function(){
                    $(this).css({
			            'background-image' : 'url(images/Gallery/background/bg-about.jpg)',
                    }).fadeIn('slow');
		        });
                GalleryTitle = "Gallery-img04";
                //让.Gallery-imgObject高度随着宽度变化
                GalleryImgObjectWidthToHeight();
                ajaxPostInfo( 10, "野营" );
            }
          break;
          case "show-5":
            {
                $(".Gallery-img05").removeClass("disabled-None");
                $(".bg-image").fadeOut('slow', function(){
                    $(this).css({
			            'background-image' : 'url(images/Gallery/background/bg-services.jpg)',
                    }).fadeIn('slow');
		        });
                GalleryTitle = "Gallery-img05";
                //让.Gallery-imgObject高度随着宽度变化
                GalleryImgObjectWidthToHeight();
                ajaxPostInfo( 10, "聚会" );
            }
          break;
          case "show-6":
            {
                $(".Gallery-img06").removeClass("disabled-None");
                $(".bg-image").fadeOut('slow', function(){
                    $(this).css({
			            'background-image' : 'url(images/Gallery/background/bg-homepage.jpg)',
                    }).fadeIn('slow');
		        });
                GalleryTitle = "Gallery-img06";
                //让.Gallery-imgObject高度随着宽度变化
                GalleryImgObjectWidthToHeight();
                ajaxPostInfo( 12, "其他" );
            }
          break;
//        default:
//            {
//            }
        }
    });
}
    //点击进入相册
    $(".Gallery-body").find("a").click(function(){
        $.cookie("address",$(this).find("img").attr("src"));
    });
});
function ajaxPostInfo( pageSize, searchVal ) {
    {
        var json = "{'pageSize':'"+pageSize+"','searchVal':'"+searchVal+"'}";
        var GalleryImg = searchValToGalleryImg(searchVal);//获取那个主题的页面编号
        $.ajax({
            type : "post",
            url : "/PhotoShow/Gallery_galleryAction.action",
            async : false, // 注意此处需要同步，
            dataType: 'json',//服务器返回的数据类型。可以为xml,script,json,html。如果不填写，
            data : json,
            contentType : "application/json;charset=utf-8",
            success:function (data){
                jsonObj=eval("("+data+")");
                if(jsonObj.state=="success"){
                    $.each(jsonObj.GalleryForms, function(n,value) {
                        insertElement(n, value, GalleryImg);
                    });
                    if( jsonObj.GalleryForms.length < pageSize ){
                        for( var i = jsonObj.GalleryForms.length ; i < pageSize ; i++ ){
                            insertElement(i, null, GalleryImg);
                        }
                    }

                }else{
                    for( var i = 0 ; i < pageSize ; i++ ){
                        insertElement(i, null, GalleryImg);
                    }
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
                        location.href = "index3.html";
                    }
                );
            }
        });
    }
}
//主题转页面编号
function searchValToGalleryImg(searchVal){
    var GalleryImg = "Gallery-img01";
    switch( searchVal.toString().trim() ){
        case "旅游":
        {
            GalleryImg = "Gallery-img01";
        }
            break;
        case "生活":
        {
            GalleryImg = "Gallery-img02";
        }
            break;
        case "自然":
        {
            GalleryImg = "Gallery-img03";
        }
            break;
        case "野营":
        {
            GalleryImg = "Gallery-img04";
        }
            break;
        case "聚会":
        {
            GalleryImg = "Gallery-img05";
        }
            break;
        case "其他":
        {
            GalleryImg = "Gallery-img06";
        }
            break;
    }
    return GalleryImg;
}
//页面编号转主题
function GalleryImgTosearchVal(){
    var GalleryImg = "旅游";
    var num = 9;
    switch( GalleryTitle.toString().trim() ){
        case "Gallery-img01":
        {
            GalleryImg = "旅游";
            num = 9;
        }
            break;
        case "Gallery-img02":
        {
            GalleryImg = "生活";
            num = 10;
        }
            break;
        case "Gallery-img03":
        {
            GalleryImg = "自然";
            num = 11;
        }
            break;
        case "Gallery-img04":
        {
            GalleryImg = "野营";
            num = 10;
        }
            break;
        case "Gallery-img05":
        {
            GalleryImg = "聚会";
            num = 10;
        }
            break;
        case "Gallery-img06":
        {
            GalleryImg = "其他";
            num = 12;
        }
            break;
    }
    var arr = new Array(GalleryImg,num);
    return arr;

}
function insertElement(n, value, GalleryImg){

    //加入全景图片前先删除之前的
    if( n==0 ){
        $( ".Gallery-panorama" ).children("div").children("a").remove();
    }
    if( value == null || value == ""){
        $("."+GalleryImg).find("a:eq("+n+")").find("img").attr("src","images/Gallery/Default.png");
        $("."+GalleryImg).find("a:eq("+n+")").find("p:hidden:eq(0)").html("");
        $("."+GalleryImg).find("a:eq("+n+")").find("p:hidden:eq(1)").html("");
    }else{
        $("."+GalleryImg).find("a:eq("+n+")").find("img").attr("src",value['imgePath']);
        $("."+GalleryImg).find("a:eq("+n+")").find("p:hidden:eq(0)").html(value['userId']);
        $("."+GalleryImg).find("a:eq("+n+")").find("p:hidden:eq(1)").html(value['title']);
        //加入全景图片
        var panoramaImag = $("<a href="+value['imgePath']+" rel='gallery' title="+value['title']+" id=Panorama-Img"+n+"><img src="+value['imgePath']+" alt='' class='Panorama-Img' /></a>");
        $( ".Gallery-panorama" ).children("div").append(panoramaImag);
    }
}
function GalleryImgObjectWidthToHeight(){
    //初始化的时候让.Gallery-imgObject高度随着宽度变化
    $( "."+GalleryTitle ).find( ".Gallery-imgObject" ).each(function() {
        $(this).height( $(this).width() );
    });
}