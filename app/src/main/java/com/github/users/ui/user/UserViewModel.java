package com.github.users.ui.user;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.github.users.data.model.Response;
import com.github.users.data.model.User;
import com.github.users.data.repository.UserRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class UserViewModel extends ViewModel {

    private final UserRepository userRepository;
    private CompositeDisposable disposable;

    private final MutableLiveData<User> selectedUser = new MutableLiveData<>();
    private final MutableLiveData<List<User>> users = new MutableLiveData<>();
    private final MutableLiveData<Boolean> error = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    public LiveData<List<User>> getUsers() {return users; }
    LiveData<Boolean> getError() {
        return error;
    }
    LiveData<Boolean> getLoading() {
        return loading;
    }
    LiveData<User> getSelectedUser() {return selectedUser;}

    @Inject
    public UserViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
        disposable = new CompositeDisposable();
    }

    public void searchUser(String query){
        loading.setValue(true);
        disposable.add(userRepository.searchUsers(query).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<Response>() {
                    @Override
                    public void onSuccess(Response value) {
                        users.setValue(value.getUsers());
                        loading.setValue(false);
                    }
                    @Override
                    public void onError(Throwable e) {
                        error.setValue(true);
                        loading.setValue(false);
                    }
                }));
    }

    public void setUser(User user){
        this.selectedUser.setValue(user);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}
