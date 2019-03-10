$(function(){
    var isSuccess = 0;
    
    $("#ChoseUl li a").click(function(){
//        $("#ChoseUl li").removeClass("active");
//        $(this).parent().addClass("active")
        return false;
    });
    
//    注册第一页
    $("#reg-button>.btn-primary").click(function(){
        switch( $("#ChoseUl li.active").index())
        {
        case 0:
            {
                if( $("#g1oRadioGroup input[name='group1']:checked").val() == "option1" ){
                    $("#ChoseUl li").removeClass("active");
                    $("#ChoseUl li:eq(1)").addClass("active");
                    $(".reg-jumbotron1").addClass("reg-jumbotron-hiddle");
                    $(".reg-jumbotron2").removeClass("reg-jumbotron-hiddle");
                }else{
                    swal("", "请您同意了注册协议后才能继续！", "error");
                }
            }
          break;
//        case 1:
//            {
//            }
//          break;
//        case 2:
//            {
//            }
//          break;
//        default:
//            {
//            }
        }
    });
    
//    注册第二页
    $("#reg-submit").click(function(){
//        对注册信息验证是否通过
        var num = $(".fa-check-square-o").size();
        if( num == 6 ) {
            //        进行URL访问
            //        -----------------------------开始--------------------------
            registerInfo();
    //        -----------------------------结束--------------------------
    //        对获取的data反馈成功或失败操作注册第三页
    //        下面的操作要个性为registerInfo()的加调函数
          /*  var data = "success";
            var fa = $("#fa-tip1");
            var span1 = $(".reg-info-p").children().children();
            if(data == "success"){
                fa.removeClass("fa-times");
                fa.addClass("fa-check");
                span1.html("");
                span1.html("注册成功");
            }else{
                fa.removeClass("fa-check");
                fa.addClass("fa-times");
                fa.css("color","red");
                span1.html("");
                span1.html("注册失败");
            }
            $("#ChoseUl li").removeClass("active");
            $("#ChoseUl li:eq(2)").addClass("active");
            $(".reg-jumbotron2").addClass("reg-jumbotron-hiddle");
            $(".reg-jumbotron3").removeClass("reg-jumbotron-hiddle");*/
        }else{
            swal("", "请完整或正确填写注册信息", "error");
        }
        return false;
    });
    
//    文本框验证
    //inputUser
    $("#inputUser").focus(function(){
        focusTip("#inputUser-tip");
    });
    $("#inputUser").blur(function(){
        $("#inputUser-tip").next().popover('hide');
        if( $(this).val().length<=16 && $(this).val().length>=5  ){
            trueTip("#inputUser-tip");
        }else{
            if( $(this).val().length==0 ){
                focusTip("#inputUser-tip");
            }else{
                falseTip("#inputUser-tip");
            }
        }
    });
    //inputPassword
    $("#inputPassword").focus(function(){
       focusTip("#inputPassword-tip");
    });
    $("#inputPassword").blur(function(){
        $("#inputPassword-tip").next().popover('hide');
        if( $(this).val().length<=20 && $(this).val().length>=5  ){
            trueTip("#inputPassword-tip");
        }else{
            if( $(this).val().length==0 ){
                focusTip("#inputPassword-tip");
            }else{
                falseTip("#inputPassword-tip");
            }
        }
    });
    //inputRePassword
    $("#inputRePassword").focus(function(){
        focusTip("#inputRePassword-tip");
    });
    $("#inputRePassword").blur(function(){
        $("#inputRePassword-tip").next().popover('hide');
        if( $("#inputPassword").val() == $(this).val() ){
            trueTip("#inputRePassword-tip");
        }else{
            if( $(this).val().length==0 ){
                focusTip("#inputRePassword-tip");
            }else{
                falseTip("#inputRePassword-tip");
            }
        }
    });
    //Email
    $("#inputEmail").focus(function(){
        focusTip("#inputEmail-tip");
    });
    $("#inputEmail").blur(function(){
        $("#inputEmail-tip").next().popover('hide');
        var isMobile=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/; //邮箱验证规则
        if( isMobile.test($(this).val())  ){
            trueTip("#inputEmail-tip");
        }else{
            if( $(this).val().length==0 ){
                focusTip("#inputEmail-tip");
            }else{
                falseTip("#inputEmail-tip");
            }
        }
    });
    //Phone
    $("#inputPhone").focus(function(){
        focusTip("#inputPhone-tip");
    });
    $("#inputPhone").blur(function(){
        $("#inputPhone-tip").next().popover('hide');
        var isMobile=/^(?:13\d|15\d|18\d)\d{5}(\d{3}|\*{3})$/; //手机号码验证规则
        var isPhone=/^((0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;   //座机验证规则
        if( isMobile.test($(this).val()) || isPhone.test($(this).val()) ){
            trueTip("#inputPhone-tip");
        }else{
            if( $(this).val().length==0 ){
                focusTip("#inputPhone-tip");
            }else{
                falseTip("#inputPhone-tip");
            }
        }
    });
    
    
    function focusTip( str ){ 
        $(str).removeClass("fa-check-square-o");
        $(str).removeClass("fa-times-circle-o");
        $(str).addClass("fa-comment");
        $(str).css("color","");
        $(str).next().popover('show');
    };
    function trueTip( str ){
        $(str).removeClass("fa-comment");
        $(str).addClass("fa-check-square-o");
        $(str).css("color","#5cb85c");  
    };
    function falseTip( str ){
        $(str).removeClass("fa-comment");
        $(str).addClass("fa-times-circle-o");
        $(str).css("color","red");
    };
    
    
//    输入框提示
    $("i.fa-comment").hover(function(){
        $(this).next().popover('show');
    });
     $("i.fa-comment").mouseout(function(){
        $(this).next().popover('hide');
     });
    
    
    $("#inputImeg1").prev().click(function(){
        $("#inputImeg1").click();
    });
//    图片上传前预览功能
    {
        $("#inputImeg1").change(function(){
            var filepath=$("#inputImeg1").val();
            var extStart=filepath.lastIndexOf(".");
            var ext=filepath.substring(extStart,filepath.length).toUpperCase();//将toUpperCase转换成大写
            if(ext!=".BMP"&&ext!=".PNG"&&ext!=".GIF"&&ext!=".JPG"&&ext!=".JPEG"&&ext!=".bmp"&&ext!=".png"&&ext!=".gif"&&ext!=".jpg"&&ext!=".jpeg"){
                swal("", "图片限于bmp,png,gif,jpeg,jpg格式", "error");
                focusTip("#inputImeg1-tip");
                $("#inputImeg1").prev().attr("src","images/register/PersonIMag.jpg");
                return false;
            }else{
//                判断文件大小得要上传到后台进行判断
                //  this.files[0]为获取文件对象
                var objUrl = getObjectURL(this.files[0]) ;
                //         文件对象是否存在
                if( objUrl ){
                    $("#inputImeg1").prev().attr("src",objUrl);
                    trueTip("#inputImeg1-tip");
                }else{
                    $("#inputImeg1").prev().attr("src","images/register/PersonIMag.jpg");
                    focusTip("#inputImeg1-tip");
                }
            }
        });

        //建立一個可存取到該file的url，对应不同游览器有不同获取方法
        function getObjectURL(file) {
                var url = null ; 
                if (window.createObjectURL!=undefined) { // basic
                        url = window.createObjectURL(file) ;
                } else if (window.URL!=undefined) { // mozilla(firefox)
                        url = window.URL.createObjectURL(file) ;
                } else if (window.webkitURL!=undefined) { // webkit or chrome
                        url = window.webkitURL.createObjectURL(file) ;
                }
                return url ;
        }        
    }
    
})


