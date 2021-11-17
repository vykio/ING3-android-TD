package fr.avasseur.td4.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.avasseur.td4.R
import fr.avasseur.td4.models.Element
import fr.avasseur.td4.viewholder.ElementViewHolder

class ElementAdapter(private var elements: List<Element>) : RecyclerView.Adapter<ElementViewHolder>() {

    private lateinit var mContext: Context;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_element, parent, false)
        mContext = parent.context
        return ElementViewHolder(view)
    }

    override fun onBindViewHolder(holder: ElementViewHolder, position: Int) {
        holder.bind(position, mContext, elements)
    }

    override fun getItemCount(): Int {
        return elements.size
    }
}