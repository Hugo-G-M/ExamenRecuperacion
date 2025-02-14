package com.example.examenrecuperacion

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.examenrecuperacion.databinding.Activity2Binding
import com.example.examenrecuperacion.datos.Centro

class actividad2 : AppCompatActivity(), OnMapReadyCallback, AdapterView.OnItemSelectedListener {
    private lateinit var mMap: GoogleMap
    private lateinit var binding: Activity2Binding

    private val centros = listOf(
        Centro("Julián Marías", "10 km", R.drawable.julian_marias_logo),
        Centro("Claudio Moyano", "5 km", R.drawable.claudio_moyano_logo),
        Centro("Virgen del Espino", "15 km", R.drawable.virgen_espino_logo)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = Activity2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val nombreCentro = intent.getStringExtra("centro")

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val selectorCentros = findViewById<Spinner>(R.id.spinner)
        val adaptadorPersonalizado = AdaptadorPersonalizado(this, R.layout.lineaspiner, centros) // Pasa la lista de centros
        selectorCentros.adapter = adaptadorPersonalizado
        selectorCentros.onItemSelectedListener = this

        val botonViajes = findViewById<Button>(R.id.button2)
        botonViajes.setOnClickListener {
            val intent = Intent(this, actividad3::class.java).apply {
                putParcelableArrayListExtra("centros", ArrayList(centros)) // Pasa la lista de centros
            }
            startActivity(intent)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val centro = LatLng(41.632273, -4.758646)
        mMap.addMarker(MarkerOptions().position(centro).title("Centro 1"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centro, 15f))
        mMap.mapType = GoogleMap.MAP_TYPE_HYBRID;

        googleMap.setOnMarkerClickListener { marker ->
            val url = marker.snippet
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
            true
        }

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val centroSeleccionado = centros[position]
        val seleccion = findViewById<TextView>(R.id.textView4)
        seleccion.text = "Distancia : " + centroSeleccionado.distancia
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    private inner class AdaptadorPersonalizado(
        context: Context,
        resource: Int,
        objects: List<Centro>
    ) : ArrayAdapter<Centro>(context, resource, objects) {

        override fun getDropDownView(
            position: Int,
            convertView: View?,
            parent: ViewGroup
        ): View {
            return crearFilaPersonalizada(position, convertView, parent)
        }

        override fun getView(
            position: Int,
            convertView: View?,
            parent: ViewGroup
        ): View {
            return crearFilaPersonalizada(position, convertView, parent)
        }

        private fun crearFilaPersonalizada(
            position: Int,
            convertView: View?,
            parent: ViewGroup
        ): View {
            val layoutInflater = LayoutInflater.from(context)

            val rowView = convertView ?: layoutInflater.inflate(R.layout.lineaspiner, parent, false)

            val centro = getItem(position)

            rowView.findViewById<TextView>(R.id.textView5).text = centro?.nombre

            rowView.findViewById<ImageView>(R.id.imageView3).setImageResource(centro?.imagen ?: R.drawable.ic_placeholder)

            return rowView
        }
    }
}