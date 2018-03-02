package site.zido.bone.mvc;

/**
 * site.zido.bone.mvc
 *
 * @author zido
 */
public class BoneHttpRequest {
    private String requestMethod;
    private String requestPath;

    public BoneHttpRequest(String requestMethod, String requestPath) {
        this.requestMethod = requestMethod;
        this.requestPath = requestPath;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }
}
