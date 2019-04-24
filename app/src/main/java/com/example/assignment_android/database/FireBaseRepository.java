package com.example.assignment_android.database;

import android.content.Context;
import android.os.AsyncTask;

import com.example.assignment_android.model.FireBaseModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class FireBaseRepository {

    DatabaseReference databaseReference;

    public class FireBaseAsync extends AsyncTask<Void,Void,Void>{
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
    }

    private Context getContext() {
        return getContext();
    }
}
