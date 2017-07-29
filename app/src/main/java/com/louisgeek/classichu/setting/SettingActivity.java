package com.louisgeek.classichu.setting;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.view.View;

import com.classichu.classichu.basic.helper.VectorOrImageResHelper;
import com.classichu.classichu.basic.tool.SharedPreferencesTool;
import com.classichu.classichu.basic.widget.ClassicInputLayout;
import com.classichu.classichu.classic.ClassicActivity;
import com.classichu.titlebar.widget.ClassicTitleBar;
import com.louisgeek.classichu.R;

public class SettingActivity extends ClassicActivity {

    @Override
    protected int setupLayoutResId() {
        return R.layout.activity_setting;
    }

    ClassicInputLayout id_cil_java_ip;
    ClassicInputLayout id_cil_java_port;

    @Override
    protected void initView() {

        id_cil_java_ip=findById(R.id.id_cil_java_ip);
        id_cil_java_port=findById(R.id.id_cil_java_port);
        //id_cil_java_ip.getInput().setInputType(0x00002002);//代码中的TYPE_NUMBER_FLAG_DECIMAL 是8192  0x00002000 不行， xml中是8194 0x00002002
        id_cil_java_port.getInput().setInputType(InputType.TYPE_CLASS_NUMBER);

        id_cil_java_ip.setText((String) SharedPreferencesTool.get("java_ip",""));
        id_cil_java_port.setText((String) SharedPreferencesTool.get("java_port",""));

        Drawable rightDrawable=
                VectorOrImageResHelper.getDrawable(R.drawable.ic_check_black_24dp);
        rightDrawable.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        mClassicTitleBar.setRightImage(rightDrawable)
                .setOnTitleBarRightItemClickListener(new ClassicTitleBar.OnTitleBarRightItemClickListener() {
            @Override
            public void onRightClick(View view) {
                SharedPreferencesTool.put("java_ip",id_cil_java_ip.getText());
                SharedPreferencesTool.put("java_port",id_cil_java_port.getText());
                finish();
            }
        });


    }

    @Override
    protected void initListener() {

    }

    @Override
    protected AppBarStyle configAppBarStyle() {
        return AppBarStyle.ClassicTitleBar;
    }


}
