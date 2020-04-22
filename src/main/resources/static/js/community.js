//提交回复
function post() {
    var question_id = $("#question_id").val();
    var comment_content = $("#comment_content").val();
    comment2target(question_id,1,comment_content);

}
//展开二级回复
function collapseComments(e) {
    var id = e.getAttribute("data-id");
    var comments = $("#comment-"+id);
    var collapse = e.getAttribute("data-collapse");
    if (collapse){
        e.removeAttribute("data-collapse");
    }else {
        if(comments.children().length != 1){
            e.setAttribute("data-collapse","in");
        }else {
            $.getJSON("/question/comment/"+id , function (data) {
                $.each(data.data.reverse(),function (index , comment) {

                    var mediaLeftElement = $("<div/>",{
                        "class" : "media-left"
                    }).append($("<img/>", {
                        "class" : "media-object indexAvatar",
                        "src" : comment.user.avatarUrl
                    }));

                    var mediaRightElement = $("<div/>",{
                       "class" : "media-right"
                    })
                        .append($("<h5/>", {
                            "class" : "media-middle question-replay",
                            "html" : comment.user.name
                    }))
                        .append($("<div/>", {
                            "html" : comment.content
                    }))
                        .append($("<div/>", {
                            "class" : "menu"
                    }))
                        .append($("<span/>", {
                            "class": "glyphicon glyphicon-thumbs-up commentIcon",
                            "aria-hidden": "true"
                    }))
                        .append($("<span/>", {
                            "class" : "pull-right dateFormat",
                            "html" : timeStamp2String(comment.gmtCreate)
                    }));

                    var mediaElement = $("<div/>",{
                        "class" : "media"
                    }).append(mediaLeftElement).append(mediaRightElement);
                    var commentElement = $("<div/>",{
                        "class":"col-lg-12 col-md-12 col-sm-12 col-xs-12 comments",
                    }).append(mediaElement).append("<hr>");
                    comments.prepend(commentElement);
                });
                e.setAttribute("data-collapse","in");
            });
        }
    }

}

function comment2target(targetId, type, content) {
    if(!content){
        alert("不能回复空内容");
        return;
    }

    $.ajax({
        type: "POST",
        url: "comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId":targetId,
            "content":content,
            "type":type
        }),
        success: function (response) {
            if(response.code == 200){
                window.location.reload();
            }else {
                if (response.code == 2003){
                    var isAccepted = confirm(response.message);
                    if (isAccepted == true){
                        window.open("https://github.com/login/oauth/authorize?client_id=a745ccb34a925b0b435b&redirect_uri=http://localhost:8888/callback&scope=user&state=1");
                        window.localStorage.setItem("closable", true);
                    }
                }else {
                    alert(response.message);
                }
            }
        },
        dataType: "json"
    });

}

function comment(e) {
    var commentId = e.getAttribute("data-id");
    var comment_content = $("#input-"+ commentId).val();
    comment2target(commentId, 2, comment_content);
}


function timeStamp2String(time){
    var datetime = new Date();
    datetime.setTime(time);
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    // var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
    // var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
    // var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
    return year + "年" + month + "月" + date + "日"
        // +" "+hour+":"+minute+":"+second
        ;
}

function selectTag(e) {
    var previous = $("#tag").val();
    var value = e.getAttribute("data-tag");
    if (previous.indexOf(value) == -1){
        if(previous){
            $("#tag").val(previous + "/" + value);
        }else {
            $("#tag").val(value);
        }
    }
}

function showSelectTags() {
    $("#select-tag").show();
}