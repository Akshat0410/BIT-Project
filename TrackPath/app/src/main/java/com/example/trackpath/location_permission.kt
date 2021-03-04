package com.example.trackpath

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.trackpath.Constants.permission_access_code
import com.vmadalin.easypermissions.EasyPermissions
import java.util.jar.Manifest

object location_permission {

    fun haspermission(context: Context)=
        EasyPermissions.hasPermissions(
            context,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
    }

    fun requestpermission(fragment: Fragment)
    {
        EasyPermissions.requestPermissions(fragment,
        "This App will not work if location is denied",
        permission_access_code,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
    }
