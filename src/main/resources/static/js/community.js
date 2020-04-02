function post() {
    var question_id = $("#question_id").val();
    var comment_content = $("#comment_content").val();
    $.ajax({
        type: "POST",
        url: "comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId":question_id,
            "content":comment_content,
            "type":1
        }),
        success: function (response) {
            console.log(response)
        },
        dataType: "json"
    });

}