package com.mobidream.mobred.gateway;

/**
 * Created by SunilMishra on 4/2/2015.
 */
public class RequestHolder {

    private String url;
    private String methodType;
    private Object postData;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public Object getPostData() {
        return postData;
    }

    public void setPostData(Object postData) {
        this.postData = postData;
    }
}
