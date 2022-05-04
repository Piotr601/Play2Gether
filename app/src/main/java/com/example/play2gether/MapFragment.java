package com.example.play2gether;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.LocationManager;
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
                // WHEN MAP IS LOADED

                // Default point on camera
                // Declaring point - default point (to change)
                // Moves camera to coordinates
                // Animates camera to coordinates
                MarkerOptions markerDef = new MarkerOptions();
                CameraUpdate point = CameraUpdateFactory.newLatLngZoom(new LatLng(51.103947, 17.034383),13);
                googleMap.moveCamera(point);
                googleMap.animateCamera(point);

                // TODO: CREATE A FUNCTION TO READING A CURRENT USER LOCATION
                // TODO: AND SHOWING ON MAP, DEPENDS ON WHERE USER IS THERE ARE A MARKER ON USER ACTUAL POSITION
                // To implement current location
                //LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

                // CLEARING MAP AND RECOVERY
                mapClearing(googleMap);
                mapRestorationMarks(googleMap);

                googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener(){
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
                    public void onMapClick(@NonNull LatLng latLng) {       // when clicked on map
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
            }
        });
        return view;
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
                .title("Boisko do piłki nożnej")
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