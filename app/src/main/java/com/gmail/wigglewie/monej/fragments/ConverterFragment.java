package com.gmail.wigglewie.monej.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;

import com.gmail.wigglewie.monej.GrammarLocaleRu;
import com.gmail.wigglewie.monej.data.Currency;
import com.gmail.wigglewie.monej.data.CurrencyViewModel;
import com.gmail.wigglewie.monej.databinding.FragmentConverterBinding;
import com.gmail.wigglewie.monej.service.impl.CurrencyCalculateService;

import java.util.ArrayList;
import java.util.Locale;

import timber.log.Timber;

public class ConverterFragment extends Fragment {

    private static final String ARG_PARAM_LIST = "paramList";

    private ArrayList<Currency> currencyValuesList;

    private FragmentConverterBinding binding;

    private Currency selectedCurrency1;

    private Currency selectedCurrency2;

    private double currencyValue1;

    private double currencyValue2;

    private CurrencyCalculateService currencyCalculateService;

    private CurrencyViewModel model;

    private double exchangeRate;

    public ConverterFragment() {
    }

    public static ConverterFragment newInstance() {
        return new ConverterFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currencyCalculateService = new CurrencyCalculateService();
        CurrencyViewModel instance = CurrencyViewModel.getInstance();
        currencyValuesList = instance.getCurrencyValue().getValue();
        System.out.println();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentConverterBinding.inflate(inflater, container, false);
        selectedCurrency1 = Currency.BELARUS;
        selectedCurrency2 = Currency.USA;
        updateView();
        initFields();
        binding.btnSwapCurrencies.setOnClickListener(view -> {
            Currency temp = selectedCurrency1;
            selectedCurrency1 = selectedCurrency2;
            selectedCurrency2 = temp;
            updateView();
        });

        return binding.getRoot();
    }

    private void initFields() {
        exchangeRate = 0;
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
                                                .getDrawable(requireContext(),
                                                        selectedCurrency1.icon));
                                binding.textAbbreviation1
                                        .setText(selectedCurrency1.abbreviation);
                                updateView();
                                Timber.d("======SELECTED====== %s",
                                        selectedCurrency1.toString());
                                dialogInterface.dismiss();
                            })
                    .setNeutralButton("cancel", (dialogInterface, i) -> dialogInterface.cancel())
                    .show();
        });
        binding.editTextCurrencyEnter1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                //TODO calculations according to currency rate
                if (!charSequence.toString().isEmpty()) {
                    currencyValue1 = Double.parseDouble(charSequence.toString());
                    currencyCalculateService.calculateRate(selectedCurrency1, selectedCurrency2);
                    updateView();
                } else {
                    System.out.println();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        binding.iconCountry2.setOnClickListener(view -> {
            AlertDialog.Builder materialAlertDialogBuilder = new AlertDialog.Builder(getContext());
            materialAlertDialogBuilder
                    .setTitle("Choose currency")
                    .setSingleChoiceItems(Currency.getAbbreviationsArray(),
                            selectedCurrency2.ordinal(),
                            (dialogInterface, position) -> {
                                selectedCurrency2 = Currency.findByIndex(position);
                                binding.iconCountry2.setImageDrawable(
                                        AppCompatResources
                                                .getDrawable(requireContext(),
                                                        selectedCurrency2.icon));
                                binding.textAbbreviation2
                                        .setText(selectedCurrency2.abbreviation);
                                updateView();
                                Timber.d("======SELECTED====== %s",
                                        selectedCurrency2.toString());
                                dialogInterface.dismiss();
                            })
                    .setNeutralButton("cancel", (dialogInterface, i) -> dialogInterface.cancel())
                    .show();
        });
        binding.editTextCurrencyEnter2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                //TODO calculations according to currency rate
                if (!charSequence.toString().isEmpty()) {
                    currencyValue2 = Double.parseDouble(charSequence.toString());
                    currencyCalculateService.calculateRate(selectedCurrency1, selectedCurrency2);
                    updateView();
                } else {
                    System.out.println();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void updateView() {
        binding.iconCountry1.setImageDrawable(
                AppCompatResources.getDrawable(getContext(),
                        selectedCurrency1.icon));
        binding.iconCountry2.setImageDrawable(
                AppCompatResources.getDrawable(getContext(), selectedCurrency2.icon));
        binding.textAbbreviation1.setText(selectedCurrency1.abbreviation);
        binding.textAbbreviation2.setText(selectedCurrency2.abbreviation);

        StringBuilder sb = new StringBuilder();
        sb.append("1").append(" ");
        String displayLanguage = Locale.getDefault().getDisplayLanguage();
        if (displayLanguage.toLowerCase().contains("ru")) {
            sb.append(getProperRussianName(selectedCurrency1));
        } else {
            sb.append(selectedCurrency1.abbreviation);
        }
        sb.append(" = ");
        exchangeRate = currencyCalculateService.calculateRate(selectedCurrency1, selectedCurrency2);
        sb.append(exchangeRate).append(" ");
        displayLanguage = Locale.getDefault().getDisplayLanguage();
        if (displayLanguage.toLowerCase().contains("ru")) {
            sb.append(getProperRussianName(selectedCurrency2));
        } else {
            sb.append(selectedCurrency2.abbreviation);
        }
        binding.textExchangeInfo.setText(sb.toString());
    }

    private String getProperRussianName(Currency currency) {
        return requireContext().getResources().getString(GrammarLocaleRu.valueOf(currency.abbreviation).getNaming(selectedCurrency1.scale));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}