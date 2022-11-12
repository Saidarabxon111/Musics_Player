package uz.saidarabxon.musicplayer.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.saidarabxon.musicplayer.R
import uz.saidarabxon.musicplayer.databinding.ActivityFavouriteBinding

class Favourite_activity : AppCompatActivity() {
    private lateinit var binding: ActivityFavouriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.coolPink)

        binding =ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}