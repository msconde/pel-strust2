# Paris en ligne
## Application Web

*Paris en ligne* est un exemple d'application Web sous Struts 2.

### Licence

Cet exemple est publié sous la licence MIT.

### Prérequis

#### Git (optionnel)

Afin d'utiliser les capacités de versionnage de cet exemple, il est recommandé d'employer l'outil Git.

##### Linux

**Remarque** : L'installation de Git sur Linux nécessite généralement de disposer des droits superutilisateur.

###### Debian/Ubuntu et dérivés

```bash
sudo apt update
sudo apt install git
```

###### Arch Linux/Manjaro

Il est nécessaire d'avoir accès au superutilisateur `root` pour exécuter ces commandes.

```bash
su -
pacman -Sy
pacman -S git 
```

##### Microsoft Windows

Téléchargez l'assistant d'installation depuis le [site officiel du projet Git](https://git-scm.com/ "Accueil du projet Git").  
**Remarque** : L'assistant d'installation nécessite que votre compte Windows possède les droits administrateur
sur votre machine.

Alternativement, vous pouvez télécharger la version portable depuis ce même site si vous n'avez pas
les droits administrateur Windows.

#### Maven
Ce projet utilise le gestionnaire de dépendances [Maven](https://apache.maven.org/ "Accueil du projet Maven").
Il peut donc être importé dans la majorité des logiciels de développement (IDE).  
Cet exemple a été conçu avec l'IDE [Apache NetBeans](https://netbeans.apache.org/ "Accueil du projet NetBeans").

Une connexion Internet est requise pour le téléchargement des outils, des plug-ins et des dépendances
par Maven.

### Téléchargement

```bash
git clone https://github.com/DirectX-Box/pel-struts2.git
```
Un ZIP peut également être téléchargé à travers l'interface Web.

### Installation

+ Ouvrez l'onglet [Releases](https://github.com/DirectX-Box/pel-struts2/releases "Releases Paris en ligne")
et téléchargez le fichier `pelmodele.zip`.
+ Décompressez l'archive dans un répertoire `pelmodele`.
+ Ouvrez le projet dans votre IDE.
+ Effectuez la tâche `maven:install`. La pratique à suivre dépend de l'IDE.

Alternativement, utilisez la commande :
```bash
mvn install
```
Cependant, cette commande ne fonctionne que si Maven est installé
sur le système. Généralement, les IDEs fournissent Maven sous
la forme d'un plug-in.

+ Ouvrez le projet `pel-struts2` dans votre IDE.
+ Compilez le projet avec l'action `Build`. Cette action dépend de l'IDE.
+ Effectuez la tâche `jetty:run`. La pratique à suivre dépend de l'IDE.

### Liens utiles

+ [Site Web du projet Struts 2](https://struts.apache.org/ "Accueil du projet Struts 2") (en anglais).
+ [Guide GitHub sur l'outil Git](https://guides.github.com/introduction/git-handbook/ "Manuel pratique Git de GitHub") (en anglais).