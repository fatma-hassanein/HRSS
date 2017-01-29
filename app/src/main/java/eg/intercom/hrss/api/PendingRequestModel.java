package eg.intercom.hrss.api;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Emad.Essam on 12/1/2016.
 */

public class PendingRequestModel implements Parcelable {

    private String empCode;
    private String empArName;
    private String empEnName;
    private String perArName;
    private String perEnName;
    private String refNo;
    private String startDate;
    private String endDate;
    private String status;
    private String remarks;
    private String noOfDays;
    private String misType;
    private String vacCode;
    private String leaveType;
    private String vacArName;
    private String vacEnName;
    private String category;
    private String hasNoticePeriod;
    private String balance;
    private String submitter;
    private String RequestType;

    public String getRequestType() {
        return RequestType;
    }

    public void setRequestType(String requestType) {
        RequestType = requestType;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpArName() {
        return empArName;
    }

    public void setEmpArName(String empArName) {
        this.empArName = empArName;
    }

    public String getEmpEnName() {
        return empEnName;
    }

    public void setEmpEnName(String empEnName) {
        this.empEnName = empEnName;
    }

    public String getPerArName() {
        return perArName;
    }

    public void setPerArName(String perArName) {
        this.perArName = perArName;
    }

    public String getPerEnName() {
        return perEnName;
    }

    public void setPerEnName(String perEnName) {
        this.perEnName = perEnName;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(String noOfDays) {
        this.noOfDays = noOfDays;
    }

    public String getMisType() {
        return misType;
    }

    public void setMisType(String misType) {
        this.misType = misType;
    }

    public PendingRequestModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.empCode);
        dest.writeString(this.empArName);
        dest.writeString(this.empEnName);
        dest.writeString(this.perArName);
        dest.writeString(this.perEnName);
        dest.writeString(this.refNo);
        dest.writeString(this.startDate);
        dest.writeString(this.endDate);
        dest.writeString(this.status);
        dest.writeString(this.remarks);
        dest.writeString(this.noOfDays);
        dest.writeString(this.misType);
        dest.writeString(this.vacCode);
        dest.writeString(this.leaveType);
        dest.writeString(this.vacArName);
        dest.writeString(this.vacEnName);
        dest.writeString(this.category);
        dest.writeString(this.hasNoticePeriod);
        dest.writeString(this.balance);
        dest.writeString(this.submitter);
        dest.writeString(this.RequestType);
    }

    protected PendingRequestModel(Parcel in) {
        this.empCode = in.readString();
        this.empArName = in.readString();
        this.empEnName = in.readString();
        this.perArName = in.readString();
        this.perEnName = in.readString();
        this.refNo = in.readString();
        this.startDate = in.readString();
        this.endDate = in.readString();
        this.status = in.readString();
        this.remarks = in.readString();
        this.noOfDays = in.readString();
        this.misType = in.readString();
        this.vacCode = in.readString();
        this.leaveType = in.readString();
        this.vacArName = in.readString();
        this.vacEnName = in.readString();
        this.category = in.readString();
        this.hasNoticePeriod = in.readString();
        this.balance = in.readString();
        this.submitter = in.readString();
        this.RequestType = in.readString();
    }

    public static final Creator<PendingRequestModel> CREATOR = new Creator<PendingRequestModel>() {
        @Override
        public PendingRequestModel createFromParcel(Parcel source) {
            return new PendingRequestModel(source);
        }

        @Override
        public PendingRequestModel[] newArray(int size) {
            return new PendingRequestModel[size];
        }
    };
}
