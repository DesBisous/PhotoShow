/**
 * Created by asus on 2016/5/12.
 */
$(function(){

    //固定块
    Myscroll();
    Myresize();
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