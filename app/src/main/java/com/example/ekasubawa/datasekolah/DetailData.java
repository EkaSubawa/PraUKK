package com.example.ekasubawa.datasekolah;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailData extends AppCompatActivity {

    @BindView(R.id.imgView) ImageView mImageView;
    @BindView(R.id.textView) TextView mTextView;
    private String getNama, getImage;

    Context ctx ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);
        ButterKnife.bind(this);

        getNama = getIntent().getExtras().get("nama").toString();
        getImage = getIntent().getExtras().get("image").toString();

        Picasso.with(ctx).load(getImage).into(mImageView);
        mTextView.setText(getNama);

    }
}
