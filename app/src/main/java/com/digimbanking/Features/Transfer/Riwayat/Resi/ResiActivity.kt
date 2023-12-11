package com.digimbanking.Features.Transfer.Riwayat.Resi

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.pdf.PdfDocument
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.core.data.network.Result
import com.core.data.response.riwayatResi.ResiResponse
import com.core.data.response.riwayatTransaksi.Transaction
import com.core.data.response.transferSesama.TransactionResponse
import com.core.domain.model.RiwayatItemModel
import com.digimbanking.BuildConfig
import com.digimbanking.Features.Dashboard.BerandaFragment
import com.digimbanking.Features.Dashboard.NavbarContainer
import com.digimbanking.databinding.ActivityResiBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class ResiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResiBinding
    private lateinit var viewModel: ResiViewModel
    private lateinit var resiView : View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResiBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[ResiViewModel::class.java]
        setContentView(binding.root)

        val dataTransaksi = intent.getParcelableExtra<Transaction>("riwayat")
        viewModel.viewModelScope.launch(Dispatchers.Main) {
            onLoading()
            if (dataTransaksi != null) {
                viewModel.doResi(dataTransaksi.kodeTransaksi).observe(this@ResiActivity){result ->
                    when(result){
                        is Result.Success -> {
                            binding.apply {
                                val convertTanggal = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
                                val originalTanggal: Date = convertTanggal.parse(dataTransaksi.tanggal)
                                val targetTanggalFormat = SimpleDateFormat("dd MM yyyy | HH:mm", Locale.US)
                                tvDateTimeTransaksi.text = targetTanggalFormat.format(originalTanggal).toString()
                                tvNamaPengirim.text = result.data.pengirim.nama
                                tvBankPengirim.text = "${result.data.pengirim.namaBank} - ${result.data.pengirim.noRekening.toString()}"
                                tvInisialPengirim.text = result.data.pengirim.nama.first().toString()
                                tvIsianNomorReferensi.text = result.data.data.kodeTransaksi.toString()
                                tvIsianTipeTransaksi.text = result.data.data.tipeTransaksi
                                tvIsianBiayaAdmin.text = "Rp${result.data.data.biayaAdmin.toLong().formatDotSeparator()}"
                                tvIsianTotalTransaksi.text = "Rp${result.data.data.totalTransaksi.toLong().formatDotSeparator()}"

                                // pengecekan jika catatan kosong
                                if(result.data.data.catatan.isNullOrEmpty()){
                                    tvIsianCatatan.text = ""
                                }else{
                                    tvIsianCatatan.text = result.data.data.catatan
                                }

                              //  tvIsianCatatan.text = result.data.data.catatan
                                tvNamaPenerimaResi.text = result.data.penerima.nama
                                tvBankPenerimaResi.text = "${result.data.penerima.namaBank} - ${result.data.penerima.noRekening.toString()}"
                                tvInisialPenerimaResi.text = result.data.penerima.nama.first().toString()
                            }
                            onFinishedLoading()
                        }

                        is Result.Error -> {
                            Log.d("Error get Resi", result.errorMessage)
                            onFinishedLoading()
                        }

                        else -> {
                            Log.d("Test", "JSON empty")
                            onLoading()
                        }
                    }
                }
            }
        }
        binding.btnSelesai.setOnClickListener {
            val intent = Intent(this@ResiActivity, NavbarContainer::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

        binding.buttonShareResi.setOnClickListener {
            val pdfFile = generatePdf()
            if (pdfFile != null) {
                sharePdf(pdfFile, "application/pdf")
            } else {
                Toast.makeText(this@ResiActivity, "Failed to generate PDF", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun generatePdf(): File? {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(resiView.width, resiView.height, 1).create()
        val page = pdfDocument.startPage(pageInfo)
        val canvas = page.canvas
        canvas.drawColor(Color.WHITE)

        // Draw the view on the PDF page
        try {
            resiView.draw(canvas)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("PDF_GENERATION", "Error drawing view on PDF canvas: ${e.message}")
            pdfDocument.close()
            return null
        }

        // Finish the page
        pdfDocument.finishPage(page)

        // Save the PDF file in the application-specific directory
        val folder = File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "ReceiptPDFs")
        if (!folder.exists()) {
            if (!folder.mkdirs()) {
                Log.e("PDF_GENERATION", "Error creating directory: ${folder.absolutePath}")
                pdfDocument.close()
                return null
            }
        }

        val pdfFile = File(folder, "Resi_Transfer_DigiBank.pdf")
        try {
            pdfDocument.writeTo(FileOutputStream(pdfFile))
            Log.d("PDF_GENERATION", "PDF successfully generated: ${pdfFile.absolutePath}")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("PDF_GENERATION", "Error saving PDF file: ${e.message}")
            return null
        } finally {
            pdfDocument.close()
        }

        return pdfFile
    }


    private fun sharePdf(pdfFile: File, mimeType: String) {
        val pdfUri = FileProvider.getUriForFile(
            this,
            BuildConfig.APPLICATION_ID + ".provider",
            pdfFile
        )

        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_STREAM, pdfUri)
            type = mimeType
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
        startActivity(Intent.createChooser(sendIntent, "Share Receipt"))
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun onLoading(){
        binding.loadScreenResi.visibility = View.VISIBLE
        binding.loadScreenResi.setOnTouchListener { _, _ ->
            true
        }
    }

    private fun onFinishedLoading(){
        binding.loadScreenResi.visibility = View.GONE
    }
}

    private fun Long.formatDotSeparator(): String{
        return toString()
            .reversed()
            .chunked(3)
            .joinToString (".")
            .reversed()
    }