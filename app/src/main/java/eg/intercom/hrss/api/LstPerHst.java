package eg.intercom.hrss.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LstPerHst {

    @SerializedName("perArName")
    @Expose
    private String perArName;
    @SerializedName("perEnName")
    @Expose
    private String perEnName;
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
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("rejectReason")
    @Expose
    private String rejectReason;

    /**
     *
     * @return
     * The perArName
     */
    public String getPerArName() {
        return perArName;
    }

    /**
     *
     * @param perArName
     * The perArName
     */
    public void setPerArName(String perArName) {
        this.perArName = perArName;
    }

    /**
     *
     * @return
     * The perEnName
     */
    public String getPerEnName() {
        return perEnName;
    }

    /**
     *
     * @param perEnName
     * The perEnName
     */
    public void setPerEnName(String perEnName) {
        this.perEnName = perEnName;
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

}