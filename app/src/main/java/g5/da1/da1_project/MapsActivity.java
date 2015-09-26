package g5.da1.da1_project;

import android.content.IntentSender;
import android.location.Location;
import android.location.LocationListener;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.firebase.client.Firebase;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private static final int CONNECTION_TIME_OUT = 9000;

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private SessionManager sessionManager;
    private Firebase myFirebaseRef;
    private Firebase myFirebaseLocations;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private MarkerOptions mMarkerOptions;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        sessionManager = new SessionManager(this);
        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://shining-inferno-7431.firebaseio.com/");
        myFirebaseLocations = myFirebaseRef.child("locations");
        setUpMapIfNeeded();
        mGoogleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
        mLocationRequest = new LocationRequest().create().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY).setInterval(10000).setFastestInterval(1000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {}

    @Override
    public void onConnected(Bundle bundle) {
       Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if(location == null){
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, (com.google.android.gms.location.LocationListener) this);
        } else {
            newLocationHandler(location);
        }
    }

    private void newLocationHandler(Location location) {
        LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
        if(mMarkerOptions == null){
            myMarkerHandler(latLng);
        } else {
            mMap.clear();
            myMarkerHandler(latLng);
            otherMarkerHandler();
        }
    }

    private void myMarkerHandler(LatLng latLng) {
        if(mMarkerOptions == null){
            mMarkerOptions = new MarkerOptions().position(latLng).title("You");
            mMap.addMarker(mMarkerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(8));
        } else {
            mMarkerOptions = new MarkerOptions().position(latLng).title("You");
            mMap.addMarker(mMarkerOptions);
            mMap.animateCamera(CameraUpdateFactory.zoomTo(mMap.getCameraPosition().zoom));
        }
    }

    private void otherMarkerHandler() {

    }

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if(connectionResult.hasResolution()){
            try{
                connectionResult.startResolutionForResult(this, CONNECTION_TIME_OUT);
            } catch(IntentSender.SendIntentException ie){

            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        newLocationHandler(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {    }

    @Override
    public void onProviderEnabled(String provider) {    }

    @Override
    public void onProviderDisabled(String provider) {    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}
