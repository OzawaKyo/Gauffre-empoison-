# Jeu de Gaufre
Bienvenue sur le projet Jeu de Gaufre ! Cette application Java est une simulation d'un jeu stratégique où deux joueurs se relaient pour manger une gaufre virtuelle tout en évitant de consommer la partie empoisonnée. Ce projet a été conçu comme un exercice pour explorer et maîtriser la technologie Java Swing avant de se lancer dans un projet plus ambitieux, nommé Quatro.

# Fonctionnalités
- Jeu interactif : Affrontez l'ordinateur ou un autre joueur dans une partie où chacun doit éviter de manger la gaufre empoisonnée.
- Divers adversaires IA : Testez vos compétences contre une IA à difficulté moyenne ou aléatoire, pour des parties toujours différentes.
- Historique de jeu : Visualisez les mouvements effectués par vous et votre adversaire grâce à la fonctionnalité GaufreHistorique.
- Modes de jeu multiples : Choisissez parmi différents modes de jeu et adversaires IA via des interfaces intuitives.
- Architecture MVC : Construit en suivant le modèle Modèle-Vue-Contrôleur (MVC), assurant une maintenance aisée et une évolutivité pour de futures améliorations.
Objectif du Projet

Le Jeu de Gaufre a été développé comme un projet de test pour explorer Java Swing, une bibliothèque graphique Java. L'objectif était de se familiariser avec la création d'interfaces utilisateur et la gestion d'événements avant de passer à des projets plus complexes, tels que Quatro.

# Prise en Main
Pour démarrer avec le Jeu de Gaufre, suivez les étapes suivantes :

1. Cloner le dépôt
git clone https://github.com/OzawaKyo/Gauffre-empoison-.git

2. Compiler le Projet
Accédez au répertoire du projet et compilez les fichiers Java :
javac *.java

3. Exécuter le Jeu
Après la compilation, lancez le jeu en utilisant Java :
java GaufreController


# Structure du Projet
Le projet est organisé de la manière suivante :

- GameOverView.java : Gère l'écran de fin de jeu et son affichage.
- Gauffre.java : Classe principale qui initialise le jeu.
- GaufreController.java : Contrôle la logique du jeu et les interactions entre le modèle et les vues.
- GaufreHistorique.java : Suit et affiche l'historique des mouvements du jeu.
- GaufreMediumAI.java : Implémente une IA de difficulté moyenne.
- GaufreModel.java : Représente les données du jeu.
- GaufreRandomAI.java : Implémente une IA avec des comportements aléatoires.
- GaufreView.java : Gère l'interface utilisateur principale du jeu.
- SelectAIView.java : Interface pour la sélection de l'adversaire IA.
- SelectModeView.java : Interface pour la sélection du mode de jeu.
