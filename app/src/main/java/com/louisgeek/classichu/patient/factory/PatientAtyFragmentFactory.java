package com.louisgeek.classichu.patient.factory;

import android.util.SparseArray;

import com.classichu.classichu.basic.BasicFragment;
import com.louisgeek.classichu.R;
import com.louisgeek.classichu.patient.PatientInfoFragment;

/**
 * Created by Classichu on 2017-6-27.
 */

public class PatientAtyFragmentFactory {
    private static SparseArray<BasicFragment> fragments = new SparseArray<>();
    public static BasicFragment create(int itemId) {
        BasicFragment basicFragment=fragments.get(itemId);
        if (basicFragment == null) {//没有在集合中取到再进入实例化过程
            switch (itemId) {
                case R.id.id_menu_item_patient_info:
                    basicFragment= PatientInfoFragment.newInstance("病人信息", "");
                    break;
                default:
                    break;
            }
            fragments.put(itemId,basicFragment);
        }
        return basicFragment;
    }
}
