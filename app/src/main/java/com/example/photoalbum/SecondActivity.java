package com.example.photoalbum;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.photoalbum.databinding.ActivitySecondBinding;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    private ActivitySecondBinding binding;
    private ArrayList<Bitmap> arrayPhoto = new ArrayList<>();
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.secondTB);
        getSupportActionBar().setTitle("Альбом фотографий");

        loadPhotos();

        binding.secondIV.setImageResource(R.drawable.photo1);

        binding.nextPhotoBTN.setOnClickListener(v -> {
            showNextPhoto();
            if (index < arrayPhoto.size() - 1) {
                index++;
            }
        });

        binding.doneBTN.setOnClickListener(v -> {
            Intent intent = new Intent(this, FinalActivity.class);
            startActivity(intent);
            finish();
        });

    }

    private void loadPhotos() {
        Resources resources = getResources();
        String packageName = getPackageName();

        String[] photos = {"photo2", "photo3", "photo4", "photo5"};

        for (String e : photos) {
            try {
                int id = resources.getIdentifier(e, "drawable", packageName);

                if (id != 0) {
                    Bitmap bitmap = BitmapFactory.decodeResource(resources, id);
                    arrayPhoto.add(bitmap);
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private void showNextPhoto() {
        if (arrayPhoto.isEmpty()) return;
        binding.secondIV.setImageBitmap(arrayPhoto.get(index));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.exit) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}