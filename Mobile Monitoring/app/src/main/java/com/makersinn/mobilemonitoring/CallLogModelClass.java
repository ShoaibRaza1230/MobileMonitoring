package com.makersinn.mobilemonitoring;

/**
 * Created by Eshal on 6/24/2019.
 */

public class CallLogModelClass {

    private String phoneNo;
    private String callType;
    private String callTDate;
    private String callDuration;

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getCallTDate() {
        return callTDate;
    }


    public void setCallTDate(String callTDate) {
        this.callTDate = callTDate;
    }

    public String getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(String callDuration) {
        this.callDuration = callDuration;
    }
}
