package com.ali.zakatcount;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ali.zakatcount.model.Db_Helper;
import com.ali.zakatcount.model.Mdl_ZF;

public class Count_ZF extends AppCompatActivity {
    //global variable
    EditText edtJumlahKel, edtNamaKepKel;
    Button hitung,reset;
    TextView textTotalZakat;
    LinearLayout linearHasil;
    Db_Helper db_helper;

    int jumlahKel;
    static final int TAKARAN = 3;
    int totalZakat =0;
    String nKepKel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count__zf);
        edtJumlahKel = findViewById(R.id.edtJumlahKeluarga);
        edtNamaKepKel = findViewById(R.id.edtkepalaKeluarga);
        hitung = findViewById(R.id.btnHitungZakatFitr);
        reset = findViewById(R.id.btnResetZfitr);
        textTotalZakat = findViewById(R.id.txtTotalZakatF);
        linearHasil = findViewById(R.id.linearHasil);
        linearHasil.setVisibility(View.GONE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtJumlahKel.getText().toString().isEmpty()){
                    edtJumlahKel.setError("Masukan Jumlah Keluarga");
                    return;
                }

                String strjumlahKel = edtJumlahKel.getText().toString();
                jumlahKel = Integer.parseInt(strjumlahKel);

                totalZakat = jumlahKel*TAKARAN;

                textTotalZakat.setText(String.valueOf(totalZakat));
                linearHasil.setVisibility(View.VISIBLE);
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtJumlahKel.setText("");
                edtNamaKepKel.setText("");
                linearHasil.setVisibility(View.INVISIBLE);
            }
        });


    }
        //method buat aksi di toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==android.R.id.home){
            finish();
        }
        if (id==R.id.item_save){
            if (totalZakat==0){
                Toast.makeText(this, "Silakan hitung dahulu", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (edtNamaKepKel.getText().toString().isEmpty()){
                edtNamaKepKel.setError("Masukan nama KK");
                return false;
            }
            Db_Helper db_helper = new Db_Helper(Count_ZF.this);
            Mdl_ZF temp = new Mdl_ZF();
            temp.setJumlahKel(jumlahKel);
            temp.setTotalZakat(totalZakat);
            temp.setNamaKK(edtNamaKepKel.getText().toString());
            db_helper.saveDataZF(temp);
            Snackbar.make(linearHasil, "Sukses save data", Snackbar.LENGTH_SHORT).show();


        }
        return true;
    }
    //buat inflate toolbar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return true;
    }
}
