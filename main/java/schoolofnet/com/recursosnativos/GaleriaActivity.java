package schoolofnet.com.recursosnativos;

import android.Manifest;
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

public class GaleriaActivity extends AppCompatActivity implements View.OnClickListener{

    private Button selecionarBtn;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);
        selecionarBtn = findViewById(R.id.selecionarBtn);
        selecionarBtn.setOnClickListener(this);

        image = findViewById(R.id.image);
    }

    void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery,1);
    }

    @Override
    public void onClick(View view) {
        // Verificando se a permissão existe
        if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            // Pede a permissão
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }
        // Se a permissão já foi dada
        else{
            openGallery();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1){
            // Quando usuario aceita
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openGallery();
            }
            // Quando o usuario nega
            else{
                Toast.makeText(getApplicationContext(),"Infelizmente não foi possivel acessar a galeria pois você não deu permissão :(", Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Se o código for o código do itent
        if(requestCode == 1){

            // Link
            Uri imagePath = data.getData();

            try{
                // Carrego a imagem dentro do meu programa pelo o link dela
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
                // Carregando imagem na imageView
                image.setImageBitmap(bitmap);
            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }
}
