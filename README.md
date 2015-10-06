# endtoend-test

Test de bout en bout du site AirCorp.

Pour plus d'information voir [cd-infrastructure](https://github.com/snicaise/cd-infrastructure)

# Usage

Prérequis : maven 3, java 8 et [gauge](http://getgauge.io) (testé avec la 0.1.8)

Execution des tests en spécifiant l'environnement
```sh
mvn gauge:execute -DspecsDir=specs -Denv="integration"
```

Execution des tests par tags
```sh
mvn gauge:execute -DspecsDir=specs -Dtags="!in-progress"
```
