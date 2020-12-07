package com.e.simampuscrud;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.simampuscrud.Buku.Buku;
import com.e.simampuscrud.Member.Member;
import com.e.simampuscrud.Peminjaman.Pinjam;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuView> {
    List<Integer> logoMenu = new ArrayList<>();
    List<String> namaMenu = new ArrayList<>();
    Context context;

    public MenuAdapter (Context ct, List<Integer> logoMenu, List<String> namaMenu){
        this.logoMenu = logoMenu;
        this.namaMenu = namaMenu;
        this.context = ct;
    }

    @NonNull
    @Override
    public MenuView onCreateViewHolder (@NonNull ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_menu, viewGroup, false);
        return  new MenuView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MenuView menuView, final int position) {

        menuView.logo.setImageResource(logoMenu.get(position));
        menuView.nama.setText(namaMenu.get(position));
    }

    @Override
    public int getItemCount() {

        return logoMenu.size();
    }

    public class MenuView extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView logo;
        TextView nama;
        LinearLayout linear;

        public MenuView(@NonNull View itemView) {
            super(itemView);

            logo = (ImageView)itemView.findViewById(R.id.logo_menu);
            nama = (TextView)itemView.findViewById(R.id.nama_menu);
            linear = (LinearLayout)itemView.findViewById(R.id.linear);

            itemView.setOnClickListener(this);

        }

        public void onClick (View v){
            final Intent intent;
            switch (getAdapterPosition()){
                case 0:
                    intent = new Intent(context, Member.class);
                    break;

                case 1:
                    intent = new Intent(context, Buku.class);
                    break;

                case 2:
                    intent = new Intent(context, Pinjam.class);
                    break;

                default:
                    intent = new Intent(context, MainActivity.class);
                    break;
            }
            context.startActivity(intent);
        }
    }
}