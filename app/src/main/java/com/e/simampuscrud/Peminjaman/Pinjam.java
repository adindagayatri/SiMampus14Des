package com.e.simampuscrud.Peminjaman;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.e.simampuscrud.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Pinjam extends AppCompatActivity {

    ListView listView;
    AlertDialog.Builder dialog;
    FloatingActionButton fab;
    List<PinjamData> itemList = new ArrayList<PinjamData>();
    PinjamAdapter adapterpinjam;
    DBHelperPeminjaman SQLite = new DBHelperPeminjaman(this);
    public static final  String TAG_ID = "id";
    public static final  String TAG_NAME = "nama_peminjam";
    public static final  String TAG_JUDUL = "judul_buku";
    public static final  String TAG_TGL = "tgl_pinjam";
    public static final  String TAG_STATUS = "status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinjam);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SQLite = new DBHelperPeminjaman(getApplicationContext());
        listView = findViewById(R.id.list_pinjam);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Pinjam.this, AddEditPinjam.class);
                startActivity(intent);
            }
        });
        adapterpinjam = new PinjamAdapter(Pinjam.this, itemList);
        listView.setAdapter(adapterpinjam);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String idx = itemList.get(position).getId();
                final String name = itemList.get(position).getNama_peminjam();
                final String judul = itemList.get(position).getJudul_buku();
                final String tgl = itemList.get(position).getTgl_pinjam();
                final String status = itemList.get(position).getStatus();
                final CharSequence[] dialogitem = {"Edit", "Delete"};
                dialog = new AlertDialog.Builder(Pinjam.this);
                dialog.setCancelable(true);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                Intent intent = new Intent(Pinjam.this, AddEditPinjam.class);
                                intent.putExtra(TAG_ID, idx);
                                intent.putExtra(TAG_NAME, name);
                                intent.putExtra(TAG_JUDUL, judul);
                                intent.putExtra(TAG_TGL, tgl);
                                intent.putExtra(TAG_STATUS, status);
                                startActivity(intent);
                                break;

                            case 1:
                                SQLite.delete(Integer.parseInt(idx));
                                itemList.clear();
                                getAllData();
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });
        getAllData();

    }

    private void  getAllData(){
        ArrayList<HashMap<String,String>> row = SQLite.getAllData();
        for (int i = 0; i < row.size(); i++){
            String id = row.get(i).get(TAG_ID);
            String nama_peminjam = row.get(i).get(TAG_NAME);
            String judul_buku = row.get(i).get(TAG_JUDUL);
            String tgl_pinjam = row.get(i).get(TAG_TGL);
            String status = row.get(i).get(TAG_STATUS);
            PinjamData data = new PinjamData(id, nama_peminjam, judul_buku, tgl_pinjam, status);
            data.setId(id);
            data.setNama_peminjam(nama_peminjam);
            data.setJudul_buku(judul_buku);
            data.setTgl_pinjam(tgl_pinjam);
            data.setStatus(status);
            itemList.add(data);
        }
        adapterpinjam.notifyDataSetChanged();
    }

    @Override
    protected void onResume(){
        super.onResume();
        itemList.clear();
        getAllData();
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_main_buku, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        int id = item.getItemId();
        if (id == R.id.action_settings){

        }
        return super.onOptionsItemSelected(item);
    }
}