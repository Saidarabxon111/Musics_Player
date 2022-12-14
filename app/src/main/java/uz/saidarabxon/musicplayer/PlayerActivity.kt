package uz.saidarabxon.musicplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.saidarabxon.musicplayer.databinding.ActivityPlayListBinding
import uz.saidarabxon.musicplayer.databinding.ActivityPlayerBinding

class PlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.coolPink)
        binding =ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}