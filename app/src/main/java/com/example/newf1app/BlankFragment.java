package com.example.newf1app;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
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

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);

        String TeamsList[] = {"Mercedes AMG Petronas", "Red Bull", "Ferrari", "Aston Martin", "Mclaren", "Alpine F1 Team","RBVCA","Hass","Williams","Kick F1 team"};

        int point[] ={690,520,500,419,370 ,295,240,205,175, 100};
        // Modify the countryList to include numbering
        String[] numberedCountryList = new String[TeamsList.length];
        for (int i = 0; i < TeamsList.length; i++) {
            numberedCountryList[i] = String.format("%d. %s %d", (i + 1), TeamsList[i], point[i]);
        }

        ListView COnstructorChampion = (ListView) rootView.findViewById(R.id.COnstructorChampion);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, numberedCountryList);
        COnstructorChampion.setAdapter(arrayAdapter);

        return rootView;
    }
}