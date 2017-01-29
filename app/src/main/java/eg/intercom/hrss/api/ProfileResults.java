package eg.intercom.hrss.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileResults {

    @SerializedName("result")
    @Expose
    private Integer result;
    @SerializedName("empCode")
    @Expose
    private Integer empCode;
    @SerializedName("empEnName")
    @Expose
    private String empEnName;
    @SerializedName("empEnShortName")
    @Expose
    private String empEnShortName;
    @SerializedName("empArName")
    @Expose
    private String empArName;
    @SerializedName("empArShortName")
    @Expose
    private String empArShortName;
    @SerializedName("birthDate")
    @Expose
    private String birthDate;
    @SerializedName("jobTitle")
    @Expose
    private String jobTitle;
    @SerializedName("employmentDate")
    @Expose
    private String employmentDate;
    @SerializedName("mngrEnName")
    @Expose
    private String mngrEnName;
    @SerializedName("mngrArName")
    @Expose
    private String mngrArName;
    @SerializedName("deptEnName")
    @Expose
    private String deptEnName;
    @SerializedName("deptArName")
    @Expose
    private String deptArName;
    @SerializedName("ext")
    @Expose
    private String ext;
    @SerializedName("mobile1")
    @Expose
    private String mobile1;
    @SerializedName("mobile2")
    @Expose
    private String mobile2;
    @SerializedName("workMail")
    @Expose
    private String workMail;
    @SerializedName("personalMail")
    @Expose
    private String personalMail;
    @SerializedName("socialStatus")
    @Expose
    private String socialStatus;
    @SerializedName("homeAddress")
    @Expose
    private String homeAddress;
    @SerializedName("country")
    @Expose
    private Integer country;
    @SerializedName("city")
    @Expose
    private Integer city;
    @SerializedName("postalCode")
    @Expose
    private String postalCode;
    @SerializedName("homePhone")
    @Expose
    private String homePhone;
    @SerializedName("photo")
    @Expose
    private String photo;

    /**
     *
     * @return
     * The result
     */
    public Integer getResult() {
        return result;
    }

    /**
     *
     * @param result
     * The result
     */
    public void setResult(Integer result) {
        this.result = result;
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
     * The empEnShortName
     */
    public String getEmpEnShortName() {
        return empEnShortName;
    }

    /**
     *
     * @param empEnShortName
     * The empEnShortName
     */
    public void setEmpEnShortName(String empEnShortName) {
        this.empEnShortName = empEnShortName;
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
     * The empArShortName
     */
    public String getEmpArShortName() {
        return empArShortName;
    }

    /**
     *
     * @param empArShortName
     * The empArShortName
     */
    public void setEmpArShortName(String empArShortName) {
        this.empArShortName = empArShortName;
    }

    /**
     *
     * @return
     * The birthDate
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     *
     * @param birthDate
     * The birthDate
     */
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    /**
     *
     * @return
     * The jobTitle
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     *
     * @param jobTitle
     * The jobTitle
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    /**
     *
     * @return
     * The employmentDate
     */
    public String getEmploymentDate() {
        return employmentDate;
    }

    /**
     *
     * @param employmentDate
     * The employmentDate
     */
    public void setEmploymentDate(String employmentDate) {
        this.employmentDate = employmentDate;
    }

    /**
     *
     * @return
     * The mngrEnName
     */
    public String getMngrEnName() {
        return mngrEnName;
    }

    /**
     *
     * @param mngrEnName
     * The mngrEnName
     */
    public void setMngrEnName(String mngrEnName) {
        this.mngrEnName = mngrEnName;
    }

    /**
     *
     * @return
     * The mngrArName
     */
    public String getMngrArName() {
        return mngrArName;
    }

    /**
     *
     * @param mngrArName
     * The mngrArName
     */
    public void setMngrArName(String mngrArName) {
        this.mngrArName = mngrArName;
    }

    /**
     *
     * @return
     * The deptEnName
     */
    public String getDeptEnName() {
        return deptEnName;
    }

    /**
     *
     * @param deptEnName
     * The deptEnName
     */
    public void setDeptEnName(String deptEnName) {
        this.deptEnName = deptEnName;
    }

    /**
     *
     * @return
     * The deptArName
     */
    public String getDeptArName() {
        return deptArName;
    }

    /**
     *
     * @param deptArName
     * The deptArName
     */
    public void setDeptArName(String deptArName) {
        this.deptArName = deptArName;
    }

    /**
     *
     * @return
     * The ext
     */
    public String getExt() {
        return ext;
    }

    /**
     *
     * @param ext
     * The ext
     */
    public void setExt(String ext) {
        this.ext = ext;
    }

    /**
     *
     * @return
     * The mobile1
     */
    public String getMobile1() {
        return mobile1;
    }

    /**
     *
     * @param mobile1
     * The mobile1
     */
    public void setMobile1(String mobile1) {
        this.mobile1 = mobile1;
    }

    /**
     *
     * @return
     * The mobile2
     */
    public String getMobile2() {
        return mobile2;
    }

    /**
     *
     * @param mobile2
     * The mobile2
     */
    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }

    /**
     *
     * @return
     * The workMail
     */
    public String getWorkMail() {
        return workMail;
    }

    /**
     *
     * @param workMail
     * The workMail
     */
    public void setWorkMail(String workMail) {
        this.workMail = workMail;
    }

    /**
     *
     * @return
     * The personalMail
     */
    public String getPersonalMail() {
        return personalMail;
    }

    /**
     *
     * @param personalMail
     * The personalMail
     */
    public void setPersonalMail(String personalMail) {
        this.personalMail = personalMail;
    }

    /**
     *
     * @return
     * The socialStatus
     */
    public String getSocialStatus() {
        return socialStatus;
    }

    /**
     *
     * @param socialStatus
     * The socialStatus
     */
    public void setSocialStatus(String socialStatus) {
        this.socialStatus = socialStatus;
    }

    /**
     *
     * @return
     * The homeAddress
     */
    public String getHomeAddress() {
        return homeAddress;
    }

    /**
     *
     * @param homeAddress
     * The homeAddress
     */
    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    /**
     *
     * @return
     * The country
     */
    public Integer getCountry() {
        return country;
    }

    /**
     *
     * @param country
     * The country
     */
    public void setCountry(Integer country) {
        this.country = country;
    }

    /**
     *
     * @return
     * The city
     */
    public Integer getCity() {
        return city;
    }

    /**
     *
     * @param city
     * The city
     */
    public void setCity(Integer city) {
        this.city = city;
    }

    /**
     *
     * @return
     * The postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     *
     * @param postalCode
     * The postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     *
     * @return
     * The homePhone
     */
    public String getHomePhone() {
        return homePhone;
    }

    /**
     *
     * @param homePhone
     * The homePhone
     */
    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    /**
     *
     * @return
     * The photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     *
     * @param photo
     * The photo
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

}