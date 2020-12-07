package com.e.simampuscrud;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerViewMenu;
    List<Integer> logoMenu = new ArrayList<>();
    List<String> namaMenu = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialized();
    }

    private void initialized(){
        recyclerViewMenu = (RecyclerView)findViewById(R.id.recycler_view_menu);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerViewMenu.setLayoutManager(gridLayoutManager);
        logoMenu.add(R.drawable.ic_baseline_person_add_24);
        logoMenu.add(R.drawable.ic_baseline_menu_book_24);
        logoMenu.add(R.drawable.ic_baseline_save_24);

        namaMenu.add("Menu Member");
        namaMenu.add("Menu Buku");
        namaMenu.add("Menu Pinjam");
        recyclerViewMenu.setAdapter(new MenuAdapter(this,logoMenu,namaMenu));
    }
}