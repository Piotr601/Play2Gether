package com.example.play2gether;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import com.google.android.gms.location.LocationRequest;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapFragment extends Fragment {

    GoogleMap googleMap;
    ImageView my_location;
    double lat = 51.103947, lng = 17.034383;

    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_map, container, false);
        my_location = (ImageView) view.findViewById(R.id.my_location);


        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.google_map);

        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                // WHEN MAP IS LOADED

                // Default point on camera
                // Declaring point - default point (to change)
                // Moves camera to coordinates
                // Animates camera to coordinates
                MarkerOptions markerDef = new MarkerOptions();
                CameraUpdate point = CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 13);
                googleMap.moveCamera(point);
                googleMap.animateCamera(point);

                // CLEARING MAP AND RECOVERY
                mapClearing(googleMap);
                mapRestorationMarks(googleMap);


                googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                    @Override
                    public void onMapLongClick(@NonNull LatLng latLng) {
                        // CLEARING MAP AND RECOVERY
                        mapClearing(googleMap);
                        mapRestorationMarks(googleMap);

                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                latLng, 13
                        ));
                    }
                });

                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull LatLng latLng) {
                        mapClearing(googleMap);
                        mapRestorationMarks(googleMap);

                        // when clicked on map
                        // initialize marker options
                        MarkerOptions markerOptions = new MarkerOptions();
                        // set position of marker
                        markerOptions.position(latLng);
                        // set title of marker
                        markerOptions.title(latLng.latitude + ":" + latLng.longitude);
                        // CLEARING MAP AND RECOVERY
                        mapClearing(googleMap);
                        mapRestorationMarks(googleMap);
                        // animating to zoom the marker
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                latLng, 15
                        ));
                        // add marker on map
                        googleMap.addMarker(markerOptions);
                    }
                });

                my_location.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mapClearing(googleMap);
                        mapRestorationMarks(googleMap);

                        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                                ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                            getLocation();

                            CameraUpdate point = CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 13);
                            LatLng temp  = new LatLng(lat, lng);
                            MarkerOptions markerOptions = new MarkerOptions();
                            markerOptions.position(temp);
                            markerOptions.title("Twoja pozycja");
                            googleMap.addMarker(markerOptions);
                            googleMap.animateCamera(point);
                        } else {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 44);
                        }
                    }
                });
            }
        });
        return view;
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        else
        {
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null) {
                    try {
                        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
                        List<Address> adressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        lat = adressList.get(0).getLatitude();
                        lng = adressList.get(0).getLongitude();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        }
    }

    private void mapRestorationMarks(GoogleMap googleMap){

        // List of Objects
        final LatLng COURT  = new LatLng(51.085484, 17.021309);
        final LatLng COURT1 = new LatLng(51.089322, 17.036011);

        // Markers which will be printed on map
        Marker markerCourt;
        Marker markerCourt1;

        // FIRST MARK (BLUE)
        markerCourt = googleMap.addMarker(new MarkerOptions()
                .position(COURT)
                .title("Młodzieżowe Centrum Sportu")
                .snippet("Otwarte 12:00-20:00")
                .icon(BitmapDescriptorFactory.defaultMarker(210))
        );
        markerCourt1 = googleMap.addMarker(new MarkerOptions()
                .position(COURT1)
                .title("Boisko do koszykówki")
                .snippet("Otwarte całodobowo")
                .icon(BitmapDescriptorFactory.defaultMarker(30))
        );

        // not used for now
        //markerCourt.setTag(0);
    }
    private void mapClearing(GoogleMap googleMap) {
        googleMap.clear();
    }

}