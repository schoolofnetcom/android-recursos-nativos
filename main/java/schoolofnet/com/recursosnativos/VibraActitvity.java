package schoolofnet.com.recursosnativos;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class VibraActitvity extends AppCompatActivity {


    private Button vibraBtn;
    private EditText vibraTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vibra_actitvity);


        vibraBtn = findViewById(R.id.vibraBtn);
        vibraTimer = findViewById(R.id.vibraTime);

        vibraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tempo de vibração
                int timer =  Integer.parseInt(vibraTimer.getText().toString());

                // Carregando o vibrator
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                // For maior do que a versão 8
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    v.vibrate(VibrationEffect.createOneShot(timer,VibrationEffect.DEFAULT_AMPLITUDE));
                }else{
                    v.vibrate(timer);
                }
            }
        });


    }
}
