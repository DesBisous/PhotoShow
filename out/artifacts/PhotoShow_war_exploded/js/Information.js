$(function(){
    
    {
        //用来判断基础信息是否可以提交了
//        用户名
        $("#inputUser").blur(function(){
            if($(this).val().length>10){
                falseTip($("#inputUser-tip"));
            }else{
                trueTip($("#inputUser-tip"));
            }
        });
//        邮箱
        $("#inputEmail").blur(function(){
            var isMobile=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/; //邮箱验证规则
            if($(this).val() == ""||$(this).val()==null||$(this).val().length<=0){
                trueTip($("#inputPhone-tip"));
            }else{
                if( !isMobile.test($(this).val()) ){
                    falseTip($("#inputEmail-tip"));
                }else{
                    trueTip($("#inputEmail-tip"));
                }
            }
        });
//        联系电话
        $("#inputPhone").blur(function(){
            var isMobile=/^(?:13\d|15\d|18\d)\d{5}(\d{3}|\*{3})$/; //手机号码验证规则
            var isPhone=/^((0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;   //座机验证规则
            if($(this).val() == ""||$(this).val()==null||$(this).val().length<=0){
                trueTip($("#inputPhone-tip"));
            }else{
                if( isMobile.test($(this).val()) || isPhone.test($(this).val()) ){
                    trueTip($("#inputPhone-tip"));
                }else{
                    falseTip($("#inputPhone-tip"));
                }  
            }
        });
//        居住地
        $("#inputAddress").blur(function(){
            if( $(this).val().length>=50 ){
                falseTip($("#inputAddress-tip"));
            }else{
                trueTip($("#inputAddress-tip"));
            }
        });  
        
//        基本信息提交按钮按断是否可以提交
        $("#form-Sign1").click(function(){
            if( $(".base .fa-times-circle-o").size()>0 ){
                $("#Error1").removeClass("MyDiaplay");
                $("#Error1").css("display","inline");
                return false;
            }else{
//                这里判断input不为空的传给服务器修改
                updateInfo();
                $("#Error1").css("display","none");
                return false;
            }
        });
        
//        判断修改密码部分
//        密码
        $("#inputPassword1").blur(function(){
            if( $(this).val() == ""||$(this).val()==null||$(this).val().length<=0 ){
                falseTip($("#inputPassword1-tip"));
            }else{
                if( $(this).val().length<=20 && $(this).val().length>=5 ){
                    trueTip($("#inputPassword1-tip"));
                }else{
                    falseTip($("#inputPassword1-tip"));
                }
            }
        });  
         $("#inputPassword1").keyup(function(){
            if( $(this).val() == ""||$(this).val()==null||$(this).val().length<=0 ){
                falseTip($("#inputPassword1-tip"));
            }else{
                if( $(this).val().length<=20 && $(this).val().length>=5 ){
                    if( $("#inputPassword2").val() == ""||$("#inputPassword2").val()==null||$("#inputPassword2").val().length<=0 ){
                        trueTip($("#inputPassword1-tip"));  
                    }else{
                        if($("#inputPassword2").val() == $(this).val()){ trueTip($("#inputPassword1-tip"));   }
                        else{ falseTip($("#inputPassword1-tip")); }
                    }
                }else{
                    falseTip($("#inputPassword1-tip"));
                }
            }
        });  
//        确认密码
        $("#inputPassword2").blur(function(){
            if( $(this).val() == ""||$(this).val()==null||$(this).val().length<=0 ){
                falseTip($("#inputPassword2-tip"));
            }else{
                if( $("#inputPassword1").val() == $(this).val() ){
                    trueTip($("#inputPassword2-tip"));
                }else{
                    falseTip($("#inputPassword2-tip"));
                }
            }
        });  
        $("#inputPassword2").keyup(function(){
            if( $(this).val() == ""||$(this).val()==null||$(this).val().length<=0 ){
                falseTip($("#inputPassword2-tip"));
            }else{
                if( $("#inputPassword1").val() == $(this).val() ){
                    trueTip($("#inputPassword2-tip"));
                }else{
                    falseTip($("#inputPassword2-tip"));
                }
            }
        });
//        修改密码提交按钮按断是否可以提交
        $("#form-Sign2").click(function(){
            if( $(".Pw .fa-times-circle-o").size()>0 ){
                $("#Error2").removeClass("MyDiaplay");
                $("#Error2").css("display","inline");
                return false;
            }else{
                if( $("#inputPassword1").val() == ""||$("#inputPassword1").val()==null||$("#inputPassword1").val().length<=0 ){
                    $("#Error2").removeClass("MyDiaplay");
                    $("#Error2").css("display","inline");
                    return false;  
                }else{
                    updatePwd();
                    $("#Error2").css("display","none");
                    swal("","密码修改成功!","success");
                    return false; 
                }
            }
        });
        
        
        function trueTip( str ){
            $(str).removeClass("MyDiaplay");
            $(str).removeClass("fa-times-circle-o");
            $(str).addClass("fa-check-square-o");
            $(str).css("color","#5cb85c");  
        };
        function falseTip( str ){
            $(str).removeClass("MyDiaplay");
            $(str).removeClass("fa-check-square-o");
            $(str).addClass("fa-times-circle-o");
            $(str).css("color","red");
        };
    }
    
});

