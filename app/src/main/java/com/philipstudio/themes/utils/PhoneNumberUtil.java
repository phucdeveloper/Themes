package com.philipstudio.themes.utils;

import android.content.Context;

import com.philipstudio.themes.R;

public class PhoneNumberUtil {
    private Context context;

    public PhoneNumberUtil(Context context) {
        this.context = context;
    }

    public String[] getCountryDialCode() {
        return context.getResources().getStringArray(R.array.dialing_country_code);
    }
}
