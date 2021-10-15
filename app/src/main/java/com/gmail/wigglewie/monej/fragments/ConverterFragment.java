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

import org.apache.commons.math3.util.Precision;

import java.util.ArrayList;
import java.util.Locale;

import timber.log.Timber;

public class ConverterFragment extends Fragment {

    private ArrayList<Currency> currencyValuesList;

    private FragmentConverterBinding binding;

    private Currency selectedCurrency1;

    private Currency selectedCurrency2;

    private double currencyValue1;

    private double currencyValue2;

    private CurrencyCalculateService calculateService;

    private CurrencyViewModel model;

    private double exchangeRate;

    private boolean isNeedUpdateView;

    public ConverterFragment() {
    }

    public static ConverterFragment newInstance() {
        return new ConverterFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        calculateService = new CurrencyCalculateService();
        CurrencyViewModel instance = CurrencyViewModel.getInstance();
        currencyValuesList = instance.getCurrencyValue().getValue();
        isNeedUpdateView = true;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentConverterBinding.inflate(inflater, container, false);
        selectedCurrency1 = Currency.BELARUS;
        selectedCurrency2 = Currency.USA;
        updateTextExchangeInfo();
        initFields();
        binding.btnSwapCurrencies.setOnClickListener(view -> {
            Currency temp = selectedCurrency1;
            selectedCurrency1 = selectedCurrency2;
            selectedCurrency2 = temp;
            updateTextExchangeInfo();
            isNeedUpdateView = false;
            if (Precision.round(currencyValue1 * exchangeRate, 2) != 0 ||
                    Precision.round(currencyValue2 * exchangeRate, 2) != 0) {
                if (selectedCurrency1.equals(Currency.RUSSIA)) {
                    currencyValue2 = Precision.round(currencyValue1 * exchangeRate / Currency.RUSSIA.scale, 2);
                } else {
                    currencyValue2 = Precision.round(currencyValue1 * exchangeRate, 2);
                }
                binding.editTextCurrencyEnter2.setText(String.valueOf(currencyValue2));
            }
            isNeedUpdateView = true;
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
                                updateTextExchangeInfo();
                                isNeedUpdateView = false;
                                if (Precision.round(currencyValue1 / exchangeRate, 2) != 0) {
                                    currencyValue2 = Precision.round(currencyValue1 * exchangeRate / selectedCurrency1.scale, 2);
                                    binding.editTextCurrencyEnter2.setText(String.valueOf(currencyValue2));
                                    isNeedUpdateView = true;
                                }
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
                if (isNeedUpdateView) {
                    if (!charSequence.toString().isEmpty()) {
                        currencyValue1 = Double.parseDouble(charSequence.toString());
                        updateTextExchangeInfo();
                        isNeedUpdateView = false;
                        currencyValue2 = Precision.round(currencyValue1 * exchangeRate, 2);
                        binding.editTextCurrencyEnter2.setText(String.valueOf(currencyValue2));
                    } else {
                        isNeedUpdateView = false;
                        binding.editTextCurrencyEnter2.setText("");
                        currencyValue1 = 0;
                        currencyValue2 = 0;
                    }
                    isNeedUpdateView = true;
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
                                updateTextExchangeInfo();
                                isNeedUpdateView = false;
                                if (Precision.round(currencyValue1 / exchangeRate, 2) != 0) {
                                    currencyValue2 = Precision.round(currencyValue1 * exchangeRate / selectedCurrency1.scale, 2);
                                    binding.editTextCurrencyEnter2.setText(String.valueOf(currencyValue2));
                                    isNeedUpdateView = true;
                                }
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
                if (isNeedUpdateView) {
                    if (!charSequence.toString().isEmpty()) {
                        currencyValue2 = Double.parseDouble(charSequence.toString());
                        updateTextExchangeInfo();
                        isNeedUpdateView = false;
                        currencyValue1 = Precision.round(currencyValue2 / exchangeRate, 2);
                        binding.editTextCurrencyEnter1.setText(String.valueOf(currencyValue1));
                    } else {
                        isNeedUpdateView = false;
                        binding.editTextCurrencyEnter1.setText("");
                        currencyValue1 = 0;
                        currencyValue2 = 0;
                    }
                    isNeedUpdateView = true;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void updateTextExchangeInfo() {
        exchangeRate = calculateService.calculateRate(selectedCurrency1, selectedCurrency2);

        binding.iconCountry1.setImageDrawable(
                AppCompatResources.getDrawable(requireContext(), selectedCurrency1.icon));
        binding.iconCountry2.setImageDrawable(
                AppCompatResources.getDrawable(requireContext(), selectedCurrency2.icon));
        binding.textAbbreviation1.setText(selectedCurrency1.abbreviation);
        binding.textAbbreviation2.setText(selectedCurrency2.abbreviation);

        StringBuilder sb = new StringBuilder();
        sb.append(selectedCurrency1.scale).append(" ");
        String displayLanguage = Locale.getDefault().getDisplayLanguage();
        if (displayLanguage.toLowerCase().contains("ru")) {
            sb.append(getProperRussianName(selectedCurrency1));
        } else {
            sb.append(selectedCurrency1.abbreviation);
        }
        sb.append(" = ");
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
        return requireContext().getResources().getString(GrammarLocaleRu.valueOf(currency.abbreviation).getNaming(currency.scale));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}