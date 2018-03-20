package com.example.kongjian.viewinjectproject;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.kongjian.viewinjectproject.ioc.ContentView;
import com.example.kongjian.viewinjectproject.ioc.OnClick;
import com.example.kongjian.viewinjectproject.ioc.OnLongClick;
import com.example.kongjian.viewinjectproject.ioc.ViewInject;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    @ViewInject(R.id.btn_1)
    Button btn_1;
    @ViewInject(R.id.btn_2)
    Button btn_2;

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, btn_1.getText().toString(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, btn_2.getText().toString(), Toast.LENGTH_SHORT).show();
    }
    @OnClick({R.id.btn_1,R.id.btn_2})
    public void mClick(View view) {
        Toast.makeText(this, view.getId() + ".............mClick", Toast.LENGTH_SHORT).show();

    }
    /**
     *如果是返回false在响应onlongclick事件之后还会响应onclick事件，
     * 返回true则只响应onlongclick事件不会响应onclick事件
     * */
    @OnLongClick({R.id.btn_2})
    public boolean mLongClick(View view) {
        Toast.makeText(this, view.getId() + ".............mLongClick", Toast.LENGTH_SHORT).show();
        return true;
    }
}
