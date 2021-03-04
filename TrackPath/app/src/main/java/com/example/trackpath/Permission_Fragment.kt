package com.example.trackpath

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.trackpath.databinding.FragmentPermissionBinding
import com.example.trackpath.location_permission.haspermission
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.dialogs.SettingsDialog


class Permission_Fragment : Fragment(),EasyPermissions.PermissionCallbacks {

       private var _binding: FragmentPermissionBinding?=null
        private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        _binding=FragmentPermissionBinding.inflate(inflater, container, false)

         binding.Continue.setOnClickListener{
             if(haspermission(requireContext()))
             {
                 findNavController().navigate(R.id.action_permission_Fragment_to_maps_Fragment)
             }
             else
             {
                 requestpermission(this)
             }
         }
        return binding.root
    }



    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        if(EasyPermissions.somePermissionPermanentlyDenied(this,perms[0]))
        {
            SettingsDialog.Builder(requireActivity()).build().show()
        }
        else
        {
            requestpermission(this)
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        findNavController().navigate(R.id.action_permission_Fragment_to_maps_Fragment)
    }

    fun hasBackgroundPermission(context: Context): Boolean{
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.Q)
        {
            return EasyPermissions.hasPermissions(context,
            android.Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        }

        return false

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}
