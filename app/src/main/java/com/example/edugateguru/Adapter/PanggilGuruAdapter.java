package com.example.edugateguru.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edugateguru.Models.PanggilGuru;
import com.example.edugateguru.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.List;

public class PanggilGuruAdapter extends RecyclerView.Adapter<PanggilGuruAdapter.MyViewHolder> {

    List<PanggilGuru> panggilGuruList;
    Context mContext;
    Dialog myDialog;


    public PanggilGuruAdapter(List<PanggilGuru> panggilGuruList, Context mContext) {
        this.panggilGuruList = panggilGuruList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PanggilGuru panggilGuru = panggilGuruList.get(position);
        holder.txtTime.setVisibility(View.INVISIBLE);
        myDialog = new Dialog(mContext);
        myDialog.setContentView(R.layout.dialog_panggil_guru);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        String keyKelas = panggilGuru.getKeyKelasMurid();
        initDialog(panggilGuru,keyKelas);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return panggilGuruList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtTitle, txtTime;
        CardView cvContainer;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTime = itemView.findViewById(R.id.timeNotif);
            txtTitle = itemView.findViewById(R.id.titleNotif);
            cvContainer = itemView.findViewById(R.id.cv_container_notif);
        }
    }


    private void initDialog(PanggilGuru panggilGuru, final String keyKelas) {
        final TextView tvKelas = myDialog.findViewById(R.id.txtKelasDialog);
        Button btnOk = myDialog.findViewById(R.id.btn_accept);
        DatabaseReference refKelas = FirebaseDatabase.getInstance().getReference("Kelas");
        refKelas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String namaKelas = dataSnapshot.child(keyKelas).getValue(String.class);
                tvKelas.setText("Jadwal Kelas : " + namaKelas);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });
    }
}
