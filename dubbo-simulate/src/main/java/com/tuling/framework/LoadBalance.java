package com.tuling.framework;

import java.util.List;
import java.util.Random;


public class LoadBalance {

    public static URL random(List<URL> list, List<URL> selectedList) {
        list.removeAll(selectedList);

        Random random =new Random();
        int n = random.nextInt(list.size());
        return list.get(n);
    }



}
