package com.e.simampuscrud.Peminjaman;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.e.simampuscrud.R;

public class AddEditPinjam extends AppCompatActivity {

    EditText txt_id, txt_name, txt_judul, txt_tgl, txt_status;
    Button btn_submit, btn_cancel;
    DBHelperPeminjaman SQLite = new DBHelperPeminjaman(this);
    String id, nama_peminjam, judul_buku, tgl_pinjam, status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_pinjam);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txt_id = (EditText) findViewById(R.id.txt_id);
        txt_name = (EditText) findViewById(R.id.txt_name);
        txt_judul = (EditText) findViewById(R.id.txt_judul);
        txt_tgl = (EditText) findViewById(R.id.txt_tgl);
        txt_status = (EditText) findViewById(R.id.txt_status);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        id = getIntent().getStringExtra(Pinjam.TAG_ID);
        nama_peminjam = getIntent().getStringExtra(Pinjam.TAG_NAME);
        judul_buku = getIntent().getStringExtra(Pinjam.TAG_JUDUL);
        tgl_pinjam = getIntent().getStringExtra(Pinjam.TAG_TGL);
        status = getIntent().getStringExtra(Pinjam.TAG_STATUS);
        if (id == null || id == ""){
            setTitle("Add Data");
        }
        else {
            setTitle("Edit Data");
            txt_id.setText(id);
            txt_name.setText(nama_peminjam);
            txt_judul.setText(judul_buku);
            txt_tgl.setText(tgl_pinjam);
            txt_status.setText(status);
        }

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (txt_id.getText().toString().equals("")){
                        save();
                    }
                    else {
                        edit();
                    }
                } catch (Exception e){
                    Log.e("Submit", e.toString());
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blank();
                finish();
            }
        });
    }

    @Override
    public void onBackPressed(){
        finish();
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                blank();
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void blank(){
        txt_name.requestFocus();
        txt_id.setText(null);
        txt_name.setText(null);
        txt_judul.setText(null);
        txt_tgl.setText(null);
        txt_status.setText(null);

    }

    private void save(){
        if (String.valueOf(txt_name.getText()).equals("") ||
                String.valueOf(txt_name.getText()).equals(null) ||
                String.valueOf(txt_judul.getText()).equals("") ||
                String.valueOf(txt_judul.getText()).equals(null) ||
                String.valueOf(txt_tgl.getText()).equals(null) ||
                String.valueOf(txt_tgl.getText()).equals("") ||
                String.valueOf(txt_status.getText()).equals(null) ||
                String.valueOf(txt_status.getText()).equals("")) {
            Toast.makeText(getApplicationContext(), "Please input the new data", Toast.LENGTH_SHORT).show();
        }

        else {
            SQLite.insert(txt_name.getText().toString().trim(),
                    txt_judul.getText().toString().trim(),
                    txt_tgl.getText().toString().trim(),
                    txt_status.getText().toString().trim());
            blank();
            finish();
        }
    }

    private void edit(){
        if (String.valueOf(txt_name.getText()).equals("") ||
                String.valueOf(txt_name.getText()).equals(null) ||
                String.valueOf(txt_judul.getText()).equals("") ||
                String.valueOf(txt_judul.getText()).equals(null) ||
                String.valueOf(txt_tgl.getText()).equals(null) ||
                String.valueOf(txt_tgl.getText()).equals("") ||
                String.valueOf(txt_status.getText()).equals(null) ||
                String.valueOf(txt_status.getText()).equals("")) {
            Toast.makeText(getApplicationContext(), "Please input the new data", Toast.LENGTH_SHORT).show();
        }

        else {
            SQLite.update(Integer.parseInt(txt_id.getText().toString().trim()),
                    txt_name.getText().toString().trim(), txt_judul.getText().toString().trim(),
                    txt_tgl.getText().toString().trim(), txt_status.getText().toString().trim());
            blank();
            finish();
        }
    }
}