package com.example.konversiair

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    // Satuan volume beserta faktor konversi ke Liter
    private val volumeUnits = listOf(
        "Liter (L)",
        "Mililiter (mL)",
        "Meter Kubik (m³)",
        "Sentimeter Kubik (cm³)",
        "Galon (US)",
        "Galon (UK)"
    )

    // Faktor konversi ke Liter
    private val toLineFactor = mapOf(
        "Liter (L)"             to 1.0,
        "Mililiter (mL)"        to 0.001,
        "Meter Kubik (m³)"      to 1000.0,
        "Sentimeter Kubik (cm³)" to 0.001,
        "Galon (US)"            to 3.78541,
        "Galon (UK)"            to 4.54609
    )

    private var selectedUnit: String = "Liter (L)"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tilVolume: TextInputLayout = findViewById(R.id.tilVolume)
        val etVolume: TextInputEditText = findViewById(R.id.etVolume)
        val spinnerVolume: Spinner = findViewById(R.id.spinnerVolume)
        val btnConvert: MaterialButton = findViewById(R.id.btnConvert)
        val btnReset: MaterialButton = findViewById(R.id.btnReset)
        val cardResult: View = findViewById(R.id.cardResult)
        val tvResultKg: TextView = findViewById(R.id.tvResultKg)
        val tvResultGram: TextView = findViewById(R.id.tvResultGram)
        val tvResultLbs: TextView = findViewById(R.id.tvResultLbs)
        val tvFormula: TextView = findViewById(R.id.tvFormula)

        // Setup Spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, volumeUnits)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerVolume.adapter = adapter

        spinnerVolume.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedUnit = volumeUnits[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // Tombol Konversi
        btnConvert.setOnClickListener {
            tilVolume.error = null
            val input = etVolume.text.toString().trim()

            if (input.isEmpty()) {
                tilVolume.error = getString(R.string.error_empty)
                return@setOnClickListener
            }

            val volume = input.toDoubleOrNull()
            if (volume == null || volume < 0) {
                tilVolume.error = getString(R.string.error_invalid)
                return@setOnClickListener
            }

            // Hitung massa
            val faktorKeLiter = toLineFactor[selectedUnit] ?: 1.0
            val volumeInLiter = volume * faktorKeLiter
            // Densitas air = 1 kg/L
            val massaKg = volumeInLiter * 1.0
            val massaGram = massaKg * 1000.0
            val massaLbs = massaKg * 2.20462

            val df = DecimalFormat("#,##0.####")
            val dfShort = DecimalFormat("#,##0.##")

            tvResultKg.text = "${df.format(massaKg)} kg"
            tvResultGram.text = "= ${df.format(massaGram)} gram"
            tvResultLbs.text = "= ${dfShort.format(massaLbs)} pon (lbs)"
            tvFormula.text = "Rumus: ${dfShort.format(volume)} ${selectedUnit} × ${faktorKeLiter} L/satuan × 1 kg/L = ${df.format(massaKg)} kg"

            cardResult.visibility = View.VISIBLE
        }

        // Tombol Reset
        btnReset.setOnClickListener {
            etVolume.text?.clear()
            tilVolume.error = null
            spinnerVolume.setSelection(0)
            cardResult.visibility = View.GONE
        }
    }
}
