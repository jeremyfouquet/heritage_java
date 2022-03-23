# EXERCICE 1 - Système scolaire

**Principe de base :**

Dans cet exercice, nous allons modéliser un établissement scolaire
composé de personnes pouvant être soit des étudiants soit des professeurs.
Un étudiant possédera : un nom, un prénom, un identifiant, la liste des
cours auxquelles il est inscrit ainsi que la liste des notes reçues pour
chacun de ses cours. Et pourra se présenter, s'inscrire ou se désinscrire d'un
cours, visualiser ses cours et ses notes ainsi que calculer sa moyenne.
Un professeur, quant à lui, possédera aussi : un nom, un prénom, un
identifiant, ainsi que la liste des cours qu'il propose. Celui-ci pourra se
présenter, visualiser la liste de ses cours, ajouter, modifier ou supprimer un
cours, voir la liste des étudiants inscrits pour un cours donné, ou attribuer
une note à un de ses étudiants.

Un cours sera caractérisé par : un lieu, un horaire, une durée, un thème, un
coefficient et un type (TP / TD / Cours magistral)

**Question :**

1 Représenter le problème suivant sous forme de diagramme de
classe

![diagramme de classe](TP9_SystemeScolaire/assets/diagramme_de_classe.drawio.svg)

2 Implémenter les classes Etudiant, Professeur et Cours ainsi que
l'interface Personne, classe mère de Professeur et Etudiant.
Utilisez une énumération pour gérer les types de cours.

3 Répondre via un commentaire dans le fichier en argumentant
votre réponse. Que pensez-vous de l'utilisation d'une interface
pour gérer le concept de personne ?
Ce choix est-il judicieux ?
Pourquoi ?

Le Choix d'une interface est une bonne utilisation ici car elle permet d'utilisé des méthodes par défauts et d'avoir la liste de tous les etudiants accessible par les professeurs grace au mot cles static.
Cependant ce n'est pas selon moi le meileur choix possible.
En effet il n'est pas possible dans une interface d'avoir des variables alors qu'ici les nom, prenom et identifiant et mesCours sont des variables communes dans Etudiant et Professeur.
Et les getters et setters sont ici uniquement des prototypes de part cette limite, alors qu'ils sont identique dans les 2 classes fille et pouraient très facilement être initialisé dans la classe mère.
Il aurrait donc mieux vallu utilisé une classe abstraite cela aurait permis de déclarer les variables nom, prenom et identifiant et mesCours dans la class mere Personne.

4 Proposer un jeu de test dans votre main permettant de tester les
différents scénarios possibles.

# EXERCICE 2 - Maison Virtuelle
 
**Principe de base :**

Dans cet exercice, nous allons modéliser virtuellement une maison. Cette
maison contiendra un ensemble de pièces à savoir : une cuisine, une
chambre, une salle de bain, un salon et des toilettes. Selon les maisons
certaines pièces pourront ou non être répétées. Chaque type de pièce
possédera une taille, une surface, une couleur, une position au sein de la
maison ainsi qu'une liste de meubles qui lui est propre. Concernant les
meubles, chacun d'eux possédera un nom, une couleur, une forme, une
taille, une position ainsi qu'un caractère permettant de le représenter. 

Dans chacune des pièces, nous retrouverons :

* Le salon : une table, un canapé, une télévision ainsi que des chaises
* La cuisine : un micro-onde, un évier, un frigo
* La salle de bain : la douche, un lavabo
* La chambre : un lit
* Les toilettes : des WC

Chaque pièce possédera aussi un objet spécifique appelé « porte »
permettant de faire le lien entre 2 pièces.

**Question :**

1 Modéliser ce problème à l'aide d'un diagramme de classe.

![diagramme de classe](TP9_MaisonVirtuelle/assets/diagramme_de_classe.drawio.svg)

2 Développer les classes évoquées dans l'énoncé en prenant en compte
les indications suivantes :

Chaque pièce pourra afficher la liste de ces objets, et s'afficher sous
forme de grille en représentant chacun de ces meubles.

La maison, quant à elle, pourra afficher l'ensemble de ces pièces en
affichant pour chacune leur nom, leurs dimensions et leur visuel. Elle
pourra aussi afficher sa surface totale.

