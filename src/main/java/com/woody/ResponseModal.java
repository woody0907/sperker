package com.woody;

/**
 * Created with IntelliJ IDEA.
 * User: woody
 * Date: 18-1-17
 * Time: 上午9:40
 * To change this template use File | Settings | File Templates.
 */
public class ResponseModal {
    private int responsecode;
    private String result;
    private String body;
    private boolean isOk;

    public ResponseModal(int responsecode,boolean isOk,String result,String body){
        this.setResponsecode(responsecode);
        this.setOk(isOk);
        this.setResult(result);
        this.setBody(body);
    }


    public int getResponsecode() {
        return responsecode;
    }

    public void setResponsecode(int responsecode) {
        this.responsecode = responsecode;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }
}
