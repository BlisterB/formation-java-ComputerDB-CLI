# TP 1: Les bases de Java
### Exercice 1 : ComputerDB CLI
##### Problématique

Un client vous a demandé de développer une application pour la gestion de son parc d'ordinateur. Il vous fournit une base de données contenant les informations sur les ordinateurs. L'application sera une interface en ligne de commande (CLI), qui proposera les fonctions demandées.

Il veut pouvoir :
 - lister les ordinateurs
 - Ajouter un ordinateur
 
Un ordinateur est caractérisé par
- un nom 
- une date d'entrée dans le parc de l'entreprise 
- une date de sortie s'ils ne sont plus présents. 
- une compagnie, qui correspond au constructeur de l'ordinateur.
- Il vous a prévenu aussi que la base de données n'est pas consistante : il faudra dons faire attention aux données.

Dans un premier temps, pour ne pas compliquer le programme avec les accès BDD, nous pouvons utiliser la class ComputerDB, qui fournit des données statiques écrites en dur dans le code.

##### Travail demandé : 
 - Cloner ce dépot git : https://github.com/NicolasThierion/formation-java-ComputerDB-CLI -b step-1
 - Ecrire les objets 'Computer' et 'Company'
 - Ecrire la classe ComputerCli, et sa méthode main() qui affiche un menu CLI.
 - Utiliser la classe ComputerDB (fournie) pour récupérer les données sur les Companies et les Computers (voir selectCompanies() et selectComputers())
 - Le CLI doit proposer de lister tous les ordinateurs, ou d'en ajouter un nouveau.
 - Surcharger les methodes 'toString()' et 'equals()' de Computer et Company.

lorsque l'étape est terminée, entrer les commandes suivantes pour valider l'étape.
'''sh
git add . -A
git commit -m"step 1"
'''

Que se passe t'il lorsqu'on ajoute un nouveau Computer et avec un ID de Company inexistant? 

### Exercice 2 : ComputerDB CLI
 - Rajouter la gestion d'erreurs par exceptions.
 - Créer l'interface "Identifiable", implémentée par Company et Computer, qui possède la méthode 'getId()'
 - Le CLI doit maintenant permettre de lister les Computer ET les Company
 - Utiliser la serialization pour sauvegarder et charger la liste d'ordinateurs et de companies, via un ObjectOutputStream
