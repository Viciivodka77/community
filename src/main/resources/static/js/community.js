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
            if(response.code == 200){
                $("#comment_section").hide();
            }else {
                console.log(1);
                if (response.code == 2003){
                    var isAccepted = confirm(response.message);
                    console.log(isAccepted);
                    console.log(2);
                    if (isAccepted == true){
                        window.open("https://github.com/login/oauth/authorize?client_id=a745ccb34a925b0b435b&redirect_uri=http://localhost:8888/callback&scope=user&state=1");
                        window.localStorage.setItem("closable", true);
                    }
                }else {
                    alert(response.message);
                    console.log(3);
                }
            }
            console.log(response);
        },
        dataType: "json"
    });

}