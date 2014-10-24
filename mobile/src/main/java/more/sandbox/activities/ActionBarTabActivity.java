package more.sandbox.activities;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;

import more.sandbox.BaseActivity;
import more.sandbox.R;
import more.sandbox.fragments.tabs.RecyclerViewFragment;
import more.sandbox.fragments.tabs.SandboxFragmentInterface;


public class ActionBarTabActivity extends BaseActivity {

    private ActionBar actionBar;
    private ViewPager pager;
    private SandboxPagerAdapter mTabsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        actionBar = this.getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        pager = (ViewPager) this.findViewById(R.id.pager);
        initalizeTabs();
    }

    private void initalizeTabs(){

        ActionBar.Tab[] screens = {
                actionBar.newTab().setIcon(R.drawable.icon_map).setTag("RecyclerView"),
                actionBar.newTab().setIcon(R.drawable.icon_map).setTag("RecyclerView")
        };

        for(int i=0;i<screens.length;i++){
            actionBar.addTab(screens[i].setTabListener(new SanboxTabListener()));
        }

        pager.setAdapter(mTabsAdapter = new SandboxPagerAdapter(this.getSupportFragmentManager(),screens));
        pager.setOnPageChangeListener(mTabsAdapter);
    }

    class SanboxTabListener implements ActionBar.TabListener{
        @Override
        public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {
            pager.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

        }
    }

    class SandboxPagerAdapter extends FragmentStatePagerAdapter implements ViewPager.OnPageChangeListener {

        ActionBar.Tab[] screens;
        public SandboxPagerAdapter(FragmentManager fm, ActionBar.Tab[] screens){
            super(fm);
            this.screens = screens;
        }

        @Override
        public Fragment getItem(int i) {
            ActionBar.Tab screenTab = screens[i];
            String screen = (String)screenTab.getTag();
            Fragment x = new Fragment();
            if(screen.equalsIgnoreCase("RecyclerView")){
                return RecyclerViewFragment.newInstance();
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
                frag.setTitle(ActionBarTabActivity.this);
            }else{
                ActionBarTabActivity.this.setTitle("Activity");
            }
            getSupportActionBar().setSelectedNavigationItem(i);
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }
}
