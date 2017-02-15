package eg.intercom.hrss.helpers;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.security.KeyPairGeneratorSpec;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;



import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.KeySpec;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.x500.X500Principal;




public class Utility {

    public static final Locale Arabic_Locale = new Locale(Constants.LANG_AR);
    public static final Locale English_Locale = new Locale(Constants.LANG_EN);
    static int iterationCount = 1000;
    static int keyLength = 256;
    static int saltLength = keyLength / 8; // same size as key output
    static SecretKey key;
    static String DELIMITER = "]";
    private static HashMap<String, String> mRetrofitHeader;

    // Amount EditText Helpers
    public  static String removeCommas(String formattedString){

        String unFormattedString = formattedString.replaceAll(",", "");

        return unFormattedString;
    }
    public static boolean isNotNull(String txt){
        return txt!=null && txt.trim().length()>0 ? true: false;
    }
    public  static String removeZeroDigits(String formattedString){

        String str;
        if(formattedString.endsWith(".00")){
            str=formattedString.replace(".00","");
            return str;
        }
        if(formattedString.endsWith(".0")){
            str=formattedString.replace(".0","");
            return str;
        }

        return formattedString;
    }

    public static String englishNumbersFormat(double d) {
        NumberFormat nf = NumberFormat.getInstance(Locale.ENGLISH);
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        String st= nf.format(d);
        return st;
    }

    public static boolean hasX0Fraction(String str){

        if(str.endsWith(".00")||str.endsWith(".10")||str.endsWith(".20")||str.endsWith(".30")||str.endsWith(".40")||str.endsWith(".50")||str.endsWith(".60")||str.endsWith(".70")||str.endsWith(".80")||str.endsWith(".90")){
            return true;
        }
        return false;
    }
//-----------------------------------
    // static final String keyStoreFile = "output/javacirecep.keystore";
//    public static String getAmountFormatted(String amount) {
//        DecimalFormat dFormat = null;
//
//        double value = Double.parseDouble(amount.replace(",", ""));
//        if (value < 1) {
//            dFormat = new DecimalFormat("0.00");
//        } else {
//            dFormat = new DecimalFormat("####,###,###.00");
//
//        }
//        return dFormat.format(value);
//    }
//
//    public static String getEnglishAmountFormatted(String amount) {
//        DecimalFormat dFormat = null;
//
//        double value = Double.parseDouble(amount.replace(",", ""));
//        if (value < 1) {
//            dFormat = new DecimalFormat("0.00");
//        } else {
//            dFormat = new DecimalFormat("####,###,###.00");
//
//        }
//        String result = String.format(Locale.ENGLISH, "%.4f", dFormat);
//        return dFormat.format(result);
//    }

    private static ProgressDialog mProgressDialog;
//
//    public static String convert(byte[] data) {
//        StringBuilder sb = new StringBuilder(data.length);
//        for (int i = 0; i < data.length; ++i) {
//            if (data[i] < 0) throw new IllegalArgumentException();
//            sb.append((char) data[i]);
//        }
//        return sb.toString();
//    }

    public static boolean isProgressDialogShowing() {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            return true;
        else
            return false;
    }



    public static void removeProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();

        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*//create new client object with static headers
    public static AsyncHttpClient getDefaultAuthClient() {
        final AsyncHttpClient client = new AsyncHttpClient();
        CustomSharedPrefrences CustomPrefrence;
        SharedPreferences prefs;
        CustomPrefrence = CustomSharedPrefrences.getInstance(MyApplication.getAppContext());
        prefs = MyApplication.getAppContext().getSharedPreferences("UBPref",
                Context.MODE_PRIVATE);
        client.addHeader("ch", CustomPrefrence.getData(Constants.ChANNEL));
        //client.addHeader("ch", prefs.getString(Constants.ChANNEL, ""));
        client.addHeader("Authorization", CustomPrefrence.getData(Constants.AUTH));
        client.addHeader("User-Agent", Utility.getUserAgent());

        if (Utility.getDeviceLanguage(MyApplication.getAppContext(), CustomPrefrence).equalsIgnoreCase(Constants.LANG_EN)) {

            client.addHeader("lang", Constants.LANG_EN);
        } else {

            client.addHeader("lang", Constants.LANG_AR);
        }
        //   client.addHeader("lang", prefs.getString(Constants.LANG, ""));
        client.addHeader("token", CustomPrefrence.getData(Constants.USER_TOKEN));

        //client.addHeader(Constants.KEY_IV_USER,Constants.KEY_IV_USER);
        client.setConnectTimeout(90 * 1000);
        client.setTimeout(90 * 1000);


        return client;
    }*/


//    public static void resetDeafultdate(ImageView reset_icon, TextView BTN_FROM, String date) {
//        reset_icon.setVisibility(View.GONE);
//        BTN_FROM.setText(ConvertToEnglish(date));
//    }

//    public static Date ConvertToDate(String dateString) {
//        Date date = null;
//        // String dateString = "03/26/2012 11:49:00 AM";
//
//        //29/09/2008
//        //String dtStart = "2010-10-15T09:27:37Z";
//        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
//        try {
//            date = format.parse(dateString);
//            System.out.println(date);
//
//        } catch (ParseException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return date;
//    }
//

