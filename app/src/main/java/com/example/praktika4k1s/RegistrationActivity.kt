package com.example.praktika4k1s

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.praktika4k1s.models.ModelClassRegistration
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.activity_registration.btnReg
import kotlinx.android.synthetic.main.activity_registration.editLogin
import kotlinx.android.synthetic.main.activity_registration.editPassword

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        btnReg.setOnClickListener {
            registration()
        }
        btnBack.setOnClickListener {
            val intent = Intent(this@RegistrationActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun registration(){
        if (editLogin.text.isNullOrEmpty() || editPassword.text.isNullOrEmpty()
            || editLastName.text.isNullOrEmpty() || editFirstName.text.isNullOrEmpty()
            || editMiddleName.text.isNullOrEmpty()) {
                Toast.makeText(applicationContext, "Ошибка! Не все поля заполнены", Toast.LENGTH_LONG).show()
                return
        }
        val apiService = RestApiManager()
        val modelClass = ModelClassRegistration(
            login = editLogin.text.toString(),
            password = editPassword.text.toString(),
            lastName = editLastName.text.toString(),
            firstName = editFirstName.text.toString(),
            middleName = editMiddleName.text.toString(),
            roleId = 1)

        apiService.userRegistration(modelClass) {
            if (it != null) {
                Toast.makeText(applicationContext, "Пользователь добавлен", Toast.LENGTH_LONG).show()
                val intent = Intent(this@RegistrationActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}