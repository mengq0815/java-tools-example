package com.example.tool.dingding;

/**
 * 钉钉消息发送返回结果
 */
public class DingSendResult {

    private boolean isSuccess;

    private Integer errorCode;

    private String errorMsg;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(" [ ");
        str.append("isSuccess=").append(isSuccess).append(",");
        str.append("errorCode=").append(errorCode).append(",");
        str.append("errorMsg=").append(errorMsg);
        str.append(" ] ");
        return String.valueOf(str);
    }
}
