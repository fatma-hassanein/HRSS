package eg.intercom.hrss.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import eg.intercom.hrss.R;

import java.util.ArrayList;
import java.util.HashMap;

import static android.R.attr.id;

/**
 * Created by Emad on 9/5/2016.
 */


public class ExtensionNumbActivity extends AppCompatActivity {
    ListView LV;
    EditText et;
    ArrayAdapter<String> adapter;
    ArrayList<HashMap<String, String>> list;
    String[] extension = {
            "10	      \t\t\t\t\t\t  Ahmed Khedr",
            "101      \t\t\t\t\t\t  Sally ElKady",
            "102      \t\t\t\t\t\t  Mamdouh Abo Ghanima",
            "103      \t\t\t\t\t\t  Nader Ibrahem",
            "104      \t\t\t\t\t\t  Mahmoud Hashim",
            "105      \t\t\t\t\t\t  Raghda ElGayar",
            "106      \t\t\t\t\t\t  Mohamed AbdElmaksoud",
            "107	  \t\t\t\t\t\t\t  Ahmed Hewady",
            "108      \t\t\t\t\t\t  Ahmed Mahrous",
            "109      \t\t\t\t\t\t  Mohamed Salah",
            "110      \t\t\t\t\t\t  Meeting G03",
            "111      \t\t\t\t\t\t  Amr Soliman",
            "112      \t\t\t\t\t\t  Hassan Ahmed",
            "113      \t\t\t\t\t\t  Khairat Nabil",
            "114      \t\t\t\t\t\t  Mostafa Abdel Khalek",
            "115      \t\t\t\t\t\t  Ahmed Hany",
            "116      \t\t\t\t\t\t  Mohamed Fawaz",
            "117      \t\t\t\t\t\t  Atef Aziz",
            "118      \t\t\t\t\t\t  Assem Ramadan",
            "119      \t\t\t\t\t\t  Mohamed Mamdouh",
            "120      \t\t\t\t\t\t  Mohamed Ibrahim",
            "122      \t\t\t\t\t\t  Security (122)",
            "123      \t\t\t\t\t\t  Tranning Room I",
            "124	  \t\t\t\t\t\t\t  Hussien Abdelmoniem",
            "127	  \t\t\t\t\t\t\t  Dalia Adel",
            "128	  \t\t\t\t\t\t\t  Noha Mohamed",
            "129	  \t\t\t\t\t\t\t  Ingi Naguib",
            "130	  \t\t\t\t\t\t\t  Salah Saber",
            "132	  \t\t\t\t\t\t\t  Mohamed Emad",
            "136	  \t\t\t\t\t\t\t  Moustafa Mandour",
            "138	  \t\t\t\t\t\t\t  Hisham ElSiofy",
            "139	  \t\t\t\t\t\t\t  Yara Adel",
            "140	  \t\t\t\t\t\t\t  Mostafa Mahmoud",
            "150	  \t\t\t\t\t\t\t  Akram Abdelhamed",
            "198	  \t\t\t\t\t\t\t  FAX",
            "199	  \t\t\t\t\t\t\t  FAX II",
            "20	      \t\t\t\t\t\t  Ibrahem Abdel Salam",
            "200	  \t\t\t\t\t\t\t  Meeting G01",
            "201	  \t\t\t\t\t\t\t  Abdel Fattah Saad",
            "202	  \t\t\t\t\t\t\t  Omnia Amin",
            "204	  \t\t\t\t\t\t\t  Taha Nasr",
            "205	  \t\t\t\t\t\t\t  Ahmed Ihab",
            "206	  \t\t\t\t\t\t\t  206",
            "207	  \t\t\t\t\t\t\t  Dina Saleh",
            "208	  \t\t\t\t\t\t\t  Ahmed El Rouby",
            "209	  \t\t\t\t\t\t\t  Tarek Gaber",
            "210	  \t\t\t\t\t\t\t  Yasser Aly",
            "212	  \t\t\t\t\t\t\t  Aya El Agamy",
            "213	  \t\t\t\t\t\t\t  Tamer Abdelmjeed",
            "214	  \t\t\t\t\t\t\t  214",
            "218	  \t\t\t\t\t\t\t  218",
            "219	  \t\t\t\t\t\t\t  Mahmoud Youssef",
            "220	  \t\t\t\t\t\t\t  Amin Al Nagar",
            "221	  \t\t\t\t\t\t\t  Abdel Monaim ElMoatasem",
            "222	  \t\t\t\t\t\t\t  Ahmed Kotb",
            "223	  \t\t\t\t\t\t\t  Yahia Abo Hashim",
            "224	  \t\t\t\t\t\t\t  224",
            "225	  \t\t\t\t\t\t\t  225",
            "227	  \t\t\t\t\t\t\t  Dina Samy",
            "228      \t\t\t\t\t\t  Abdel Moneim Emad",
            "229	  \t\t\t\t\t\t\t  Karim Ahmed",
            "230	  \t\t\t\t\t\t\t  230",
            "231	  \t\t\t\t\t\t\t  231",
            "232	  \t\t\t\t\t\t\t  Azza Helmy",
            "233	  \t\t\t\t\t\t\t  233",
            "234	  \t\t\t\t\t\t\t  Mostafa Ahmed",
            "235	  \t\t\t\t\t\t\t  Ahmed Adel",
            "238	  \t\t\t\t\t\t\t  238",
            "239	  \t\t\t\t\t\t\t  239",
            "241	  \t\t\t\t\t\t\t  Emad Massoud",
            "242	  \t\t\t\t\t\t\t  Meeting 209",
            "243	  \t\t\t\t\t\t\t  Amal El Marsafawy",
            "244	  \t\t\t\t\t\t\t  Usama Kamel",
            "245	  \t\t\t\t\t\t\t  Kareem Hewady",
            "250	  \t\t\t\t\t\t\t  Ahmed Fahmy",
            "251	  \t\t\t\t\t\t\t  Ahmed Gamal",
            "252	  \t\t\t\t\t\t\t  252",
            "253	  \t\t\t\t\t\t\t  253",
            "255	  \t\t\t\t\t\t\t  Wael Ramzy",
            "30	      \t\t\t\t\t\t  Saleh",
            "301	  \t\t\t\t\t\t\t  Mona Khairy",
            "302	  \t\t\t\t\t\t\t  Hana Ghatwary",
            "303	  \t\t\t\t\t\t\t  Nada Selim",
            "304	  \t\t\t\t\t\t\t  Hossam Seyam",
            "305	  \t\t\t\t\t\t\t  Mohamed Ehab Helmy",
            "306	  \t\t\t\t\t\t\t  Hossam Abu Hashim",
            "307	  \t\t\t\t\t\t\t  Mohamed Assem",
            "308	  \t\t\t\t\t\t\t  Amr Rafaat",
            "309	  \t\t\t\t\t\t\t  Waleed Kandeel",
            "310	  \t\t\t\t\t\t\t  Ahmed Lotfy",
            "311	  \t\t\t\t\t\t\t  Ahmed Selim",
            "312	  \t\t\t\t\t\t\t  Yasser Abu Hashim",
            "313	  \t\t\t\t\t\t\t  Yossef Galal",
            "314	  \t\t\t\t\t\t\t  Omar Yasser",
            "315	  \t\t\t\t\t\t\t  Ayat Yehia",
            "316	  \t\t\t\t\t\t\t  AbdelRahman Magdy",
            "317	  \t\t\t\t\t\t\t  Emad Abd elfattha",
            "318	  \t\t\t\t\t\t\t  Abd El Khalek Helmy",
            "320	  \t\t\t\t\t\t\t  Lamiaa Helal",
            "321	  \t\t\t\t\t\t\t  Mohamed El Sayed",
            "322	  \t\t\t\t\t\t\t  322",
            "323	  \t\t\t\t\t\t\t  Marwa Abu Hashim",
            "324	  \t\t\t\t\t\t\t  Hany Sayed Emam",
            "325	  \t\t\t\t\t\t\t  325",
            "326	  \t\t\t\t\t\t\t  Mohamed Singer",
            "329	  \t\t\t\t\t\t\t  Ali Gomaa",
            "330	  \t\t\t\t\t\t\t  Mohammad ELtayeb",
            "332	  \t\t\t\t\t\t\t  332",
            "333	  \t\t\t\t\t\t\t  Hossam Abu Hashim II",
            "334	  \t\t\t\t\t\t\t  334",
            "336	  \t\t\t\t\t\t\t  Mohamed Zaki",
            "337	  \t\t\t\t\t\t\t  Emad Ahmed",
            "338	  \t\t\t\t\t\t\t  Hedaya Fawaz",
            "340	  \t\t\t\t\t\t\t  Moustafa Hussein",
            "342	  \t\t\t\t\t\t\t  Ahmed Shosha",
            "40	      \t\t\t\t\t\t  Salah Saber",
            "401	  \t\t\t\t\t\t\t  Refaat Marei",
            "402	  \t\t\t\t\t\t\t  402",
            "403	  \t\t\t\t\t\t\t  403",
            "404	  \t\t\t\t\t\t\t  Hany Kassab",
            "405	  \t\t\t\t\t\t\t  Ismail Senousi",
            "406	  \t\t\t\t\t\t\t  Rani Mohamed",
            "407	  \t\t\t\t\t\t\t  Mohamed M Adel",
            "409	  \t\t\t\t\t\t\t  Hossam Abdraboh",
            "411	  \t\t\t\t\t\t\t  Aya Hamdy",
            "412	  \t\t\t\t\t\t\t  Armia Raouf",
            "414	  \t\t\t\t\t\t\t  Yasmine Taha",
            "419	  \t\t\t\t\t\t\t  Khaled Sedky", "424	  \t\t\t\t\t\t\t  Nouran Ali", "425	  \t\t\t\t\t\t\t  Karim Badreldin", "426	  \t\t\t\t\t\t\t  426", "427	  \t\t\t\t\t\t\t  427", "428      \t\t\t\t\t\t  428", "429      \t\t\t\t\t\t  429",
            "431	  \t\t\t\t\t\t\t  Neveen ElBadrawy", "432      \t\t\t\t\t\t  Ali Badr", "435	  \t\t\t\t\t\t\t  Osama Abbas", "437      \t\t\t\t\t\t  Haitham Salah", "445      \t\t\t\t\t\t  Ayman Saeed",
            "446	  \t\t\t\t\t\t\t  Shereen Elmahdy", "447      \t\t\t\t\t\t  Sami Ayman", "448	  \t\t\t\t\t\t\t  Kareem Nader", "449	  \t\t\t\t\t\t\t  Ahmed Ateya",
            "450	  \t\t\t\t\t\t\t  Yasser Fathy Zaki Herez", "451	  \t\t\t\t\t\t\t  Sherif El Khoriby", "50	      \t\t\t\t\t\t  Samy Zakareya", "501	  \t\t\t\t\t\t\t  Marwan Sultan",
            "502	  \t\t\t\t\t\t\t  Waleed Sultan", "503	  \t\t\t\t\t\t\t  Waleed Sultan II", "505	  \t\t\t\t\t\t\t  Ahmed Salah", "507	  \t\t\t\t\t\t\t  Mohammed Said", "510	  \t\t\t\t\t\t\t  Hisham Fathi", "512	  \t\t\t\t\t\t\t  Mohamed Saleh", "514      \t\t\t\t\t\t  Hany ElKholy", "516	  \t\t\t\t\t\t\t  Meeting Room 5th Floor", "517	  \t\t\t\t\t\t\t  Fayrouz Aziz", "518	  \t\t\t\t\t\t\t  518", "519	  \t\t\t\t\t\t\t  519",
            "520	  \t\t\t\t\t\t\t  Ahmed AlGezawy", "522	  \t\t\t\t\t\t\t  522", "525	  \t\t\t\t\t\t\t  Mai Ziad", "526	  \t\t\t\t\t\t\t  Ahmed Ramdan",
            "527	  \t\t\t\t\t\t\t  Banking", "534	  \t\t\t\t\t\t\t  Amr Afifi", "535	  \t\t\t\t\t\t\t  Marwan Sultan II", "536	  \t\t\t\t\t\t\t  Omar ELBialy", "60	      \t\t\t\t\t\t  Abdo Mohamed", "603	  \t\t\t\t\t\t\t  Mostafa Aly", "604	  \t\t\t\t\t\t\t  604",
            "605	  \t\t\t\t\t\t\t  Nour Yasser", "607	  \t\t\t\t\t\t\t  Mohamed Alaa ElDen", "608	  \t\t\t\t\t\t\t  Mohamed Alaa", "609	  \t\t\t\t\t\t\t  Fatma Al-Zahraa", "610	  \t\t\t\t\t\t\t  Mohamed Essam", "611	  \t\t\t\t\t\t\t  Hussien Fawaz", "612	  \t\t\t\t\t\t\t  Abdel Monem Abdel Azeem", "613	  \t\t\t\t\t\t\t  Alaa Abdel moneim",
            "614	  \t\t\t\t\t\t\t  Mohamed Ashour", "615	  \t\t\t\t\t\t\t  Abeer Assim", "616      \t\t\t\t\t\t  Mokhtar Mahmoud", "617      \t\t\t\t\t\t  Andrew Atef",
            "618	  \t\t\t\t\t\t\t  Ismail Baseouni", "619	  \t\t\t\t\t\t\t  Ayman Mokhtar", "622	  \t\t\t\t\t\t\t  Ahmed Zedan", "623	  \t\t\t\t\t\t\t  Support", "624      \t\t\t\t\t\t  Mohamed Emam", "625	  \t\t\t\t\t\t\t  Hany Mohamed",
            "626	  \t\t\t\t\t\t\t  Ahmed Mossallam", "627	  \t\t\t\t\t\t\t  Ahmed Youssef", "628	  \t\t\t\t\t\t\t  Mahmoud Amin", "630	  \t\t\t\t\t\t\t  Meeting Room 6th Floor", "632	  \t\t\t\t\t\t\t  Mohamed Atef", "633	  \t\t\t\t\t\t\t  Omnia Fekry", "634	  \t\t\t\t\t\t\t  Moataz Nabil"
    };

    public void gethide() {

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.extention_number);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.extension_toolbar);
//        setSupportActionBar(toolbar);
        LV = (ListView) findViewById(R.id.list);
        et = (EditText) findViewById(R.id.text);

        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (id == R.id.search || id == EditorInfo.IME_ACTION_SEARCH) {


                    return true;
                }
                gethide();
                return false;
            }

        });
        adapter = new ArrayAdapter<String>(this, R.layout.item_list, R.id.item, extension);
        LV.setAdapter(adapter);


        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                ExtensionNumbActivity.this.adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
//}
