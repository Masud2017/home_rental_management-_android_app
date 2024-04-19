package com.home_rental.home_rental_management.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class User {
    @PrimaryKey
    @Setter(AccessLevel.NONE) // no setter for id field
    private long id;

    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "password")
    private String password;
    @ColumnInfo(name = "address")
    private String address;
    @ColumnInfo(name = "email")
    private String email;
}
