package com.example.makeupstudio.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.makeupstudio.R;
import com.example.makeupstudio.databinding.FragmentPopularMakeupBinding;

public class PopularMakeupFragment extends Fragment {

    FragmentPopularMakeupBinding binding;
    Intent intent;
    String makeupImg, makeupName, makeupDescription;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPopularMakeupBinding.inflate(getLayoutInflater(), container, false);

        intent = getActivity().getIntent();

        makeupImg = intent.getStringExtra("makeupImg");
        makeupName = intent.getStringExtra("makeupName");
        makeupDescription = intent.getStringExtra("makeupDescription");

        binding.makeItemName.setText(makeupName);
        binding.descriptionField.setText(makeupDescription);
        Glide.with(getActivity()).load(makeupImg).placeholder(R.drawable.imagehint).into(binding.popularMakeupImg);

        binding.backBtn.setOnClickListener(view -> {
            getActivity().finish();
        });

        return binding.getRoot();
    }
}