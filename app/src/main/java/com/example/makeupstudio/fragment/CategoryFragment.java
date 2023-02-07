package com.example.makeupstudio.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.makeupstudio.databinding.FragmentCategoryBinding;
import com.example.makeupstudio.model.MakeupItem;
import com.example.makeupstudio.adapter.MakeupItemAdapter;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {

    FragmentCategoryBinding binding;
    FirebaseFirestore database;
    List<MakeupItem> makeupItemList;
    MakeupItemAdapter itemAdapter;
    Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCategoryBinding.inflate(getLayoutInflater(), container, false);

        makeupItemList = new ArrayList<>();

        intent = getActivity().getIntent();
        String categoryId = intent.getStringExtra("categoryId");
        String categoryName = intent.getStringExtra("categoryName");

        binding.categoryNameTV.setText("MakeUp Items of "+categoryName);

        database = FirebaseFirestore.getInstance();
        database.collection("MakeUp")
                .document("category")
                .collection("categoryList")
                .document(categoryId)
                .collection("makeupItemList").addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        makeupItemList.clear();
                        List<MakeupItem> data = value.toObjects(MakeupItem.class);
                        makeupItemList.addAll(data);
                        itemAdapter = new MakeupItemAdapter(getActivity(), makeupItemList);
                        binding.itemRecyclerView.setAdapter(itemAdapter);
                        binding.progressBar.setVisibility(View.GONE);
                    }
                });

        binding.backBtn.setOnClickListener(view -> {
            getActivity().finish();
        });

        return binding.getRoot();
    }
}