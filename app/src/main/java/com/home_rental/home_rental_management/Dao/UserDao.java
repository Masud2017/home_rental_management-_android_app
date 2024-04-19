package com.home_rental.home_rental_management.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.home_rental.home_rental_management.Models.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM User where email = (:email)")
    public List<User> getUserListByEmail(String email);
    @Insert
    public void insertAllI(List<User> userList);
    @Delete
    public void delete(User user);
}