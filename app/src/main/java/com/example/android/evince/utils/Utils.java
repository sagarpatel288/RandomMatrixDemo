package com.example.android.evince.utils;

import java.util.List;

public final class Utils {
    private Utils() {
    }

    public static boolean hasElement(List list, int position){
        return list != null && !list.isEmpty() && position != -1 && list.size() > position;
    }

    public static boolean isNotNullNotEmpty(List list){
        return list != null && !list.isEmpty();
    }
}
