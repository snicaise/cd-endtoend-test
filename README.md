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

# Paramètres

Le répertoire `env` contient plusieurs répertoires spécifiques à un environnement. Chaque répertoire contient des fichiers de configuration qui définissent les variables d'environnement utilisés lors de l'exécution des tests.

## base_uri

URL d'accès au site AirCorp.

```
base_uri = http://server1.dev.polaris.com:5080
```

## selenium_remote_uri

URL d'accès au server selenium. Si la propriété n'est pas définie, les tests selenium s'executeront en local.

```
selenium_remote_uri = http://goagent.dev.polaris.com:4444/wd/hub
```
