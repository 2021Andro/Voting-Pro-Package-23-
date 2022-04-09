package com.example.myapplication23.CostumeAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication23.CostumeClasses.Category;
import com.example.myapplication23.Interfaces.MyRecEvent;
import com.example.myapplication23.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyCatRecAdapter extends RecyclerView.Adapter<MyCatRecAdapter.MyViewHolder> {

    private MyRecEvent myClickEvent;
    private ArrayList<Category> catList;

    public MyCatRecAdapter(Context context) {
        this.myClickEvent = (MyRecEvent) context;
    }

    public void setCatList(ArrayList<Category> catList) {

        if (catList != null)
        {
            this.catList = catList;
            notifyDataSetChanged();
        }
        else {
            this.catList = new ArrayList<>();
        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cat_rec_adapter_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Category category = catList.get(position);

        holder.itemView.setTag(catList.get(position));

        holder.tvName.setText(category.getCategoryName());

        Glide
                .with(holder.itemView.getContext())
                .asBitmap()
                .load(category.getCategoryImage())
                .into(holder.profileImage);

    }

    @Override
    public int getItemCount() {
        return catList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView tvName;
        private CircleImageView profileImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName_MCL);
            profileImage = itemView.findViewById(R.id.profile_image_MCL);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    myClickEvent.setOnRecItemClickListener( catList.indexOf( (Category) itemView.getTag() ) );

                }
            });


        }
    }
}