    /****
     * Method for Setting the Height of the ListView dynamically.
     * *** Hack to fix the issue of not showing all the items of the ListView
     * *** when placed inside a ScrollView
     ****/
    //http://stackoverflow.com/a/19311197/1312796
//    public static void setListViewHeightBasedOnChildren(ListView listView) {
//        ListAdapter listAdapter = listView.getAdapter();
//        if (listAdapter == null)
//            return;
//
//        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
//        int totalHeight = 0;
//        View view = null;
//        for (int i = 0; i < listAdapter.getCount(); i++) {
//            view = listAdapter.getView(i, view, listView);
//            if (i == 0)
//                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, AbsoluteLayout.LayoutParams.WRAP_CONTENT));
//
//            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
//            totalHeight += view.getMeasuredHeight();
//        }
//        ViewGroup.LayoutParams params = listView.getLayoutParams();
//        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
//        listView.setLayoutParams(params);
//    }

//    public static void hideKeyboard(EditText myEditText, Context context) {
//        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(myEditText.getWindowToken(), 0);
//    }
//
//    public static void hideKeyboard(NumberPicker myEditText, Context context) {
//        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(myEditText.getWindowToken(), 0);
//    }
//
//    public static boolean isEmailValid(String evalid) {
//        String emailReg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//        if (evalid.matches(emailReg))
//
//            return true;
//        else
//            return false;
//    }
    public static Typeface applyCustomFonts(String fontName, Context context){
//Roboto-Black.ttf
        Typeface typeface = Typeface.createFromAsset(context.getResources().getAssets(), fontName);
        return typeface;
    }

//    public static String getDateFormatFromUnixTimeStamp(String time_unix_format) {
//        long unixSeconds = Long.parseLong(time_unix_format);
//        Date date = new Date(unixSeconds * 1000L); // *1000 is to convert seconds to milliseconds
//        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMMM dd, yyyy", Locale.ENGLISH); // the format of your date
//
//        DateFormat df = new SimpleDateFormat("hh:mm:ss a", Locale.ENGLISH);
//        String hour = df.format(date);
//
//
//        String formattedDate = sdf.format(date);
//
//        //Log.v("Date Format", formattedDate + " at " + hour);
//
//        return (formattedDate + " at " + hour);
//    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

//
//    public static void setupUI(View view, final Context mcontext) {
//
//        //Set up touch listener for non-text box views to hide keyboard.
//        if (!(view instanceof EditText)) {
//
//            view.setOnTouchListener(new View.OnTouchListener() {
//
//                public boolean onTouch(View v, MotionEvent event) {
//                    hideSoftKeyboard((Activity) mcontext);
//                    return false;
//                }
//
//            });
//        }
//    }

    /*

    public static String getDeviceLanguage(Context mcontext, SharedPreferences prefs) {
//law mal2ash fe el shared prefrecnce take defult device
        String Lang;
        Resources res = mcontext.getResources();

        String dev_Lang = Locale.getDefault().getLanguage();



        prefs = mcontext.getSharedPreferences("UBPref",
                Context.MODE_PRIVATE);
        Lang = prefs.getString(Constants.LOCALE, dev_Lang);


        return Lang;
    }
*/

    //Get distance between my location and other Location : http://stackoverflow.com/a/8383987/1312796
    //http://stackoverflow.com/questions/8383863/how-can-find-nearest-place-from-current-location-from-given-data


