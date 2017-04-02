package com.example.puff.finalproject.student;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.puff.finalproject.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuickLink.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QuickLink#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuickLink extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView ask,scholar,visa,test,loan,vas;
    String text;

    private OnFragmentInteractionListener mListener;

    public QuickLink() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuickLink.
     */
    // TODO: Rename and change types and number of parameters
    public static QuickLink newInstance(String param1, String param2) {
        QuickLink fragment = new QuickLink();
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
        View  view =  inflater.inflate(R.layout.fragment_quick_link, container, false);
        ask=(TextView)view.findViewById(R.id.t1);
        //ask.setMovementMethod(LinkMovementMethod.getInstance());
        ask.setClickable(true);
        ask.setMovementMethod(LinkMovementMethod.getInstance());
        text = "<a href='http://www.google.com'> Ask Our Counsellor </a>";
        ask.setText(Html.fromHtml(text));

        scholar=(TextView)view.findViewById(R.id.t2);
        scholar.setClickable(true);
        scholar.setMovementMethod(LinkMovementMethod.getInstance());
        text = "<a href='http://www.google.com'>Scholarships </a>";
        scholar.setText(Html.fromHtml(text));

        visa=(TextView)view.findViewById(R.id.t3);
        visa.setClickable(true);
        visa.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='http://www.google.com'>Visa Application</a>";
        visa.setText(Html.fromHtml(text));

        loan=(TextView)view.findViewById(R.id.t5);
        loan.setClickable(true);
        loan.setMovementMethod(LinkMovementMethod.getInstance());
        text = "<a href='http://www.google.com'>Educational Loans </a>";
        loan.setText(Html.fromHtml(text));

        test=(TextView)view.findViewById(R.id.t4);
        test.setClickable(true);
        test.setMovementMethod(LinkMovementMethod.getInstance());
        text = "<a href='http://www.google.com'>Standardized Tests </a>";
        test.setText(Html.fromHtml(text));

        vas=(TextView)view.findViewById(R.id.t6);
        vas.setClickable(true);
        vas.setMovementMethod(LinkMovementMethod.getInstance());
        text = "<a href='http://www.google.com'>Value Added Services </a>";
        vas.setText(Html.fromHtml(text));
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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
     * activity.
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
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
