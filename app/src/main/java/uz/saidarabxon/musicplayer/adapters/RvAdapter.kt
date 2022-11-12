package uz.saidarabxon.musicplayer.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.saidarabxon.musicplayer.databinding.MusicViewBinding
import uz.saidarabxon.musicplayer.utils.Music

class RvAdapter(var context: Context, private var musicList: ArrayList<Music>) : RecyclerView.Adapter<RvAdapter.Vh>() {

     class Vh( var item: MusicViewBinding): RecyclerView.ViewHolder(item.root){
//        fun onBind(position1: String, position: Int){
//        }
        val title   = item.songName
        val album  = item.songAlbum
        val image =item.imageMusicVIew
        val duration =item.songDuration

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(MusicViewBinding.inflate(LayoutInflater.from(context) , parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
//        holder.title.text = list[position]
        holder.title.text = musicList[position].title
        holder.album.text = musicList[position].album
        holder.duration.text = musicList[position].duration.toString()

//        holder.onBind(musicList[position], position)
    }

    override fun getItemCount(): Int = musicList.size

}