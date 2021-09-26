package com.gmail.wigglewie.monej.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;

import com.gmail.wigglewie.monej.Currency;
import com.gmail.wigglewie.monej.CurrencyRate;
import com.gmail.wigglewie.monej.GrammarLocaleRu;
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

    private double currencyValue1;

    private double currencyValue2;

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
        binding.btnSwapCurrencies.setOnClickListener(view -> {
            String string =
                    getContext().getResources().getString(GrammarLocaleRu.USD.getNaming(12));
            System.out.println();
        });

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
        binding.editTextCurrencyEnter1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                //TODO calculations according to currency rate
                currencyValue1 = Double.parseDouble(charSequence.toString());
                System.out.println();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
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
        return getContext().getResources().getString(GrammarLocaleRu.valueOf(selectedCurrency1.abbreviation).getNaming(selectedCurrency1.scale));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}