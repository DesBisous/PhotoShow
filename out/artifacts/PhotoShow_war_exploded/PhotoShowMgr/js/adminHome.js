/**
 * Created by ruihe on 16-5-21.
 */
$(function () {
    onLine();
    sortsOfAlbum();
    maxNumOrderBy();
    betterUser();
    BackFiveAdmin();
    TwoTypeAlbum();
});

function onLine() {
    $.ajax({
        type : "post",
        url : "/PhotoShow/adminHome_onLine.action",
        success : function(data) {
            var jsonObj=eval("("+data+")");
            console.log(data);
            if(jsonObj.state=="success"){
                $(".online").text(jsonObj.online);
                $(".allUser").text(jsonObj.allUser);
            }
            if(jsonObj.state=="error"){
                alert("data is error");
            }
        },
        error: function (data) {
            console.log(data);
        }
    });
}
function sortsOfAlbum() {
    $.ajax({
        type : "post",
        url : "/PhotoShow/adminHome_sortsOfAlbum.action",
        success : function(data) {
            var jsonObj=eval("("+data+")");
            console.log(data);
            if(jsonObj.state=="success"){
                $(".tour").text(jsonObj.tour);
                $(".life").text(jsonObj.life);
                $(".natural").text(jsonObj.natural);
                $(".camp").text(jsonObj.camp);
                $(".party").text(jsonObj.party);
                $(".other").text(jsonObj.other);
            }
            if(jsonObj.state=="error"){
                alert("data is error");
            }
        },
        error: function (data) {
            console.log(data);
        }
    });
}
function maxNumOrderBy() {
    $.ajax({
        type : "post",
        url : "/PhotoShow/adminHome_maxNumOrderBy.action",
        success : function(data) {
            var jsonObj=eval("("+data+")");
            if(jsonObj.state=="success"){
                $(".is-img1").attr("src","../imgs/"+jsonObj[0].id+"/"+jsonObj[0].title+"/"+jsonObj[0].userHeadImg);
                $(".is-img2").attr("src","../imgs/"+jsonObj[1].id+"/"+jsonObj[1].title+"/"+jsonObj[1].userHeadImg);
                $(".is-img3").attr("src","../imgs/"+jsonObj[2].id+"/"+jsonObj[2].title+"/"+jsonObj[2].userHeadImg);

                $(".is-good1").text(jsonObj[0].good);
                $(".is-good2").text(jsonObj[1].good);
                $(".is-good3").text(jsonObj[2].good);

                $(".is-album1 h4").text("相册名:"+jsonObj[0].title);
                $(".is-album2 h4").text("相册名:"+jsonObj[1].title);
                $(".is-album3 h4").text("相册名:"+jsonObj[2].title);

                $(".is-album1 span").text("作者:"+jsonObj[0].userId);
                $(".is-album2 span").text("作者:"+jsonObj[1].userId);
                $(".is-album3 span").text("作者:"+jsonObj[2].userId);

                $(".is-album1 p").text("相册介绍:"+limitLength(jsonObj[0].albumIntroduction));
                $(".is-album2 p").text("相册介绍:"+limitLength(jsonObj[1].albumIntroduction));
                $(".is-album3 p").text("相册介绍:"+limitLength(jsonObj[2].albumIntroduction));
            }
            if(jsonObj.state=="error"){
                alert("maxNumOrderBy is error");
            }
        },
        error: function (data) {
            console.log(data);
        }
    });
}
function betterUser() {
    $.ajax({
        type:"post",
        url:"/PhotoShow/adminHome_betterUser.action",
        success:function (data) {
            var jsonObj=eval("("+data+")");
            if(jsonObj.state=="success"){
                jsonObj=jsonObj.data;
                var percent=0.0;
                for(var i=0;jsonObj.length;i++){
                    if(i==0){
                        percent=90;
                    }else {
                        percent=(jsonObj[i].userAlbumNum)/(jsonObj[0].userAlbumNum) *90;
                    }
                    if(jsonObj[i].userName==null){
                        name=jsonObj[i].userId;
                    }else {
                        name=jsonObj[i].userName;
                    }
                    aBeterUserHtml(jsonObj[i],percent,name);
                }
            }
            if(jsonObj.state=="error"){
                swal("", "系统异常！", "error");
            }
        },
        error:function (data) {
            swal("", "系统异常！", "error");
        }
    });
}
function BackFiveAdmin(){
    $.ajax({
        type : "post",
        data : json,
        url : "/PhotoShow/admin_backFiveAdmin.action",
        contentType : "application/json",
        success : function(data) {
            jsonObj = eval("(" + data + ")");
            if(jsonObj.state=="success"){
                $.each(jsonObj.adminForms, function(n,value) {
                    AdminHtml(n, value);
                });
            }else{
                swal("", "系统异常！", "error");
            }
        },
        error:function (data) {
            swal("", "系统出错！", "error");
        }
    });
}
function TwoTypeAlbum(){
    $.ajax({
        type : "post",
        data : "{'TwoTypeAlbum':'true'}",
        url : "/PhotoShow/adminHome_TwoTypeAlbum.action",
        contentType : "application/json",
        success : function(data) {
            jsonObj = eval("(" + data + ")");
            if(jsonObj.state=="success"){
                var sum = parseInt(jsonObj.NoOtherTheme) + parseInt(jsonObj.OtherTheme);
                var NoOtherTheme = parseInt(parseInt(jsonObj.NoOtherTheme)/sum*100);
                var OtherTheme = parseInt(parseInt(jsonObj.OtherTheme)/sum*100);
                $(".knob:eq(0)").find("span").attr("data-percent",NoOtherTheme);
                $(".knob:eq(0)").find("span").find("span.percent").text(NoOtherTheme+"%");
                $(".knob:eq(1)").find("span").attr("data-percent",OtherTheme);
                $(".knob:eq(1)").find("span").find("span.percent").text(OtherTheme+"%");
            }else{
                swal("", "系统异常！", "error");
            }
        },
        error:function (data) {
            swal("", "系统出错！", "error");
        }
    });
}
function aBeterUserHtml(jsonObj,percent,name) {
    var isHtml='<li>'+
                    '<div class="prog-avatar">'+
                        '<img src="../imgs/'+jsonObj.id+'/'+jsonObj.userHeadImg+'" alt=""/>'+
                    '</div>'+
                    '<div class="details">'+
                        '<div class="title">'+
                            '<a href="#">'+name+'</a>'+
                        '</div>'+
                        '<div class="progress progress-xs">'+
                            '<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: '+percent+'%">'+
                                '<span class="">'+percent+'%</span>'+
                            '</div>'+
                        '</div>'+
                    '</div>'+
                '</li>';
    $(".betterUser").append(isHtml);
}
function AdminHtml(n, value) {
    var isHtml='<li> ' +
        '<div class="prog-avatar"> ' +
        '<img src="/PhotoShowMgr/imgs/'+value['id']+'/'+value['headImg']+'" alt=""/> ' +
        '</div> ' +
        '<div class="details"> ' +
        '<div class="title"> ' +
        '<a href="#">'+value['name']+'</a><br> ' +
        '<p>Email:'+value['email']+'</p> ' +
        '</div> ' +
        '</div> ' +
        '</li>';
    $(".goal-progress:eq(1)").append(isHtml);
}
function limitLength( Content ){
    //   MAX=210
    var PLength = Content.length;
    if( PLength>=23 ){
        Content = Content.substring(0,21);
        Content = Content+"...";
    }
    return Content;
}