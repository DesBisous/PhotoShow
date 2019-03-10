/**
 * Created by dairuihe on 2016/4/30.
 */
$(function () {
    LimitWidthToHeight();
    var scroHNum = 0;
    //滚动条事件
    $(window).scroll(function(){
        //获取滚动条到顶部的垂直高度
        var scroH = $(document).scrollTop();
        if(scroH>=258&&scroHNum<1) {showTowAlbum(); scroHNum++;}
        if(scroH>=830&&scroHNum<2) {showSixPhoto(); scroHNum++;}
        if(scroH>=1564&&scroHNum<3) {showPercent(); scroHNum++;}
        if(scroH>=2047&&scroHNum<4) {$(".DrowHome-child").find("img").attr("src","images/index/DrowHome.jpg"); scroHNum++;}
        if(scroH>=2710&&scroHNum<5) {getMasterinfo(); scroHNum++;}
    })
    $(document).on('click','.thickbox',function () {
        $.cookie("address",$(this).attr("title"),{path:"/"});
    });
    //  监视游览器的宽度
    {
        $(window).resize(function(){
            //让.hotoGallery-Imag高度随着宽度变化
            LimitWidthToHeight();
        });
    }
});
function showPercent() {
    var jsonObj;
    $.ajax({
        type: "post",
        url: "/PhotoShow/home_showPercent.action",
        sync: false,
        success: function (data) {
            jsonObj=eval("("+data+")");
            if(jsonObj.state=="success"){
               // console.log(jsonObj);
                $(".america").attr("aria-valuenow",jsonObj.america);
                $(".america").css("width",jsonObj.america+"%");
                $(".america").text(jsonObj.america+"%");

                $(".china").attr("aria-valuenow",jsonObj.china);
                $(".china").css("width",jsonObj.china+"%");
                $(".china").text(jsonObj.china+"%");

                $(".britain").attr("aria-valuenow",jsonObj.britain);
                $(".britain").css("width",jsonObj.britain+"%");
                $(".britain").text(jsonObj.britain+"%");

                $(".australia").attr("aria-valuenow",jsonObj.australia);
                $(".australia").css("width",jsonObj.australia+"%");
                $(".australia").text(jsonObj.australia+"%");
            }
            if(jsonObj.state=="error"){
               // console.log(jsonObj);
            }
        },
        error: function (data) {
            swal("", "系统出错！", "error");
            return false;
        }
    });
}
function showTowAlbum() {
    var jsonObj;
    var img="images/index/1.jpg";
    $.ajax({
        type: "post",
        url: "/PhotoShow/home_twoAlbum.action",
        sync: false,
        success: function (data) {
            jsonObj=eval("("+data+")");
            if(jsonObj.state=="success"){
                jsonObj=jsonObj.data;
                console.log(jsonObj);
                $(".img1").attr('src',jsonObj[0].path);
                $(".img2").attr('src',jsonObj[1].path);

                $(".wenzhi1 h3 a").text(jsonObj[0].title);
                $(".wenzhi1 h4").text("主题:"+jsonObj[0].theme);
                $(".wenzhi1 div").text(jsonObj[0].albumIntroduction);

                $(".wenzhi2 h3 a").text(jsonObj[1].title);
                $(".wenzhi2 h4").text("主题:"+jsonObj[1].theme);
                $(".wenzhi2 div").text(jsonObj[1].albumIntroduction);
            }
        },
        error: function (data) {
            swal("", "系统出错！", "error");
            return false;
        }
    });
}
function showSixPhoto() {
    var jsonObj;
    var img="images/index/1.jpg";
    $.ajax({
        type: "post",
        url: "/PhotoShow/home_showSixAlbum.action",
        sync: false,
        success: function (data) {
            jsonObj=eval("("+data+")");
            // console.log(jsonObj);
            for(var i=0;i<6;i++){
                img=jsonObj[i];
                createAnAlbum(img);
            }
        },
        error: function (data) {
            swal("", "系统出错！", "error");
            return false;
        }
    });
}
function getMasterinfo() {
    var jsonObj;
    $.ajax({
        type: "post",
        url: "/PhotoShow/home_Masterinfo.action",
        sync: false,
        success: function (data) {
            jsonObj=eval("("+data+")");
            if(jsonObj.state=="success"){
                // console.log(jsonObj.masterinfoForms);
                $.each(jsonObj.masterinfoForms, function(n,value) {
                    modifyMasterinfo(value);
                });
            }
            if(jsonObj.state=="error"){
                swal("", "Masterinfo为空", "error");
            }
        },
        error: function (data) {
            swal("", "系统出错！", "error");
            return false;
        }
    });
}
function createAnAlbum(img) {
    var htmlStr=    '<div class="col-md-4 col1 gallery-grid">'+
                        '<a href="HomePhotoGallery.html" rel="title" class="b-link-stripe b-animate-go  thickbox" title="'+img+'">'+
                            '<figure class="effect-bubba">'+
                                '<img class="img-responsive" src="'+img+'" alt="">'+
                                '<figcaption>'+
                                    '<h4 class="gal" > Nemo voluptatem</h4>'+
                                    '<p class="gal1">In sit amet sapien eros Integer dolore magna aliqua</p>'+
                                '</figcaption>'+
                            '</figure>'+
                        '</a>'+
                    '</div>';
    $(".gallery-bott").append(htmlStr);
    LimitWidthToHeight();
}
function modifyMasterinfo(json) {
    if( json['id']=="1" ){
        $(".Present-div1:eq(0)").text(json['name']);
        $(".Present-strong2:eq(0)").text(json['nickname']);
        $(".Present-spokesman1:eq(0)").text(json['name']);
        var arr = json['basicinfo'].split('@');
        for( var i = 0; i<4 && i<arr.length; i++  ){
            $(".Present-spokesman3:eq(0)").find("p:eq("+i+")").text(arr[i]);
        }
        $(".Present-spokesman4:eq(0)").children("p").text(json['introduce']);
    }else{
        $(".Present-div1:eq(1)").text(json['name']);
        $(".Present-strong2:eq(1)").text(json['nickname']);
        $(".Present-spokesman1:eq(1)").text(json['name']);
        var arr = json['basicinfo'].split('@');
        for( var i = 0; i<4 && i<arr.length; i++  ){
            $(".Present-spokesman3:eq(1)").find("p:eq("+i+")").text(arr[i]);
        }
        $(".Present-spokesman4:eq(1)").children("p").text(json['introduce']);
    }
}
function LimitWidthToHeight(){
    //初始化的时候让.hotoGallery-Imag高度随着宽度变化
    $(".effect-bubba").each(function() {
        $(this).children("img").height( $(this).parent().parent().width()*0.751428 );
    });
}
