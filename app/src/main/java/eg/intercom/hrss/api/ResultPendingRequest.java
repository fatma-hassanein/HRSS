package eg.intercom.hrss.api;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultPendingRequest {

    @SerializedName("result")
    @Expose
    private Integer result;
    @SerializedName("lstVacReq")
    @Expose
    private List<PendingRequestModel> lstVacReq = new ArrayList<PendingRequestModel>();
    @SerializedName("lstPerReq")
    @Expose
    private List<PendingRequestModel> lstPerReq = new ArrayList<PendingRequestModel>();
    @SerializedName("lstCompReq")
    @Expose
    private List<PendingRequestModel> lstCompReq = new ArrayList<PendingRequestModel>();
    @SerializedName("lstMisReq")
    @Expose
    private List<PendingRequestModel> lstMisReq = new ArrayList<PendingRequestModel>();

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
     * The lstVacReq
     */
    public List<PendingRequestModel> getLstVacReq() {
        return lstVacReq;
    }

    /**
     *
     * @param lstVacReq
     * The lstVacReq
     */
    public void setLstVacReq(List<PendingRequestModel> lstVacReq) {
        this.lstVacReq = lstVacReq;
    }

    /**
     *
     * @return
     * The lstPerReq
     */
    public List<PendingRequestModel> getLstPerReq() {
        return lstPerReq;
    }

    /**
     *
     * @param lstPerReq
     * The lstPerReq
     */
    public void setLstPerReq(List<PendingRequestModel> lstPerReq) {
        this.lstPerReq = lstPerReq;
    }

    /**
     *
     * @return
     * The lstCompReq
     */
    public List<PendingRequestModel> getLstCompReq() {
        return lstCompReq;
    }

    /**
     *
     * @param lstCompReq
     * The lstCompReq
     */
    public void setLstCompReq(List<PendingRequestModel> lstCompReq) {
        this.lstCompReq = lstCompReq;
    }

    /**
     *
     * @return
     * The lstMisReq
     */
    public List<PendingRequestModel> getLstMisReq() {
        return lstMisReq;
    }

    /**
     *
     * @param lstMisReq
     * The lstMisReq
     */
    public void setLstMisReq(List<PendingRequestModel> lstMisReq) {
        this.lstMisReq = lstMisReq;
    }

}
