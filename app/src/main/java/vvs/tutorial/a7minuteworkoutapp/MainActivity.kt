package vvs.tutorial.a7minuteworkoutapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.FrameLayout
import android.widget.Toast
import vvs.tutorial.a7minuteworkoutapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        binding?.flStart?.setOnClickListener{
            val intent = Intent(this, exercise::class.java)

            startActivity(intent)
        }
        binding?.Bmi?.setOnClickListener{
            val intent = Intent(this, Bmi2::class.java)
            startActivity(intent)
        }
        binding?.history?.setOnClickListener{
            val intent = Intent(this, History::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        binding = null
    }


}