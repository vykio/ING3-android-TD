package fr.avasseur.td5.models

class Contact {

    var nom: String = "";
    var prenom: String = "";
    var imageUrl: String = "";

    constructor(nom: String, prenom: String, imageUrl: String)
    {
        this.nom = nom;
        this.prenom = prenom;
        this.imageUrl = imageUrl;
    }

}