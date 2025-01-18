package com.example.uas.ui.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas.retrofit.ObatResponseItem
import com.example.uas.retrofit.ObatViewModel
import com.example.uas.ui.Table.Table
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

@Composable
fun Graph(
    obatList: List<ObatResponseItem>,
    viewModel: ObatViewModel = viewModel()
) {
    val context = LocalContext.current
    val barEntries = remember(obatList) {
        obatList.mapIndexedNotNull { index, obat ->
            obat.harga?.let { BarEntry(index.toFloat(), it.toFloat()) }
        }
    }

    val barDataSet = BarDataSet(barEntries, "Harga Obat").apply {
        valueTextSize = 12f
        colors = listOf(android.graphics.Color.BLUE)
    }

    val barData = BarData(barDataSet)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        AndroidView(
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth(),
            factory = {
            BarChart(context).apply {
                data = barData
                description.isEnabled = false
                setFitBars(true)
                xAxis.apply {
                    granularity = 1f
                    position = XAxis.XAxisPosition.BOTTOM
                    valueFormatter = IndexAxisValueFormatter(obatList.map { it.nama?: "" })
                    setDrawGridLines(false)
                }
                axisLeft.apply {
                    axisMinimum = 0f
                    setDrawGridLines(false)
                }
                axisRight.isEnabled = false
                legend.isEnabled = true
                animateY(1000)
                invalidate()
            }
        })
        Table(viewModel)
    }
}