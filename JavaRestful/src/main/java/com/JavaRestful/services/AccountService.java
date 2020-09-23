package com.JavaRestful.services;

import com.JavaRestful.models.AccountModel;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    public static AccountModel addAllData(String user , String password , String address , String phone, boolean author){
        try{
            AccountModel account = new AccountModel();
            account.setAddress(address);
            account.setUser(user);
            account.setPassword(password);
            account.setPhone(phone);
            account.setAuthor(author);
            return account;
        }catch (Exception e){
            return null;
        }
    }
}
