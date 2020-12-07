package com.e.simampuscrud.Peminjaman;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.e.simampuscrud.R;

import java.util.List;

public class PinjamAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<PinjamData> pinjamData;
    public PinjamAdapter (Activity activity, List<PinjamData> pinjamData){
        this.activity = activity;
        this.pinjamData = pinjamData;
    }
    @Override
    public int getCount() {
        return pinjamData.size();
    }

    @Override
    public Object getItem(int position) {
        return pinjamData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null){
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }
        if (convertView == null){
            convertView = inflater.inflate(R.layout.list_pinjam, null);
        }
        TextView id = (TextView) convertView.findViewById(R.id.id);
        TextView nama = (TextView) convertView.findViewById(R.id.nama);
        TextView judul = (TextView) convertView.findViewById(R.id.judul);
        TextView tgl = (TextView) convertView.findViewById(R.id.tgl);
        TextView status = (TextView) convertView.findViewById(R.id.status);
        PinjamData data = pinjamData.get(position);
        id.setText(data.getId());
        nama.setText(data.getNama_peminjam());
        judul.setText(data.getJudul_buku());
        tgl.setText(data.getTgl_pinjam());
        status.setText(data.getStatus());
        return convertView;
    }
}