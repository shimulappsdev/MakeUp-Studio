package com.example.makeupstudio.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.makeupstudio.R;
import com.example.makeupstudio.databinding.FragmentMakeupProductBinding;

public class MakeupProductFragment extends Fragment {

    FragmentMakeupProductBinding binding;
    Intent intent;
    String productImg, productName, productDescription;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMakeupProductBinding.inflate(getLayoutInflater(), container, false);

        intent = getActivity().getIntent();

        productImg = intent.getStringExtra("productImg");
        productName = intent.getStringExtra("productName");
        productDescription = intent.getStringExtra("productDescription");

        binding.productName.setText(productName);
        binding.descriptionField.setText(productDescription);
        Glide.with(getActivity()).load(productImg).placeholder(R.drawable.imagehint).into(binding.productImg);

        binding.backBtn.setOnClickListener(view -> {
            getActivity().finish();
        });

        return binding.getRoot();
    }
}