class Livre (var titre: String, var auteur: String, var anneeDePublication: Int, var estEmprunte: Boolean) {
    // Méthode pour emprunter un livre
  def emprunter(): Unit = {
    estEmprunte = true
  }
 // Méthode pour rendre un livre
  def rendre(): Unit = {
    estEmprunte = false
  }
// Redéfinir la méthode toString pour afficher les détails du livre
  override def toString: String = s"$titre;$auteur;$anneeDePublication;$estEmprunte"
// Redéfinir la méthode equals pour comparer deux livres
  override def equals(other: Any): Boolean = other match {
    case that: Livre =>
      this.titre == that.titre &&
        this.auteur == that.auteur &&
        this.anneeDePublication == that.anneeDePublication &&
        this.estEmprunte == that.estEmprunte
    case _ => false
  }

}
