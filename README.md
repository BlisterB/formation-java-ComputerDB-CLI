# TP 1: Les bases de Java
### Exercice 1 : ComputerDB CLI
##### Problématique

Un client vous a demandé de développer une application pour la gestion de son parc informatique. Il vous fournit une base de données contenant les informations sur ses ordinateurs.
L'application sera une interface en ligne de commande (CLI).

Le client veut pouvoir :
 - lister les ordinateurs
 - ajouter un ordinateur

Une compagnie (Company) est caractérisée par :
 - un identifiant numérique
 - un nom

Un ordinateur (Computer) est caractérisé par
 - un identifiant numérique
 - un nom 
 - une date d'entrée dans le parc de l'entreprise 
 - une date de sortie s'il n'y est plus présent
 - une compagnie, qui correspond au constructeur de l'ordinateur

Le client vous a aussi prévenu que la base de données n'est pas consistante : il faudra donc faire attention aux données pouvant être incohérentes.

##### Prérequis
 - Cloner ce dépot git, votre repertoire de travail :
```sh
git clone https://github.com/NicolasThierion/formation-java-ComputerDB-CLI -b step-1
```

### Exercice 1 : Un CLI mocké, partie 1

Dans cet exercice, nous allons mettre en oeuvre les bases de notre CLI.
Pour ne pas compliquer le TP avec la gestion de la base de données, nous allons dans un premier temps utiliser la classe ComputerDB.
Cette classe simule une base de données à l'aide de données "en dur" dans le code (on dit qu'elle "mocke" la base de données).

 - Ecrire les classes "Computer" et "Company"
    - Dans un soucis de simplicité, nous utiliserons des Strings pour modéliser les dates.
    - Pensez à surcharger la méthode toString( ) afin de pouvoir afficher les information de ces objets à l'aide de la fonction System.out.println( )
 - Ecrire la classe Cli contenant la méthode main(). Cli affiche le menu contextuel de l'application (le CLI)
 - Utiliser la classe ComputerDB pour lister et ajouter des Computers
    - ComputerDB est fournie dans le package src.com.formation.ensta.computerdb
    - pensez à consulter les fonctions selectCompanies( ) et selectComputers( ) de cette classe

NB : Vous rappellez-vous de la contrainte des données non consistantes ? Que se passe-t'il lorsque l'on ajoute un nouveau Computer avec un ID de Company inexistante ?

Lorsque cet exercice est terminé, veuillez le valider avec les commandes suivantes :
```sh
git add . -A
git commit -m "step 1"
```
Ces lignes de commandes permettent d'enregistrer votre travail à l'aide de l'outil Git.

### Exercice 2 : Un CLI mocké, partie 2
Nous allons maintenant utiliser des concepts avancés de Java afin d'étoffer notre CLI.
 - Ajouter la gestion d'erreurs par exceptions
    - un comportement interdit justifie de lever une exception
    - ajouter un Computer donc la Company n'existe pas est-il un comportement autorisable ?
 - Créer l'interface "Identifiable"
    - elle possède la méthode 'getId()'    
    - Company et Computer doivent désormais implémenter cette interface
 - Le CLI doit maintenant permettre de lister de ajouter des Company
 - Utiliser la serialization pour sauvegarder et charger la liste d'ordinateurs et de companies
    - Company et Computer doivent maintenant implémenter l'interface Serializable
    - Utiliser les objets FileOutputStream et ObjectOutputStream afin d'enregistrer la liste de Computers dans un fichier computers.txt
    - Vous pouvez utiliser cette [ressources en ligne](http://www.jmdoudoux.fr/java/dej/chap-serialisation.htm) afin d'apréhender l'utilisation de ces outils

### Exercice 3 : Connexion à une vraie base de données
Nous allons créer notre base de données, et la remplir à l'aide des fichiers '1-schema.sql' et '2-entries.sql'.
 - Naviguez jusqu'au dossier contenant '1-schema.sql' et '2-entries.sql'
 - Accédez à votre client mysql
```sh
mysql -u[login] -p[mot de passe]
```
Si le login de votre base de donnée est "chuck" et que son mot de passe est "norris", la commande à exécuter est :
```sh
mysql -uchuck -pnorris
```

- Créez une base de données SQL initialisée avec les fichiers '1-schema.sql' et '2-entries.sql'
    - Dans le CLI de mysql, entrez les commandes suivantes :
```sh
SOURCE 1-schema.sql
SOURCE 2-entries.sql
```

Une fois votre base de données initialisée et remplie, il devient possible de récupérer ses données dans votre CLI.
 - Créez une classe CompanyDAO permettant de lister les Companies stockée dans la base de donnée et d'en ajouter. Puis une classe ComputerDAO faisant de même avec les Computers.
    - Utiliser JDBC pour effectuer les requètes SQL récupèrant et ajoutant les données.
 - Dans la classe CLI, remplacez les appels à la classe ComputerDB par des appels à vos classe CompanyDAO et ComputerDAO.
 
 Vous avez donc obtenu un CLI permettant de communiquer avec la base de donnée de manière compréhensible pour un utilisateur lambda, votre cahier des charges est rempli !

Pour aller plus loin :
- Implémenter la suppression des Computer
- Implémenter la suppresion des Companies
    - Que faire en cas de suppression d'une Company référencée par plusieurs Computer ?
    - Demandez une confirmation au client lorsque vous détectez ce cas, puis supprimez les Computer concernés avant de supprimer la Company.
- Implémentez la recherche par critère.
    - Par nom. Pour cela, interessez vous à la clause LIKE des requêtes SQL
    - Par date d'entrée ou sortie du parc informatique.
- Implémentez la mise à jour des Computers/Companies. Permettant de modifier un objet déjà existant en base.
