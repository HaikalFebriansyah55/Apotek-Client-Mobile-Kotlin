package com.example.uas.ui.presentation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.uas.R
import com.example.uas.retrofit.ObatViewModel
import java.text.DecimalFormat

@Composable
fun Medicine(
    viewModel: ObatViewModel = viewModel()
) {
    val obatList by viewModel.obatList.observeAsState(emptyList())
    val isLoading by viewModel.isLoading.observeAsState(false)
    val errorMessage by viewModel.errorMessage.observeAsState("")
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when {
            isLoading -> {
                CircularProgressIndicator()
            }

            errorMessage.isNotEmpty() -> {
                Text(
                    text = errorMessage,
                    fontSize = 16.sp
                )
            }

            obatList.isNotEmpty() -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(obatList.size) { index ->
                        val obat = obatList[index]
                        // Lakukan null check pada 'gambar'
                        val gambarName = obat.gambar?.takeIf { it.isNotEmpty() } ?: "defaultmed"
                        Log.d("Haikal", "Test${obat.gambar}")
                        val resId = context.resources.getIdentifier(
                            gambarName,
                            "drawable",
                            context.packageName
                        )
                        Card(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth()
                                .height(200.dp),
                            shape = RoundedCornerShape(10.dp),
                            elevation = CardDefaults.outlinedCardElevation()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(7.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceAround
                            ) {
                                AsyncImage(
                                    model = if (resId != 0) resId else R.drawable.defaultmed,
                                    contentDescription = "Gambar Obat",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(100.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                )
                                Column(
                                    verticalArrangement = Arrangement.SpaceAround,
                                    horizontalAlignment = Alignment.Start,
                                    modifier = Modifier.fillMaxHeight()
                                ) {
                                    Text(text = "Nama: ${obat.nama ?: "Tidak Diketahui"}")
                                    val formattedHarga = obat.harga?.let {
                                        "Rp ${DecimalFormat("#,###").format(it)}"
                                    } ?: "Tidak Diketahui"
                                    Text(text = "Harga: ${formattedHarga ?: "Tidak Diketahui"}")
                                    Text(text = "Jenis: ${obat.jenis ?: "Tidak Diketahui"}")
                                    Text(text = "Deskripsi: ${obat.deskripsi ?: "Tidak Ada"}")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
