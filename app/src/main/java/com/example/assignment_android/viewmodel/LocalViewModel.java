package com.example.assignment_android.viewmodel;

import android.app.Application;

import com.example.assignment_android.database.MyDbRepository;
import com.example.assignment_android.model.PersonModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class LocalViewModel extends AndroidViewModel {

    private MyDbRepository myDbRepository;
    private LiveData<List<PersonModel>>personmodels;

    public LocalViewModel(@NonNull Application application) {
        super(application);
        myDbRepository=new MyDbRepository(application);
        personmodels=myDbRepository.showAll();
    }
    public void insert(PersonModel personModel){
        myDbRepository.insert(personModel);
    }
    public LiveData<List<PersonModel>> showAll(){
        return personmodels;
    }
}
