package eg.intercom.hrss.api;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompensationHistoryResults {

    @SerializedName("result")
    @Expose
    private Integer result;
    @SerializedName("lstCompHst")
    @Expose
    private List<LstCompHst> lstCompHst = new ArrayList<LstCompHst>();

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
     * The lstCompHst
     */
    public List<LstCompHst> getLstCompHst() {
        return lstCompHst;
    }

    /**
     *
     * @param lstCompHst
     * The lstCompHst
     */
    public void setLstCompHst(List<LstCompHst> lstCompHst) {
        this.lstCompHst = lstCompHst;
    }

}