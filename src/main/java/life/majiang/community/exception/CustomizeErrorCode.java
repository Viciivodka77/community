package life.majiang.community.exception;

public enum CustomizeErrorCode implements CustomizeErrorCodeImpl {
    QUESTION_NOT_FOUND("你找的问题不存在，看看其他的？");
    private String msg;

    CustomizeErrorCode(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
