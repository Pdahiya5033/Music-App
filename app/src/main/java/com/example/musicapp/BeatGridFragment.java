package com.example.musicapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BeatGridFragment extends Fragment {
    public static BeatGridFragment newInstance(){
        return new BeatGridFragment();
    }
//    private View itemView;
    private BeatGrid mBeatGrid;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mBeatGrid=new BeatGrid(getActivity());

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saverdInstanceState){
        View view=inflater.inflate(R.layout.fragment_beat_grid,container,false);
        RecyclerView recyclerView=(RecyclerView) view
                .findViewById(R.id.fragment_beat_grid_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));

        recyclerView.setAdapter(new SoundAdapter(mBeatGrid.getmSounds()));
        return view;
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        mBeatGrid.release();
    }
    private class SoundHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Button mButton;
        private Sound mSound;
        public SoundHolder(LayoutInflater inflater,ViewGroup container){
            super(inflater.inflate(R.layout.list_item_sound,container,false));
            mButton=(Button) itemView.findViewById(R.id.list_item_sound_button);
            mButton.setOnClickListener(this);
        }
        public void bindSound(Sound sound){
            mSound=sound;
            mButton.setText(mSound.getmName());
        }
        @Override
        public void onClick(View v){
            mBeatGrid.play(mSound);
        }
    }

    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder>{

        private List<Sound> mSounds;
        public SoundAdapter(List<Sound> sounds){
            mSounds=sounds;
        }
        @NonNull
        @Override
        public SoundHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater=LayoutInflater.from(getActivity());
            return new SoundHolder(inflater,parent);
        }

        @Override
        public void onBindViewHolder(@NonNull SoundHolder holder, int position) {
            Sound sound=mSounds.get(position);
            holder.bindSound(sound);
        }

        @Override
        public int getItemCount() {
            return mSounds.size();
        }
    }
}
