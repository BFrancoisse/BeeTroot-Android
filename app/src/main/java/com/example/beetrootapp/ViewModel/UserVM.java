package com.example.beetrootapp.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.beetrootapp.DAO.UserDAO;
import com.example.beetrootapp.model.User;

public class UserVM extends androidx.lifecycle.ViewModel{

    private LiveData<User> userById;

    public LiveData<User> getUserById() {
        userById = new MutableLiveData<>();
        userById = loadUserById();
        return userById;
    }

   private LiveData<User> loadUserById(){
       UserDAO userDAO = new UserDAO();
       return userDAO.getUserById();
   }
}
