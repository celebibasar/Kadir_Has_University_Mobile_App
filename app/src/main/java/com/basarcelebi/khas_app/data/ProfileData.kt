package com.basarcelebi.khas_app.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Security
import com.basarcelebi.khas_app.R
import com.basarcelebi.khas_app.model.ProfileOptions

object ProfileData {
    val defaultData = ProfileOptionsData()[0]

    fun ProfileOptionsData():List<ProfileOptions>
    {
        return listOf(
            ProfileOptions(
                name = "Account",
                description = "You can see the detail of your account settings",
                icon = Icons.Default.AccountCircle

            ),
            ProfileOptions(
                name = "Security",
                description = "You can access the privacy policy of the Khas_App",
                icon = Icons.Default.Security

            )

        )

    }
}