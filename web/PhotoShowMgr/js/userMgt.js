/**
 * Created by ruihe on 16-5-27.
 */
$(function () {
    getUser(1);
    $(document).on('click','.deleteUser',function () {
        deleteUser($(this).attr("title"));
    });
    $(".findBtn").click(function () {
        findUser();
    });
    //页面转换
    {
        $(".pagination a").click(function(){
            var CurrentPage = Number($(".CurrentPage").text());
            if( $(this).text() == "«" ) CurrentPage = CurrentPage - 1;
            else if( $(this).text() == "»" ) CurrentPage = CurrentPage + 1;
            if( CurrentPage<=0  ) CurrentPage = 1;
            getUser(CurrentPage);
            return false;
        });
    }
});

function findUser() {
    var jsonObj;
    var keywords=$(".find").val();
    var json="{'keyWordsForm':{'keywords':'"+keywords+"'}}";
    $.ajax({
        type:"post",
        data:json,
        url:"/PhotoShow/adminUser_findUser.action",
        contentType:"application/json",
        success:function (data) {
            jsonObj=eval("("+data+")");
            $(".userTbody").children().remove();
            if(jsonObj.state="success"){
                jsonObj=jsonObj.data;
                for(var i=0;i<jsonObj.length;i++){
                    createTable(jsonObj[i]);
                }
            }
        },
        error:function (data) {
            console.log(data);
        }
    })
}

function getUser(page) {
    var jsonObj;
    var json="{'userPageForm':{'page':'"+page+"'}}";
    $.ajax({
        type:"post",
        data:json,
        url:"/PhotoShow/adminUser_userPage.action",
        contentType : "application/json",
        success:function (data) {
            jsonObj=eval("("+data+")");
            $(".userTbody").children().remove();
            if(jsonObj.state="success"){
                $(".CurrentPage").text(jsonObj.currentPage);
                jsonObj=jsonObj.data;
                for(var i=0;i<jsonObj.length;i++){
                    createTable(jsonObj[i]);
                }
            }
        },
        error: function (data) {
            console.log(data);
        }
    });
}

function deleteUser(id) {
    var json="{'userForm':{'id':'"+id+"'}}";
    console.log(json);
    var jsonObj;
    $.ajax({
        type:"post",
        data:json,
        url:"/PhotoShow/adminUser_toDeleteUser.action",
        contentType : "application/json",
        success:function (data) {
            jsonObj=eval("("+data+")");
            console.log(jsonObj);
            if(jsonObj.state=="success"){
                swal("", "删除成功！", "success");
            }
            if(jsonObj.state=="error"){
                swal("", "删除失败！", "error");
            }
        },
        error:function (data) {
            alert("is system error");
        }
    });
}

function jsonStr(objectName, key, value) {
    var result = "{'" + objectName + "':{'";
    for ( var i in key) {
        if (i < key.length - 1)
            result += key[i] + "':'" + value[i] + "','";
        else
            result += key[i] + "':'" + value[i] + "'}}";
    }
    return result;
}

function createTable(jsonObj){
    var htmlStr='<tr>'
                    +'<td><a href="#">'+jsonObj.id+'</a></td>'
                    +'<td><img  src="../imgs/'+jsonObj.id+'/'+jsonObj.userHeadImg+'" alt="" class="" width="50px" height="50px"/></td>'
                    +'<td>'+jsonObj.userId+'</td>'
                    +'<td>'+jsonObj.userName+'</td>'
                    +'<td>'+jsonObj.userPhNum+'</td>'
                    +'<td>'+jsonObj.userEmail+'</td>'
                    +'<td>'+jsonObj.userAlbumNum+'</td>'
                    +'<td>'
                        +'<a href="#" class="deleteUser" title="'+jsonObj.id+'">'
                         +'<span class="label label-warning label-mini">Delete</span>'
                        +'</a>'
                    +'</td>'
                +'</tr>';
    $(".userTbody").append(htmlStr);
}


