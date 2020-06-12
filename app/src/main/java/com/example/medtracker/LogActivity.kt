package com.example.medtracker

import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_log.*

class LogActivity : AppCompatActivity(0) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)
        loadFragment(RegisterFragment())
        val line  = ConstraintSet() // creating the Constraintset for setting constraints
        line.clone(clay) // cloning the current used layout

        register.setOnClickListener {
            setLayoutLineLeft(line)
            loadFragment(RegisterFragment())
        }

        login.setOnClickListener {
            setLayoutLineRight(line)
            loadFragment(LoginFragment())
        }
    }

    private fun setLayoutLineLeft(line: ConstraintSet) {
        // This sets the indicator line to the left when selecting Register
        line.clear(registerBalk.id, ConstraintSet.TOP)
        line.clear(registerBalk.id, ConstraintSet.START)
        line.connect(registerBalk.id, ConstraintSet.TOP, register.id, ConstraintSet.BOTTOM, 8.px)
        line.connect(registerBalk.id, ConstraintSet.END, empty.id, ConstraintSet.START)
        line.applyTo(clay)
    }

    private fun setLayoutLineRight(line: ConstraintSet) {
        // This sets the indicator line to the right when selecting Login
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
    val Int.px: Int
        get() = (this * Resources.getSystem().displayMetrics.density).toInt() // px to dp





}