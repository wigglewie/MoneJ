package com.gmail.wigglewie.monej.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.os.ConfigurationCompat;
import androidx.fragment.app.Fragment;

import com.gmail.wigglewie.monej.Currency;
import com.gmail.wigglewie.monej.CurrencyRate;
import com.gmail.wigglewie.monej.databinding.FragmentConverterBinding;

import java.util.ArrayList;
import java.util.Locale;

import timber.log.Timber;

public class ConverterFragment extends Fragment {

    private static final String ARG_PARAM_LIST = "paramList";

    private ArrayList<CurrencyRate> mList;

    private FragmentConverterBinding binding;

    private Currency selectedCurrency1;

    private Currency selectedCurrency2;

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
        selectedCurrency1 = Currency.BELARUS;
        binding.iconCountry1.setImageDrawable(
                AppCompatResources.getDrawable(getContext(),
                        selectedCurrency1.icon));
        binding.textAbbreviation1.setText(selectedCurrency1.abbreviation);
        updateTextExchangeInfo();
        initField1();

        return binding.getRoot();
    }

    private void initField1() {
        binding.iconCountry1.setOnClickListener(view -> {
            AlertDialog.Builder materialAlertDialogBuilder = new AlertDialog.Builder(getContext());
            materialAlertDialogBuilder
                    .setTitle("Choose currency")
                    .setSingleChoiceItems(Currency.getAbbreviationsArray(),
                            selectedCurrency1.ordinal(),
                            (dialogInterface, position) -> {
                                selectedCurrency1 = Currency.findByIndex(position);
                                binding.iconCountry1.setImageDrawable(
                                        AppCompatResources
                                                .getDrawable(getContext(),
                                                        selectedCurrency1.icon));
                                binding.textAbbreviation1
                                        .setText(selectedCurrency1.abbreviation);
                                updateTextExchangeInfo();
                                Timber.d("======SELECTED====== %s",
                                        selectedCurrency1.toString());
                                dialogInterface.dismiss();
                            })
                    .setNeutralButton("cancel", (dialogInterface, i) -> dialogInterface.cancel())
                    .show();
        });
    }

    private void updateTextExchangeInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(selectedCurrency1.scale).append(" ");
        String displayLanguage = Locale.getDefault().getDisplayLanguage();
        if (displayLanguage.toLowerCase().contains("ru")) {
            sb.append(getProperRussianName());
        } else {
            sb.append(selectedCurrency1.abbreviation);
        }
        sb.append(" = ");
        sb.append("Currency2.rate").append(" ");
        sb.append("Currency2.abbreviation");
        binding.textExchangeInfo.setText(sb.toString());
        System.out.println();
    }

    private String getProperRussianName() {
        return "RUS";
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}