package com.example.medtracker

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.result.Result
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*
import java.security.MessageDigest

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_login, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setClickListenPassword()
        setClickListenEmail()

        loginButton.setOnClickListener {
            if(loginEmailLay.error != null || loginEmail.getText()!!.length == 0) {
                loginEmail.requestFocus()
            } else if(loginPasswordLay.error != null || loginPassword.getText()!!.length == 0) {
                loginPassword.requestFocus()
            } else post()
        }
    }

    private fun post() {
        val postEmail = loginEmail.text.toString()
        val postPassword = loginPassword.text.toString().sha512()
        val registerFormBody = "{\"email\":\"$postEmail\",\"password\":\"$postPassword\"}"

        //setting up the request
        Thread(Runnable {
            val (request, response, result) = Fuel.post("http://192.168.1.4:8080/login") //TODO make this request to server
                .jsonBody(registerFormBody)
                .also { println(it) }
                .responseString()

            when (result) {
                is Result.Success -> {
                    activity?.runOnUiThread {
                        if(result.value.toInt() != 0) {
                            LogActivity().saveToken(result.value)
                        } else {
                            activity?.runOnUiThread {
                                Toast.makeText(context,"Your email or password is incorrect", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                }
                is Result.Failure -> {
                    println(result)
                    activity?.runOnUiThread {
                        Toast.makeText(context,"Your email or password is incorrect", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }).start()
    }

    fun setClickListenPassword() {
        loginPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            } //Not used but mandatory

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
            } //Not used but mandatory

            override fun afterTextChanged(s: Editable?) {
                val content = loginPassword.text.toString().trim()
                if (content.length > 254) {
                    showError(loginPasswordLay, "Your email is too long")
                } else if (content.isEmpty()) {
                    showError(loginPasswordLay, "You need to fill in a password")
                } else loginPasswordLay.error = null
            }
        })
    }

    fun setClickListenEmail() {
        loginEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            } //Not used but mandatory

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
            } //Not used but mandatory

            override fun afterTextChanged(s: Editable?) {
                val content = loginEmail.text.toString().trim()
                if (content.length > 254) {
                    showError(loginEmailLay, "Your email is too long")
                } else if (content.isEmpty()) {
                    showError(loginEmailLay, "You need to fill in an email")
                } else if (!TextUtils.isEmpty(content) && !android.util.Patterns.EMAIL_ADDRESS.matcher(content).matches()) {
                    showError(loginEmailLay, "Your email is invalid")
                } else loginEmailLay.error = null
            }
        })
    }

    fun showError(editText: TextInputLayout, message: String) {
        editText.error = message
        editText.requestFocus()
    }

    fun String.sha512(): String { //hashing extension
        return this.hashWithAlgorithm("SHA-512")
    }


    private fun String.hashWithAlgorithm(algorithm: String): String { //hashing. Use like this: myString.sha512()
        val digest = MessageDigest.getInstance(algorithm)
        val bytes = digest.digest(this.toByteArray(Charsets.UTF_8))
        return bytes.fold("", { str, it -> str + "%02x".format(it) })
    }
}