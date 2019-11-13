package br.com.impacta.kotlin.mylocation

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(),
    OnMapReadyCallback,
    GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener {

    private val TAG = "mylocation"

    lateinit var map : GoogleMap

    val mapFragment : SupportMapFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
    }

    val mGoogleApiClient : GoogleApiClient by lazy {
        GoogleApiClient.Builder(this)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API)
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mapFragment.getMapAsync(this)

        PermissionUtils.validate(this, 0,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION)
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        for(result in grantResults){
            if(result == PackageManager.PERMISSION_DENIED){
                alertAndFinish()
                return
            }
        }
    }

    override fun onStart() {
        mGoogleApiClient.connect()
        super.onStart()
    }

    override fun onStop() {
        mGoogleApiClient.disconnect()
        super.onStop()
    }

    private fun alertAndFinish(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.app_name)
            .setMessage("Para utilizar esse aplicativo você precisa aceitar as permissões")
        builder.setPositiveButton("OK"){dialog, _ -> finish() }
        builder.show()
    }

    override fun onMapReady(map: GoogleMap?) {
        Log.d(TAG, "onMapReady: $map")
        if(map != null){
            this.map = map
            this.map.mapType = GoogleMap.MAP_TYPE_NORMAL
        }
    }

    override fun onConnected(p0: Bundle?) {
        toast("Conectado no Google Play Services")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(menu: MenuItem?): Boolean {
        when(menu?.itemId){
            R.id.action_my_location  -> {
                pegarUltimaLocalizacao()
                return true
            }
        }

        return super.onOptionsItemSelected(menu)
    }

    @SuppressLint("MissingPermission")
    private fun pegarUltimaLocalizacao(){
       val fusedClient = LocationServices.getFusedLocationProviderClient(this)
        fusedClient.lastLocation
            .addOnSuccessListener { location -> adicionaLocalNoMapa(location) }
            .addOnFailureListener { Log.e(TAG, "Não foi possível buscar a localização do GPS") }
    }

    private fun adicionaLocalNoMapa(localizacao: Location) {
        if(map != null){
            val latLng = LatLng(localizacao.latitude, localizacao.longitude)
            val update = CameraUpdateFactory.newLatLngZoom(latLng, 15f)
            map.animateCamera(update)

            Log.d(TAG, "adicionaLocalNoMapa: $localizacao")
            text.text = String.format("Lat/Lng: ${localizacao.latitude}/${localizacao.longitude}, provider: ${localizacao.provider}")

            val circle = CircleOptions().center(latLng)
            circle.fillColor(Color.RED)
            circle.radius(25.0)
            map.clear()
            map.addCircle(circle)
        }
    }


    override fun onConnectionSuspended(p0: Int) {
        toast("Conexão Interropida")
    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        toast("Erro ao conectar: $connectionResult")
    }

}
