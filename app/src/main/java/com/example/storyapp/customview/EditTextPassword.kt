package com.example.storyapp.customview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.example.storyapp.R

class EditTextPassword : AppCompatEditText {

    private lateinit var visibilityIcon: Drawable
    private lateinit var visibilityOffIcon: Drawable
    private var isVisible = false

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttrs: Int) : super(
        context,
        attrs,
        defStyleAttrs
    ) {
        init()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun init() {
        visibilityIcon =
            ContextCompat.getDrawable(context, R.drawable.baseline_visibility_24) as Drawable
        visibilityOffIcon =
            ContextCompat.getDrawable(context, R.drawable.baseline_visibility_off_24) as Drawable
        inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        compoundDrawablePadding = 16

        setHint(R.string.password)
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                error = if (!s.isNullOrEmpty() && s.length < 8)
                    context.getString(R.string.password_warning)
                else null
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        updateDrawable()
        setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP &&
                event.rawX >= (right - compoundDrawablesRelative[2].bounds.width())
            ) {
                togglePasswordVisibility()
                return@setOnTouchListener true
            }
            false
        }
    }

    private fun togglePasswordVisibility() {
        isVisible = !isVisible
        updateDrawable()
        inputType =
            if (isVisible) InputType.TYPE_CLASS_TEXT else InputType.TYPE_TEXT_VARIATION_PASSWORD
    }

    private fun updateDrawable() {
        val icon = if (isVisible) visibilityIcon else visibilityOffIcon
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, icon, null)
    }
}