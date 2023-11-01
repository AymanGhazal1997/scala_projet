import org.scalatest._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.util.{Failure, Success}

  class BiblioTest  extends AnyFlatSpec with Matchers {

    "Bibliotheque" should "add a book to the library" in {
      val bibliotheque = new Bibliotheque
      val livre = new Livre("Book Title", "Author Name", 2023, false)
      bibliotheque.ajouterLivre(livre)
      bibliotheque.listeDeLivres should contain(livre)
    }

    it should "borrow a book successfully" in {
      var bibliotheque = new Bibliotheque
      var livre = new Livre("Book Title", "Author Name", 2023, false)
      bibliotheque.ajouterLivre(livre)
      val result = bibliotheque.emprunterLivre("Book Title")
      result should be(Success(()))
      livre.estEmprunte should be(true)
    }

    it should "fail to borrow a book that is already borrowed" in {
      val bibliotheque = new Bibliotheque
      val livre = new Livre("Book Title", "Author Name", 2023, true)
      bibliotheque.ajouterLivre(livre)
      val result = bibliotheque.emprunterLivre("Book Title")
      result.failed.get.getMessage should be("Le livre est déjà emprunté.")
    }

    it should "fail to borrow a book that is not in the library" in {
      val bibliotheque = new Bibliotheque
      val result = bibliotheque.emprunterLivre("Book Title")
      result.failed.get.getMessage should be("Livre non trouvé.")
    }

    it should "return a borrowed book successfully" in {
      val bibliotheque = new Bibliotheque
      val livre = new Livre("Book Title", "Author Name", 2023, true)
      bibliotheque.ajouterLivre(livre)
      val result = bibliotheque.rendreLivre("Book Title")
      result should be(Success(()))
      livre.estEmprunte should be(false)
    }

    it should "fail to return a book that is not borrowed" in {
      val bibliotheque = new Bibliotheque
      val livre = new Livre("Book Title", "Author Name", 2023, false)
      bibliotheque.ajouterLivre(livre)
      val result = bibliotheque.rendreLivre("Book Title")
      result.failed.get.getMessage should be("Le livre n'est pas actuellement emprunté.")
      livre.estEmprunte should be(false)
    }

    it should "fail to return a book that is not in the library" in {
      val bibliotheque = new Bibliotheque
      val result = bibliotheque.rendreLivre("Book Title")
      result.failed.get.getMessage should be("Livre non trouvé.")
    }
  }


