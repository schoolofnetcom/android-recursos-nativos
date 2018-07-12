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
import android.widget.Toast;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity implements View.OnClickListener{

    private Button selecionarBtn;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        selecionarBtn = findViewById(R.id.selecionarBtn);
        videoView = findViewById(R.id.video);

        selecionarBtn.setOnClickListener(this);

    }

    void openVideoGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
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
            openVideoGallery();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1){
            // Quando usuario aceita
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openVideoGallery();
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

        if(requestCode == 1 && resultCode == RESULT_OK){
            // Pego o caminho do video selecionado
            Uri videoPath = data.getData();
            // Eu carrego o video no player
            videoView.setVideoURI(videoPath);
            // Inicio o video
            videoView.start();
        }
    }
}
