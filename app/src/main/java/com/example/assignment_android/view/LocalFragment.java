package com.example.assignment_android.view;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.assignment_android.databinding.FragmentLocalBinding;
import com.example.assignment_android.model.PersonModel;
import com.example.assignment_android.adapter.ProgrammingAdapter;
import com.example.assignment_android.R;
import com.example.assignment_android.viewmodel.LocalViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;



public class LocalFragment extends Fragment {

    private LocalViewModel localViewModel;
    private ProgrammingAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentLocalBinding fragmentLocalBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_local,null,false);
        View view=fragmentLocalBinding.getRoot();
        fragmentLocalBinding.programlist.setLayoutManager(new LinearLayoutManager(getContext()));
        fragmentLocalBinding.programlist.setHasFixedSize(true);
        adapter=new ProgrammingAdapter();
        fragmentLocalBinding.programlist.setAdapter(adapter);

        localViewModel = ViewModelProviders.of(this).get(LocalViewModel.class);
        localViewModel.showAll().observe(this, new Observer<List<PersonModel>>() {
            @Override
            public void onChanged(List<PersonModel> personModels) {
               adapter.setList(personModels);
            }
        });
        return view;
    }
    public LocalFragment() {
    }
}
