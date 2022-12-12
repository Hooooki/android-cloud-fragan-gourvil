package fr.fragan.ccm.androidcloudproject.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.fragan.ccm.androidcloudproject.databinding.ActivityMainBinding
import fr.fragan.ccm.androidcloudproject.view.recycler.PhoneListActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.mainButtonRecyclerView.setOnClickListener { goToRecyclerView() }
    }

    private fun goToRecyclerView() {
        startActivity(Intent(this, PhoneListActivity::class.java))
    }

}