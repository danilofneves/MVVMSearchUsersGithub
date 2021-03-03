package com.github.users.ui.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.github.users.base.BaseFragment;
import com.github.users.databinding.FragmentUserdetailBinding;
import com.github.users.di.util.ViewModelFactory;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;


public class UserDetailFragment extends BaseFragment{

    @Inject
    ViewModelFactory viewModelFactory;
    private UserViewModel userViewModel;

    private FragmentUserdetailBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentUserdetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        userViewModel = ViewModelProviders.of(getBaseActivity(), viewModelFactory).get(UserViewModel.class);
        userViewModel.getSelectedUser().observe(getViewLifecycleOwner(), user->{
            binding.txname.setText(user.getLogin());
            Picasso.get().load(user.getAvatar()).into(binding.image);
        });
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }

}
