package com.example.uas.ui.Table

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.breens.beetablescompose.BeeTablesCompose
import com.example.uas.retrofit.ObatViewModel

@Composable
fun Table(
    viewModel: ObatViewModel = viewModel()
) {
    val obatList by viewModel.obatList.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(initial = false)
    val errorMessage by viewModel.errorMessage.observeAsState(initial = "")
    val tableColumn = listOf(
        "Nama",
        "Jenis",
        "Harga",
        "Deskripsi"
    )

    val tableData = obatList?.map {
        Obat(
            nama = it.nama ?: "Unknown",
            jenis = it.jenis ?: "Unknown",
            harga = it.harga?.toInt() ?: 0,
            deskripsi = it.deskripsi ?: "No Description"
        )
    } ?: emptyList()

    Scaffold (
        modifier = Modifier
            .fillMaxSize()
    ){ paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ){
            if (tableData.isNotEmpty()){
                BeeTablesCompose(
                    data = tableData,
                    headerTableTitles = tableColumn
                )
            } else {
                Text(
                    "Data Tidak Tersedia",
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

data class Obat(
    val nama : String,
    val jenis : String,
    val harga : Int,
    val deskripsi : String
)