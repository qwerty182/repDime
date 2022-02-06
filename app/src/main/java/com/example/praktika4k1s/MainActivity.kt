package com.example.praktika4k1s

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.praktika4k1s.R
import com.example.praktika4k1s.RestApiManager
import com.example.praktika4k1s.models.ModelClassUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val permission: String = Manifest.permission.CAMERA
        val grant = ContextCompat.checkSelfPermission(this, permission)
        if (grant != PackageManager.PERMISSION_GRANTED) {
            val permission_list = arrayOfNulls<String>(1)
            permission_list[0] = permission
            ActivityCompat.requestPermissions(this, permission_list, 1)
        }

        btnAuto.setOnClickListener {
            authorization()

        }
        btnReg.setOnClickListener {
            val intent = Intent(this@MainActivity, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }

    private fun authorization() {
        val sharedPreferences = getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)
        val apiService = RestApiManager()
        val userLog = ModelClassUser(idUser = null,
            roleId = null,
            login = editLogin.text.toString(),
            password = editPassword.text.toString())

        apiService.userAuthorization(userLog) {
            if (it?.idUser != null && it?.roleId != 2) {
                Toast.makeText(applicationContext, "Вход...", Toast.LENGTH_LONG).show()
                val intent = Intent(this@MainActivity, CabinetActivity::class.java)
                intent.putExtra("idUser", it.idUser)

                val login = editLogin.text.toString().trim()
                val idUser = Integer.parseInt(it.idUser.toString().trim())
                val password = editPassword.text.toString().trim()

                val editor = sharedPreferences.edit()

                editor.putString("LOGIN", login)
                editor.putInt("IDUSER", idUser)
                editor.putString("PASSWORD", password)

                editor.apply()

                startActivity(intent)
            } else if (it?.idUser != null && it?.roleId == 2) {
                Toast.makeText(applicationContext, "Вход...", Toast.LENGTH_LONG).show()
                val intent = Intent(this@MainActivity, MainActivityAdmin::class.java)
                intent.putExtra("idUser", it.idUser)

                val loginAd = editLogin.text.toString().trim()
                val idUserAd = Integer.parseInt(it.idUser.toString().trim())
                val passwordAd = editPassword.text.toString().trim()

                val editor = sharedPreferences.edit()

                editor.putString("LOGINADMIN", loginAd)
                editor.putInt("IDUSERADMIN", idUserAd)
                editor.putString("PASSWORDADMIN", passwordAd)

                editor.apply()

                startActivity(intent)
            }else {
                Toast.makeText(applicationContext, "Такого пользователя не существует. Зарегистрироваться?", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this@MainActivity, "permission granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@MainActivity, "permission not granted", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}