Les meubles auront les tailles suivantes :
* table / douche / lit (carré / taille 4),
* canapé / frigo (rectangle / taille 2),
* autres objets (taille 1)

3 Coder l'objet « porte » héritant de meuble et permettant de stocker les
2 pièces qu'elle connecte. Pour déterminer le nombre de portes que
doit avoir chaque pièce, basez-vous sur la collection utilisée pour
stocker celles-ci au sein de la maison. Une pièce aura donc un
nombre de portes déterminé selon son nombre d'éléments voisins.

4 Une personne peut donc visiter la maison, en se déplaçant dans les
différentes pièces en choisissant à l'aide d'un menu la porte à ouvrir,
afficher la liste des objets présents dans la pièce, ou les
caractéristiques de celles-ci. Développer la fonctionnalité suivante
dans votre main.

5 Bonus (Pour les maîtres du Java)
Développer un générateur aléatoire de maison pouvant concevoir
diverses maisons dans lesquelles on retrouvera toujours les pièces de base, mais où il sera possible d'avoir plusieurs salles de bain / chambres voire d'autres pièces et d'autres objets.

# EXERCICE 3 - Médiathèque
 
**Présentation :**

Dans cet exercice, nous voulons simuler une médiathèque, lieu proposant des livres, des CD et des DVD. Pour cela, nous allons suivre les consignes suivantes :

* Chaque document possède un nom et un identifiant.

* Un livre à un auteur, un éditeur, un titre ainsi qu'un type (BD, Roman, Manga, Journal, Nouvelle) et ne peut être conservé qu'un mois maximum.

* Un CD à un titre, un auteur, un compositeur et ne peut être conservé qu'une semaine.

* Un DVD à un titre, un studio de développement et comme pour un CD, il ne peut être conservé qu'une semaine.

Les abonnés peuvent emprunter jusqu'à 3 documents et consulter ceux qu'ils n'ont pas encore rendu. Attention tous les documents ne peuvent pas être empruntés, certains seront uniquement disponible sur place.

Seuls les membres du personnel ont le droit d'ajouter de nouveaux documents ou de rendre certains indisponibles pour cause de rénovation.

**Question :**

1 Représenter sous forme de diagramme de classe ce problème 

![diagramme de classe](TP9_Mediatheque/assets/diagramme_de_classe.drawio.svg)

2 Coder les différentes fonctionnalités en utilisant de la POO. Faites attention aux cas d'erreurs potentielles.

3 Dans votre main, réaliser un jeu de test permettant de valider vos différentes fonctionnalités. Pour rappel, un jeu de test ne doit pas permettre à l'utilisateur de tester votre programme mais plutôt montrer les différents cas que vous avez vous même testé. Pour réaliser cela, vous êtes libre d'utiliser une technologie comme JUNIT ou de les faire à la main dans votre main. Chaque test doit avoir un commentaire précisant le cas à tester (cas normal ou particulier), exécuter les méthodes nécessaires et afficher les résultats obtenus en console.

# Auto-Évaluation :

**Lisibilité du code** : 1/1

**Commentaire** : 1/1

**Modélisation** : 2/2

**Pertinence des tests** : 1/2
* Je ne suis pas très fiere de mes tests notamment ceux pour tester les boucles dans lequel le scanner reste actif tant que != "RETOUR"
* Il manque encore beaucoup de test pour vraiment dire que je les aient tous fait !

**Ajout perso** : 1/2 
* J'ai ajouté pas mal d'exeption comme demandé dans le tp précedent
* J'ai ajouté quelque generateur de donné aleatoire en plus de la question bonus de l'exercice 2

**Avancement du TP** : 4/4

**Bonne implémentation des concept vu en cours** : 4/6
* Je ne maitrise pas encore tres bien les enums et ni quand il peuvent se substituer à l'utilisation d'une autre type de classe.

**Bonne pratique** : 1/2 
* beaucoup de boucle imbriqué notament avec les exceptions et les try ... catch que je ne maitrise pas du tout

**Mauvais pratique** : 0/-2

TOTAL : 15