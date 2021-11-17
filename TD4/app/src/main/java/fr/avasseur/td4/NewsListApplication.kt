package fr.avasseur.td4

import android.app.Application

class NewsListApplication: Application() {

    var username: String? = null

    override fun onCreate() {
        super.onCreate()
        this.username = null;
    }

}