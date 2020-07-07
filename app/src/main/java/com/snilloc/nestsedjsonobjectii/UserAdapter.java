package com.snilloc.nestsedjsonobjectii;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.snilloc.nestsedjsonobjectii.Model.UsersListResponse.Users;

public class UserAdapter extends ListAdapter<Users, UserAdapter.UserViewHolder> {
    private OnCardClickListener listener;

    public UserAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Users> DIFF_CALLBACK = new DiffUtil.ItemCallback<Users>() {
        @Override
        public boolean areItemsTheSame(@NonNull Users oldItem, @NonNull Users newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Users oldItem, @NonNull Users newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    };


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Create the view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        //Bind the data to the views
        Users currentUser = getItem(position);

        holder.nameTextView.setText(currentUser.getName());
        holder.emailTextView.setText(currentUser.getEmail());
        holder.addressTextView.setText(currentUser.getAddress().getCity());
        holder.phoneNumberTextView.setText(currentUser.getPhone());
        holder.websiteTextView.setText(currentUser.getWebsite());
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView emailTextView;
        TextView addressTextView;
        TextView phoneNumberTextView;
        TextView websiteTextView;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.name);
            emailTextView = itemView.findViewById(R.id.email);
            addressTextView = itemView.findViewById(R.id.address);
            phoneNumberTextView = itemView.findViewById(R.id.phoneNumber);
            websiteTextView = itemView.findViewById(R.id.website);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Get the user at a particular position
                    int position = getAdapterPosition();
                    //Check if the listener is called and we have a valid position of the itemView(Card)
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    //Interface for the card click listener
    public interface OnCardClickListener {
        //Declare a method
        void onItemClick(Users users);
    }

    //Method that contains our OnCardClickListener
    public void setItemClickListener(OnCardClickListener listener) {
        this.listener = listener;
    }
}
