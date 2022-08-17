package com.example.inshortsassignment.datasource.utils;

import androidx.annotation.NonNull;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.List;

public final class Utils {

    @NonNull
    public static <U, V> List<U> map(
        @NonNull
            List<V> list, Function<? super V, ? extends U> mapper) throws Exception {
        List<U> result = new ArrayList<>(list.size());
        for (V v : list) {
            result.add(mapper.apply(v));
        }
        return result;
    }
}
