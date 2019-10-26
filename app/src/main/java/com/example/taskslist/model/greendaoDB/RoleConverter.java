package com.example.taskslist.model.greendaoDB;

import com.example.taskslist.model.Role;

import org.greenrobot.greendao.converter.PropertyConverter;

public class RoleConverter implements PropertyConverter<Role,Integer> {
    @Override
    public Role convertToEntityProperty(Integer databaseValue) {
        if (databaseValue == null)
            return null;
        for (Role role : Role.values()) {
            if(role.getI()==databaseValue)
                return role;
        }
        return null;
    }

    @Override
    public Integer convertToDatabaseValue(Role entityProperty) {
        if (entityProperty == null)
            return null;
        return entityProperty.getI();
    }
}
