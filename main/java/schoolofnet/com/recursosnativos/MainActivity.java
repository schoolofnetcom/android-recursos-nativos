package schoolofnet.com.recursosnativos;

import android.content.Intent;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button galeriaBtn;
    private Button cameraBtn;
    private Button videoBtn;
    private Button locBtn;
    private Button vibraBtn;
    private Button telBtn;
    private Button alarmBtn;
    private Button contactBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Pegando o botão
        galeriaBtn = findViewById(R.id.galeriaBtn);
        cameraBtn = findViewById(R.id.cameraBtn);
        videoBtn = findViewById(R.id.videoBtn);
        locBtn = findViewById(R.id.locBtn);
        vibraBtn = findViewById(R.id.vibraBtn);
        telBtn = findViewById(R.id.telBtn);
        alarmBtn = findViewById(R.id.alarmBtn);
        contactBtn = findViewById(R.id.contactBtn);
        //Clique do botão galeria
        galeriaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Exemplo da galeria", Toast.LENGTH_SHORT).show();

                // Defino o meu link
                Intent intent = new Intent(getApplicationContext(),GaleriaActivity.class);
                // Vou para o link
                startActivity(intent);
            }
        });

        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),CamActivity.class);
                startActivity(intent);
            }
        });

        videoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), VideoActivity.class);
                startActivity(intent);
            }
        });

        locBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LocActivity.class);
                startActivity(intent);
            }
        });

        vibraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), VibraActitvity.class);
                startActivity(intent);
            }
        });

        telBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TelActivity.class);
                startActivity(intent);
            }
        });


        alarmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AlarmActivity.class);
                startActivity(intent);
            }
        });


        contactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NumberActivity.class);
                startActivity(intent);
            }
        });

    }
}
