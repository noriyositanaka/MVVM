package com.example.mvvm;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MyDao {
    @Insert
    void insert(MyTable myTable);

    @Query("DELETE FROM my_table")
    void deleteAll();

    @Query("SELECT * FROM my_table ORDER BY myKey ASC")
    LiveData<List<MyTable>> getAll();

    @Query("SELECT * FROM my_table WHERE identification LIKE '%'||:s||'%'")
    LiveData<List<MyTable>> getFilteredTable(String s);

}
