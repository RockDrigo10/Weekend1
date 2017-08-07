package com.example.admin.navigationdrawerapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ThirdFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ThirdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThirdFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    SeekBar sb;
    TextView tv_sb_val,amount_borrowed,result_field,year_payment,result_field_year;
    EditText etDuration,amount_borrowed_field;
    Button button;
    double amount,rate,emi,anual;
    int year;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ThirdFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThirdFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThirdFragment newInstance(String param1, String param2) {
        ThirdFragment fragment = new ThirdFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_third, container, false);
        getActivity().setTitle("EMI Calculator");
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        //Displaying the Progress Value of the Seek Bar
        button = (Button) view.findViewById(R.id.button);
        sb = (SeekBar) view.findViewById(R.id.seek_bar);
        tv_sb_val = (TextView) view.findViewById(R.id.seek_bar_val);
        amount_borrowed = (TextView) view.findViewById(R.id.amount_borrowed);
        amount_borrowed_field = (EditText) view.findViewById(R.id.amount_borrowed_field);
        etDuration = (EditText) view.findViewById(R.id.etDuration);
        result_field = (TextView) view.findViewById(R.id.result_field);
        year_payment = (TextView) view.findViewById(R.id.year_payment);
        result_field_year = (TextView) view.findViewById(R.id.result_field_year);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                double progress_value = (double) progress;
                rate = progress;
                String temp1 = getResources().getString(R.string.seek_bar_value);
                //String temp2 = "" ;//if(temp1.contains(" ")){temp1 = temp1.substring(0,temp1.indexOf(" "));//}
                tv_sb_val.setText(temp1+progress_value);

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    amount = Double.parseDouble(amount_borrowed_field.getText().toString());
                    year = Integer.parseInt(etDuration.getText().toString());
                    rate=rate/(12*100);
                    year = year * 12;
                    emi= (amount*rate*Math.pow(1+rate,year))/(Math.pow(1+rate,year)-1);
                    //converts to string it was a double
                    anual = emi * year;
                    result_field.setText("$"+ emi + "");
                    result_field_year.setText("$" +anual + "");
                    sb.setProgress(0);
                    amount_borrowed_field.setText("");
                    etDuration.setText("");
                }
                catch (Exception e)
                {
                    Toast.makeText(getActivity(), "Missing Values", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return  view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("savText", result_field.getText().toString());
        outState.putString("savTextYear", result_field_year.getText().toString());
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            result_field.setText(savedInstanceState.getString("savText"));
            result_field_year.setText(savedInstanceState.getString("savTextYear"));
        }
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
