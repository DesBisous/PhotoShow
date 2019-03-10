/**
 * Created by dairuihe on 2016/4/25.
 */
function getPersonlGalleryAddress(title) {
    var json ="{'addressForm':{'address':'"+title+"'}}";
    var jsonObj;
    console.log(json);
    $.ajax({
        type : "post",
        data : json,
        url : "/PhotoShow/album_showAnAlbum.action",
        contentType : "application/json",
        async:false,
        success : function(data) {
            jsonObj=eval("("+data+")");
            console.log(jsonObj);
            //alert(jsonObj[0].address);
        },
        error: function (data) {
            swal("", "系统出错！", "error");
        }
    });
}
function personalAlbumPage(page) {
    var json ="{'pageForm':{'currentPage':"+page+"}}";
    var img;
    var jsonObj;
    // console.log(json);
    $.ajax({
        type : "post",
        data : json,
        url : "/PhotoShow/album_personalAlbumShow.action",
        contentType : "application/json",
        async:false,
        success : function(data) {
            jsonObj=eval("("+data+")");
            //console.log(jsonObj);
            $(".row1").remove();
            for(var i=0;i<jsonObj.PersonalForms.length;i++){
                if(i==0 || i==2){
                    showPhoto2(jsonObj.PersonalForms[i].imge,jsonObj.PersonalForms[i].good,jsonObj.PersonalForms[i].title,jsonObj.PersonalForms[i].albumIntroduction);
                }
                if(i==1 || i==3){
                    showPhoto1(jsonObj.PersonalForms[i].imge,jsonObj.PersonalForms[i].good,jsonObj.PersonalForms[i].title,jsonObj.PersonalForms[i].albumIntroduction);
                }
            }
            $(".CurrentPage").text(jsonObj.currentPage);
        },
        error: function (data) {
            swal("", "系统出错！", "error");
        }
    });
}

function showPhoto1(img,good,title,albumIntroduction) {
    var rowDiv='<div class="row row1">' +
                    '<div class="col-lg-6 content-item">' +
                        '<div class="Photo-img">' +
                            '<img src="'+img+'" alt=""/>' +
                        '</div>'+
                    '</div>' +
                    '<div class="col-lg-6 content-item">' +
                        '<div class="Photo-info text-center">' +
                            '<i class="fa fa-heart fa-2x"><a href="#">Good <span class="badge">'+good+'</span></a></i>'+
                            '<h2>'+title+'<strong>!</strong></h2>'+
                            '<p id="Photo-info-p">'+limitLength(albumIntroduction)+'</p>'+
                            '<i class="fa fa-picture-o"><a href="/PhotoGallery.html" class="goGallery" title="'+title+'">进入相册</a></i>'+
                         '</div>' +
                    '</div>'+
                 '</div>';
    $(".personalPhotoShow").prepend(rowDiv);
}
function showPhoto2(img,good,title,albumIntroduction) {
    var rowDiv='<div class="row row1">' +
                    '<div class="col-lg-6 content-item">' +
                        '<div class="Photo-info text-center">' +
                            '<i class="fa fa-heart fa-2x"><a href="#">Good <span class="badge">'+good+'</span></a></i>'+
                            '<h2>'+title+'<strong>!</strong></h2>'+
                            '<p id="Photo-info-p">'+limitLength(albumIntroduction)+'</p>'+
                            '<i class="fa fa-picture-o"><a href="/PhotoGallery.html" class="goGallery" title="'+title+'">进入相册</a></i>'+
                        '</div>' +
                     '</div>'+
                    '<div class="col-lg-6 content-item">' +
                        '<div class="Photo-img">' +
                             '<img src="'+img+'" alt=""/>' +
                        '</div>'+
                    '</div>' +
                '</div>';
    $(".personalPhotoShow").prepend(rowDiv);
}
//  控制P的长度
function limitLength( Content ){
    //   MAX=232
    var PLength = Content.length;
    if( PLength>=230 ){
        Content = Content.substring(0,228);
        Content = Content+"...";
    }
    return Content;
}