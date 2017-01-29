package eg.intercom.hrss.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstMisHst {

    @SerializedName("refNo")
    @Expose
    private String refNo;
    @SerializedName("perCode")
    @Expose
    private Integer perCode;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("endDate")
    @Expose
    private String endDate;
    @SerializedName("misType")
    @Expose
    private String misType;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("rejectReason")
    @Expose
    private String rejectReason;
    @SerializedName("remarks")
    @Expose
    private String remarks;

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
     * The perCode
     */
    public Integer getPerCode() {
        return perCode;
    }

    /**
     *
     * @param perCode
     * The perCode
     */
    public void setPerCode(Integer perCode) {
        this.perCode = perCode;
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
     * The misType
     */
    public String getMisType() {
        return misType;
    }

    /**
     *
     * @param misType
     * The misType
     */
    public void setMisType(String misType) {
        this.misType = misType;
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
     * The duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     *
     * @param duration
     * The duration
     */
    public void setDuration(String duration) {
        this.duration = duration;
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