package eg.intercom.hrss.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstPerReq {

    @SerializedName("empCode")
    @Expose
    private Integer empCode;
    @SerializedName("empArName")
    @Expose
    private String empArName;
    @SerializedName("empEnName")
    @Expose
    private String empEnName;
    @SerializedName("perArName")
    @Expose
    private String perArName;
    @SerializedName("perEnName")
    @Expose
    private String perEnName;
    @SerializedName("refNo")
    @Expose
    private String refNo;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("endDate")
    @Expose
    private String endDate;
    @SerializedName("status")
    @Expose
    private String status;
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