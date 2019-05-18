package com.example.mvvm;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "my_table")
public class MyTable {

    @NonNull
    @ColumnInfo
    private String identification;

    @NonNull
    @ColumnInfo
    private String description;

    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    private Integer myKey;



    public MyTable(@NonNull String identification, @NonNull String description) {
        this.identification = identification;
        this.description = description;
    }

    public Integer getMyKey() {
        return myKey;
    }

    public void setMyKey(Integer myKey) {
        this.myKey = myKey;
    }

    @NonNull
    public String getIdentification() {
        return identification;
    }

    public void setIdentification(@NonNull String identification) {
        this.identification = identification;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }
}
