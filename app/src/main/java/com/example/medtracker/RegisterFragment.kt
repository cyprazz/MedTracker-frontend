package com.example.medtracker

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.service.autofill.VisibilitySetterAction
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.FocusFinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.result.Result
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_register.*
import java.security.MessageDigest

class RegisterFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_register, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



        setClickListenUsername() //To set the listener for the username
        setClickListenEmail() //To set the listener for the email
        setClickListenPassword()
        setClickListenDate() //To set the listener for the date picker



        registerButton.setOnClickListener {
            if (usernameLay.error != null || username.getText()!!.length == 0) {
                username.requestFocus()
            } else if(emailLay.error != null || email.getText()!!.length == 0) {
                email.requestFocus()
            } else if(passwordLay.error != null || password.getText()!!.length == 0) {
                password.requestFocus()
            } else if(datePicker.getText()!!.length == 0) {
                activity?.currentFocus?.clearFocus()
                datePicker.performClick()
            } else post()
        }
    }


    fun post() { //post function for posting the user
        //getting text from the form
        val username = username.text.toString();
        val Email = email.text.toString();
        val password = password.text.toString().sha512(); //already hashed
        val dateOfBirth = datePicker.text.toString();
        val registerFormBody = "{\"username\":\"$username\",\"email\":\"$Email\", \"password\":\"$password\",\"verfied\":false,\"birthdate\":\"$dateOfBirth\"}"

        //setting up the request
        Fuel.post("http://192.168.1.4:8080/user") //TODO make this request to server
            .jsonBody(registerFormBody)
            .also { println(it) }
            .response { result ->
                when (result) {
                    is Result.Success -> println("Success") //TODO login
                    is Result.Failure -> showError(emailLay, "Your email was already found in our database")
                }
            }
    }

    fun String.sha512(): String { //hashing extension
        return this.hashWithAlgorithm("SHA-512")
    }


    private fun String.hashWithAlgorithm(algorithm: String): String { //hashing. Use like this: myString.sha512()
        val digest = MessageDigest.getInstance(algorithm)
        val bytes = digest.digest(this.toByteArray(Charsets.UTF_8))
        return bytes.fold("", { str, it -> str + "%02x".format(it) })
    }

    fun showError(editText: TextInputLayout, message: String) {
        editText.error = message
        editText.requestFocus()
    }

    fun setClickListenDate() {
        val datePickerText = datePicker
        val c: Calendar = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        datePicker.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus == true) {
                val dpd =
                    activity?.let { it1 ->
                        DatePickerDialog(
                            it1,
                            DatePickerDialog.OnDateSetListener { it, year, monthOfYear, dayOfMonth ->
                                datePickerText.setText(("$year-$monthOfYear-$dayOfMonth"))
                            },
                            year,
                            month,
                            day
                        )
                    }
                if (dpd != null) {
                    dpd.show()
                }
            } else if(hasFocus == false) {}
        }

        datePicker.setOnClickListener {
            //onClickListener voor het invullen van een datum
            val dpd =
                activity?.let { it1 ->
                    DatePickerDialog(
                        it1,
                        DatePickerDialog.OnDateSetListener { it, year, monthOfYear, dayOfMonth ->
                            datePickerText.setText(("$year-$monthOfYear-$dayOfMonth"))
                        },
                        year,
                        month,
                        day
                    )
                }
            if (dpd != null) {
                dpd.show()
            }
        }
    }

    fun setClickListenUsername() {
        username.addTextChangedListener(object : TextWatcher {
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
                val content = username.text.toString()
                if (content.length > 254) {
                    showError(usernameLay, "Your name is too long")
                } else if (content.isEmpty()) {
                    showError(usernameLay, "You need to fill in an username")
                } else usernameLay.error = null
            }
        })
    }

    fun setClickListenEmail() {
        email.addTextChangedListener(object : TextWatcher {
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
                val content = email.text.toString().trim()
                if (content.length > 254) {
                    showError(emailLay, "Your email is too long")
                } else if (content.isEmpty()) {
                    showError(emailLay, "You need to fill in an email")
                } else if (!TextUtils.isEmpty(content) && !android.util.Patterns.EMAIL_ADDRESS.matcher(content).matches()) {
                    showError(emailLay, "Your email is invalid")
                } else emailLay.error = null
            }
        })
    }

    fun setClickListenPassword() {
        val set = ConstraintSet() //TODO loskkoppelen en eigen functie maken
        set.clone(registerLay)

        password.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus == false && password.error != null) {
                v.requestFocus()
            } else if (hasFocus == false && password.error == null) {
                set.clear(passwordLay.id, ConstraintSet.TOP)
                set.connect(passwordLay.id, ConstraintSet.TOP, emailLay.id, ConstraintSet.BOTTOM)
                set.applyTo(registerLay)
                password_strength.visibility = View.GONE
                progressBar.visibility = View.GONE
                login_instructions.visibility = View.GONE
            }

            if (hasFocus == true) {
                set.clear(passwordLay.id, ConstraintSet.TOP)
                set.connect(passwordLay.id, ConstraintSet.TOP, login_instructions.id, ConstraintSet.BOTTOM)
                set.applyTo(registerLay)
                password_strength.visibility = View.VISIBLE
                progressBar.visibility = View.VISIBLE
                login_instructions.visibility = View.VISIBLE
            }
        }

        password.addTextChangedListener(object : TextWatcher {
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
               updatePasswordStrengthView(s.toString())
            }
        })
    }

    private fun updatePasswordStrengthView(password: String) {

        val progressBar = progressBar as ProgressBar
        val strengthView = password_strength as TextView
        if (TextView.VISIBLE != strengthView.visibility)
            return

        if (TextUtils.isEmpty(password)) {
            strengthView.text = ""
            progressBar.progress = 0
            return
        }

        val str = PasswordStrength.calculateStrength(password)
        strengthView.text = str.getText(this)
        strengthView.setTextColor(str.color)

        progressBar.progressDrawable.setColorFilter(str.color, android.graphics.PorterDuff.Mode.SRC_IN)
        if (str.getText(this) == "Weak") {
            progressBar.progress = 25
        } else if (str.getText(this) == "Medium") {
            progressBar.progress = 50
        } else if (str.getText(this) == "Strong") {
            progressBar.progress = 75
        } else {
            progressBar.progress = 100
        }
    }

}


