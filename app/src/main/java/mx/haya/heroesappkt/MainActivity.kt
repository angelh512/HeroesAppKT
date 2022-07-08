package mx.haya.heroesappkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint
import mx.haya.heroesappkt.util.OnBackPressedListener

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.main_activity) {

    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)



    }

    override fun onBackPressed() {

        val methodName = "onBackPressed"

        val currentFragment = navHostFragment.childFragmentManager.fragments.get(0)

        if (currentFragment is OnBackPressedListener) {
            currentFragment.onBackPressed()
        } else {
            Log.e(methodName, "Does not hava onBackPressed")
        }

    }

    override fun onSupportNavigateUp(): Boolean {

        Log.e("onSupportNavigateUp", "toolbar back")

        onBackPressed()

        return true
    }
}
