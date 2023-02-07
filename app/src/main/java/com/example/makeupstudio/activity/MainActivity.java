package com.example.makeupstudio.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.makeupstudio.R;
import com.example.makeupstudio.adapter.BrandAdapter;
import com.example.makeupstudio.adapter.CategoryAdapter;
import com.example.makeupstudio.adapter.PopularMakeupAdapter;
import com.example.makeupstudio.adapter.ProductAdapter;
import com.example.makeupstudio.databinding.ActivityMainBinding;
import com.example.makeupstudio.model.Brand;
import com.example.makeupstudio.model.Category;
import com.example.makeupstudio.model.PopularMakeup;
import com.example.makeupstudio.model.Product;
import com.example.makeupstudio.model.Slider;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseFirestore database;
    List<SlideModel> imageList;
    List<Slider> sliderList;
    List<PopularMakeup> popularMakeupList;
    List<Category> categoryList;
    List<Product> productList;
    List<Brand> brandList;
    CategoryAdapter categoryAdapter;
    PopularMakeupAdapter popularMakeupAdapter;
    ProductAdapter productAdapter;
    BrandAdapter brandAdapter;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        imageList = new ArrayList<>();
        sliderList = new ArrayList<>();
        popularMakeupList = new ArrayList<>();
        categoryList = new ArrayList<>();
        productList = new ArrayList<>();
        brandList = new ArrayList<>();

        intent = getIntent();
        String categoryId = intent.getStringExtra("categoryId");
        String makeupItemId = intent.getStringExtra("makeItemId");

        database = FirebaseFirestore.getInstance();

        database.collection("MakeUp")
                .document("slider_img")
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

        database.collection("MakeUp")
                .document("popularMakeup")
                .collection("popularMakeupList").addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        popularMakeupList.clear();
                        List<PopularMakeup> data = value.toObjects(PopularMakeup.class);
                        popularMakeupList.addAll(data);
                        popularMakeupAdapter = new PopularMakeupAdapter(getApplicationContext(), popularMakeupList);
                        binding.popularRecyclerView.setAdapter(popularMakeupAdapter);

                        if (popularMakeupList.size()>0){
                            binding.popular.setVisibility(View.VISIBLE);
                        }

                    }
                });

        database.collection("MakeUp")
                .document("category")
                .collection("categoryList").addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        categoryList.clear();
                        List<Category> data = value.toObjects(Category.class);
                        categoryList.addAll(data);
                        categoryAdapter = new CategoryAdapter(getApplicationContext(), categoryList);
                        binding.categoryRecyclerView.setAdapter(categoryAdapter);
                        if (categoryList.size()>0){
                            binding.category.setVisibility(View.VISIBLE);
                        }

                    }
                });

        database.collection("MakeUp")
                .document("product")
                .collection("productList").addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        productList.clear();
                        List<Product> data = value.toObjects(Product.class);
                        productList.addAll(data);
                        productAdapter = new ProductAdapter(getApplicationContext(), productList);
                        binding.makeUpProductRecyclerView.setAdapter(productAdapter);
                        if (productList.size()>0){
                            binding.makeUpProduct.setVisibility(View.VISIBLE);
                        }
                    }
                });

        database.collection("MakeUp")
                .document("brand")
                .collection("brandList").addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        brandList.clear();
                        List<Brand> data = value.toObjects(Brand.class);
                        brandList.addAll(data);
                        brandAdapter = new BrandAdapter(getApplicationContext(), brandList);
                        binding.popularBrandsRecyclerView.setAdapter(brandAdapter);
                        if (brandList.size()>0){
                            binding.popularBrands.setVisibility(View.VISIBLE);
                        }

                    }
                });


    }
}