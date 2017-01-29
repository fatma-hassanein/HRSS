package eg.intercom.hrss.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstCompHst {

    @SerializedName("empCode")
    @Expose
    private Integer empCode;
    @SerializedName("refNo")
    @Expose
    private String refNo;
    @SerializedName("empArName")
    @Expose
    private String empArName;
    @SerializedName("empEnName")
    @Expose
    private String empEnName;
    @SerializedName("noOfDays")
    @Expose
    private Integer noOfDays;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("rejectReason")
    @Expose
    private String rejectReason;
    @SerializedName("remarks")
    @Expose
    private String remarks;

    /**
     *
     * @return
     * The empCode
     */
    public Integer getEmpCode() {
        return empCode;
    }

    /**
     *
     * @param empCode
     * The empCode
     */
    public void setEmpCode(Integer empCode) {
        this.empCode = empCode;
    }

    /**
     *
     * @return
     * The refNo
     */
    public String getRefNo() {
        return refNo;
    }

    /**
     *
     * @param refNo
     * The refNo
     */
    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    /**
     *
     * @return
     * The empArName
     */
    public String getEmpArName() {
        return empArName;
    }

    /**
     *
     * @param empArName
     * The empArName
     */
    public void setEmpArName(String empArName) {
        this.empArName = empArName;
    }

    /**
     *
     * @return
     * The empEnName
     */
    public String getEmpEnName() {
        return empEnName;
    }

    /**
     *
     * @param empEnName
     * The empEnName
     */
    public void setEmpEnName(String empEnName) {
        this.empEnName = empEnName;
    }

    /**
     *
     * @return
     * The noOfDays
     */
    public Integer getNoOfDays() {
        return noOfDays;
    }

    /**
     *
     * @param noOfDays
     * The noOfDays
     */
    public void setNoOfDays(Integer noOfDays) {
        this.noOfDays = noOfDays;
    }

    /**
     *
     * @return
     * The startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     *
     * @param startDate
     * The startDate
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The rejectReason
     */
    public String getRejectReason() {
        return rejectReason;
    }

    /**
     *
     * @param rejectReason
     * The rejectReason
     */
    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    /**
     *
     * @return
     * The remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     *
     * @param remarks
     * The remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}