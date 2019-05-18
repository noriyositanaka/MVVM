package com.example.mvvm;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import org.w3c.dom.ls.LSException;

import java.util.List;

public class MyRepository {

    MyDao myDao;
    LiveData<List<MyTable>> allTable;
    LiveData<List<MyTable>> filteredTable;

    public MyRepository (Application application){

        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        myDao = db.MyDao();
        allTable=myDao.getAll();
        filteredTable = myDao.getAll();
    }

    public LiveData<List<MyTable>> getAllTable(){
        return allTable;
    }
    public LiveData<List<MyTable>> getFilteredTable(String s){

        this.filteredTable = myDao.getFilteredTable(s);
        return filteredTable;
    }

    public void insert(MyTable myTable){

        InsertAsyncTask insertAsyncTask = new InsertAsyncTask(myDao);
        insertAsyncTask.execute(myTable);

    }

    public void deleteAll(){
        myDao.deleteAll();
    }

    private static class InsertAsyncTask extends AsyncTask<MyTable,Void,Void>{

        private MyDao mydao;

        public InsertAsyncTask(MyDao mydao){

            this.mydao = mydao;

        }

        @Override
        protected Void doInBackground(MyTable... myTables) {

            mydao.insert(myTables[0]);
            return null;
        }
    }
}
