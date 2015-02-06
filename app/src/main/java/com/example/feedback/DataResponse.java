package com.example.feedback;

/**
 * Created by trmehta on 1/30/2015.
 */
public class DataResponse {
    String pushDeviceTokenUri;
    String status = "ACTIVE";

    public void setPushDeviceTokenUri(String pushDeviceTokenUri) { this.pushDeviceTokenUri = pushDeviceTokenUri;}
    public String getPushDeviceTokenUri (){ return pushDeviceTokenUri;}

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
