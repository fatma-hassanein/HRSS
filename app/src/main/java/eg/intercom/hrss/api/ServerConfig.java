package eg.intercom.hrss.api;

public class ServerConfig {
//28---rest
    //33 //rest2
    public static String SERVER_URl3 = "https://amemad.intercom.com.eg:8080/ss/rest/";
    public static String SERVER_URl = "http://10.8.0.33:8080/ss/rest/";

    public static final String LOGIN_PATH = "user/login";
    public static final String VAC_BALANCE = "vac/balance";
    public static final String PERMISSION_REQUEST = "prm/req";
    public static final String COMPENSATION_REQUEST = "comp/req";
    public static final String VACATION_REQUEST = "vac/req";
    public static final String PROFILE_DETAIL = "user/profile";
    public static final String PENDING_REQUEST = "combined/requests";
    public static final String PERMISSION_HISTORY = "prm/prsn/history";
    public static final String MISSION_HISTORY = "prm/wrk/history";
    public static final String COMPENSATION_HISTORY = "comp/history";
    public static final String VACATION_HISTORY = "vac/history";
    public static final String VACATION = "vac/";
    public static final String PERMISSION = "prm/";
    public static final String MISSION = "prm/";
    public static final String COMPENSATION = "comp/";
    public static final String ACCEPT = "/accept";
    public static final String REJECT = "/cancel";
    public static final String CANCEL = "/cancel";

    public static final String METHOD_GET = "get";

    public static final String METHOD_POST = "post";

    public static final int AcceptID = 1;
    public static final int RejectID = 2;
    public static final int CancelID = 3;
    public static final int VAcceptID = 4;
   // public static final int VCancelID = 6;

    private ServerConfig() {

    }
}
