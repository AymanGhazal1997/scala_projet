class Livre (var titre: String, var auteur: String, var anneeDePublication: Int, var estEmprunte: Boolean) {
  def emprunter(): Unit = {
    estEmprunte = true
  }

  def rendre(): Unit = {
    estEmprunte = false
  }

  override def toString: String = s"$titre;$auteur;$anneeDePublication;$estEmprunte"

  override def equals(other: Any): Boolean = other match {
    case that: Livre =>
      this.titre == that.titre &&
        this.auteur == that.auteur &&
        this.anneeDePublication == that.anneeDePublication &&
        this.estEmprunte == that.estEmprunte
    case _ => false
  }

}
