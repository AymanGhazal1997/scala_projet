Projet Scala - Gestion de Bibliothèque  (créée par MOUTARAD Hicham et GHAZAL Ayman)


Ce descriptif explique la méthodologie ainsi que les attributs de chaque classe.


Classes & Attributs

[Attributs] 
Livre
- `titre` :  Une chaîne de caractères
- `auteur` : Une chaîne de caractères
- `annee de publication` : Une valeur numérique
- `estEmprunte` : Un booléen (par défaut à false, false signifie que le livre n'est pas emprunté et est disponible dans la bibliothèque)

Bibliotheque
- `listeDeLivres` : Une liste initialement vide, mais à laquelle on ajoutera des instances de la classe Livre.
  
[Méthodes]
 Livre
 - `emprunter` : Modifie l'état d'un livre récemment emprunté à TRUE
 - `rendre` : Modifie l'état d'un livre précédemment emprunté et rendu à FALSE
 - 
Bibliotheque
- `ajouterLivre` : Prend en paramètre un objet de type Livre à ajouter à la liste listeDeLivres.
- `rechercherParTitre`: Prend en paramètre le titre d'un livre de type chaîne de caractères, recherche le livre dans la liste par son titre, et renvoie une Option[Livre] qui peut contenir soit une valeur (Some) si le livre est trouvé, soit aucune valeur (None) s'il n'est pas trouvé.
- `rechercherParAuteur`: Fonctionnant de la même manière que la méthode rechercherParTitre, cette méthode effectue la recherche en fonction de l'auteur du livre.
- `emprunterLivre`: Recherche un livre par son titre, tente de l'emprunter s'il n'est pas déjà emprunté, renvoie un succès, sinon signale une erreur si le livre est déjà emprunté ou introuvable.
- `rendrelivre`: Recherche un livre par son titre, tente de le rendre s'il est emprunté, renvoie un succès, sinon signale une erreur si le livre n'est pas actuellement emprunté ou introuvable.
- `sauvegarderDansFichier`: Prend un nom de fichier en paramètre et utilise PrintWriter pour écrire les données de la liste de livres dans ce fichier.
- `chargerDepuisFichier`: Prend le fichier contenant les données de la bibliothèque. Elle utilise getResourceAsStream pour obtenir un flux depuis le fichier dans les ressources de la classe. Ensuite, elle parcourt chaque ligne du fichier, crée un nouvel objet Livre avec les données de la ligne, et l'ajoute à la liste de livres à l'aide de la fonction `ajouterLivre`.
  
/Testing/

Des tests unitaires ont été réalisés pour les deux méthodes clés, à savoir 'emprunterLivre' et 'rendreLivre'. Ces derniers se trouvent dans le fichier src/test/scala/BiblioTest.scala.

/Fichier de données mis à jour/

Une fois le fichier sauvegardé après avoir lancé une opération donnée, les modifications seront visibles sur le fichier biblio.csv dont le path est target/scala-2.13/classes/biblio.csv.







