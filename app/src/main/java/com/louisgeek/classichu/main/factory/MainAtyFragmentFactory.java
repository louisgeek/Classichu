package com.louisgeek.classichu.main.factory;

import android.util.SparseArray;

import com.classichu.classichu.basic.BasicFragment;
import com.louisgeek.classichu.BFragment;
import com.louisgeek.classichu.main.MainFragment;

/**
 * Created by Classichu on 2017-6-27.
 */

public class MainAtyFragmentFactory {
    private static SparseArray<BasicFragment>  fragments = new SparseArray<>();
    public static BasicFragment create(int pos) {
        BasicFragment basicFragment=fragments.get(pos);
        if (basicFragment == null) {//没有在集合中取到再进入实例化过程
            switch (pos) {
                case 0:
                    basicFragment= MainFragment.newInstance("病人列表", "", 0);
                    break;
                case 1:
                    basicFragment=MainFragment.newInstance("联系人", "", 1);
                    break;
                case 2:
                    basicFragment=BFragment.newInstance("动态", "", 2);
                    break;
                case 3:
                    basicFragment=MainFragment.newInstance("我的", "", 3);
                    break;
                default:
                    break;
            }
            fragments.put(pos,basicFragment);
        }
        return basicFragment;
    }
}
