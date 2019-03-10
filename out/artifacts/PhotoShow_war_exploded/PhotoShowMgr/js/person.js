/**
 * Created by asus on 2016/5/30.
 */
$(function(){
    var AdminInfo = backAdminInfo();
    // console.log(AdminInfo);
    // alert(AdminInfo.adminForm.id);
    AdminPerson(AdminInfo);
});
function AdminPerson(AdminInfo) {
    var admin ='<div class="media"> ' +
        '<a class="col-lg-3 col-md-3 col-sm-12 col-xs-12 person-a " > ' +
        '<h4>'+AdminInfo.adminForm.name+'</h4> ' +
        '<img class="thumb media-object" src="/PhotoShowMgr/imgs/'+AdminInfo.adminForm.id+'/'+AdminInfo.adminForm.headImg+'" alt="" > ' +
        '</a> ' +
        '<div class="media-body person-body col-lg-9 col-md-9 col-sm-12 col-xs-12" > ' +
        '<address> ' +
        '<p>联系电话：<strong>'+AdminInfo.adminForm.phone+'</strong></p> ' +
        '<p>邮箱：<strong>'+AdminInfo.adminForm.email+'</strong></p> ' +
        '<p>住址：<strong>'+AdminInfo.adminForm.address+'</strong></p><br> ' +
        '<p>账号：<strong>'+AdminInfo.adminForm.adminid+'</strong></p> ' +
        '</address> ' +
        '<ul class="social-links"> ' +
        '</ul> ' +
        '</div> ' +
        '</div>';
    $(".panel-body:eq(1)").append(admin);
}