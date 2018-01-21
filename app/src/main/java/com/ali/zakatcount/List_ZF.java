package com.ali.zakatcount;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ali.zakatcount.adapter.ZF_adapter;
import com.ali.zakatcount.model.Db_Helper;
import com.ali.zakatcount.model.Mdl_ZF;

import java.util.ArrayList;

public class List_ZF extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton fabAdd;
    Db_Helper db_helper;
    ArrayList<Mdl_ZF> listTangkap;
    ZF_adapter adapter;
    //buat static instance
    public static List_ZF activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__zf);
        recyclerView= findViewById(R.id.recycleView);
        fabAdd = findViewById(R.id.floatingAdd);
        activity=this;


        //activate back button on toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Tangkap data dari database
        db_helper = new Db_Helper(List_ZF.this);
        listTangkap = db_helper.getAlldataFitr();

        //inisiasi adapter
        adapter = new ZF_adapter(listTangkap, List_ZF.this);

        //setting layout manager dan set adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(List_ZF. this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(List_ZF.this, Count_ZF.class));
                finish();
            }
        });
    }
    public void deleteFromList(Mdl_ZF mdl_zf){
        db_helper = new Db_Helper(List_ZF.this);
            //delete dari database
        db_helper.deleteDataZfitr(mdl_zf);
        //delete dari list
        listTangkap.remove(mdl_zf);
        //kabari adapter bahwa data sudah berubah
        adapter.notifyDataSetChanged();
        Snackbar.make(recyclerView, "Data berhasil dihapus",Snackbar.LENGTH_SHORT).show();
    }
}
