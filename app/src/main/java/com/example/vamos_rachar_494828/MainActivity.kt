package com.example.vamos_rachar_494828


import android.content.Intent
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import com.google.android.material.switchmaterial.SwitchMaterial
import java.math.RoundingMode
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val qtd = findViewById<EditText>(R.id.qtd)
        val valor = findViewById<EditText>(R.id.valor)
        val result = findViewById<EditText>(R.id.result)
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        qtd.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val calculo = df.format((valor.text.toString().toFloat()/qtd.text.toString().toFloat()).toBigDecimal())
                result.setText(calculo.toString())
                result.setFocusable(false)
                result.setFocusableInTouchMode(false)
                result.setClickable(false)
            }
        }
        valor.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val calculo = df.format((valor.text.toString().toFloat()/qtd.text.toString().toFloat()).toBigDecimal())
                result.setText(calculo.toString())
            }
        }
        val button = findViewById<ImageButton>(R.id.share)
        button.setOnClickListener{
            val sendIntent: Intent = Intent().apply {
                val shareResult = result.text.toString()
                val finalShare = getString(R.string.share) + " " + shareResult + " " + getString(R.string.share2)
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, finalShare)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
        val night = findViewById<Switch>(R.id.switch1)
        night.setOnCheckedChangeListener{ _, isChecked ->
            val sendIntent: Intent = Intent().apply{
                if (!isChecked)(
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                        )
                else(
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                        )
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)

        }

    }}
