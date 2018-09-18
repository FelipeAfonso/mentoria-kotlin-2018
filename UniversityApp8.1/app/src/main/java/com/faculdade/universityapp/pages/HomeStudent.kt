package com.faculdade.universityapp.pages

import android.Manifest
import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.TextView
import com.faculdade.universityapp.R
import com.faculdade.universityapp.bd.EstudanteBDHelper
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.activity_home_student.*


class HomeStudent : AppCompatActivity(), GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, android.location.LocationListener {

    private var mLatitudeTextView : TextView? = null
    private var mLongitudeTextView : TextView? = null
    private var mGoogleApiClient : GoogleApiClient? = null
    private var mLocation : Location? = null
    private var mLocationManager : LocationManager? = null

    private var mLocationRequest : LocationRequest? = null
    private val listener : com.google.android.gms.location.LocationListener? = null
    private val UPDATE_INTERVAL = (2 * 1000).toLong()  /* 10 secs */
    private val FASTEST_INTERVAL : Long = 2000 /* 2 sec */

    private var locationManager : LocationManager? = null

    private val isLocationEnabled: Boolean
        get() {
            locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            return locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager!!.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        }

    @SuppressLint("MissingPermission")
    override fun onConnected(p0: Bundle?) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            return
        }
        startLocationUpdates()
    }

    override fun onConnectionSuspended(p0: Int) {
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
    }

    override fun onLocationChanged(location: Location?) {
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
    }

    override fun onProviderEnabled(provider: String?) {
    }

    override fun onProviderDisabled(provider: String?) {
    }

    private fun checkLocation(): Boolean {
        if (!isLocationEnabled)
            showAlert()
        return isLocationEnabled
    }

    private fun showAlert() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Ativar localização")
                .setMessage("Sua localização está desativada.\nPor favor, ative a localização para " + "usar esse aplicativo")
                .setPositiveButton("Ativar") { paramDialogInterface, paramInt ->
                    val myIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    startActivity(myIntent)
                }
                .setNegativeButton("Cancelar") { paramDialogInterface, paramInt -> }
        dialog.show()
    }

    @SuppressLint("MissingPermission")
    protected fun startLocationUpdates() {
        // Create the location request
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL)
                .setFastestInterval(FASTEST_INTERVAL)
        // Request location updates
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        /*
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,

                mLocationRequest, this)*/

        //LocationServices.getFusedLocationProviderClient(this).requestLocationUpdates(mLocationRequest, LocationCallback().)


        Log.d("reque", "--->>>>")
    }

    lateinit var EstudanteBDHelper : EstudanteBDHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_student)

        mLatitudeTextView = findViewById(R.id.latitude_tv) as TextView
        mLongitudeTextView = findViewById(R.id.longitude_tv) as TextView

        mGoogleApiClient = GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build()
        mLocationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        Log.d("gggg", "uooo")
        checkLocation()

        EstudanteBDHelper = EstudanteBDHelper(this)

        var student = EstudanteBDHelper.lerEstudante(1)
        student.forEach {
            nomeStudent.text = it.nome
            nascimentoStudent.text = it.nascimento
            ingressoStudent.text = it.ingresso.toString()
            var cursado = (2018-it.ingresso)/2
            cursadoStudent.text = cursado.toString()
        }

        cadDisciplina_button.setOnClickListener {
        val i = Intent(applicationContext, Disciplinas::class.java)
        startActivity(i)
        }
        }
}
