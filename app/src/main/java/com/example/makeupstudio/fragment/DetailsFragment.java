package com.example.makeupstudio.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.makeupstudio.R;
import com.example.makeupstudio.databinding.FragmentDetailsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DetailsFragment extends Fragment {

    FragmentDetailsBinding binding;
    DocumentReference documentReference;
    FirebaseFirestore database;
    List<SlideModel> imageList;
    Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailsBinding.inflate(getLayoutInflater(), container, false);

        imageList = new ArrayList<>();
        intent = getActivity().getIntent();
        String categoryId = intent.getStringExtra("categoryId");
        String makeupItemId = intent.getStringExtra("makeupItemId");

        database = FirebaseFirestore.getInstance();

        database.collection("MakeUp")
                .document("category")
                .collection("categoryList")
                .document(categoryId)
                .collection("makeupItemList")
                .document(makeupItemId)
                .collection("slider").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot querySnapshot: task.getResult()){
                                imageList.add(new SlideModel(querySnapshot.getString("slider_image"), ScaleTypes.CENTER_CROP));
                                binding.imageSlider.setImageList(imageList);
                            }
                        }
                    }
                });

        documentReference = FirebaseFirestore.getInstance().collection("MakeUp")
                .document("category")
                .collection("categoryList")
                .document(categoryId)
                .collection("makeupItemList")
                .document(makeupItemId);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    binding.makeItemName.setText(documentSnapshot.getString("makeupItem_name"));
                    binding.aboutField.setText(documentSnapshot.getString("makeupItem_about"));
                    binding.procedureField.setText(documentSnapshot.getString("makeupItem_procedure"));
                    binding.removeField.setText(documentSnapshot.getString("makeupItem_remove"));
                }
            }
        });

        binding.backBtn.setOnClickListener(view -> {
            getActivity().finish();
        });

        return binding.getRoot();
    }
}