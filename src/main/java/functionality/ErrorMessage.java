package functionality;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class ErrorMessage {
    private String errorMessage;
    private int errorCode;
    private String errorDocUrl;

    public ErrorMessage(){};

    public ErrorMessage(String errorMessage, int errorCode, String errorDocUrl) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.errorDocUrl = errorDocUrl;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDocUrl() {
        return errorDocUrl;
    }

    public void setErrorDocUrl(String errorDocUrl) {
        this.errorDocUrl = errorDocUrl;
    }
}
