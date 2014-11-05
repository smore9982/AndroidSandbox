package more.sandbox.activities;

import android.nfc.Tag;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.gms.internal.ne;

import more.sandbox.BaseActivity;
import more.sandbox.R;
import more.sandbox.fragments.tabs.ExtrasFragment;
import more.sandbox.fragments.tabs.RecyclerViewFragment;
import more.sandbox.fragments.tabs.SandboxFragmentInterface;
import more.sandbox.views.SlidingTabLayout;


public class ActionBarTabActivity extends BaseActivity {

    private ActionBar actionBar;
    private Toolbar toolbar;
    private Toolbar toolbar2;
    private ViewPager pager;
    private SandboxPagerAdapter mTabsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        actionBar = this.getSupportActionBar();
        pager = (ViewPager) this.findViewById(R.id.pager);
        toolbar = (Toolbar) this.findViewById(R.id.tab_toolbar);
        toolbar2 = (Toolbar) this.findViewById(R.id.second_tab_toolbar);
        this.setSupportActionBar(toolbar);

        initalizeTabs();
        this.showActionBarToggleSymbol(false);
    }

    private void initalizeTabs(){

        Tab[] screens = {
            new Tab().setTitle("Fragment1").setTag("RecyclerView").setIconRes(R.drawable.icon_map),
            new Tab().setTitle("Fragment2").setTag("RecyclerView").setIconRes(R.drawable.icon_map),
            new Tab().setTitle("Fragment3").setTag("RecyclerView").setIconRes(R.drawable.icon_map),
            new Tab().setTitle("Fragment4").setTag("RecyclerView").setIconRes(R.drawable.icon_map),
            new Tab().setTitle("Fragment5").setTag("ExtrasView").setIconRes(R.drawable.icon_map),
        };

        pager.setAdapter(mTabsAdapter = new SandboxPagerAdapter(this.getSupportFragmentManager(),screens));
        pager.setOnPageChangeListener(mTabsAdapter);

        SlidingTabLayout tabLayout = new SlidingTabLayout(this);
        tabLayout.setViewPager(pager);
        tabLayout.setOnPageChangeListener(mTabsAdapter);
        toolbar2.addView(tabLayout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    class SandboxPagerAdapter extends FragmentStatePagerAdapter implements ViewPager.OnPageChangeListener {

        Tab[] screens;
        public SandboxPagerAdapter(FragmentManager fm, Tab[] screens){
            super(fm);
            this.screens = screens;
        }

        @Override
        public Fragment getItem(int i) {
            Tab screenTab = screens[i];
            String screen = (String)screenTab.getTag();
            Fragment x = new Fragment();
            if(screen.equalsIgnoreCase("RecyclerView")){
                return RecyclerViewFragment.newInstance();
            }else if (screen.equals("ExtrasView")){
                return ExtrasFragment.newInstance();
            }
            return x;
        }

        @Override
        public int getCount() {
            return screens.length;
        }

        @Override
        public void onPageScrolled(int i, float v, int i2) {

        }

        @Override
        public void onPageSelected(int i) {
            if(mTabsAdapter.getItem(i) instanceof SandboxFragmentInterface ) {
                SandboxFragmentInterface frag = (SandboxFragmentInterface) mTabsAdapter.getItem(i);
                //frag.setTitle(ActionBarTabActivity.this);
            }else{
                ActionBarTabActivity.this.setTitle("Activity");
            }
        }

        @Override
        public java.lang.CharSequence getPageTitle(int position){
            Tab tab = screens[position];
            return tab.getTitle();
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }

    class Tab{
        private String title;
        private int iconRes;
        private String tag;

        public Tab newTab(){
            return new Tab();
        }

        public String getTitle() {
            return title;
        }

        public Tab setTitle(String title) {
            this.title = title;
            return this;
        }

        public int getIconRes() {
            return iconRes;
        }

        public Tab setIconRes(int iconRes) {
            this.iconRes = iconRes;
            return this;
        }

        public String getTag() {
            return tag;
        }

        public Tab setTag(String tag) {
            this.tag = tag;
            return this;
        }
    }
}
