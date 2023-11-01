import scala.io.StdIn
import scala.util.control.Breaks.break
import scala.util.{Failure, Success}

object main extends App {

  val biblio = new Bibliotheque()

  biblio.chargerDepuisFichier("biblio.csv")

  var continuer = true
  while (continuer) {
    println("\nMenu:")
    println("1. Ajouter un livre")
    println("2. Emprunter un livre")
    println("3. Rendre un livre")
    println("4. Rechercher par titre")
    println("5. Rechercher par auteur")
    println("6. Sauvegarder et quitter")
    print("Choisissez une option: ")
    try{
      var choix=StdIn.readInt()
      choix match {
        case 1 =>
          println("Entrez le titre du livre: ")
          val titre = scala.io.StdIn.readLine()
          println("Entrez l'auteur du livre: ")
          val auteur = scala.io.StdIn.readLine()
          println("Entrez l'année de publication: ")
          val annee = scala.io.StdIn.readInt()
          val nouveauLivre = new Livre(titre, auteur, annee, false)
          biblio.ajouterLivre(nouveauLivre)
          println(s"$titre a été ajouté à la bibliothèque.")

        case 2 =>
          println("Entrez le titre du livre à emprunter: ")
          val titre = scala.io.StdIn.readLine()
          biblio.emprunterLivre(titre) match {
            case Success(_) => println(s"Success : vous avez emprunté le  livre <<${titre}>>")
            case Failure(e) => println(s"Erreur : ${e.getMessage}")
          }

        case 3 =>
          println("Entrez le titre du livre à rendre: ")
          val titre = scala.io.StdIn.readLine()
          biblio.rendreLivre(titre) match {
            case Success(_) => println(s"Success : vous avez  rendu le livre <<${titre}>>")
            case Failure(e) => println(s"Erreur : ${e.getMessage}")
          }

        case 4 =>
          println("Entrez le titre à rechercher: ")
          val titre = scala.io.StdIn.readLine()
          biblio.rechercherParTitre(titre) match {
            case Some(livre) =>
              println(s"Le Livre ${livre.titre} a été retrouvé")
              println(livre.toString)
            case None =>
              Failure(new Exception("Livre non trouvé."))
          }

        case 5 =>
          println("Entrez l'auteur à rechercher: ")
          val auteur = scala.io.StdIn.readLine()
          biblio.rechercherParAuteur(auteur) match {
            case Some(livre) =>
              println(s"Le livre de l'auteur ${livre.auteur} a été retrouvé")
              println(livre.toString)
            case None =>
              Failure(new Exception("Livre non trouvé."))
          }

        case 6 =>
          biblio.sauvegarderDansFichier("biblio.csv")
          println("Bibliothèque sauvegardée. Au revoir!")
          continuer = false

        case _ =>
          println(s"${choix} est une option invalide . Veuillez choisir un nombre entre 1 et 6")
      }
    }
    catch {
      case e: NumberFormatException =>
        println(s"Option invalide . Veuillez choisir un nombre entre 1 et 6")
    }


  }

}
