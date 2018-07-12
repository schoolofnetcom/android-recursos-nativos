package schoolofnet.com.recursosnativos;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LocActivity extends AppCompatActivity implements View.OnClickListener {

    private Button locBtn;

    private LocationManager locationManager;
    private LocationListener locationListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loc);

        locBtn = findViewById(R.id.locBtn);
        locBtn.setOnClickListener(this);

        // Carregando o gerenciador de localização
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        locationListner = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Toast.makeText(getApplicationContext(),"Lat: " + location.getLatitude() + "Long: " + location.getLongitude(),Toast.LENGTH_LONG).show();
                // "geo:47.6,-122.3" geo:latitude, longitude
                Uri loc = Uri.parse("geo:"+location.getLatitude() + "," + location.getLongitude());
                showMap(loc);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Toast.makeText(getApplicationContext(),"Por favor ligue o seu GPS", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };
    }


    void showMap(Uri location){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(location);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }

    void getLocation() {
        // Se eu não tenho a permissão
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Pergunto ao usuário seu eu posso ter
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION},1);
        }
        // Se eu já tiver a permissão
        else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 1000, locationListner);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 1){
            // Seu eu tenho permissão
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getLocation();
            }
            // Caso o usuário não me der a permissão
            else{
                Toast.makeText(getApplicationContext(),"Infelizmente você negou a permissão de localizaçã", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onClick(View view) {
        getLocation();
    }
}
