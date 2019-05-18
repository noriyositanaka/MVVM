package com.example.mvvm;

import android.app.Application;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.randomstringgenerator.RandomStringGenerator;

import java.util.List;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    MyViewModel myViewModel;
    Application application;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        application = getApplication();

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopulateDBAsync populateDBAsync = new PopulateDBAsync();
                populateDBAsync.execute(application);


            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final MyAdapter adapter = new MyAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
/*
        myViewModel.getAllTable().observe(this, new Observer<List<MyTable>>() {
            @Override
            public void onChanged(@Nullable List<MyTable> myTables) {
                adapter.setTableList(myTables);
            }
        });

*/
        myViewModel.getFilteredTable("0").observe(this, new Observer<List<MyTable>>() {
            @Override
            public void onChanged(@Nullable List<MyTable> myTables) {
                adapter.setTableList(myTables);
            }
        });

//        ClearDBAsync clearDBAsync = new ClearDBAsync();
//        clearDBAsync.execute(application);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void deleteAll(){
        myViewModel.deleteAll();
    }

    private void insert(MyTable myTable){
        myViewModel.insert(myTable);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private static class PopulateDBAsync extends AsyncTask<Application,Void,Void>{

        RandomStringGenerator idGenerator = new RandomStringGenerator(RandomStringGenerator.GENERATE_MODE.NUM,
                8);
        RandomStringGenerator descGenerator = new RandomStringGenerator(RandomStringGenerator.GENERATE_MODE.NUM_AND_CHAR,
                16);


        @Override
        protected Void doInBackground(Application... applications) {

       //     for (int i = 0 ; i<100 ; i++) {

                MyTable myTable = new MyTable(idGenerator.obtain(), descGenerator.obtain());

                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                InsertDBAsync insertDBAsync = new InsertDBAsync(myTable);
                insertDBAsync.execute(applications[0]);
         //   }
            return null;
        }
    }

    private static class InsertDBAsync extends AsyncTask<Application,Void,Void>{

        private MyTable myTable;

        public InsertDBAsync(MyTable myTable) {

            this.myTable = myTable;
        }

        @Override
        protected Void doInBackground(Application... applications) {
            MyViewModel myViewModel=new MyViewModel(applications[0]);
            myViewModel.insert(myTable);
            return null;
        }
    }

    private static class ClearDBAsync extends AsyncTask<Application,Void,Void>{

        @Override
        protected Void doInBackground(Application... applications) {

            MyViewModel myViewModel=new MyViewModel(applications[0]);
            myViewModel.deleteAll();
            return null;
        }
    }

}
