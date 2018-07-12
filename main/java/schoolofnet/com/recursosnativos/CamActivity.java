package schoolofnet.com.recursosnativos;

import android.Manifest;
import android.app.WallpaperManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.net.URI;

public class CamActivity extends AppCompatActivity implements View.OnClickListener{

    private Button selectButton;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam);
        selectButton = findViewById(R.id.selecionarBtn);
        image = findViewById(R.id.image);

        selectButton.setOnClickListener(this);
    }


    void openCamera(){
        PackageManager pm = getApplicationContext().getPackageManager();

        if(pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)){

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            // Caso eu consiga abrir a camera
            if(intent.resolveActivity(getPackageManager()) != null){
                startActivityForResult(intent,1);
            }

        }else{
            Toast.makeText(getApplicationContext(),"Você não tem uma câmera disponível", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view) {
        // Verificando se a permissão existe
        if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            // Pede a permissão
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},1);
        }
        // Se a permissão já foi dada
        else{
            openCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1){
            // Quando usuario aceita
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openCamera();
            }
            // Quando o usuario nega
            else{
                Toast.makeText(getApplicationContext(),"Infelizmente não foi possivel acessar a camera para tirar um foto, pois você não deu permissão :(", Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK){
            // Recebendo imagem
            Bitmap thumbnail = data.getParcelableExtra("data");
            image.setImageBitmap(thumbnail);

            // Pegando o gerenciador padrão de papeis de parede
            WallpaperManager wm = WallpaperManager.getInstance(getApplicationContext());

            try{
                wm.setBitmap(thumbnail);
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),"Falha ao definir papel de parede", Toast.LENGTH_LONG).show();
            }

        }

    }

}
