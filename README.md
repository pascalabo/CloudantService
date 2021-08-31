
## Dieses Repository umfasst alle Dateien in Verbindung mit der Erstellung des Protoypen  

OpenTOSCA mit [Docker Compose](https://docs.docker.com/compose/) nutzen

* Repoitory https://github.com/OpenTOSCA/opentosca-docker clonen
* Zuerst `.env` File erstellen, dazu `_.env` kopieren
* Dann eigene lokale IP-Adresse der Variable `PUBLIC_HOSTNAME` zu weisen.

* Anschließend `docker-compose.override.yml` file erstellen, dazu `_docker-compose.override.yml` kopieren.
* Folgende Anpassungen vornehmen um das mit gelieferte Repository (tosca-definitions-example-applications-main:) zu mappen:

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
* Dann `docker-compose up` Befehl ausführen. Warten bis die Docker Container fertig gestartet sind.
* In der OpenTOSCA UI unter <http://localhost:8088> unter Reiter Applications die CSAR hochladen,
* In die Detailansicht navigieren und unter `+`  und `instantiate`den Build-Plan ausführen und eine neue Instanz erstellen
* Unter <http://localhost:9763/ode/#/instances> können die Ausführung der Management-Pläne eingesehen werden

* Unter <<http://localhost:8080> können in Winery die TOSCA-Komponenten angesehen werden

| OpenTOSCA Component | URL | GitHub | Docker Hub |
|:------------------- |:--- |:------ |:---------- |
| OpenTOSCA UI | <http://localhost><br><http://localhost:8088> | [Link](https://github.com/OpenTOSCA/ui) | [Link](https://hub.docker.com/r/opentosca/ui) |
| OpenTOSCA Modelling (Eclipse Winery) | <http://localhost/winery><br><http://localhost:8080> | [Link](https://github.com/OpenTOSCA/winery) | [Link](https://hub.docker.com/r/opentosca/winery) |
| OpenTOSCA Container API | <http://localhost:1337> | [Link](https://github.com/OpenTOSCA/container) | [Link](https://hub.docker.com/r/opentosca/container) |
| OpenTOSCA Container Repository | <http://localhost:8081> | [Link](https://github.com/OpenTOSCA/winery) | [Link](https://hub.docker.com/r/opentosca/winery) |
| Plan Engine BPEL (Apache ODE) | <http://localhost/ode><br><http://localhost:9763/ode> | [Link](https://github.com/OpenTOSCA/ode) | [Link](https://hub.docker.com/r/opentosca/ode) |
| Plan Engine BPMN (Camunda) | <http://localhost/camunda><br><http://localhost:8092/camunda> | [Link](https://github.com/OpenTOSCA/camunda-bpmn) | [Link](https://hub.docker.com/r/opentosca/camunda-bpmn) |
| IA Engine (Apache Tomcat) | <http://localhost/manager><br><http://localhost:8090/manager><br>(user: `admin`, password: `admin`) | [Link](https://github.com/OpenTOSCA/engine-ia) | [Link](https://hub.docker.com/r/opentosca/engine-ia) |