package more.sandbox.fragments.tabs;



import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import more.sandbox.R;
import android.support.v7.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class RecyclerViewFragment extends Fragment implements SandboxFragmentInterface {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static RecyclerViewFragment newInstance() {
        RecyclerViewFragment fragment = new RecyclerViewFragment();
        return fragment;
    }

    public RecyclerViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview,container,false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.sandbox_recyclerView);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);


        final String[] items = {"String1","String2","String3"};
        mRecyclerView.setAdapter(new RecyclerAdp(items));

        return view;
    }

    @Override
    public void setTitle(Activity activity) {
        activity.setTitle("Recycler View");
    }


    class RecyclerAdp extends  RecyclerView.Adapter<RecyclerHolder>{
        String[] items;
        public RecyclerAdp(String[] items){
            this.items = items;
        }

        @Override
        public RecyclerHolder onCreateViewHolder(ViewGroup parent,int viewType) {
            // create a new view
            TextView view = new TextView(RecyclerViewFragment.this.getActivity());
            RecyclerHolder vh = new RecyclerHolder(view);
            return vh;
        }

        @Override
        public void onBindViewHolder(RecyclerHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.mTextView.setText(items[position]);
        }

        @Override
        public int getItemCount() {
            return items.length;
        }
    }


    class RecyclerHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public RecyclerHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

}
