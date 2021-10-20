package com.mrtienthinh.nguyentienthinh;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Feedback")
public class FeedbackEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "email")
    public String email;
    @ColumnInfo(name = "spinner")
    public String spinner;
    @ColumnInfo(name = "description")
    public String description;
    @ColumnInfo(name = "isEmail")
    public Boolean isEmail;

}
