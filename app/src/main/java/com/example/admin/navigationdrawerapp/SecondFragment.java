package com.example.admin.navigationdrawerapp;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class SecondFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "player";
    private OnFragmentInteractionListener mListener;
    Boolean isStop = false;
    public SecondFragment() {
        // Required empty public constructor
    }
    Button btnPlay,btnPause,btnStop;
    static MediaPlayer mp3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Media Player");
        //final MediaPlayer sound = MediaPlayer.create(getActivity(),R.raw.madness);
        if(mp3!=null && !mp3.isPlaying()) {
            mp3 = MediaPlayer.create(getActivity(),R.raw.madness);
        }
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        btnPlay = (Button) view.findViewById(R.id.btnPlya);
        btnPause = (Button) view.findViewById(R.id.btnPause);
        btnStop = (Button) view.findViewById(R.id.btnStop);
        btnPlay.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        return view;
    }
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnPlya:
                if(mp3 == null) {
                    Log.d(TAG, "stopped" +mp3);
                    mp3 = MediaPlayer.create(getActivity().getApplicationContext(),R.raw.madness);
                    mp3.start();
                }
                if(!mp3.isPlaying()){
                    mp3.start();}
                break;
            case R.id.btnPause:
                if(mp3 != null){
                    if(mp3.isPlaying())
                        mp3.pause();
                }
                break;
            case R.id.btnStop:
                if(mp3 != null){
                    mp3.stop();
                    mp3.release();
                    mp3 = null;
                }
                break;
        }
    }
    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }
    @Override
    public void onStop() {
        super.onStop();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
