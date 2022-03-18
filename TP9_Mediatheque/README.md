# Médiathèque
 
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

![diagramme de classe](assets/diagramme_de_classe.drawio.svg)

2 Coder les différentes fonctionnalités en utilisant de la POO. Faites attention aux cas d'erreurs potentielles.

3 Dans votre main, réaliser un jeu de test permettant de valider vos différentes fonctionnalités. Pour rappel, un jeu de test ne doit pas permettre à l'utilisateur de tester votre programme mais plutôt montrer les différents cas que vous avez vous même testé. Pour réaliser cela, vous êtes libre d'utiliser une technologie comme JUNIT ou de les faire à la main dans votre main. Chaque test doit avoir un commentaire précisant le cas à tester (cas normal ou particulier), exécuter les méthodes nécessaires et afficher les résultats obtenus en console.