    //Button effect
    //http://stackoverflow.com/a/14814595/1312796

//    public static void onViewClickEffect(View view) {
//        view.setOnTouchListener(new View.OnTouchListener() {
//
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN: {
//                        v.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
//                        v.invalidate();
//                        break;
//                    }
//                    case MotionEvent.ACTION_UP: {
//                        v.getBackground().clearColorFilter();
//                        v.invalidate();
//                        break;
//                    }
//                }
//                return false;
//            }
//        });
//    }
//
//    public static void onViewClickEffectLIGHTEN(View view) {
//        view.setOnTouchListener(new View.OnTouchListener() {
//
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN: {
//                        v.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_OVER);
//                        v.invalidate();
//                        break;
//                    }
//                    case MotionEvent.ACTION_UP: {
//                        v.getBackground().clearColorFilter();
//                        v.invalidate();
//                        break;
//                    }
//                }
//                return false;
//            }
//        });
//    }

//    public static Drawable convertBase64ToDrawable(String base64String, Context context) {
//
//        byte[] decodedString = Base64.decode(base64String.toString(), Base64.DEFAULT);
//        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//        Drawable d = new BitmapDrawable(context.getResources(), decodedByte);
//
//        return d;
//    }
//
//    public static String ConvertToEnglish(String fulltext) {
//        fulltext = fulltext.replaceAll("١", "1").replaceAll("٢", "2").replaceAll("٣", "3").replaceAll("٤", "4").replaceAll("٥", "5").replaceAll("٦", "6").replaceAll("٧", "7").replaceAll("٨", "8").replaceAll("٩", "9").replaceAll("٠", "0").replaceAll("٬", ",").replaceAll("٫", ".");
//
//        return fulltext;
//    }
//    public static String convertToArabic(String fulltext) {
//        fulltext = fulltext.replaceAll("1","١" ).replaceAll("2","٢" ).replaceAll("3", "٣").replaceAll("4", "٤").replaceAll("5", "٥").replaceAll("6", "٦").replaceAll("7","٧" ).replaceAll("8","٨" ).replaceAll("9","٩" ).replaceAll("0","٠" );
//
//        return fulltext;
//    }






//    public static void dismissBottomButtonsWhilePopKeypad(final View activityView, final View bottom_relative) {
//        //http://stackoverflow.com/a/30461111/1312796
//        activityView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                Rect r = new Rect();
//                activityView.getWindowVisibleDisplayFrame(r);
//                int heightDiff = activityView.getRootView().getHeight() - (r.bottom - r.top);
//
//                if (heightDiff > 100) { // if more than 100 pixels, its probably a keyboard...
//                    //ok now we know the keyboard is up...
//                    bottom_relative.setVisibility(View.GONE);
//
//                } else {
//                    //ok now we know the keyboard is down...
//                    bottom_relative.setVisibility(View.VISIBLE);
//                }
//            }
//        });
//    }

    /// get user agent

//    public static String getUserAgent() {
//        String agent_name = "android";
//
//        return agent_name;
//    }




//    public static String toBase64(byte[] bytes) {
//        return Base64.encodeToString(bytes, Base64.NO_WRAP);
//    }
//
//
//    public static String toHex(byte[] bytes) {
//        StringBuffer buff = new StringBuffer();
//        for (byte b : bytes) {
//            buff.append(String.format("%02X", b));
//        }
//
//        return buff.toString();
//    }
//
//    public static byte[] fromBase64(String base64) {
//        return Base64.decode(base64, Base64.NO_WRAP);
//    }
//
//


//
//    public static Boolean ConvertStringToBoolean(String value) {
//        Boolean boolean1 = Boolean.valueOf(value);
//
//        return boolean1;
//    }
//
//    public static void forceKeyboardTobeHidden(View view) {
//        // Check if no view has focus:c
//        if (view != null) {
//            InputMethodManager imm = (InputMethodManager) MyApplication.getAppContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//        }
//    }


    public static void showProgressDialog(Context context,
                                          String msg) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(context, "", msg);
                mProgressDialog.setCancelable(false);
            }

            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();


            }

        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void generateRetrofitHttpHeader(Context mContext ) {
        // Utility.showProgressDialog(mActivity, getResources().getString(R.string.Loading), null);

        mRetrofitHeader = new HashMap<>();
        // params = new Bundle();

        mRetrofitHeader.put("token", Constants.getDataInSharedPrefrences(Constants.USER_TOKEN,mContext));

        Log.e("mRetrofitHeader Drawer",mRetrofitHeader.size() +" ");
    }
}