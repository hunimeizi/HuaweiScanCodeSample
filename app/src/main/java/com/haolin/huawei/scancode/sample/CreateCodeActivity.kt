package com.haolin.huawei.scancode.sample

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.huawei.hms.hmsscankit.ScanUtil
import com.huawei.hms.ml.scan.HmsBuildBitmapOption
import com.huawei.hms.ml.scan.HmsScan
import java.io.File
import java.io.FileOutputStream
import java.util.*

class CreateCodeActivity : AppCompatActivity() {
    private var resultImage: Bitmap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_code)
        val editContent  = findViewById<EditText>(R.id.etContent)
        val barcodeImage  = findViewById<ImageView>(R.id.barcode_image)
        findViewById<Button>(R.id.btnCreateCode).setOnClickListener {
            if (TextUtils.isEmpty(editContent.text.toString())){
                Toast.makeText(this, "请先输入内容", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            //Generate the barcode.
            val options = HmsBuildBitmapOption.Creator().setBitmapMargin(1).setBitmapColor(Color.BLACK)
                    .setBitmapBackgroundColor(Color.WHITE).create()
            resultImage = ScanUtil.buildBitmap(editContent.text.toString(), HmsScan.QRCODE_SCAN_TYPE, 700, 700, options)
            barcodeImage.setImageBitmap(resultImage)
        }
        findViewById<Button>(R.id.btnSaveQCode).setOnClickListener {
            if (resultImage == null) {
                Toast.makeText(this, "请先生成二维码", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            try {
                val fileName = System.currentTimeMillis().toString() + ".jpg"
                val storePath = Environment.getExternalStorageDirectory().absolutePath
                val appDir = File(storePath)
                if (!appDir.exists()) {
                    appDir.mkdir()
                }
                val file = File(appDir, fileName)
                val fileOutputStream = FileOutputStream(file)
                val isSuccess = resultImage!!.compress(Bitmap.CompressFormat.JPEG, 70, fileOutputStream)
                fileOutputStream.flush()
                fileOutputStream.close()
                val uri = Uri.fromFile(file)
                sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri))
                if (isSuccess) {
                    Toast.makeText(this, "已保存到本地", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "未知错误", Toast.LENGTH_SHORT).show()
            }
        }
    }
}