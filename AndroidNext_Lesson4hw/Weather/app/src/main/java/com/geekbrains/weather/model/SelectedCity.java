package com.geekbrains.weather.model;

/**
 * Created by shkryaba on 28/07/2018.
 */

public class SelectedCity {
    private String city;
    private Boolean isSelected;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
