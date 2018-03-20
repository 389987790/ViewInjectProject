package com.example.kongjian.viewinjectproject;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.kongjian.viewinjectproject.ioc.InjectUtils;

/**
 * Created by user on 2018/3/20.
 */

public class BaseActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectUtils.inject(this);
    }
}
