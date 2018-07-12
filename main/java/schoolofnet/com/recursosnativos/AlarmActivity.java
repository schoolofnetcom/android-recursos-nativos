package schoolofnet.com.recursosnativos;

import android.content.Intent;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AlarmActivity extends AppCompatActivity {


    private Button setAlarmBtn;
    private EditText hourText, minutesText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);


        setAlarmBtn = findViewById(R.id.setAlarmBtn);
        hourText =  findViewById(R.id.hoursText);
        minutesText = findViewById(R.id.mituesText);


        setAlarmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int hour = Integer.parseInt(hourText.getText().toString());
                int minutes = Integer.parseInt(minutesText.getText().toString());

                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM).
                        putExtra(AlarmClock.EXTRA_MESSAGE,"Aplicativo: Rcursos nativos").
                        putExtra(AlarmClock.EXTRA_HOUR,hour).
                        putExtra(AlarmClock.EXTRA_MINUTES, minutes).
                        putExtra(AlarmClock.EXTRA_SKIP_UI, true);

                // Se o alarme estiver disponivel
                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                }

            }
        });


    }
}
