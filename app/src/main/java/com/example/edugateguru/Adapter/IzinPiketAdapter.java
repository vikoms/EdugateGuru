package com.example.edugateguru.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edugateguru.Models.IzinPiket;
import com.example.edugateguru.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class IzinPiketAdapter extends RecyclerView.Adapter<IzinPiketAdapter.NotificationViewHolder> {

    List<IzinPiket> mList;
    Dialog myDialog;
    Context mContext;


    String alasanIzin ;
    String nama ;
    String keyGuru ;
    String keyMurid ;
    String status;
    String waktuIzin ;
    String idIzinPiket;
    String keyGuru_status;

    DatabaseReference dataRef;

    public IzinPiketAdapter(List<IzinPiket> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
        mContext = parent.getContext();

        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        IzinPiket izin = mList.get(position);

        holder.txtTime.setText(izin.getWaktuIzin());
        holder.txtTitle.setText("Izin Piket dari : " + izin.getNamaMurid());
        myDialog = new Dialog(mContext);
        myDialog.setContentView(R.layout.dialog_izin_piket);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        initComponentDialog(izin);

        holder.cvContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.show();
            }
        });


    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtTime;
        CardView cvContainer;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTime = itemView.findViewById(R.id.timeNotif);
            txtTitle = itemView.findViewById(R.id.titleNotif);
            cvContainer = itemView.findViewById(R.id.cv_container_notif);
        }
    }



    private void initComponentDialog(final IzinPiket izin) {

        TextView tvNama = myDialog.findViewById(R.id.tv_nama);
        TextView tvAlasan = myDialog.findViewById(R.id.tv_alasan);
        Button btnAcc = myDialog.findViewById(R.id.btnAcc);
        Button btnNo= myDialog.findViewById(R.id.btnNo);
        final ProgressBar pgConfirm = myDialog.findViewById(R.id.pg_izin_piket);
        final RelativeLayout containerBtn = myDialog.findViewById(R.id.container_btn);

        pgConfirm.setVisibility(View.INVISIBLE);

         alasanIzin = izin.getAlasanIzin();
         nama = izin.getNamaMurid();
         keyGuru = izin.getKeyGuru();
         keyMurid = izin.getKeyMurid();
         status= izin.getStatus();
         waktuIzin = izin.getWaktuIzin();
        idIzinPiket = izin.getIdIzinPiket();

        tvNama.setText(nama);
        tvAlasan.setText("Izin : "+ alasanIzin);

        dataRef = FirebaseDatabase.getInstance().getReference("Izin Piket").child(idIzinPiket);
        btnAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pgConfirm.setVisibility(View.VISIBLE);
                containerBtn.setVisibility(View.INVISIBLE);

                status = "1";
                keyGuru_status = izin.getKeyGuru()+"_1";
                        IzinPiket izinPiket = new IzinPiket(idIzinPiket,nama,waktuIzin,alasanIzin,keyGuru,status,keyMurid,keyGuru_status);
                dataRef.setValue(izinPiket).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(mContext, "Berhasil", Toast.LENGTH_SHORT).show();
                        myDialog.dismiss();

                        pgConfirm.setVisibility(View.INVISIBLE);
                        containerBtn.setVisibility(View.VISIBLE);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        pgConfirm.setVisibility(View.INVISIBLE);
                        containerBtn.setVisibility(View.VISIBLE);
                    }
                });


            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pgConfirm.setVisibility(View.VISIBLE);
                containerBtn.setVisibility(View.INVISIBLE);

                status = "2";

                keyGuru_status = izin.getKeyGuru()+"_2";
                IzinPiket izinPiket = new IzinPiket(idIzinPiket,nama,waktuIzin,alasanIzin,keyGuru,status,keyMurid,keyGuru_status);
                dataRef.setValue(izinPiket).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(mContext, "Berhasil", Toast.LENGTH_SHORT).show();
                        myDialog.dismiss();

                        pgConfirm.setVisibility(View.INVISIBLE);
                        containerBtn.setVisibility(View.VISIBLE);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        pgConfirm.setVisibility(View.INVISIBLE);
                        containerBtn.setVisibility(View.VISIBLE);
                    }
                });

            }
        });

    }

}
