package com.example.taskslist.model;

import com.example.taskslist.model.greendaoDB.StateConverter;
import com.example.taskslist.model.greendaoDB.UUIDConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToOne;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

@Entity(nameInDb = "task")
public class Task {

    @Id(autoincrement = true)
    private Long _id;

    @Property(nameInDb = "uuid")
    @Index(unique = true)
    @Convert(converter = UUIDConverter.class, columnType = String.class)
    private UUID mId;

    @Property(nameInDb = "title")
    private String mTitle;

    @Property(nameInDb = "description")
    private String mDescription;

    @Property(nameInDb = "date")
    private Date mDate;

    @Property(nameInDb = "hour")
    private Date mHour;

    @Property(nameInDb = "state")
    @Convert(converter = StateConverter.class, columnType = Integer.class)
    private States mState;

    @Property(nameInDb = "user_id")
    private Long userid;

    @ToOne(joinProperty = "userid")
    private User user;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1469429066)
    private transient TaskDao myDao;

    @Generated(hash = 251390918)
    private transient Long user__resolvedKey;

    public UUID getId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public Date getHour() {
        return mHour;
    }

    public void setHour(Date hour) {
        mHour = hour;
    }

    public States getmState() {
        return mState;
    }


    public void setState(States mState) {
        this.mState = mState;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getUserid() {
        return userid;
    }

    public Task(UUID id) {
        mId = id;
        mDate = new Date();
        mHour = new Date();
        mState = null;
//        this.userid = userid;

    }

    @Generated(hash = 1360103806)
    public Task(Long _id, UUID mId, String mTitle, String mDescription, Date mDate,
            Date mHour, States mState, Long userid) {
        this._id = _id;
        this.mId = mId;
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mDate = mDate;
        this.mHour = mHour;
        this.mState = mState;
        this.userid = userid;
    }

    @Generated(hash = 733837707)
    public Task() {
    }

    public String getStrHour() {
        return new SimpleDateFormat("hh:mm a").format(getHour());
    }

    public String getStrDate() {
        return new SimpleDateFormat("MMM dd,yyyy").format(getmDate());
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

    public String getMTitle() {
        return this.mTitle;
    }

    public void setMTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getMDescription() {
        return this.mDescription;
    }

    public void setMDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public Date getMDate() {
        return this.mDate;
    }

    public void setMDate(Date mDate) {
        this.mDate = mDate;
    }

    public Date getMHour() {
        return this.mHour;
    }

    public void setMHour(Date mHour) {
        this.mHour = mHour;
    }

    public States getMState() {
        return this.mState;
    }

    public void setMState(States mState) {
        this.mState = mState;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 405388571)
    public User getUser() {
        Long __key = this.userid;
        if (user__resolvedKey == null || !user__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UserDao targetDao = daoSession.getUserDao();
            User userNew = targetDao.load(__key);
            synchronized (this) {
                user = userNew;
                user__resolvedKey = __key;
            }
        }
        return user;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1824073571)
    public void setUser(User user) {
        synchronized (this) {
            this.user = user;
            userid = user == null ? null : user.get_id();
            user__resolvedKey = userid;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1442741304)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTaskDao() : null;
    }
}