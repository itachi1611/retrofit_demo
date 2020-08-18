package com.fox.demo.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.fox.demo.R;

public class UserViewHolder extends RecyclerView.ViewHolder {

    public ImageView ivAvatar;
    public TextView tvName;
    public TextView tvEmail;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        ivAvatar = itemView.findViewById(R.id.ivAvatar);
        tvName = itemView.findViewById(R.id.tvName);
        tvEmail = itemView.findViewById(R.id.tvEmail);
    }
}
