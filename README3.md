
* Install OpenTOSCA

* Repoitory https://github.com/OpenTOSCA/opentosca-docker clonen
* Zuerst `.env` File erstellen, dazu `_.env` kopieren
* Dann eigene lokale IP-Adresse der Variable `PUBLIC_HOSTNAME` zu weisen.

* Anschließend `docker-compose.override.yml` file erstellen, dazu `_docker-compose.override.yml` kopieren.
* Folgende Anpassungen vornehmen um das mit gelieferte Repository zu mappen:

  ```yaml
    winery:
      volumes:
        - <path on host system>:/var/opentosca/repository
      environment:
        WINERY_REPOSITORY_PATH: /var/opentosca/repository
    ```
* Bei mir was es:
```yaml
   winery:
    volumes:
      - /Users/abotsitsepa/OpenTOSCA/tosca-definitions-example-applications-main:/var/opentosca/repository
    environment:
      WINERY_REPOSITORY_PATH: /var/opentosca/repository
```

* Speichern
* Dann `docker-compose` up Befehl ausführen. Warten bis die Docker Container fertig gestartet sind.
* unter kann winery erreicht werden
* unter .. kan TOSCA UI erreicht werden
* In der TOSCA UI unter Reiter Applications, die CSAR hochladen,
* In die Detailansicht navigieren
* Unter `+`  und `instantiate`den Build-Plan ausführen und eine neue Instanz erstellen