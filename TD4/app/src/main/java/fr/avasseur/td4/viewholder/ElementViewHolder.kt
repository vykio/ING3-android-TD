package fr.avasseur.td4.viewholder

import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import fr.avasseur.td4.R
import fr.avasseur.td4.models.Element

class ElementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val name = itemView.findViewById<TextView>(R.id.item_element_name)

    fun bind(position: Int, context: Context, elements: List<Element>)
    {
        name.text = elements[position].name
        name.setOnClickListener {
            Toast.makeText(context, name.text.toString(), Toast.LENGTH_SHORT).show()
        }
    }

}