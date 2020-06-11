package com.example.medtracker

import android.accounts.AccountManager
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_log.*

public class LogActivity : AppCompatActivity(0) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        setContentView(R.layout.activity_log)
        loadFragment(RegisterFragment())
        val line  = ConstraintSet()
        line.clone(clay)

        register.setOnClickListener {
            setLayoutLineLeft(line)
            loadFragment(RegisterFragment())
        }

        login.setOnClickListener {
            setLayoutLineRight(line)
            loadFragment(LoginFragment())
        }
    }

    fun setLayoutLineLeft(line: ConstraintSet) {
        line.clear(registerBalk.id, ConstraintSet.TOP)
        line.clear(registerBalk.id, ConstraintSet.START)
        line.connect(registerBalk.id, ConstraintSet.TOP, register.id, ConstraintSet.BOTTOM, 8.px)
        line.connect(registerBalk.id, ConstraintSet.END, empty.id, ConstraintSet.START)
        line.applyTo(clay)
    }

    fun setLayoutLineRight(line: ConstraintSet) {
        line.clear(registerBalk.id, ConstraintSet.TOP)
        line.clear(registerBalk.id, ConstraintSet.END)
        line.connect(registerBalk.id, ConstraintSet.TOP, login.id, ConstraintSet.BOTTOM, 8.px)
        line.connect(registerBalk.id, ConstraintSet.START, empty.id, ConstraintSet.END)
        line.applyTo(clay)
    }

    private fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.containerLogin, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    //tool don't delete!
    val Int.dp: Int
        get() = (this / Resources.getSystem().displayMetrics.density).toInt() // dp to px
    val Int.px: Int
        get() = (this * Resources.getSystem().displayMetrics.density).toInt() // px to dp

    public fun saveToken(token: String) {
        val sharedPreferences = getSharedPreferences("APIToken", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("Token", token)
    }
}