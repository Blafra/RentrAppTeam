package com.example.franzi.rentrapp.Controller;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;

import com.example.franzi.rentrapp.Model.Question;
import com.example.franzi.rentrapp.Model.Survey;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RetrieveFromDB {



    //Get SpecificSurveyIdList from Database
    /*
    public ArrayList<String> getSpecificSurveyIdList(){

        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("Survey").child(surveyCode).child("specificSurveyIdList");
        final ArrayList<String> specificSurveyIdList = new ArrayList<>();

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot sn : dataSnapshot.getChildren()){

                    specificSurveyIdList.add(sn.getValue().toString());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }

        });

        return specificSurveyIdList;

    }*/
}
