package eg.intercom.hrss.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import com.github.florent37.materialviewpager.MaterialViewPager;

import eg.intercom.hrss.R;
import eg.intercom.hrss.adapters.ViewPagerAdapter;

/**
 * Created by lenovo-pc on 08-Feb-17.
 */
public class TestActivity extends ActionBarActivity {

    private MaterialViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mytest);

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
    }
}
