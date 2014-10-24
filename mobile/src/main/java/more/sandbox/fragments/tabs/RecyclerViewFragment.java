package more.sandbox.fragments.tabs;



import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
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

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class RecyclerViewFragment extends Fragment implements SandboxFragmentInterface {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

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
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerAdp(inflater,items);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void setTitle(Activity activity) {
        activity.setTitle("Recycler View");
    }


    class RecyclerAdp extends  RecyclerView.Adapter<RecyclerHolder>{
        String[] items;
        LayoutInflater inflater;
        public RecyclerAdp(LayoutInflater inflater, String[] items) {
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
            holder.mCardTextView.setText(items[position]);
            Picasso.with(iv.getContext()).cancelRequest(iv);
            Picasso.with(iv.getContext()).load(items[position]).into(iv);
            holder.itemView.setTag(position);
        }

        @Override
        public int getItemCount() {
            return items.length;
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
