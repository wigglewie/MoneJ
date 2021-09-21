package com.gmail.wigglewie.monej.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.wigglewie.monej.CurrencyRate;
import com.gmail.wigglewie.monej.databinding.FragmentConverterBinding;

import java.util.ArrayList;
import java.util.List;

public class ConverterFragment extends Fragment {

    private static final String ARG_PARAM_LIST = "paramList";

    private ArrayList<CurrencyRate> mList;

    private FragmentConverterBinding binding;

    public ConverterFragment() {
    }

    public static ConverterFragment newInstance(ArrayList<CurrencyRate> listCurrency) {
        ConverterFragment fragment = new ConverterFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_LIST, listCurrency);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mList = (ArrayList<CurrencyRate>) getArguments().getSerializable(ARG_PARAM_LIST);
            System.out.println();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        binding = FragmentConverterBinding.inflate(inflater, container, false);
        binding.fragmentContainerTest.setText(mList != null ? mList.get(0).abbreviation : "empty");
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}