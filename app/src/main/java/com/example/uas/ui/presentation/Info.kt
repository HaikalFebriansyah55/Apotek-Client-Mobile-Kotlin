package com.example.uas.ui.presentation

import android.content.Intent
import android.net.Uri
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uas.R

@Composable
fun Info() {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(R.drawable.icon1)
            , contentDescription = ""
        , modifier = Modifier
                .size(120.dp)
        )
        Text(
            "Aplikasi catatan obat-obatan ini berfungsi sebagai platform informasi yang menampilkan daftar obat-obatan beserta detail pentingnya. Fitur utamanya mencakup pencatatan nama obat, harga, dosis yang dianjurkan, dan deskripsi singkat mengenai kegunaan atau fungsi obat tersebut.",
            )
        Spacer(
            modifier = Modifier
                .height(15.dp)
        )
        Column (
            verticalArrangement = Arrangement.spacedBy(7.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                "Demo Aplikasi",
                fontSize = 16.sp
            )
            Image(
                painter = painterResource(R.drawable.mediaplayer),
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
                    .clickable {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://youtu.be/l8J8HuDYYGw?si=fd36vhu3pc7CQDsD")
                        )
                        context.startActivity(intent)
                    }
            )
        }
    }
}