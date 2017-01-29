package eg.intercom.hrss.api;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PermissionHistoryResults {

    @SerializedName("result")
    @Expose
    private Integer result;
    @SerializedName("lstPerHst")
    @Expose
    private List<LstPerHst> lstPerHst = new ArrayList<LstPerHst>();

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
    public List<LstPerHst> getLstPerHst() {
        return lstPerHst;
    }

    /**
     *
     * @param lstPerHst
     * The lstPerHst
     */
    public void setLstPerHst(List<LstPerHst> lstPerHst) {
        this.lstPerHst = lstPerHst;
    }

}