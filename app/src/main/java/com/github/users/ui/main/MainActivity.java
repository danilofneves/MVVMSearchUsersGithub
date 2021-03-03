package com.github.users.ui.main;


import android.os.Bundle;

import com.github.users.base.BaseActivity;
import com.github.users.databinding.ActivityMainBinding;
import com.github.users.ui.user.UserListFragment;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if(savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().replace(binding.container.getId(), new UserListFragment()).commit();
    }

    @Override
    protected void onDestroy() {
        binding = null;
        super.onDestroy();
    }
}