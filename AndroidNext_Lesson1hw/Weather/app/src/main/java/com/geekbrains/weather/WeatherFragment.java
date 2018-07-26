package com.geekbrains.weather;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class WeatherFragment extends BaseFragment implements CreateActionFragment.OnHeadlineSelectedListener {

    private static final String ARG_COUNTRY = "ARG_COUNTRY";
    private static final String ARG_TEMP = "ARG_TEMP";
    private String country;
    private String temp;
    private TextView textView;
    private TextView textViewTemp;
    private BaseActivity mBaseActivity;

    public WeatherFragment() {
//        Особенностью поведения android-а состоит в том, что в любой момент
//        он может убить конкретный фрагмент (с случаи нехватки памяти например)
//        и потом попытаться восстановить его, используя конструктор без параметров,
//                следовательно передача параметров через конструкторы черевата
//        крэшами приложения в произвольный момент времени.

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            temp = bundle.getString("TEMP", "-273");
        }

    }



    public static WeatherFragment newInstance(String country, BaseActivity baseActivity) {
//        Для того что бы положить требуемые значения во фрагмент,
//        нужно обернуть их в Bundle и передать через метод setArguments.
//        Стандартным способом передачи параметров считается создание статического
//        метода newInstance (...),
//        а для восстановление параметров используется метод getArguments(...),вызываемый в
//        методе жизненного цикла onCreate (...) .
        //temp = baseActivity.getTemp();



//        Bundle args = new Bundle();
//        args.putString(ARG_COUNTRY, country);
//        //args.putString(ARG_TEMP, temp);


        WeatherFragment fragment = new WeatherFragment();
        //fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            country = getArguments().getString(ARG_COUNTRY);
            //temp = getArguments().getString(ARG_TEMP);
            ;
            //temp = mBaseActivity.getTemp();


        }
        Bundle bundle1 = this.getArguments();
        if (bundle1 != null) {
            temp = bundle1.getString("TEMP", "-273");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        temp = mBaseActivity.getTemp();

        return inflater.inflate(R.layout.weather_layout, container, false);

    }

    @Override
    protected void initLayout(View view, Bundle savedInstanceState) {
        textView = view.findViewById(R.id.tv_country);
        textViewTemp = view.findViewById(R.id.bigTemp);
        textViewTemp.setText(temp);
        //проверяем нашу переменную если она не пустая показываем город, если наоборот - ничего не показываем
       // temp = getBaseActivity().getTemp();
        if (country != null && country.length() > 0) {
            textView.setVisibility(View.VISIBLE);
            textView.setText(country);
            //temp = mBaseActivity.getTemp();


           // textViewTemp.invalidate();
        } else {
            textView.setVisibility(View.GONE);
        }

        ((TextView) getBaseActivity().findViewById(R.id.tv_humidity)).setText("30%");
        ((TextView) getBaseActivity().findViewById(R.id.tv_pressure)).setText("752mmHg");

    }

    @Override
    public void onArticleSelected(ArrayList<String> citiesList) {
        textView.setVisibility(View.VISIBLE);
        String cities = citiesList.toString();
        textView.setText(cities.substring(cities.indexOf("[") + 1, cities.indexOf("]")));
    }


}
