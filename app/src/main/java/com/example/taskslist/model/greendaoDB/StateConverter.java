package com.example.taskslist.model.greendaoDB;

import com.example.taskslist.model.States;

import org.greenrobot.greendao.converter.PropertyConverter;

public class StateConverter implements PropertyConverter<States,Integer> {
    @Override
    public States convertToEntityProperty(Integer databaseValue) {
        if(databaseValue==null)
            return null;
        for (States state : States.values()) {
            if (state.getI() == databaseValue)
                return state;
        }
            return null;
    }

    @Override
    public Integer convertToDatabaseValue(States entityProperty) {
        if(entityProperty==null)
            return null;
        return entityProperty.getI();
    }
}
