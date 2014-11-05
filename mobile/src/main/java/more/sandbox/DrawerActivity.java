package more.sandbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import more.sandbox.activities.ActionBarTabActivity;
import more.sandbox.activities.SandboxWebviewActivity;
import more.sandbox.activities.WidgetActivity;
import more.sandbox.views.DrawerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by more on 10/20/14.
 */
public class DrawerActivity extends ActionBarActivity {
    ActionBar actionBar;
    DrawerLayout drawer;
    ViewGroup content;
    DrawerView drawerContainer;
    ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_drawer);
        actionBar = this.getSupportActionBar();
        drawer = (DrawerLayout)this.findViewById(R.id.sandbox_drawer);
        content =(ViewGroup)this.findViewById(R.id.content);
        drawerContainer = (more.sandbox.views.DrawerView)this.findViewById(R.id.drawer_view);

        List<DrawerListItem> items = new ArrayList<DrawerListItem>();
        DrawerListItem item1 = new DrawerListItem("Tabs Sample",R.drawable.icon_disk,"TabsSample", ActionBarTabActivity.class);
        DrawerListItem item2 = new DrawerListItem("Widget Box",R.drawable.icon_disk,"WidgetBox", WidgetActivity.class);
        DrawerListItem item3 = new DrawerListItem("WebView",R.drawable.icon_disk,"WebView", SandboxWebviewActivity.class);
        items.add(item2);
        items.add(item1);
        items.add(item3);
        DrawerListAdapter adp = new DrawerListAdapter(items);
        drawerContainer.setTopImage(R.drawable.ic_launcher);
        drawerContainer.setListAdapter(adp);

        if(actionBar != null) {
            mDrawerToggle = new ActionBarDrawerToggle(this, drawer, R.string.drawer_open, R.string.drawer_close){
                /** Called when a drawer has settled in a completely closed state. */
                public void onDrawerClosed(View view) {
                    super.onDrawerClosed(view);
                    //getActionBar().setTitle(mTitle);
                    invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                }

                /** Called when a drawer has settled in a completely open state. */
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                    //getActionBar().setTitle(mDrawerTitle);
                    invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                }
            };
            drawer.setDrawerListener(mDrawerToggle);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if(mDrawerToggle != null){
            mDrawerToggle.syncState();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setContentView(int contentViewId){
        this.getLayoutInflater().inflate(contentViewId,content);
    }


    class DrawerListAdapter extends BaseAdapter {
        List<DrawerListItem> items;
        public DrawerListAdapter(List<DrawerListItem> items){
            this.items = items;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = DrawerActivity.this.getLayoutInflater().inflate(R.layout.view_drawer_listitem,null);
            }
            ImageView iconView = (ImageView)convertView.findViewById(R.id.drawer_listitem_icon);
            TextView textView = (TextView)convertView.findViewById(R.id.drawer_listitem_title);

            final DrawerListItem item = (DrawerListItem) this.getItem(position);
            iconView.setImageResource(item.getDrawableResourceId());
            textView.setText(item.getText());
            /*if(DrawerFragment.selectedItem.equalsIgnoreCase(item.getTag())){
                textView.setText("***" + item.getText());
            }*/
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Class activityToCall = item.getActivityToCall();
                    Intent i = new Intent(DrawerActivity.this,activityToCall);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    DrawerActivity.this.startActivity(i);
                }
            });

            return convertView;
        }
    }

    class DrawerListItem{
        private String text;
        private int drawableResourceId;
        private String tag;
        private Class activityToCall;

        public DrawerListItem(String text, int resId,String tag,Class activityToCall){
            this.setText(text);
            this.setDrawableResourceId(resId);
            this.setTag(tag);
            this.setActivityToCall(activityToCall);
        }


        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getDrawableResourceId() {
            return drawableResourceId;
        }

        public void setDrawableResourceId(int drawableResourceId) {
            this.drawableResourceId = drawableResourceId;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public Class getActivityToCall() {
            return activityToCall;
        }

        public void setActivityToCall(Class activityToCall) {
            this.activityToCall = activityToCall;
        }
    }

    public void showActionBarToggleSymbol(boolean show){
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(show);
        }else{

        }
    }


}
