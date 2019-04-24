package com.example.assignment_android.database;

import android.content.Context;
import android.os.AsyncTask;

import com.example.assignment_android.database.MyDao;
import com.example.assignment_android.model.PersonModel;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {PersonModel.class},version = 1,exportSchema = false)
public abstract class MyDb extends RoomDatabase {
    private static MyDb instance;
    public abstract MyDao myDao();

    public static synchronized MyDb getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),MyDb.class,"Person_Db_1").fallbackToDestructiveMigration().addCallback(callback).build();
        }
        return instance;
    }

    private static RoomDatabase.Callback callback=new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new Populate(instance).execute();
        }
    };
    private static class Populate extends AsyncTask<Void,Void,Void>{
        private MyDao myDao;

        private Populate(MyDb myDb) {
            myDao=myDb.myDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            myDao.addUser(new PersonModel("Md.Rokibul Islam","Intern","Web","https://www.proeins.com/wp-content/uploads/2018/09/default-avatar-rapper-guy.png"));
            myDao.addUser(new PersonModel("Md.Sumon Ali","Intern","Android","https://www.proeins.com/wp-content/uploads/2018/09/default-avatar-rapper-guy.png"));
            myDao.addUser(new PersonModel("Md.Zahid","Intern","Web","https://www.proeins.com/wp-content/uploads/2018/09/default-avatar-rapper-guy.png"));
            myDao.addUser(new PersonModel("Tushar Pramanick","Intern","Android","https://www.proeins.com/wp-content/uploads/2018/09/default-avatar-rapper-guy.png"));
            myDao.addUser(new PersonModel("Al-Amin","Intern","Web","https://www.proeins.com/wp-content/uploads/2018/09/default-avatar-rapper-guy.png"));
            myDao.addUser(new PersonModel("Soma Paul","Intern","Android","https://www.proeins.com/wp-content/uploads/2018/09/default-avatar-rapper-guy.png"));
            myDao.addUser(new PersonModel("Naz Zarren Oishie","Intern","Web","https://www.proeins.com/wp-content/uploads/2018/09/default-avatar-rapper-guy.png"));
            myDao.addUser(new PersonModel("Rumpa Paul","Intern","Android","https://www.proeins.com/wp-content/uploads/2018/09/default-avatar-rapper-guy.png"));
            myDao.addUser(new PersonModel("Md.Juel","Intern","Web","https://www.proeins.com/wp-content/uploads/2018/09/default-avatar-rapper-guy.png"));
            return null;
        }
    }
}
