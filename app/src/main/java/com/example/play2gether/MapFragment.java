package com.example.play2gether;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment {


    private final LatLng COURT = new LatLng(51.085484, 17.021309);

    private Marker markerCourt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.google_map);


        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                // when map is loaded
                MarkerOptions markerDef = new MarkerOptions();
                // Default point on camera
                CameraUpdate point = CameraUpdateFactory.newLatLngZoom(new LatLng(51.103947, 17.034383),13);
                // moves camera to coordinates
                googleMap.moveCamera(point);
                // animates camera to coordinates
                googleMap.animateCamera(point);

                // CLEARING MAP AND RECOVERY
                mapClearingAndRestorationMarks(googleMap);

                googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener(){
                    @Override
                    public void onMapLongClick(@NonNull LatLng latLng) {
                        // CLEARING MAP AND RECOVERY
                        mapClearingAndRestorationMarks(googleMap);
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                latLng, 13
                        ));
                    }
                });

                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull LatLng latLng) {       // when clicked on map
                        // initialize marker options
                        MarkerOptions markerOptions = new MarkerOptions();
                        // set position of marker
                        markerOptions.position(latLng);
                        // set title of marker
                        markerOptions.title(latLng.latitude + ":" + latLng.longitude);
                        // CLEARING MAP AND RECOVERY
                        mapClearingAndRestorationMarks(googleMap);
                        // animating to zoom the marker
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                latLng, 15
                        ));
                        // add marker on map
                        googleMap.addMarker(markerOptions);
                    }
                });


            }
        });
        return view;
    }

    void mapClearingAndRestorationMarks(GoogleMap googleMap){
        googleMap.clear();
        // FIRST MARK (BLUE)
        markerCourt = googleMap.addMarker(new MarkerOptions()
                .position(COURT)
                .title("Boisko")
                .snippet("Otwarte 12:00-20:00")
                .icon(BitmapDescriptorFactory.defaultMarker(210))
        );
        markerCourt.setTag(0);
    }

}