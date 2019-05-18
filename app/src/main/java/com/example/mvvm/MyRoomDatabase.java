package com.example.mvvm;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

@Database(entities = {MyTable.class},version = 1)
public abstract class MyRoomDatabase extends RoomDatabase {

    public abstract MyDao MyDao();

    private static MyRoomDatabase INSTANCE;

    public static MyRoomDatabase getDatabase(final Context context){

        if(INSTANCE == null){
            synchronized (MyRoomDatabase.class){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                            MyRoomDatabase.class,"my_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(myCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback myCallback = new RoomDatabase.Callback(){


        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);


        }

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}
