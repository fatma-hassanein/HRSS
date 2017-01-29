package eg.intercom.hrss.api;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstVacReq {

    @SerializedName("category")
    @Expose
    private Integer category;
    @SerializedName("vacArName")
    @Expose
    private String vacArName;
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
    private Integer noOfDays;
    @SerializedName("leaveType")
    @Expose
    private String leaveType;
    @SerializedName("empEnName")
    @Expose
    private String empEnName;
    @SerializedName("empArName")
    @Expose
    private String empArName;
    @SerializedName("empCode")
    @Expose
    private Integer empCode;
    @SerializedName("hasNoticePeriod")
    @Expose
    private Boolean hasNoticePeriod;
    @SerializedName("balance")
    @Expose
    private Double balance;
    @SerializedName("submitter")
    @Expose
    private String submitter;

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
     * The vacArName
     */
    public String getVacArName() {
        return vacArName;
    }

    /**
     *
     * @param vacArName
     * The vacArName
     */
    public void setVacArName(String vacArName) {
        this.vacArName = vacArName;
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
     * The hasNoticePeriod
     */
    public Boolean getHasNoticePeriod() {
        return hasNoticePeriod;
    }

    /**
     *
     * @param hasNoticePeriod
     * The hasNoticePeriod
     */
    public void setHasNoticePeriod(Boolean hasNoticePeriod) {
        this.hasNoticePeriod = hasNoticePeriod;
    }

    /**
     *
     * @return
     * The balance
     */
    public Double getBalance() {
        return balance;
    }

    /**
     *
     * @param balance
     * The balance
     */
    public void setBalance(Double balance) {
        this.balance = balance;
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

}