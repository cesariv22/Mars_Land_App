package com.example.mars_land_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mars_land_app.Model.Remote.MarsRealState
import com.example.mars_land_app.databinding.MarsItemBinding


class AdapterMars  : RecyclerView.Adapter<AdapterMars.MarsVH>() {

    private var listMarsItem = listOf<MarsRealState>()

    // Para seleccinar tenemos una variables que tiene un listado de nuestra clase
    val selectedTerrain = MutableLiveData<MarsRealState>()

    // Funcion  para seleccionar
    fun selectedItem(): LiveData<MarsRealState> = selectedTerrain


    fun update(list: List<MarsRealState>) {
        listMarsItem = list
        notifyDataSetChanged()
    }


    //1 INNER CLASS
    inner class MarsVH(private val binding: MarsItemBinding) :
        RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {
        fun bin(mars: MarsRealState) {
            // Acá glide no olvidar dependencias
            Glide.with(binding.imageView2).load(mars.img_Src).into(binding.imageView2)
            // Activar el Clik
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            // para seleccionar
            selectedTerrain.value = listMarsItem[adapterPosition]
        }

    }


    //_______________________________Estos Métodos se implementan----------------------------

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsVH {
        return MarsVH(MarsItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MarsVH, position: Int) {
        val terrain = listMarsItem[position]
        holder.bin(terrain)
    }

    override fun getItemCount(): Int =
        listMarsItem.size
}


