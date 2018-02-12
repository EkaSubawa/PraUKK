package com.example.ekasubawa.datasekolah;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ekasubawa.datasekolah.model.Desa;
import com.example.ekasubawa.datasekolah.model.Sekolah;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Dashboard extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private FloatingActionButton btnAdd;
    @BindView(R.id.recycleView) RecyclerView mRecycleView;

    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference db_desa = mDatabase.getReference().child("Alamat").child("Desa");
    private DatabaseReference db_sekolah = mDatabase.getReference().child("Sekolah");

    FirebaseRecyclerAdapter<Sekolah,SekolahViewHolder> firebaseRecyclerAdapter;
    private List<Sekolah> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnAdd = (FloatingActionButton) findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add = new Intent(Dashboard.this, tambah_sekolah.class);
                startActivity(add);
            }
        });

        LinearLayoutManager horizontalLayoutManagaer=new LinearLayoutManager(Dashboard.this,LinearLayoutManager.VERTICAL,false);
        mRecycleView.setLayoutManager(horizontalLayoutManagaer);
        db_sekolah.keepSynced(true);

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Sekolah, SekolahViewHolder>(
                Sekolah.class,
                R.layout.sekolah_view,
                SekolahViewHolder.class,
                db_sekolah

        ) {
            @Override
            protected void populateViewHolder(SekolahViewHolder viewHolder, final Sekolah model, int position) {
                viewHolder.setNama(model.getNama());
                viewHolder.setAlamat(model.getAlamat());
                viewHolder.setImage(getApplicationContext(), model.getImage());

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Dashboard.this, DetailData.class);
                        intent.putExtra("nama", model.getNama());
                        intent.putExtra("image", model.getImage());

                        startActivity(intent);

                    }
                });
            }
        };

        mRecycleView.setAdapter(firebaseRecyclerAdapter);

    }

    public static class SekolahViewHolder extends RecyclerView.ViewHolder{

        View mView;
        TextView post_nama, post_alamat;
        ImageView mImg;

        public SekolahViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setNama(String nama){
            post_nama = (TextView) mView.findViewById(R.id.nama);
            post_nama.setText(nama);
        }
        public void setAlamat(String alamat){
            post_alamat = (TextView) mView.findViewById(R.id.alamat);
            post_alamat.setText(alamat);
        }
        public void setImage(Context ctx, String image){
            mImg = (ImageView) mView.findViewById(R.id.image);
            Picasso.with(ctx).load(image).into(mImg);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)){

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
