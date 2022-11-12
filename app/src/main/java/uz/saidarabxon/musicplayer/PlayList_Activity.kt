package uz.saidarabxon.musicplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.saidarabxon.musicplayer.databinding.ActivityPlayListBinding

class PlayList_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.coolPink)

        binding = ActivityPlayListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}