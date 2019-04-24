package com.example.assignment_android.database;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.example.assignment_android.model.PersonModel;
import com.example.assignment_android.view.HomeActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.lifecycle.LiveData;

import static android.content.Context.MODE_PRIVATE;

public class MyDbRepository {



    //Application application;
    //private ArrayList<PersonModel> AllpersonModels;

    private MyDao myDao;
    private LiveData<List<PersonModel>> personModels;

    public MyDbRepository(Application application) {
        //this.application=application;
        MyDb myDb=MyDb.getInstance(application);
        myDao=myDb.myDao();
        personModels=myDao.showAll();
    }
    public LiveData<List<PersonModel>>showAll(){
        return personModels;
    }

    public void insert(PersonModel personModel){

        new InsertPerson(myDao).execute(personModel);

    }


    private static class  InsertPerson extends AsyncTask<PersonModel,Void,Void>{

        private MyDao myDao;

        public InsertPerson(MyDao myDao) {
            this.myDao = myDao;
        }

        @Override
        protected Void doInBackground(PersonModel... personModels) {
            myDao.addUser(personModels[0]);
            return null;
        }

    }
}
