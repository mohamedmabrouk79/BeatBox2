package com.example.moham.beatbox;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

/**
 * Created by moham on 27/09/2016.
 */
public class BeatBoxFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private BeatBox mBeatBox;
    private Sound mSound;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBeatBox=new BeatBox(getActivity());
        setRetainInstance(true);
    }

    public static BeatBoxFragment newInstance(){
        return new BeatBoxFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_beat_box,container,false);
        mRecyclerView=(RecyclerView)view.findViewById(R.id.beat_box_recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));

        mRecyclerView.setAdapter(new SoundAdapter(mBeatBox.getSounds()));

        return view;
    }



    /***************Sound Holder  ****************/
    private class SoundHolder extends RecyclerView.ViewHolder{
        private Button mButtonSound;
        private Sound mSound;
        public SoundHolder(View itemView) {
            super(itemView);
            mButtonSound=(Button)itemView.findViewById(R.id.list_item_sound_button);
            mButtonSound.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBeatBox.play(mSound);
                }
            });
        }

        /************ bind sound ****************/
        public void bindSoud(Sound  sound){
            mSound=sound;
        mButtonSound.setText(mSound.getName());
        }
    }

    /*************** Sound Adapter **************/
    private class  SoundAdapter extends RecyclerView.Adapter<SoundHolder>{
        private List<Sound> mSounds;

        public SoundAdapter(List<Sound> sounds){
            mSounds=sounds;
        }
        @Override
        public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(getActivity()).inflate(R.layout.list_item_sound,parent,false);

            return new SoundHolder(view);
        }

        @Override
        public void onBindViewHolder(SoundHolder holder, int position) {
       Sound mSound=mSounds.get(position);
            holder.bindSoud(mSound);
        }

        @Override
        public int getItemCount() {
            return mSounds.size();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBeatBox.release();
    }
}
