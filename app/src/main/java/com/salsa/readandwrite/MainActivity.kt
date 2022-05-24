package com.salsa.readandwrite

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
// Digunakan untuk menyimpan file di penyimpanan internal
import java.io.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Digunakan untuk mengisikan data dari file name
        val fileName = findViewById<EditText>(R.id.editFile)
        // Digunakan untuk mengisikan data dari file data
        val fileData = findViewById<EditText>(R.id.editData)

        // Digunakan untuk menyimpan data-data dari file nama file dan data setelah pengguna menginputkan
        val btnSave = findViewById<Button>(R.id.btnSave)
        // Digunakan untuk melihat data-data dari file nama file dan data setelah pengguna menginputkan
        val btnView = findViewById<Button>(R.id.btnView)

        // Didalam kode program ini digunakan untuk menyimpan konten file dengan mengklik tombol save
        btnSave.setOnClickListener(View.OnClickListener {
            // Digunakan untuk memginputkan data file name dengan tipe data string
            val file:String = fileName.text.toString()
            // Digunakan untuk memginputkan data file data dengan tipe data string
            val data:String = fileData.text.toString()
            // Digunakan untuk menjalankan fungsi kirim data teks
            val fileOutputStream:FileOutputStream
            try {
                // Digunakan untuk menyimpan file khusus
                fileOutputStream = openFileOutput(file, Context.MODE_PRIVATE)
                fileOutputStream.write(data.toByteArray())
            // Digunakan untuk mengekseksui error
            } catch (e: FileNotFoundException){
                e.printStackTrace()
            }catch (e: NumberFormatException){
                e.printStackTrace()
            }catch (e: IOException){
                e.printStackTrace()
            }catch (e: Exception){
                e.printStackTrace()
            }
            // Jika pada saat pengguna menginputkan File Name dan File Data sesuai dengan aturannya penulisan
            // maka akan menghasilkan keluaran output yaitu data save
            Toast.makeText(applicationContext,"data save",Toast.LENGTH_LONG).show()
            fileName.text.clear()
            fileData.text.clear()
        })

        // Didalam kode program ini digunakan untuk mengambil konten file dengan mengklik tombol view
        btnView.setOnClickListener(View.OnClickListener {
            // Fungsi dari kode program dibawah ini yaitu dimana user diminta untuk megisikan kembali
            // file nama yang sebelumnya sudah di save.
            // Setelah pengguna menginputkan File name yang sesuai kemudian pengguna klik view dan akan menampilkan File data.
            val filename = fileName.text.toString()
            if(filename.toString()!=null && filename.toString().trim()!=""){
                var fileInputStream: FileInputStream? = null
                fileInputStream = openFileInput(filename)
                var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
                val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
                val stringBuilder: StringBuilder = StringBuilder()
                var text: String? = null
                while ({ text = bufferedReader.readLine(); text }() != null) {
                    stringBuilder.append(text)
                }
                // Menampilkan data di EditText
                fileData.setText(stringBuilder.toString()).toString()
            }else{
                // Berfungsi jika pengguna tidaK memasukkan file name yang sesuai nama yang disimpan maka
                // aplikasi otomatis akan mengeluarkan teks file name cannot be blank
                Toast.makeText(applicationContext,"file name cannot be blank",Toast.LENGTH_LONG).show()
            }
        })

    }
}