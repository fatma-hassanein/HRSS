package eg.intercom.hrss.api;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MissionHistoryResults {

    @SerializedName("result")
    @Expose
    private Integer result;
    @SerializedName("lstPerHst")
    @Expose
    private List<LstMisHst> lstMisHst = new ArrayList<LstMisHst>();

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
     * The lstPerHst
     */
    public List<LstMisHst> getLstPerHst() {
        return lstMisHst;
    }

    /**
     *
     * @param lstMisHst
     * The lstPerHst
     */
    public void setLstPerHst(List<LstMisHst> lstMisHst) {
        this.lstMisHst = lstMisHst;
    }

}