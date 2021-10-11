package com.example.funcy_portfolio_android.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val Worklist = listOf<work>(
            work(R.drawable.garden_strand, "garden_strand", "ネックレス"),
            work(R.drawable.gatsby_hat, "gatsby_hat","ハット"),
            work(R.drawable.stella_sunglasses, "stella_sunglasses","グラス"),
            work(R.drawable.strut_earrings, "strut_earrings","イヤリング"),
            work(R.drawable.vagabond_sack, "vagabond_sack","リュックサック"),
            work(R.drawable.varsity_sovks, "varsity_sovks","ソックス"),
            work(R.drawable.whitey_belt, "whitey_belt","ベルト"),

            )
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        recyclerView.adapter = CardAdapter(Worklist)
        recyclerView.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)

//        setSupportActionBar(binding.toolbar)
//
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
//        val navController = navHostFragment.navController
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    // オプションメニューを使う場合に参考になりそうなので残し
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.action_settings -> true
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

}