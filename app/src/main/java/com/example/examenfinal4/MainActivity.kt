package com.example.examenfinal4

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.examenfinal4.databinding.ActivityMainBinding
import com.example.examenfinal4.fragments.ApiFragment
import com.example.examenfinal4.fragments.InicioFragment
import com.example.examenfinal4.fragments.RoomFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    lateinit var binding: ActivityMainBinding

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

        drawerLayout = findViewById<DrawerLayout>(R.id.main)

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)


        val toolBar: androidx.appcompat.widget.Toolbar =
            findViewById<androidx.appcompat.widget.Toolbar>(R.id.appbar)
        setSupportActionBar(toolBar)

        // Configurar el botón de hamburguesa para abrir/cerrar el menú lateral
        toggle = ActionBarDrawerToggle(this, drawerLayout, toolBar, R.string.open_drawer, R.string.close_drawer)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


    //Inicializamos con el HomeFragment
    supportFragmentManager.beginTransaction()
    .replace(R.id.container, InicioFragment())
    .commit()
    binding.bottomNavigation.setOnNavigationItemSelectedListener {
        it.isChecked = true
        when (it.itemId) {
            R.id.navigation_room -> {
                // Acción al seleccionar Home
                replaceFragment(RoomFragment())
            }
            R.id.navigation_api -> {
                // Acción al seleccionar Dashboard
                replaceFragment(ApiFragment())
            }

        }
        false
    }

}

//Funcion para ir cambiando de fragments
private fun replaceFragment(fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(R.id.container, fragment)
        .commit()
}

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> supportFragmentManager.beginTransaction()
                .replace(R.id.container, InicioFragment()).commit()
            R.id.nav_settings -> supportFragmentManager.beginTransaction()
                .replace(R.id.container, ApiFragment()).commit()
            R.id.nav_share -> supportFragmentManager.beginTransaction()
                .replace(R.id.container, RoomFragment()).commit()
            R.id.nav_logout -> Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show()
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}