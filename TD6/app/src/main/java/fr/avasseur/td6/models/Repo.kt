package fr.avasseur.td6.models

import com.google.gson.annotations.SerializedName

class Repo {

    var id = 0
    var name: String? = null
    @SerializedName("full_name")
    var full_name: String? = null
    var html_url: String? = null

}