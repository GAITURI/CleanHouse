package com.example.cleanhouse

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_confrim_data.*

class Advert : AppCompatActivity() {
    lateinit var btnphone:Button
    lateinit var  btnemail:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advert)


        btnphone=findViewById(R.id.contact_phone)
        btnemail=findViewById(R.id.contact_email)


        btnphone.setOnClickListener(View.OnClickListener {    val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel" + "0798275251")
            startActivity(dialIntent)
        })

        btnemail.setOnClickListener(View.OnClickListener {   val addresses = "gaiturimark@gmail.com"
            val subject = "works"
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.setData(Uri.parse("mailto:")) // only email apps should handle this
            intent.putExtra(Intent.EXTRA_EMAIL, addresses)
            intent.putExtra(Intent.EXTRA_SUBJECT, subject)
            if (intent.resolveActivity(packageManager) != null)
            {
                startActivity(intent)
            }
        })


    }
}