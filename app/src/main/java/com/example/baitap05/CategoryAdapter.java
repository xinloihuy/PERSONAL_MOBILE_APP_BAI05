package com.example.baitap05;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.baitap05.model.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    Context context;
    List<Category> array;

    // Constructor để nhận Context và danh sách Category
    public CategoryAdapter(Context context, List<Category> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Ánh xạ layout item_category.xml vào View
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    // Phương thức này bị thiếu trong ảnh, nhưng nó là cốt lõi để gắn dữ liệu
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Category category = array.get(position);

        // Gắn dữ liệu (Ví dụ: Gắn tên danh mục)
        holder.tenSp.setText(category.getName());

        // Gắn hình ảnh (Glide/Picasso để tải ảnh từ URL category.getImages()
        Glide.with(context).load(category.getImages()).into(holder.images);
    }

    // Phương thức này cũng bị thiếu, trả về tổng số item
    @Override
    public int getItemCount() {
        return array == null ? 0 : array.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView images;
        public TextView tenSp; // Tên này nên là 'tvNameCategory' theo id trong XML

        // Constructor của ViewHolder
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            // Ánh xạ các View con từ item_category.xml
            images = (ImageView) itemView.findViewById(R.id.image_cate);
            tenSp = (TextView) itemView.findViewById(R.id.tvNameCategory);

            // Thiết lập sự kiện click cho toàn bộ item
            itemView.setOnClickListener(this);
        }

        // Xử lý sự kiện click
        @Override
        public void onClick(View v) {
            // Xử lý khi nhấp vào Item trên category
            // Toast.makeText(context, "Bạn đã chọn category" + tenSp.getText().toString(), Toast.LENGTH_SHORT).show();

            // Lấy vị trí của Item được click
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Category clickedCategory = array.get(position);
                // Ví dụ: Chuyển sang màn hình chi tiết với dữ liệu của clickedCategory
                Toast.makeText(context, "Bạn đã chọn: " + clickedCategory.getName(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}