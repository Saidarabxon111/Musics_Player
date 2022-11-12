package uz.saidarabxon.musicplayer.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import uz.saidarabxon.musicplayer.PlayList_Activity
import uz.saidarabxon.musicplayer.PlayerActivity
import uz.saidarabxon.musicplayer.R
import uz.saidarabxon.musicplayer.adapters.RvAdapter
import uz.saidarabxon.musicplayer.databinding.ActivityMainBinding
import uz.saidarabxon.musicplayer.utils.Music
import java.io.File
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var rvAdapter: RvAdapter
//    private lateinit var list: ArrayList<String>

    companion object{
     lateinit   var MusicList :ArrayList<Music>
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeLayout()



        binding.shuffle.setOnClickListener {
            startActivity(Intent(this, PlayerActivity::class.java))
        }
        binding.favourite.setOnClickListener {
            startActivity(Intent(this, Favourite_activity::class.java))
        }
        binding.playlist.setOnClickListener {
            startActivity(Intent(this, PlayList_Activity::class.java))
        }

        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navFeedback -> Toast.makeText(baseContext, "FeedBack", Toast.LENGTH_SHORT)
                    .show()
                R.id.navSetting -> Toast.makeText(baseContext, "Settings", Toast.LENGTH_SHORT)
                    .show()
                R.id.navAbout -> Toast.makeText(baseContext, "About", Toast.LENGTH_SHORT).show()
                R.id.navExit -> exitProcess(1)
            }
            true
        }

    }

    private fun requestRunTimePermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                13
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 13) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this, "permission granted", Toast.LENGTH_SHORT).show()
            else
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    13
                )
        }

    }


    // drawer layout
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)


    }

    private fun initializeLayout() {
        requestRunTimePermission()
        setTheme(R.style.coolPinkNav)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // drawer layout
        toggle = ActionBarDrawerToggle(this, binding.root, R.string.open, R.string.close)
        binding.root.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        MusicList =getALlAudio()
//        list = ArrayList()
////val musicList = MusicList
//        list.add("1 song")
//        list.add("2 song")
//        list.add("3 song")
//        list.add("4 song")
//        list.add("5 song")


        binding.rv.setHasFixedSize(true)
        binding.rv.setItemViewCacheSize(13)
        binding.rv.layoutManager = LinearLayoutManager(this@MainActivity)
        rvAdapter = RvAdapter(this@MainActivity, MusicList)
        binding.rv.adapter = rvAdapter
        binding.total.text = "Total Songs:" + rvAdapter.itemCount


    }

    @SuppressLint("Range")
    private fun getALlAudio(): ArrayList<Music> {

        val tempList = ArrayList<Music>()

        val selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0"
        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.DATE_ADDED,
            MediaStore.Audio.Media.DATA
        )

        val cursor = this.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            null,
            MediaStore.Audio.Media.DATE_ADDED + "Desc",
            null
        )

        if (cursor != null)
            if (cursor.moveToFirst())
                do {
                    val titleC = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
                    val idC = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID))
                    val albumC = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM))
                    val artistC = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))
                    val pathC = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))
                    //
                    val durationC = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))

                    val music = Music(
                        id = idC,
                        title = titleC,
                        album = albumC,
                        path = pathC,
                        duration = durationC,
                        artist = artistC
                    )
                    val file = File(music.path)
                    if (file.exists())

                        tempList.add(music)

                } while (cursor.moveToNext())
        cursor?.close()

        return tempList

    }
}