package com.gmail.wigglewie.monej.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;

import com.gmail.wigglewie.monej.CountryCurrency;
import com.gmail.wigglewie.monej.CurrencyRate;
import com.gmail.wigglewie.monej.databinding.FragmentConverterBinding;

import java.util.ArrayList;

import timber.log.Timber;

public class ConverterFragment extends Fragment {

    private static final String ARG_PARAM_LIST = "paramList";

    private ArrayList<CurrencyRate> mList;

    private FragmentConverterBinding binding;

    private CountryCurrency selectedCountryCurrency1;

    private CountryCurrency selectedCountryCurrency2;

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
        selectedCountryCurrency1 = CountryCurrency.BELARUS;
        binding.iconCountry1.setImageDrawable(
                AppCompatResources.getDrawable(getContext(),
                        selectedCountryCurrency1.icon));
        binding.textAbbreviation1.setText(selectedCountryCurrency1.abbreviation);

        initField1();

        return binding.getRoot();
    }

    private void initField1() {
        binding.iconCountry1.setOnClickListener(view -> {
            AlertDialog.Builder materialAlertDialogBuilder = new AlertDialog.Builder(getContext());
            materialAlertDialogBuilder
                    .setTitle("Choose currency")
                    .setSingleChoiceItems(CountryCurrency.getAbbreviationsArray(),
                            selectedCountryCurrency1.index,
                            (dialogInterface, i) -> {
                                selectedCountryCurrency1 = CountryCurrency.findByIndex(i);
                                binding.iconCountry1.setImageDrawable(
                                        AppCompatResources.getDrawable(getContext(),
                                                selectedCountryCurrency1.icon));
                                binding.textAbbreviation1
                                        .setText(selectedCountryCurrency1.abbreviation);
                                Timber.d("======SELECTED====== %s", selectedCountryCurrency1.toString());
                                dialogInterface.dismiss();
                            })
                    .setNeutralButton("cancel", (dialogInterface, i) -> dialogInterface.cancel())
                    .show();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}