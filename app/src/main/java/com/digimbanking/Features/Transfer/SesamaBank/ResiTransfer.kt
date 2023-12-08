package com.digimbanking.Features.Transfer.SesamaBank

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.pdf.PdfDocument
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.core.view.isVisible
import com.core.data.response.transferSesama.TransactionResponse
import com.digimbanking.BuildConfig
import com.digimbanking.Features.Dashboard.NavbarContainer
import com.digimbanking.R
import com.digimbanking.databinding.ActivityResiTransferBinding
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ResiTransfer : AppCompatActivity() {
    private lateinit var binding: ActivityResiTransferBinding
    private lateinit var recieptView: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResiTransferBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataResi = intent.getParcelableExtra<TransactionResponse>("dataResi")
        recieptView = binding.recieptView
        binding.apply {
            if (dataResi != null) {
                val convertedDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
                val originalDate: Date = convertedDate.parse(dataResi.dataTransaksi.timeTransaksi)
                val targetDateFormat = SimpleDateFormat("dd MMM yyyy | HH:mm", Locale.US)
                tvSenderName.text = dataResi.pengirim.nama
                tvInitialSender.text = dataResi.pengirim.nama.first().toString()
                tvSenderRekening.text = "${dataResi.pengirim.namaBank} - ${dataResi.pengirim.noRekening.toString()}"
                tvRecieverName.text = dataResi.penerima.nama
                tvInitialReciever.text = dataResi.penerima.nama.first().toString()
                tvrecieverRekening.text = "${dataResi.penerima.namaBank} - ${dataResi.penerima.noRekening.toString()}"
                tvRefNumb.text = dataResi.dataTransaksi.id.toString()
                tvTimeStamp.text = targetDateFormat.format(originalDate).toString()
                tvAdminFee.text = "Rp ${dataResi.dataTransaksi.biayaAdmin.toLong().formatDotSeparator()}"
                tvSum.text = "Rp ${dataResi.dataTransaksi.totalTransaksi.toLong().formatDotSeparator()}"
                tvTransType.text = dataResi.dataTransaksi.jenisTransaksi
                if (dataResi.dataTransaksi.catatan.isEmpty()){
                    tvCatatan.visibility = View.INVISIBLE
                    cvCatatan.visibility = View.INVISIBLE
                    tvTittleCatatan.visibility = View.INVISIBLE
                } else {
                    tvCatatan.text = dataResi.dataTransaksi.catatan
                }
            }
        }
        binding.btnSelesaiResi.setOnClickListener {
            startActivity(Intent(this, NavbarContainer::class.java))
            finishAffinity()
        }
        binding.btnShareResi.setOnClickListener {
            val pdfFile = generatePdf()
            if (pdfFile != null) {
                sharePdf(pdfFile, "application/pdf")
            } else {
                Toast.makeText(this, "Failed to generate PDF", Toast.LENGTH_SHORT).show()
            }
        }
    }
    @SuppressLint("MissingSuperCall")
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        startActivity(Intent(this, NavbarContainer::class.java))
        finishAffinity()
    }
    private fun generatePdf(): File? {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(recieptView.width, recieptView.height, 1).create()
        val page = pdfDocument.startPage(pageInfo)
        val canvas = page.canvas
        canvas.drawColor(Color.WHITE)

        // Draw the view on the PDF page
        try {
            recieptView.draw(canvas)
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

    private fun Long.formatDotSeparator(): String{
        return toString()
            .reversed()
            .chunked(3)
            .joinToString (".")
            .reversed()
    }
}