package com.dicoding.mindspace.view.customview

import android.content.Context
import android.graphics.Canvas
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatEditText

class EditTextPassword : AppCompatEditText {
    var allowed : Boolean = false

    constructor(context: Context) : super(context) {
        passAllowed()
    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        passAllowed()
    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

        passAllowed()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }
    private fun passAllowed(){
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().length < 8) {
                    allowed = false
                    setError("Password tidak boleh kurang dari 8 karakter", null)
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