package com.mkdevelopers.permissionssolid.permissiondialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.annotation.DrawableRes
import com.mkdevelopers.permissionssolid.R
import com.mkdevelopers.permissionssolid.databinding.DialogPermissionBinding

abstract class PermissionDialog(
    context: Context
) : Dialog(context, R.style.Widget_App_Dialog) {

    abstract val description: String

    abstract val btnText: String

    @get:DrawableRes
    abstract val image: Int

    abstract fun buttonClicked()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DialogPermissionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bindUi()
    }

    private fun DialogPermissionBinding.bindUi(){

        ivPermissionImage.setImageResource(image)

        tvDescription.text = description

        tvBtn.text = btnText

        bindClick()
    }

    private fun DialogPermissionBinding.bindClick(){
        tvBtn.setOnClickListener {
            buttonClicked()
        }

        ivClose.setOnClickListener {
            dismiss()
        }
    }
}