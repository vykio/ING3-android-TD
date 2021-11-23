package fr.avasseur.td5.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.avasseur.td5.R
import fr.avasseur.td5.models.Contact

class ContactsAdapter(contacts :List<Contact>) : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    var mContacts: List<Contact> = contacts

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var firstNameTextView: TextView = itemView.findViewById(R.id.contactFirstname) as TextView
        var lastNameTextView: TextView = itemView.findViewById(R.id.contactLastname) as TextView
        var contactImageView: ImageView = itemView.findViewById(R.id.contactImageView) as ImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context: Context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.firstNameTextView.text = mContacts[position].prenom
        holder.lastNameTextView.text = mContacts[position].nom
        Glide.with(holder.contactImageView).load(mContacts[position].imageUrl).centerCrop().into(holder.contactImageView)
    }

    override fun getItemCount(): Int {
        return mContacts.size
    }

}