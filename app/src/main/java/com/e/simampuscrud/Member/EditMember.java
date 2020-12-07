package com.e.simampuscrud.Member;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.e.simampuscrud.Buku.DBHelperBuku;
import com.e.simampuscrud.Buku.EditBuku;
import com.e.simampuscrud.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditMember extends AppCompatActivity {
    DBHelperMember helper;
    EditText TxKodeMember, TxNamaMember, TxTempatLahir, TxTglLahir, TxAlamat, TxNoTelp;
    Spinner SpJK;
    long id;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_member);

        helper = new DBHelperMember(this);

        id = getIntent().getLongExtra(DBHelperBuku.COLUMN_4, 0);

        TxKodeMember = (EditText)findViewById(R.id.txKode_Member_Edit);
        TxNamaMember = (EditText)findViewById(R.id.txNama_Edit);
        TxTempatLahir = (EditText)findViewById(R.id.txTempat_Lahir_Edit);
        TxTglLahir = (EditText)findViewById(R.id.txTanggal_Lahir_Edit);
        TxAlamat = (EditText)findViewById(R.id.txAlamat_Edit);
        TxNoTelp = (EditText)findViewById(R.id.txNo_Telepon_Edit);
        SpJK = (Spinner)findViewById(R.id.spJenis_Kelamin_Edit);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        TxTglLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });
        getData();
    }

    private void showDateDialog() {
        Calendar calendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                TxTglLahir.setText(dateFormatter.format(newDate.getTime()));
            }
        },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void getData(){
        Cursor cursor = helper.oneData(id);
        if(cursor.moveToFirst()){
            String kodeMember = cursor.getString(cursor.getColumnIndex(DBHelperMember.COLUMN_5));
            String namaMember = cursor.getString(cursor.getColumnIndex(DBHelperMember.COLUMN_6));
            String tempatLahir = cursor.getString(cursor.getColumnIndex(DBHelperMember.COLUMN_7));
            String tglLahir = cursor.getString(cursor.getColumnIndex(DBHelperMember.COLUMN_8));
            String jenisKelamin = cursor.getString(cursor.getColumnIndex(DBHelperMember.COLUMN_9));
            String alamat = cursor.getString(cursor.getColumnIndex(DBHelperMember.COLUMN_10));
            String noTelp = cursor.getString(cursor.getColumnIndex(DBHelperMember.COLUMN_11));

            TxKodeMember.setText(kodeMember);
            TxNamaMember.setText(namaMember);
            TxTempatLahir.setText(tempatLahir);
            TxTglLahir.setText(tglLahir);

            if (jenisKelamin.equals("Laki-Laki")){
                SpJK.setSelection(0);
            }else if(jenisKelamin.equals("Perempuan")){
                SpJK.setSelection(1);
            }

            TxAlamat.setText(alamat);
            TxNoTelp.setText(noTelp);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_edit:
                String kodeMember = TxKodeMember.getText().toString().trim();
                String namaMember = TxNamaMember.getText().toString().trim();
                String tempatLahir = TxTempatLahir.getText().toString().trim();
                String tglLahir = TxTglLahir.getText().toString().trim();
                String alamat = TxAlamat.getText().toString().trim();
                String noTelp = TxNoTelp.getText().toString().trim();
                String jenisKelamin = SpJK.getSelectedItem().toString().trim();

                ContentValues values = new ContentValues();
                values.put(DBHelperMember.COLUMN_5, kodeMember);
                values.put(DBHelperMember.COLUMN_6, namaMember);
                values.put(DBHelperMember.COLUMN_7, tempatLahir);
                values.put(DBHelperMember.COLUMN_8, tglLahir);
                values.put(DBHelperMember.COLUMN_9, jenisKelamin);
                values.put(DBHelperMember.COLUMN_10, alamat);
                values.put(DBHelperMember.COLUMN_11, noTelp);

                if (kodeMember.equals("") || namaMember.equals("") ||
                        tempatLahir.equals("") || tglLahir.equals("") ||
                        alamat.equals("") || noTelp.equals("")
                ){
                    Toast.makeText(EditMember.this, "Data Member Tidak Boleh Kosong !", Toast.LENGTH_SHORT).show();
                }else{
                    helper.updateData(values, id);
                    Toast.makeText(EditMember.this, "Data Member Berhasil Di Edit !", Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
        switch (item.getItemId()){
            case R.id.delete_edit:
                AlertDialog.Builder builder = new AlertDialog.Builder(EditMember.this);
                builder.setMessage("Hapus Data Member ?");
                builder.setCancelable(true);
                builder.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        helper.deleteData(id);
                        Toast.makeText(EditMember.this, "Data Member Berhasil Di Hapus !", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}