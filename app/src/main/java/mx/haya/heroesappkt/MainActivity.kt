package mx.haya.heroesappkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import mx.haya.heroesappkt.ui.hero.HeroFragment
import mx.haya.heroesappkt.ui.home.HomeFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HeroFragment.newInstance())
                .commitNow()
        }
    }
}