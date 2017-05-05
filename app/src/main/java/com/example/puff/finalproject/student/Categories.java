package com.example.puff.finalproject.student;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.puff.finalproject.R;
import com.example.puff.finalproject.sharedPrefrences.InitializePref;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.puff.finalproject.R.id.c1;
import static com.example.puff.finalproject.R.id.c2;
import static com.example.puff.finalproject.R.id.c3;
import static com.example.puff.finalproject.R.id.e1;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Categories.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Categories#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Categories extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    final String Category_URL = "https://alishakapoor22895.000webhostapp.com/category.php";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View view;

    Spinner spn;
    ArrayList<String> array1;
    ArrayAdapter adap;

    RadioButton country1, country2, country3;
    RadioGroup radiogp;
    private RadioButton or3;

    EditText course;

    Button btn;

    private Categories.OnFragmentInteractionListener mListener;

    public Categories() {
        // Required empty public constructor
    }

    public static Categories newInstance(String param1, String param2) {
        Categories fragment = new Categories();
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
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_categories, container, false);

        btn = (Button) view.findViewById(R.id.b1);
        btn.setOnClickListener(this);

        country1 = (RadioButton) view.findViewById(c1);
        country2 = (RadioButton) view.findViewById(c2);
        country3 = (RadioButton) view.findViewById(c3);
        radiogp = (RadioGroup) view.findViewById(R.id.radiogrp);

        course = (EditText) view.findViewById(e1);
        spn = (Spinner) view.findViewById(R.id.s1);
        spn.setOnItemSelectedListener(this);
        array1 = new ArrayList<>();
        array1.add("Postgraduate Courses");
        array1.add("Undergraduate Courses");
        array1.add("Diploma");
        array1.add("[Choose Study Level]");
        final int listsize = array1.size() - 1;
        adap = new ArrayAdapter<String>(this.getActivity(), R.layout.spinner1_item, array1) {
            @Override
            public int getCount() {
                return (listsize); // Truncate the list
            }
        };
        adap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(adap);
        spn.setSelection(listsize);
        return view;

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onClick(View v) {
        if (v == btn) {
            if (inValid()) {
                return;
            }
            if (radiogp.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this.getActivity(), "Select Country", Toast.LENGTH_SHORT).show();

                return;
            }
            categoryEntry();
        }
    }

    public void categoryEntry() {
        final String courses = course.getText().toString();
        final String spnr = spn.getSelectedItem().toString();
        final String getName;
        int SelectedId = radiogp.getCheckedRadioButtonId();
        or3 = (RadioButton) view.findViewById(SelectedId);
        final String getCountry = or3.getText().toString();
        StringRequest stringRequest;
        final ProgressDialog loading = ProgressDialog.show(this.getActivity(), "Please Wait.....", "Response Submit", false, false);
        stringRequest = new StringRequest(Request.Method.POST, Category_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("DataBase Response", response);
                        if (response.equals("success")) {
                            loading.dismiss();
                            Toast.makeText(getActivity(), "Success....", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getActivity(), MainDrawer.class);
                            startActivity(intent);
                        } else {
                            loading.dismiss();
                            Toast.makeText(getActivity(), "Try again...", Toast.LENGTH_LONG).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                //params.put("student",getName);
                params.put("country", getCountry);
                params.put("course", courses);
                params.put("study_level", spnr);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this.getActivity());
        requestQueue.add(stringRequest);
    }

    public boolean inValid() {
        String getCourse = course.getText().toString();
        String getCountry1 = country1.getText().toString();
        String getCountry2 = country2.getText().toString();
        String getCountry3 = country3.getText().toString();


        if (getCourse.isEmpty()) {
            course.setError("Enter First Name");
            return true;
        }
        if (getCountry1.isEmpty()) {
            country1.setError("Select Country");
            return true;
        }
        if (getCountry2.isEmpty()) {
            country2.setError("Select Country");
            return true;
        }
        if (getCountry3.isEmpty()) {
            country3.setError("Select Country");
            return true;
        }
        return false;
    }

    public class OnFragmentInteractionListener {
    }
}
