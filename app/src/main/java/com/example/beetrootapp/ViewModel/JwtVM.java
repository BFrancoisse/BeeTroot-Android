package com.example.beetrootapp.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.beetrootapp.model.JwtToken;
import com.example.beetrootapp.model.Login;
import com.example.beetrootapp.repository.JwtRepository;


public class JwtVM extends androidx.lifecycle.ViewModel{


    private LiveData<JwtToken> jwtTokenLiveData;

    public LiveData<JwtToken> getToken(Context context, Login login) {
        JwtRepository jwtRepository = new JwtRepository(context);
        jwtTokenLiveData = new MutableLiveData<>();
        jwtTokenLiveData = jwtRepository.getToken(login);
        return jwtTokenLiveData;
    }

}
