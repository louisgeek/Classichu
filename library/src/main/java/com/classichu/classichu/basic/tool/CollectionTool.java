package com.classichu.classichu.basic.tool;

import java.util.Collection;

/**
 * Created by louisgeek on 2016/11/2.
 */

public class CollectionTool {

    public static boolean isEmpty(Collection collection) {
        if (collection == null || collection.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(Collection collection) {
        if (collection != null && !collection.isEmpty()) {
            return true;
        }
        return false;
    }
}
