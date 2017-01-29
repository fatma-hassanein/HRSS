package eg.intercom.hrss.api;



public interface APIListener {
	
	public void onSuccess(int id, String url, String response);
	public void onFailure(int id, String url, String response, int responseCode);

}
