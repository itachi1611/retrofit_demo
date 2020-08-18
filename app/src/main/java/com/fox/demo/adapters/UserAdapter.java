package com.fox.demo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.fox.demo.R;
import com.fox.demo.holders.UserViewHolder;
import com.fox.demo.models.Datum;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private Context mContext;
    private List<Datum> list;

    public UserAdapter(Context mContext, List<Datum> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Datum datum = list.get(position);
        holder.tvName.setText(datum.getFirstName().trim() + " " +  datum.getLastName().trim());
        holder.tvEmail.setText(datum.getEmail().trim());
        Glide.with(holder.itemView)
                .load(datum.getAvatar())
                .priority(Priority.NORMAL)
                .placeholder(R.drawable.ic_no_image)
                .error(R.drawable.ic_no_image)
                .into(holder.ivAvatar);
    }

    @Override
    public int getItemCount() {
        return (list != null) ? list.size() : 0;
    }
}
