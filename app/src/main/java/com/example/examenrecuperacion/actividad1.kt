package com.example.examenrecuperacion

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class actividad1 : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private val nombres = arrayOf("Julián Marías", "Claudio Moyano", "Virgen del Espino")

    private val logos = arrayOf(R.drawable.julian_marias_logo, R.drawable.claudio_moyano_logo, R.drawable.virgen_espino_logo)

    private val telefono = arrayOf(123456789, 987654321, 147258369)

    companion object {
        const val PREFS_NAME = "MyPrefsFile" // Nombre del archivo de preferencias compartidas
        const val IMAGE_KEY = "image" // Clave para almacenar el color de fondo
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_1)

        val listaCentros = findViewById<ListView>(R.id.listView)
        val adaptadorPersonalizado = AdaptadorPersonalizado(this, R.layout.item_lista, nombres)
        listaCentros.adapter = adaptadorPersonalizado
        listaCentros.onItemSelectedListener = this
        val imagenBoton = findViewById<ImageView>(R.id.imageView)

//        val sharedPref = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
//        val imagenDefault = sharedPref.getString(IMAGE_KEY, "res/drawable/claudio_moyano_logo")
//        imagenBoton.setImageResource(imagenDefault.toString().toInt())
//
//        with (sharedPref.edit()) {
//            putString(IMAGE_KEY, imagenDefault)
//            apply()
//        }
        val tvTelefono = findViewById<TextView>(R.id.textView)

        imagenBoton.setOnClickListener {
            val intent = Intent(this, actividad2::class.java).apply {
                putExtra("centro", listaCentros)
            }

            startActivity(intent)
        }

        tvTelefono.setOnClickListener {

        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val c = view?.findViewById<TextView>(R.id.textView)
        val seleccion = findViewById<ImageView>(R.id.imageView)
        val nombre = nombres[position]

        seleccion.setImageResource(logos[position])
        c?.text = nombres[position]
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        val seleccion = findViewById<TextView>(R.id.textView)
        seleccion.text = "nada seleccionado!"
    }

    private inner class AdaptadorPersonalizado(
        context: Context,
        resource: Int,
        objects: Array<String>
    ) : ArrayAdapter<String>(context, resource, objects) {

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

            val rowView = convertView ?: layoutInflater.inflate(R.layout.item_lista, parent, false)

            rowView.findViewById<TextView>(R.id.textView2).text = nombres[position]
            rowView.findViewById<ImageView>(R.id.imageView2).setImageResource(logos[position])
            rowView.findViewById<TextView>(R.id.textView3).text = telefono[position].toString()

            return rowView
        }
    }
}