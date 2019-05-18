package com.example.mvvm;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class MyViewModel extends AndroidViewModel {

    private MyRepository myRepository;
    private LiveData<List<MyTable>> allTable;
    LiveData<List<MyTable>> filteredTable;


    public MyViewModel(@NonNull Application application) {
        super(application);
        myRepository = new MyRepository(application);
        allTable = myRepository.getAllTable();
        filteredTable = myRepository.getAllTable();

    }

    public LiveData<List<MyTable>> getAllTable(){
        return allTable;
    }

    public LiveData<List<MyTable>> getFilteredTable(String s){

        filteredTable= myRepository.getFilteredTable(s);
        return filteredTable;

    }

    public void insert(MyTable myTable){
        myRepository.insert(myTable);
    }

    public void deleteAll(){
        myRepository.deleteAll();
    }
}
