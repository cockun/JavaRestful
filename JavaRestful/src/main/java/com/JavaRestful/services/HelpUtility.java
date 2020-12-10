package com.JavaRestful.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.Date;

public class HelpUtility {
    public static final Pattern valid_email = Pattern.compile("^[a-zA-z][a-zA-Z0-9]*@{1}[a-zA-z]+.com$",
            Pattern.CASE_INSENSITIVE);
    public static final Pattern valid_phone = Pattern.compile("^0[1-9]{1}[0-9]{8,9}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern valid_password = Pattern
            .compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$", Pattern.CASE_INSENSITIVE);

    public static String getRandomCode(String root) {
        int randomNum = ThreadLocalRandom.current().nextInt(1000000, 9999999 + 1);

        return root + randomNum;

    }

    public static boolean validEmail(String email) {
        Matcher matcher = valid_email.matcher(email);
        return matcher.find();
    }

    public static boolean validPhone(String phone) {
        Matcher matcher = valid_phone.matcher(phone);
        return matcher.find();
    }

    public static boolean validPassword(String phone) {
        Matcher matcher = valid_password.matcher(phone);
        return matcher.find();
    }

    public static Date convertStringToDate(String date) throws ParseException {
        Date tmp = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        return tmp;
    }



}
