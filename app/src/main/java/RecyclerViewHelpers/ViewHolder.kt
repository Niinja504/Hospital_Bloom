package RecyclerViewHelpers

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import luis_dominguez.eduardo_sanchez.hospital_bloom.R

class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val txtNombre: TextView = view.findViewById(R.id.txtNombreCard)
    val imgBorrar: ImageView = view.findViewById(R.id.imgBorrar)
    val imgEditar: ImageView = view.findViewById(R.id.imgEditar)

}