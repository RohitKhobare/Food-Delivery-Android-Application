package com.example.myapplication.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.adapters.HomeListAdapter;
import com.example.myapplication.databinding.FragmentDetailsBinding;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.models.Product;
import com.example.myapplication.viewmodels.HomeViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;


public class DetailsFragment extends Fragment   {
    FragmentDetailsBinding fragmentDetailsBinding;
    HomeViewModel homeViewModel;

    private NavController navController;

    public DetailsFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        fragmentDetailsBinding=FragmentDetailsBinding.inflate(inflater,container,false);
        return fragmentDetailsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeViewModel=new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        fragmentDetailsBinding.setHomeViewModel(homeViewModel);


    }


}