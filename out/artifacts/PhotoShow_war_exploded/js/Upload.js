$(function(){
    $(".Upload-right-ul").change(function () {
        $("#Error1").addClass("MyDiaplay");
        $("#Error1").css("display","none");
    });
    
   $("#form-Sign").click(function(){
       var Error1 = 0;
    if( $("input[name=threme_radio]:checked").val()==null ||
            $("input[name=threme_radio]:checked").val()=="" ){
        Error1 = 0;
    }else{
        if( $("#inputTitle1").val()==null || $("#inputTitle1").val()=="" ){
            Error1 = 0;
        }else{
                Error1 = 1;
        }
    }
     if($("#inputImeg1").val()==null || $("#inputImeg1").val()==""){
         Error1 = 0;
     }
     if( $("#Upload_textarea").val()==null ||
           $("#Upload_textarea").val()=="" ) {
         Error1 = 0;
     }
      // alert(Error1);
    if( Error1==0 ){
        $("#Error1").removeClass("MyDiaplay");
        $("#Error1").css("display","inline");
        return false;
    }else{
        uploadGallery();
        //swal("","提交成功，感谢您的关注!","success");
        return false;
    }

   });
//   点击图片触发input
   $("#inputImeg1").prev().click(function(){
        $("#inputImeg1").click();
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
        $(str).next().popover('hide');
    };
    function falseTip( str ){
        $(str).removeClass("fa-comment");
        $(str).addClass("fa-times-circle-o");
        $(str).css("color","red");
    };
   
   
//    图片上传
    {
        $("#inputImeg1").change(function(){
            var filepath=$("#inputImeg1").get(0).files;
            //alert($("#inputImeg1").get(0).files[0]+"----"+$("#inputImeg1").files+"----"+this.files);
            var extStart=new Array();
            var ext=new Array();
            var fileName=new Array();
            var fileSize=new Array();
            for (var i=0;i<filepath.length;i++){
                fileName[i]=filepath[i].name;
                fileSize[i]=filepath[i].size;
                extStart[i]=filepath[i].name.lastIndexOf(".");
                ext[i]=fileName[i].substring(extStart[i],fileName[i].length).toUpperCase();
                //console.log(fileSize[i]);
                if(ext[i]!=".BMP"&&ext[i]!=".PNG"&&ext[i]!=".GIF"&&ext[i]!=".JPG"&&ext[i]!=".JPEG"){
                    focusTip("#inputImeg1-tip");
                    $("#inputImeg1").prev().attr("src","images/register/PersonIMag.jpg");
                    $("#inputImeg1").val("");
                   // alert(fileName[i]+"格式不正确");
                   //  swal("",fileName[i]+"格式不正确","error");
                    return false;
                }else if(fileSize[i]>4194304){
                    //alert(fileName[i]+"文件超过4M");
                    // swal("",fileName[i]+"文件超过4M","error");
                    focusTip("#inputImeg1-tip");
                    $("#inputImeg1").prev().attr("src","images/register/PersonIMag.jpg");
                    $("#inputImeg1").val("");
                    return false;
                }else{
                    var objUrl = getObjectURL(filepath[i]) ;
                    //         文件对象是否存在
                    if( objUrl ){
                        $("#inputImeg1").prev().attr("src",objUrl);
                        trueTip("#inputImeg1-tip");
                    }else{
                        $("#inputImeg1").prev().attr("src","images/register/PersonIMag.jpg");
                        focusTip("#inputImeg1-tip");
                        $("#inputImeg1").val("");
                    }
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


});

