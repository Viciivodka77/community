//提交回复
function post() {
    var question_id = $("#question_id").val();
    var comment_content = $("#comment_content").val();
    comment2target(question_id,1,comment_content);

}
//展开二级回复
function collapseComments(e) {
    var id = e.getAttribute("data-id");
    var comments = $("comment-"+id);
    var collapse = e.getAttribute("data-collapse");
    comments.addClass("in");

    $.getJSON("/comment/"+id , function (data) {
        var commentBody = $("comment-body-"+id);
        var items = [];

        $.each(data.data,function (comment) {
            var c = $("<div/>",{
                "class":"col-lg-12 col-md-12 col-sm-12 col-xs-12 comments",
                html: comment.content
            });
            items.push(c);
        });


        $("<div/>",{
            "class":"col-lg-12 col-md-12 col-sm-12 col-xs-12 collapse comments-comments",
            "id":"comment-"+id,
            html: items.join("")
        }).appendTo(commentBody);

    });
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

