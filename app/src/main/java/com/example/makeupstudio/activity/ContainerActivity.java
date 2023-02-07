package com.example.makeupstudio.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.makeupstudio.R;
import com.example.makeupstudio.databinding.ActivityContainerBinding;
import com.example.makeupstudio.fragment.CategoryFragment;
import com.example.makeupstudio.fragment.DetailsFragment;
import com.example.makeupstudio.fragment.MakeupProductFragment;
import com.example.makeupstudio.fragment.PopularMakeupFragment;
import com.example.makeupstudio.fragment.SplashFragment;

public class ContainerActivity extends AppCompatActivity {

    ActivityContainerBinding binding;
    SplashFragment splashFragment = new SplashFragment();
    CategoryFragment categoryFragment = new CategoryFragment();
    DetailsFragment detailsFragment = new DetailsFragment();
    PopularMakeupFragment popularMakeupFragment = new PopularMakeupFragment();
    MakeupProductFragment productFragment = new MakeupProductFragment();
    Intent intent;
    String category, details, popularMakeup, product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContainerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        intent = getIntent();
        category = intent.getStringExtra("category");
        details = intent.getStringExtra("details");
        popularMakeup = intent.getStringExtra("popularMakeup");
        product = intent.getStringExtra("product");

        if (category == null && details == null && popularMakeup == null && product == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.container, splashFragment).commit();
        }

        if (category != null){
            if (category.equals("category")){
                getSupportFragmentManager().beginTransaction().replace(R.id.container, categoryFragment).commit();
            }
        }

        if (details != null){
            if (details.equals("details")){
                getSupportFragmentManager().beginTransaction().replace(R.id.container, detailsFragment).commit();
            }
        }

        if (popularMakeup != null){
            if (popularMakeup.equals("popularMakeup")){
                getSupportFragmentManager().beginTransaction().replace(R.id.container, popularMakeupFragment).commit();
            }
        }

        if (product != null){
            if (product.equals("product")){
                getSupportFragmentManager().beginTransaction().replace(R.id.container, productFragment).commit();
            }
        }

    }
}