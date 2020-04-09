package life.majiang.community.exception;

public enum CustomizeErrorCode implements CustomizeErrorCodeImpl {
    QUESTION_NOT_FOUND( 2001,"你找的问题不存在，看看其他的？"),
    TARGET_PARAM_NOT_FOUND( 2002,"未选中任何问题或者评论进行回复"),
    NO_LOGIN( 2003,"当前操作需要登陆，请登陆后重试"),
    SYS_ERROR( 2004,"服务异常，再试试？"),
    TYPE_PARAM_WRONG( 2005,"评论类型错误或不存在"),
    COMMENT_NOT_FOUND( 2006,"你操作的评论不存在，看看其他的？"),
    CONTENT_IS_EMPTY( 2007,"输入内容不能为空");

    private String msg;
    private Integer code;

    CustomizeErrorCode(Integer code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
