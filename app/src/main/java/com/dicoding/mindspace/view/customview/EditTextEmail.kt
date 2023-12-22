package com.dicoding.mindspace.view.customview

import android.content.Context
import android.graphics.Canvas
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatEditText

class EditTextEmail : AppCompatEditText {
    var allowed : Boolean = false

    constructor(context: Context) : super(context) {
        emailAllowed()
    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        emailAllowed()
    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        emailAllowed()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }
    private fun emailAllowed(){
        fun isValidEmail(email: String): Boolean {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!isValidEmail(s.toString())) {
                    allowed = false
                    setError("Email Tidak Valid", null)
                } else {
                    allowed = true
                    error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }
}
