package more.sandbox.fragments.tabs;



import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import more.sandbox.R;
import android.support.v7.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class RecyclerViewFragment extends Fragment implements SandboxFragmentInterface {
    private RecyclerView mRecyclerView;
    private RecyclerAdp mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button removeButton;
    private Button addButton;

    final String[] items = {"http://lorempixel.com/400/200/",
                            "http://lorempixel.com/600/200/",
                            "http://lorempixel.com/400/200/",
                            "http://lorempixel.com/600/200/",
                            "http://lorempixel.com/600/600/",
                            "http://lorempixel.com/400/200/",
            "http://lorempixel.com/400/200/",
            "http://lorempixel.com/600/200/",
            "http://lorempixel.com/400/200/",
            "http://lorempixel.com/600/200/",
            "http://lorempixel.com/600/600/",
            "http://lorempixel.com/400/200/",
            "http://lorempixel.com/400/200/",
            "http://lorempixel.com/600/200/",
            "http://lorempixel.com/400/200/",
            "http://lorempixel.com/600/200/",
            "http://lorempixel.com/600/600/",
            "http://lorempixel.com/400/200/",
            "http://lorempixel.com/400/200/",
            "http://lorempixel.com/600/200/",
            "http://lorempixel.com/400/200/",
            "http://lorempixel.com/600/200/",
            "http://lorempixel.com/600/600/",
            "http://lorempixel.com/400/200/"};
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
        mLayoutManager = new GridLayoutManager(this.getActivity(),3);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(items));
        mAdapter = new RecyclerAdp(inflater, arrayList);
        mRecyclerView.setAdapter(mAdapter);

        addButton = (Button) view.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.addItem("http://lorempixel.com/800/800/");
                mRecyclerView.scrollToPosition(mAdapter.getItemCount()-1);
            }
        });

        removeButton = (Button)view.findViewById(R.id.removeButton);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.removeItem(0);
            }
        });


        return view;
    }

    @Override
    public void setTitle(Activity activity) {
        activity.setTitle("Recycler View");
    }


    class RecyclerAdp extends  RecyclerView.Adapter<RecyclerHolder>{
        List<String> items;
        LayoutInflater inflater;
        public RecyclerAdp(LayoutInflater inflater, List<String> items) {
            this.inflater = inflater;
            this.items = items;
        }

        @Override
        public RecyclerHolder onCreateViewHolder(ViewGroup parent,int viewType) {
            // create a new view
            CardView view = (CardView) inflater.inflate(R.layout.view_card,null);
            RecyclerHolder vh = new RecyclerHolder(view);
            return vh;
        }

        @Override
        public void onBindViewHolder(RecyclerHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            ImageView iv = holder.mCardImageView;
            //holder.mCardTextView.setText(items[position]);
            Picasso.with(iv.getContext()).cancelRequest(iv);
            Picasso.with(iv.getContext()).load(items.get(position)).into(iv);
            holder.itemView.setTag(position);
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        public void removeItem(int position){
            this.items.remove(position);
            this.notifyItemRemoved(position);
        }

        public void addItem(String item){
            this.items.add(item);
            this.notifyItemInserted(items.size()-1);
        }
    }


    class RecyclerHolder extends RecyclerView.ViewHolder{
        public CardView mCardView;
        public ImageView mCardImageView;
        public TextView mCardTextView;
        public RecyclerHolder(CardView v) {
            super(v);
            mCardView = v;
            mCardImageView = (ImageView)v.findViewById(R.id.card_image);
            mCardTextView = (TextView)v.findViewById(R.id.card_text);
        }
    }

}
