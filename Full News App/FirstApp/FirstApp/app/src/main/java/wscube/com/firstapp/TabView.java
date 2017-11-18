package wscube.com.firstapp;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class TabView extends AppCompatActivity {

    ViewPager viewPager;
    Toolbar toolbar;
    TabLayout tabLayout;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_view);
        i=getIntent().getIntExtra("id",0);

       // toolbar= (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewPager= (ViewPager) findViewById(R.id.view_pager);
        setupViewPager(viewPager);
        tabLayout= (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        if(i==R.id.btn_simple_tab)
        {
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        }
         if(i!=R.id.btn_simple_tab)
        {
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
        if(i==R.id.btn_icon_and_text_tabs || i==R.id.btn_only_icon_tabs || i==R.id.btn_custom_view)
        {
            setUpTabIcon();
        }

    }
private void setUpTabIcon()
{
    if(i==R.id.btn_custom_view)
    {
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        TextView txtTabOne= (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab,null);
        txtTabOne.setText("One");
        txtTabOne.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.notification_template_icon_bg,0,0);
        tabLayout.getTabAt(0).setCustomView(txtTabOne);

        TextView txtTabTwo= (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab,null);
        txtTabTwo.setText("Two");
        txtTabTwo.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.abc_ratingbar_full_material,0,0);
        tabLayout.getTabAt(1).setCustomView(txtTabTwo);

        TextView txtTabThree= (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab,null);
        txtTabThree.setText("Three1");
        txtTabThree.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.common_full_open_on_phone,0,0);
        tabLayout.getTabAt(2).setCustomView(txtTabThree);
    }
    else {
        tabLayout.getTabAt(0).setIcon(R.drawable.notification_template_icon_bg);
        tabLayout.getTabAt(1).setIcon(R.drawable.abc_ratingbar_full_material);
        tabLayout.getTabAt(2).setIcon(R.drawable.common_full_open_on_phone);
        tabLayout.getTabAt(3).setIcon(R.drawable.abc_btn_check_material);
        tabLayout.getTabAt(4).setIcon(R.drawable.abc_text_cursor_mtrl_alpha);
        tabLayout.getTabAt(5).setIcon(R.drawable.abc_btn_check_material);
    }

}
    private void setupViewPager(ViewPager viewPager) {
      ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new OneFragment(),"First");
        viewPagerAdapter.addFragment(new SecondFragment(),"Second");
        viewPagerAdapter.addFragment(new ThirdFragment(),"Third");
        if(i!=R.id.btn_simple_tab && i!=R.id.btn_custom_view) {
            viewPagerAdapter.addFragment(new FourthFragment(), "Fourth");
            viewPagerAdapter.addFragment(new FifthFragment(), "Fifth");
            viewPagerAdapter.addFragment(new SixthFragment(), "Sixth");
        }
        viewPager.setAdapter(viewPagerAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
            return super.onOptionsItemSelected(item);
        }
    }
    public class ViewPagerAdapter extends FragmentPagerAdapter
    {
        private final ArrayList<Fragment> arrFragment=new ArrayList<>();
        private final ArrayList<String> arrFragmentTitleList=new ArrayList<>();
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        public void addFragment(Fragment fragment,String title)
        {
            arrFragment.add(fragment);
            arrFragmentTitleList.add(title);
        }
        @Override
        public Fragment getItem(int position) {
            return arrFragment.get(position);
        }

        @Override
        public int getCount() {
            return arrFragment.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if(i==R.id.btn_only_icon_tabs)
                return "";
            return arrFragmentTitleList.get(position);
        }

    }
}
