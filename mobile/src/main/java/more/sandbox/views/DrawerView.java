package more.sandbox.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import more.sandbox.R;

/**
 * Created by more on 10/21/14.
 */
public class DrawerView extends RelativeLayout {

    ImageView topImageView;
    ListView activityList;

    public DrawerView(Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.view_drawer,this);
        topImageView= (ImageView) view.findViewById(R.id.sandbox_drawer_topimage);
        activityList= (ListView) view.findViewById(R.id.sandbox_drawer_list);
    }

    public DrawerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.view_drawer,this);
        topImageView= (ImageView) view.findViewById(R.id.sandbox_drawer_topimage);
        activityList= (ListView) view.findViewById(R.id.sandbox_drawer_list);
    }

    public DrawerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.view_drawer,this);
        topImageView= (ImageView) view.findViewById(R.id.sandbox_drawer_topimage);
        activityList= (ListView) view.findViewById(R.id.sandbox_drawer_list);
    }

    public DrawerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.view_drawer,this);
        topImageView= (ImageView) view.findViewById(R.id.sandbox_drawer_topimage);
        activityList= (ListView) view.findViewById(R.id.sandbox_drawer_list);
    }

    public void setTopImage(int resId){
        this.topImageView.setImageResource(resId);
    }

    public void setListAdapter(BaseAdapter adapter){
        this.activityList.setAdapter(adapter);
    }
}
