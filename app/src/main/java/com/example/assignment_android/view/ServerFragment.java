package com.example.assignment_android.view;


import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.assignment_android.adapter.FireBaseAdapter;
import com.example.assignment_android.model.FireBaseModel;
import com.example.assignment_android.R;
import com.example.assignment_android.model.PersonModel;
import com.example.assignment_android.viewmodel.LocalViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ServerFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private ArrayList<FireBaseModel>fireBaseModels;

    DatabaseReference databaseReference;

    public ServerFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_server, container, false);
        recyclerView=view.findViewById(R.id.programlists);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        fireBaseModels =new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (!snapshot.hasChild("Person")) {

                    AsyncTaskClass asyncTaskClass=new AsyncTaskClass();
                    asyncTaskClass.execute();

                }
                else {

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(getActivity(),"Error",Toast.LENGTH_SHORT).show();
            }
        });

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Person");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    FireBaseModel fireBaseModel=dataSnapshot1.getValue(FireBaseModel.class);
                    fireBaseModels.add(fireBaseModel);
                }
                adapter=new FireBaseAdapter(fireBaseModels,getContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(),"No Data To Show",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public class AsyncTaskClass extends AsyncTask<Void,Void,Void> {

        DatabaseReference databaseReference;


        public AsyncTaskClass(){

        }

        @Override
        protected Void doInBackground(Void... voids) {

            databaseReference = FirebaseDatabase.getInstance().getReference("Person");

            String json;
            String pName;
            String pDes;
            String pTeam;
            String pId;

            try {
                InputStream inputStream=getContext().getAssets().open("ok.json");
                int size=inputStream.available();
                byte [] buffer=new byte[size];
                inputStream.read(buffer);
                String pImage;
                inputStream.close();
                json=new String(buffer,"UTF-8");
                JSONArray jsonArray=new JSONArray(json);
                for (int i=0;i<jsonArray.length();i++){

                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    pId=databaseReference.push().getKey();

                    pName = jsonObject.getString("name");
                    pDes = jsonObject.getString("desegnation");
                    pTeam= jsonObject.getString("team");
                    pImage= jsonObject.getString("image");

                    FireBaseModel fireBaseModel=new FireBaseModel(pId,pName,pDes,pTeam,pImage);
                    databaseReference.child(pId).setValue(fireBaseModel);
                }
            }catch (IOException e){
                e.printStackTrace();
            }catch (JSONException e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

}

