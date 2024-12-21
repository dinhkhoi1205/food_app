package asset;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import com.example.food_app_2.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.button.MaterialButton;

import java.io.IOException;
import java.util.List;

import bottom_nav_fragment.home_screen;

public class Map extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener,
GoogleMap.OnMarkerDragListener{

    private GoogleMap googleMap;

    private Geocoder geocoder;
    private final int LOCATION_PERMISSION_REQUEST_CODE = 10001;
    private SearchView mapSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.id_map);
        mapSearchView = findViewById(R.id.map_search_view);
        MaterialButton chooseLocationButton = findViewById(R.id.btn_choose_location);
        assert mapFragment != null;

        geocoder = new Geocoder(this);
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        mapSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = mapSearchView.getQuery().toString();
                List<Address> addresses = null;
                Geocoder geocoder = new Geocoder(Map.this);

                try{
                    addresses = geocoder.getFromLocationName(location,1);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                if (addresses == null || addresses.isEmpty()) {
                    Toast.makeText(Map.this, "Location not found", Toast.LENGTH_SHORT).show();
                    return false;
                }
                Address address = addresses.get(0);
                LatLng latLng = new LatLng(address.getLatitude(),address.getLongitude());
                runOnUiThread(() ->{
                    googleMap.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title(location));
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
                });

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        chooseLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng currentLocation = googleMap.getCameraPosition().target;
                getAddressed(currentLocation);
            }
        });

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap map) {
        googleMap = map;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.setOnMapLongClickListener(this);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            enableUserLocation();
        } else{
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        }

        LatLng vietnamLatLng = new LatLng(10.8231, 106.6297);
        googleMap.addMarker(new MarkerOptions()
                .position(vietnamLatLng)
                .title(" "));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(vietnamLatLng, 12));
    }

    private void enableUserLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            try {
                googleMap.setMyLocationEnabled(true);
            } catch (SecurityException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
             geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1, new Geocoder.GeocodeListener() {
                @Override
                public void onGeocode(@NonNull List<Address> addresses) {
                    if (!addresses.isEmpty()) {
                        Address address = addresses.get(0);
                        String streetAddress = address.getAddressLine(0);
                        runOnUiThread(() -> {
                            googleMap.addMarker(new MarkerOptions().
                                    position(latLng).
                                    title(streetAddress).
                                    draggable(true));
                        });
                    }
                }
        });
    }

    @Override
    public void onMarkerDrag(@NonNull Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(@NonNull Marker marker) {
        LatLng latLng = marker.getPosition();
            geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1, new Geocoder.GeocodeListener() {
                @Override
                public void onGeocode(@NonNull List<Address> addresses) {
                    if(!addresses.isEmpty()){
                        Address address = addresses.get(0);
                        String streetAddress = address.getAddressLine(0);
                        marker.setTitle(streetAddress);
                    }
                }
            });
    }

    @Override
    public void onMarkerDragStart(@NonNull Marker marker) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == LOCATION_PERMISSION_REQUEST_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                enableUserLocation();
            }
        }
    }

    private void getAddressed(LatLng latLng){
        Geocoder geocoder = new Geocoder(this);
             geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1, new Geocoder.GeocodeListener() {
                @Override
                public void onGeocode(@NonNull List<Address> addresses) {
                    if (!addresses.isEmpty()) {
                        Address address = addresses.get(0);
                        String street = address.getAddressLine(0);
                        String city = address.getLocality();
                        String adminArea = address.getAdminArea();

                        // Only append city if it's unique
                        String shortAddress = street + ", " + (city != null && !city.equals(adminArea) ? city : ""); // Short address
                        sendLocation(shortAddress);
                    }
                }
            });
    }

    private void sendLocation(String shortAddress){
        Intent intent = new Intent();
        intent.putExtra("short_address", shortAddress);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
