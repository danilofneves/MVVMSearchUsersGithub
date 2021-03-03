package com.github.users.ui.user.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.github.users.data.model.User;
import com.github.users.databinding.LineItemUsersBinding;
import com.github.users.ui.user.UserListener;
import com.github.users.ui.user.UserViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private UserListener userListener;
    private final List<User> data = new ArrayList<>();

    public UserAdapter(UserViewModel viewModel, LifecycleOwner lifecycleOwner, UserListener userListener) {
        this.userListener = userListener;
        viewModel.getUsers().observe(lifecycleOwner, users -> {
            data.clear();
            if (users != null) {
                if(!users.isEmpty()) {
                    data.addAll(users);
                    notifyDataSetChanged();
                }
            }
        });
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LineItemUsersBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false), userListener);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static final class UserViewHolder extends RecyclerView.ViewHolder {
        private LineItemUsersBinding binding;
        private User user;

        UserViewHolder(LineItemUsersBinding binding, UserListener userListener) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(v ->{
                userListener.onClick(user);
            });
        }

        void bind(User user) {
            this.user = user;
            binding.txname.setText(user.getLogin());
            Picasso.get().load(user.getAvatar()).into(binding.image);
        }
    }
}
