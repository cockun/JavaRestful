package com.JavaRestful.services;

import java.util.concurrent.ThreadLocalRandom;

public class HelpUtility {
    public static String getRandomCode(String root){
        int randomNum = ThreadLocalRandom.current().nextInt(1000000, 9999999 + 1);

        return root + randomNum;

    }


}
