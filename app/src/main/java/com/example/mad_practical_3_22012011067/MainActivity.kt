package com.example.mad_practical_3_22012011067

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.CallLog
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val camera: Button = findViewById(R.id.camerabtn)
        val gallery: Button = findViewById(R.id.gallerybtn)
        val alarm: Button = findViewById(R.id.alarmbtn)
        val callLog : Button = findViewById(R.id.callbtn)
        val login : Button = findViewById(R.id.loginbtn)
        login.setOnClickListener{ Intent(this,LoginActivity::class.java)
            .putExtra("emailInput", "hdhdsghds")
            .putExtra("passwordInput", "bdsbdsnbds")
            .also{ startActivity(it)
            }
        }
        callLog.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = CallLog.Calls.CONTENT_URI
                type = CallLog.Calls.CONTENT_TYPE }
            startActivity(intent) }
        val inputNumber : EditText = findViewById(R.id.phoneInput)
        val phone : Button = findViewById(R.id.phonebtn)
        phone.setOnClickListener{
            val phoneNumber = inputNumber.text.toString()
            Intent(Intent.ACTION_DIAL).setData(Uri.parse(phoneNumber)).also{startActivity(it)}
        }
        val urlInput : EditText = findViewById(R.id.urlInput)
        val browse : Button = findViewById(R.id.browse)
        browse.setOnClickListener{
            val intent = Intent( Intent.ACTION_VIEW, Uri.parse(urlInput.text.toString()) )
            startActivity(intent) }
        gallery.setOnClickListener{ Intent(Intent.ACTION_VIEW).setType("image/*").also{
            startActivity(it)
        }
        }
        camera.setOnClickListener{
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also{
                startActivity(it)
            }
        }
        alarm.setOnClickListener{
            Intent(AlarmClock.ACTION_SET_ALARM).also{
                startActivity(it)
            }
        }
    }
}
