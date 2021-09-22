package com.gmail.wigglewie.monej.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gmail.wigglewie.monej.CurrencyRate;
import com.gmail.wigglewie.monej.databinding.FragmentConverterBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ConverterFragment extends Fragment {

    private static final String ARG_PARAM_LIST = "paramList";

    private ArrayList<CurrencyRate> mList;

    private FragmentConverterBinding binding;

    private int selectedCurrencyIndex = 0;

    private AlertDialog alertDialog;

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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        binding = FragmentConverterBinding.inflate(inflater, container, false);
        binding.iconCountry1.setOnClickListener(view -> {
            AlertDialog.Builder materialAlertDialogBuilder = new AlertDialog.Builder(
                    getContext());
            String[] items = { "Item 1", "Item 2", "Item 3" };
            materialAlertDialogBuilder
                    .setTitle("title")
                    .setSingleChoiceItems(items, selectedCurrencyIndex, (dialogInterface, i) -> {
                        selectedCurrencyIndex = i;
                        String s = "selected item #" + (selectedCurrencyIndex + 1);
                        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                    })
                    //                    .setItems(items, (dialogInterface, i) -> {
                    //                        selectedCurrencyIndex = i;
                    //                        String s = "selected item #" + (selectedCurrencyIndex + 1);
                    //                        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                    //                        alertDialog = materialAlertDialogBuilder.create();
                    //                        alertDialog.getListView().setSelection(selectedCurrencyIndex);
                    //                    })
                    .setNeutralButton("cancel", (dialogInterface, i) -> {
                        dialogInterface.cancel();
                    }).show();
        });
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}