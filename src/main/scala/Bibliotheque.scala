import scala.collection.mutable.ListBuffer
import java.io.InputStream
import java.io.{File, FileInputStream, ObjectInputStream, PrintWriter}
import scala.io.Source
import scala.io.Source.fromResource
import scala.util.{Failure, Success, Try}

class Bibliotheque {
  var listeDeLivres: List[Livre] = List()

  def ajouterLivre(livre: Livre): Unit = {
    listeDeLivres = listeDeLivres :+ livre
  }

  def emprunterLivre(titre: String): Try[Unit] = {
    var livre=rechercherParTitre(titre)
    livre match {
      case Some(livre) if !livre.estEmprunte =>
        livre.emprunter()
        Success(())
      case Some(_) =>
        Failure(new Exception("Le livre est déjà emprunté."))
      case None =>
        Failure(new Exception("Livre non trouvé."))
    }
  }

  def rendreLivre(titre: String): Try[Unit] = {
    var livre=rechercherParTitre(titre)
    livre match {
      case Some(livre) if livre.estEmprunte =>
        livre.rendre()
        Success(())
      case Some(_) =>
        Failure(new Exception("Le livre n'est pas actuellement emprunté."))
      case None =>
        Failure(new Exception("Livre non trouvé."))
    }
  }

  def rechercherParTitre(titre: String): Option[Livre] = {
    listeDeLivres.find(_.titre.toLowerCase == titre.toLowerCase)
  }


  def rechercherParAuteur(auteur: String): Option[Livre] = {
    listeDeLivres.find(_.auteur.toLowerCase == auteur.toLowerCase)
  }

  def sauvegarderDansFichier(nomFichier: String): Unit = {
      val outputPath = s"$nomFichier"
      val p=getClass.getResource(outputPath).getPath
      val writer = new java.io.PrintWriter(new java.io.File(p))
      try {
        listeDeLivres.foreach { line =>writer.println(line)}
        println(s"Data saved to $outputPath (${listeDeLivres.length})")
      } finally {
        writer.close()
      }
  }



  def chargerDepuisFichier(nomFichier: String): Unit = {
    val resourceStream = getClass.getResourceAsStream(nomFichier)
    if (resourceStream == null) {
      println(s"Le fichier $nomFichier n'est pas retrouvé.")
    } else {
      val source = Source.fromInputStream(resourceStream)
      for (line <- source.getLines) {
        val fields = line.split(";")
        if (fields.length == 4) {
          val titre = fields(0)
          val auteur = fields(1)
          val annee = fields(2).toInt
          val estEmprunte = fields(3).toBoolean
          val livre = new Livre(titre, auteur, annee, estEmprunte)
          ajouterLivre(livre)
        }
      }
      println(s"data loaded (${listeDeLivres.length})")
      source.close()
    }
  }

}


