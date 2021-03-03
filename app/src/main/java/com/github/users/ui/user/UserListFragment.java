package com.github.users.ui.user;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.github.users.R;
import com.github.users.base.BaseFragment;
import com.github.users.data.model.User;
import com.github.users.databinding.FragmentUserlistBinding;
import com.github.users.di.util.ViewModelFactory;
import com.github.users.ui.user.adapter.UserAdapter;

import javax.inject.Inject;


public class UserListFragment extends BaseFragment implements UserListener {

    @Inject
    ViewModelFactory viewModelFactory;
    private UserViewModel userViewModel;

    private FragmentUserlistBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentUserlistBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        userViewModel = ViewModelProviders.of(getBaseActivity(), viewModelFactory).get(UserViewModel.class);

        binding.ltvUsers.addItemDecoration(new DividerItemDecoration(getBaseActivity(), DividerItemDecoration.VERTICAL));
        binding.ltvUsers.setAdapter(new UserAdapter(userViewModel,this, this));
        binding.ltvUsers.setLayoutManager(new LinearLayoutManager(getContext()));

        binding.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            Handler handler = new Handler(Looper.getMainLooper());
            Runnable workRunnable;

            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacks(workRunnable);
                workRunnable = () -> userViewModel.searchUser(editable.toString());
                handler.postDelayed(workRunnable, 500);
            }
        });

        observable();

    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }

    private void observable(){
        userViewModel.getLoading().observe(getViewLifecycleOwner(), loading -> {
            if(loading)
                binding.vflipper.setDisplayedChild(2);
        });
        userViewModel.getUsers().observe(getViewLifecycleOwner(), users ->{
            if(!users.isEmpty())
                binding.vflipper.setDisplayedChild(1);
            else
                binding.vflipper.setDisplayedChild(0);
        });
        userViewModel.getError().observe(getViewLifecycleOwner(), error ->{
            if(error)
                binding.vflipper.setDisplayedChild(3);
        });
    }

    @Override
    public void onClick(User user) {
        getBaseActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new UserDetailFragment()).addToBackStack(null).commit();
        userViewModel.setUser(user);
    }
}
