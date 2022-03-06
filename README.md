## Neko
Hacked Client Neko

## Disclaimer
C'est un coktail de vieux et nouveau code donc ça peut piquer les yeux.

## Installation
* Télécharger [MCP](http://www.mediafire.com/file/betelj9vmcjdjkm/file) pour la 1.8
* Avoir Minecraft et lancer au moins une fois la version 1.8
* Décompresser le mcp dans un dossier
* Exécuter le `decompile.bat`
* Supprimer les dossiers dans `src` puis cloner ce repo dedans (l'intérieur du repo)
* Ouvrir Eclipse et choisir le répértoire `src`
* Télécharger [Client](http://www.mediafire.com/file/k0col8f2yo1pvh0/Client.rar/file) et extraire dans un dossier hors du projet

## Setup dans Eclipse
* Clic droit sur le projet > Build path > Configure build path
  * Dans Sources, si ce n'est pas `src/minecraft`, éditer et mettre `minecraft`
  * Dans librairies > Add external JARS > Sélectionner tous les jars dans le dossier `librairies` du Client
    * Sélectionner la petite flèche/plus de la librarie `lwjgl-2.9.1` > Double clic sur Native library location et sélectionner le dossier correspondant à votre OS dans le dossier `librairies/native`
* Clic droit sur le projet > Run as.. > Run configuration > Clic droit sur Java Application > New Configuration
  * Dans Main:
    * Project: Celui sélectionné
    * Main class: Start
  * Dans Arguments:
    * Cocher Other comme Working directory et mettre le dossier `jars` qui se trouve à la racine du projet
  * Dans JRE:
    * Mettre jre1.8.0_xxx
