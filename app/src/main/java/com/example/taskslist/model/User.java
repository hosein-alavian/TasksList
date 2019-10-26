package com.example.taskslist.model;

import com.example.taskslist.model.greendaoDB.RoleConverter;
import com.example.taskslist.model.greendaoDB.UUIDConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;

import java.util.Date;
import java.util.UUID;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "user")
public class User {

    @Id(autoincrement = true)
    private Long _id;

    @Property(nameInDb = "uuid")
    @Index(unique = true)
    @Convert(converter = UUIDConverter.class,columnType = String.class)
    private UUID mId;

    @Property(nameInDb = "username")
    @Unique
    private String mUserName;

    @Property(nameInDb = "password")
    private String mPassword;

    @Property(nameInDb = "register_date")
    private Date mRegisterDate;

    @Property(nameInDb = "tasks_count")
    private int mTasksCount;

    @Property(nameInDb = "role")
    @Convert(converter = RoleConverter.class,columnType = Integer.class)
    private Role mRole;


    public User(String userName, String password,UUID id) {
        this.mUserName = userName;
        this.mPassword = password;
        this.mId=id;
//        if (userName.equals("ADMIN")){
//            mPriority=true;
//        }
        this.mRegisterDate=new Date();
        this.mRole=Role.NORMAL;
    }



    @Generated(hash = 644005807)
    public User(Long _id, UUID mId, String mUserName, String mPassword,
            Date mRegisterDate, int mTasksCount, Role mRole) {
        this._id = _id;
        this.mId = mId;
        this.mUserName = mUserName;
        this.mPassword = mPassword;
        this.mRegisterDate = mRegisterDate;
        this.mTasksCount = mTasksCount;
        this.mRole = mRole;
    }



    @Generated(hash = 586692638)
    public User() {
    }



    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        this.mUserName = userName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }

    public Date getRegisterDate() {
        return mRegisterDate;
    }

    public void setRegisterDate(Date registerDate) {
        mRegisterDate = registerDate;
    }

    public int getTasksCount() {
        return mTasksCount;
    }

    public void setTasksCount(int tasksCount) {
        mTasksCount = tasksCount;
    }

    public Role getRole() {
        return mRole;
    }

    public void setRole(Role role) {
        mRole = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return mUserName.equals(user.mUserName) &&
                mPassword.equals(user.mPassword);
    }



    public Long get_id() {
        return this._id;
    }



    public void set_id(Long _id) {
        this._id = _id;
    }



    public UUID getMId() {
        return this.mId;
    }



    public void setMId(UUID mId) {
        this.mId = mId;
    }



    public String getMUserName() {
        return this.mUserName;
    }



    public void setMUserName(String mUserName) {
        this.mUserName = mUserName;
    }



    public String getMPassword() {
        return this.mPassword;
    }



    public void setMPassword(String mPassword) {
        this.mPassword = mPassword;
    }



    public Date getMRegisterDate() {
        return this.mRegisterDate;
    }



    public void setMRegisterDate(Date mRegisterDate) {
        this.mRegisterDate = mRegisterDate;
    }



    public int getMTasksCount() {
        return this.mTasksCount;
    }



    public void setMTasksCount(int mTasksCount) {
        this.mTasksCount = mTasksCount;
    }



    public Role getMRole() {
        return this.mRole;
    }



    public void setMRole(Role mRole) {
        this.mRole = mRole;
    }
}

