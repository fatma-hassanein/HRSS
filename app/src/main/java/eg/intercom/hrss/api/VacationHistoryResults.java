package eg.intercom.hrss.api;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VacationHistoryResults {

    @SerializedName("result")
    @Expose
    private Integer result;
    @SerializedName("lstVacHst")
    @Expose
    private List<LstVacHst> lstVacHst = new ArrayList<LstVacHst>();

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
     * The lstVacHst
     */
    public List<LstVacHst> getLstVacHst() {
        return lstVacHst;
    }

    /**
     *
     * @param lstVacHst
     * The lstVacHst
     */
    public void setLstVacHst(List<LstVacHst> lstVacHst) {
        this.lstVacHst = lstVacHst;
    }

}