
package eg.intercom.hrss.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstVacHst {

    @SerializedName("category")
    @Expose
    private Integer category;
    @SerializedName("vacName")
    @Expose
    private String vacName;
    @SerializedName("vacEnName")
    @Expose
    private String vacEnName;
    @SerializedName("refNo")
    @Expose
    private String refNo;
    @SerializedName("vacCode")
    @Expose
    private Integer vacCode;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("endDate")
    @Expose
    private String endDate;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("noOfDays")
    @Expose
    private String noOfDays;
    @SerializedName("leaveType")
    @Expose
    private String leaveType;
    @SerializedName("submitter")
    @Expose
    private String submitter;
    @SerializedName("rejectReason")
    @Expose
    private String rejectReason;

    /**
     *
     * @return
     * The category
     */
    public Integer getCategory() {
        return category;
    }

    /**
     *
     * @param category
     * The category
     */
    public void setCategory(Integer category) {
        this.category = category;
    }

    /**
     *
     * @return
     * The vacName
     */
    public String getVacName() {
        return vacName;
    }

    /**
     *
     * @param vacName
     * The vacName
     */
    public void setVacName(String vacName) {
        this.vacName = vacName;
    }

    /**
     *
     * @return
     * The vacEnName
     */
    public String getVacEnName() {
        return vacEnName;
    }

    /**
     *
     * @param vacEnName
     * The vacEnName
     */
    public void setVacEnName(String vacEnName) {
        this.vacEnName = vacEnName;
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
     * The vacCode
     */
    public Integer getVacCode() {
        return vacCode;
    }

    /**
     *
     * @param vacCode
     * The vacCode
     */
    public void setVacCode(Integer vacCode) {
        this.vacCode = vacCode;
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
     * The endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     *
     * @param endDate
     * The endDate
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
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
     * The noOfDays
     */
    public String getNoOfDays() {
        return noOfDays;
    }

    /**
     *
     * @param noOfDays
     * The noOfDays
     */
    public void setNoOfDays(String noOfDays) {
        this.noOfDays = noOfDays;
    }

    /**
     *
     * @return
     * The leaveType
     */
    public String getLeaveType() {
        return leaveType;
    }

    /**
     *
     * @param leaveType
     * The leaveType
     */
    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    /**
     *
     * @return
     * The submitter
     */
    public String getSubmitter() {
        return submitter;
    }

    /**
     *
     * @param submitter
     * The submitter
     */
    public void setSubmitter(String submitter) {
        this.submitter = submitter;
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

}