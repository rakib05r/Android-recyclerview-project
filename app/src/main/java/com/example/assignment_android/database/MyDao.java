package com.example.assignment_android.database;


import com.example.assignment_android.model.PersonModel;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface MyDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addUser(PersonModel personModel);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addAllUser(List<PersonModel>personModels);

    @Query("select * from Person")
    //List<PersonModel> showAll();
    LiveData<List<PersonModel>> showAll();
}
