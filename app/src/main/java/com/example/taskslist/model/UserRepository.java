package com.example.taskslist.model;


import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static UserRepository instance;
    private List<User> userList;

    private UserRepository(){
        userList=new ArrayList<>();
    }

    public static UserRepository getInstance() {
        if(instance==null)
            instance=new UserRepository();
        return instance;
    }
    public void addUser(User user) throws WrongUserNameException {
        if(userList.contains(user))
            throw new WrongUserNameException();
        this.userList.add(user);
    }

    public boolean login(String userName,String password){
        for(User user:userList)
            if(user.getUserName().equals(userName) &&
                    user.getPassword().equals(password))
                return true;
        return false;
    }

}

