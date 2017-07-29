package com.louisgeek.classichu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Deprecated
public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        Pair<Integer,String> pair=new Pair<>(1,"20");
        Map<Integer,Object> map=new HashMap<>();
        List<Object> list=new ArrayList<>();



    }
}
