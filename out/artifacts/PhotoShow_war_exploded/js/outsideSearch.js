$(function(){
   $("#search-form-a").click(function () {
       // 获取搜索输入的关键词
       var searchVal = $(this).prev().val();
       $.cookie("searchVal",searchVal.toString().trim());
       $.cookie("page","1");
   });
    $("#WebSearch").click(function () {
        // 获取搜索输入的关键词
        var searchVal = $(this).prev().val();
        if(searchVal.toString().trim()=="请输入..."){
            $.cookie("searchVal","");
        }else{
            $.cookie("searchVal",searchVal.toString().trim());
        }
        $.cookie("page","1");
        location.href ="search.html";
    });
});