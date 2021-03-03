package com.github.users.ui.main;

import com.github.users.ui.user.UserDetailFragment;
import com.github.users.ui.user.UserListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBindingModule {

    @ContributesAndroidInjector
    abstract UserListFragment provideUserListFragment();

    @ContributesAndroidInjector
    abstract UserDetailFragment provideUserDetailFragment();
}
