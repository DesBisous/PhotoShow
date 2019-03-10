/**
 * Created by asus on 2016/5/1.
 */
$(function(){
    //初始化的时候让.ThemeImg高度随着宽度变化
    ThemeImgObjectWidthToHeight();

    //  监视游览器的宽度
    {
        $(window).resize(function(){
            //让.ThemeImg高度随着宽度变化
            ThemeImgObjectWidthToHeight();
        });
    }
    //Theme样式
    $("#Theme-aide1").hover(function(){
        ThemeAideHover("Theme-aide1");
    });
    $("#Theme-aide1").mouseout(function(){
        ThemeAideMouseout("Theme-aide1");
    });
    $("#Theme-aide2").hover(function(){
        ThemeAideHover("Theme-aide2");
    });
    $("#Theme-aide2").mouseout(function(){
        ThemeAideMouseout("Theme-aide2");
    });
    $("#Theme-aide3").hover(function(){
        ThemeAideHover("Theme-aide3");
    });
    $("#Theme-aide3").mouseout(function(){
        ThemeAideMouseout("Theme-aide3");
    });
    $("#Theme-aide4").hover(function(){
        ThemeAideHover("Theme-aide4");
    });
    $("#Theme-aide4").mouseout(function(){
        ThemeAideMouseout("Theme-aide4");
    });
    $("#Theme-aide5").hover(function(){
        ThemeAideHover("Theme-aide5");
    });
    $("#Theme-aide5").mouseout(function(){
        ThemeAideMouseout("Theme-aide5");
    });
    $("#Theme-aide6").hover(function(){
        ThemeAideHover("Theme-aide6");
    });
    $("#Theme-aide6").mouseout(function(){
        ThemeAideMouseout("Theme-aide6");
    });
    
    
    
    
    
    
    
    
    
    
});
//Theme样式
function ThemeAideHover(obj){
    $("#"+obj).prev().css({'z-index':'2','border-left':'15px solid #ea66a6','padding':'2px 45px'});
    $("#"+obj).prev().prev().prev().animate({'opacity':'0.8'},400);
    $("#"+obj).prev().prev().children("p").animate({'font-size':'38px'},200);
    $("#"+obj).prev().find("p").css({'opacity':'1'});
    if(obj=="Theme-aide1"){
        $("#"+obj).prev().find("p").show().animate({'width':$("#"+obj).width()*0.2+'px'}, 300);
        $("#"+obj).prev().show().animate({'width':$("#"+obj).width()*0.4+'px'}, 0.1);
    }else if( obj=="Theme-aide6" ){
        $("#"+obj).prev().find("p").show().animate({'width':$("#"+obj).width()*0.3+'px'}, 300);
        $("#"+obj).prev().show().animate({'width':$("#"+obj).width()*0.4+'px'}, 0.1);
    }else{
        $("#"+obj).prev().find("p").show().animate({'width':$("#"+obj).width()*0.6+'px'}, 300);
        $("#"+obj).prev().show().animate({'width':$("#"+obj).width()*0.6+'px'}, 0.1);
    }
}
function ThemeAideMouseout(obj){
    $("#"+obj).prev().prev().prev().animate({'opacity':'1'},100);
    $("#"+obj).prev().prev().children("p").animate({'font-size':'30px'},200);
    $("#"+obj).prev().find("p").animate({'opacity':'0'}, 0.1);
    $("#"+obj).prev().find("p").animate({'width':'0px'}, 0.1);
    $("#"+obj).prev().animate({'width':'0px','z-index':'-1','padding':'0px'}, 0.1);
}
function ThemeImgObjectWidthToHeight(){
    //初始化的时候让.Gallery-imgObject高度随着宽度变化
    $( ".Theme-body").find(".col-lg-8").find( ".Theme-imgin" ).each(function() {
        $(this).height( $(this).width()*0.435 );
    });
    $( ".Theme-body").find(".col-lg-12").find( ".Theme-imgin" ).each(function() {
        $(this).height( $(this).width()*0.28 );
    });
    $( ".Theme-body").find(".col-lg-4").find( ".Theme-imgin" ).each(function() {
        $(this).height( $(this).width()*0.90 );
    });
}
