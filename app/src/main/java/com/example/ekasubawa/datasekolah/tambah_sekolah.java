package com.example.ekasubawa.datasekolah;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.UriMatcher;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ekasubawa.datasekolah.model.Desa;
import com.example.ekasubawa.datasekolah.model.Sekolah;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.lang.reflect.Array;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class tambah_sekolah extends AppCompatActivity {

    private static final String TAG = tambah_sekolah.class.getSimpleName();

    @BindView(R.id.spinner) Spinner mSpinner;
    @BindView(R.id.nama) EditText mNama;
    @BindView(R.id.nss) EditText mNss;
    @BindView(R.id.alamat) EditText mAlamat;
    @BindView(R.id.rw) EditText mRw;
    @BindView(R.id.rt) EditText mRt;
    @BindView(R.id.no_telp) EditText mNo_telp;
    @BindView(R.id.no_fax) EditText mNo_fax;
    @BindView(R.id.email) EditText mEmail;
    @BindView(R.id.website) EditText mWebsite;
    @BindView(R.id.kepala_sekolah) EditText mKepala_sekolah;
    @BindView(R.id.btnTambah) Button mbtnTambah;
    @BindView(R.id.btnImg) ImageButton mbtnImg;

    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference mStorageReference = storage.getReference();
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference dbSekolah = database.getReference("Sekolah");
    private DatabaseReference dbDesa = database.getReference("Alamat").child("Desa");
    private ArrayAdapter<String> arrayAdapter;

    private ProgressDialog mProgressDialog;
    private String getSpinner;

    private Uri mImageUri = null;
    private static final int GALERY_REQUEST = 1;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_sekolah);
        getSupportActionBar().hide();
        ButterKnife.bind(this);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Menambahkan...");

        mbtnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALERY_REQUEST);
            }
        });

        dbDesa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<String> list = new ArrayList<>();
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    Desa desa = ds.getValue(Desa.class);
                    list.add(desa.getNama());
                }

                arrayAdapter = new ArrayAdapter<String>(tambah_sekolah.this, android.R.layout.simple_spinner_item, list);
                arrayAdapter.setDropDownViewResource(R.layout.spinner);
                mSpinner.setAdapter(arrayAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mbtnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tambahkan();
            }
        });

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getSpinner = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void tambahkan() {

        final String nama = mNama.getText().toString();
        final String nss = mNss.getText().toString();
        final String alamat = mAlamat.getText().toString();
        final String rw = mRw.getText().toString();
        final String rt = mRt.getText().toString();
        final String no_telp = mNo_telp.getText().toString();
        final String no_fax = mNo_fax.getText().toString();
        final String email = mEmail.getText().toString();
        final String website = mWebsite.getText().toString();
        final String kepala_sekolah = mKepala_sekolah.getText().toString();

        if (TextUtils.isEmpty(nama)
                && TextUtils.isEmpty(nss)
                && TextUtils.isEmpty(alamat)
                && TextUtils.isEmpty(rw)
                && TextUtils.isEmpty(rt)
                && TextUtils.isEmpty(no_telp)
                && TextUtils.isEmpty(no_fax)
                && TextUtils.isEmpty(email)
                && TextUtils.isEmpty(website)
                && TextUtils.isEmpty(kepala_sekolah)){

            Toast.makeText(this, R.string.form_kosong, Toast.LENGTH_LONG).show();

        }

        else if (TextUtils.isEmpty(nama)){
            Toast.makeText(this, R.string.nama_sekolah, Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(nss)){
            Toast.makeText(this, R.string.nss, Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(alamat)){
            Toast.makeText(this, R.string.alamat, Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(rw)){
            Toast.makeText(this, R.string.rw, Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(rt)){
            Toast.makeText(this, R.string.rt, Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(no_telp)){
            Toast.makeText(this, R.string.no_telp, Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(no_fax)){
            Toast.makeText(this, R.string.no_fax, Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(email)){
            Toast.makeText(this, R.string.email, Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(website)){
            Toast.makeText(this, R.string.website, Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(kepala_sekolah)){
            Toast.makeText(this, R.string.kepala_sekolah, Toast.LENGTH_LONG).show();
        }

        if (!TextUtils.isEmpty(nama)
                    && !TextUtils.isEmpty(nss)
                    && !TextUtils.isEmpty(alamat)
                    && !TextUtils.isEmpty(rw)
                    && !TextUtils.isEmpty(rt)
                    && !TextUtils.isEmpty(no_telp)
                    && !TextUtils.isEmpty(no_fax)
                    && !TextUtils.isEmpty(email)
                    && !TextUtils.isEmpty(website)
                    && !TextUtils.isEmpty(kepala_sekolah)
                    && mImageUri != null){
            mProgressDialog.show();

            StorageReference filepatch = mStorageReference.child("Sekolah").child(mImageUri.getPath());
            filepatch.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>(){

                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(tambah_sekolah.this, "Upload Data Berhasil", Toast.LENGTH_LONG).show();

                    Uri uriDownload = taskSnapshot.getDownloadUrl();
                    tambahkan_baru(nama,nss,alamat,getSpinner,rw,rt,no_telp,no_fax,email,website,kepala_sekolah,uriDownload.toString());

                }
            });

        }


    }

    private void tambahkan_baru(String nama, String nss, String alamat, String getSpinner, String rw, String rt, String no_telp, String no_fax, String email, String website, String kepala_sekolah, String uriDownload) {

        String uId = dbSekolah.push().getKey();

        Sekolah sekolah = new Sekolah(nama,nss,alamat,getSpinner,rw,rt,no_telp,no_fax,email,website,kepala_sekolah,uriDownload);

        dbSekolah.child(uId).setValue(sekolah).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(tambah_sekolah.this, Dashboard.class));
                    Toast.makeText(tambah_sekolah.this, R.string.berhasil, Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(tambah_sekolah.this, R.string.gagal_menambahkan, Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALERY_REQUEST && resultCode == RESULT_OK){
            mImageUri = data.getData();
            mbtnImg.setImageURI(mImageUri);
        }

    }
}
