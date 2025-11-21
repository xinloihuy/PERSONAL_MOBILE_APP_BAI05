package com.example.baitap05;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.example.baitap05.model.Category;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    // Khai báo các thành phần
    RecyclerView rcCate;
    CategoryAdapter categoryAdapter;
    APIService apiService;
    List<Category> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo List (nên làm để tránh lỗi NullPointer)
        categoryList = new ArrayList<>();

        AnhXa();
        GetCategory(); // load dữ liệu cho category
    }

    private void AnhXa() {
        // ánh xạ
        rcCate = findViewById(R.id.rc_category); // Dùng findViewById trong Activity
    }

    private void GetCategory() {
        // Gọi Interface trong APIService
        apiService = RetrofitClient.getRetrofit().create(APIService.class);

        // Thực hiện Request bất đồng bộ
        apiService.getCategoryAll().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {

                // Kiểm tra xem request có thành công không (HTTP code 2xx)
                if (response.isSuccessful()) {
                    // Log the response body to see the raw data
                    Log.d("RetrofitResponse", "Response: " + new Gson().toJson(response.body()));

                    // nhận mảng dữ liệu từ response
                    categoryList = response.body();

                    // Khởi tạo Adapter và thiết lập cho RecyclerView
                    categoryAdapter = new CategoryAdapter(MainActivity.this, categoryList);

                    // Thiết lập cố định kích thước (tối ưu hiệu suất)
                    rcCate.setHasFixedSize(true);

                    // Thiết lập Layout Manager (Hiển thị ngang, không đảo ngược)
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                    rcCate.setLayoutManager(layoutManager);

                    // Gắn Adapter vào RecyclerView
                    rcCate.setAdapter(categoryAdapter);

                    // Thông báo Adapter rằng dữ liệu đã thay đổi
                    categoryAdapter.notifyDataSetChanged();
                } else {
                    // Xử lý lỗi từ Server (ví dụ: 404, 500)
                    int statusCode = response.code();
                    // Bạn có thể show Toast lỗi ở đây
                    Log.e("RetrofitError", "Request failed with code: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                // Xử lý lỗi kết nối mạng (Network failure)
                Log.d("logg", "Error: " + t.getMessage());
                Toast.makeText(MainActivity.this, "Lỗi kết nối mạng: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}