package schoolofnet.com.recursosnativos;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TelActivity extends AppCompatActivity implements View.OnClickListener {

    private Button discBtn, callBtn;
    private EditText numberField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tel);

        discBtn = findViewById(R.id.discBtn);
        discBtn.setOnClickListener(this);
        callBtn = findViewById(R.id.callBtn);
        callBtn.setOnClickListener(this);
        numberField = findViewById(R.id.numberField);
    }


    void call(Uri tel) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(tel);
        if (intent.resolveActivity(getPackageManager()) != null) {
            // Se não tiver a permissão
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // Pergunte
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},1);
            }
            // Se a permissão já foi dada
            else{
                startActivity(intent);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                // Define o numero
                Uri tel = Uri.parse("tel:"+numberField.getText().toString());
                call(tel);
            }else{
                Toast.makeText(getApplicationContext(),"Infelizmente não foi possível fazer a ligação, pois você não deu permissão",Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public void onClick(View view) {
        // Verificando se o discBtn
        if(view.getId() == R.id.discBtn){
            // Define o numero
            Uri tel = Uri.parse("tel:"+numberField.getText().toString());
            // Cria um intent para discagem
            Intent intent = new Intent(Intent.ACTION_DIAL);
            // Passsa o numero como parametro
            intent.setData(tel);

            // Se é possível abrir o discador
            if(intent.resolveActivity(getPackageManager()) != null){
                //Disque
                startActivity(intent);
            }

        }
        // Verficando se é o callBtn
        else if(view.getId() == R.id.callBtn){
            // Define o numero
            Uri tel = Uri.parse("tel:"+numberField.getText().toString());
            call(tel);
        }
    }
}
