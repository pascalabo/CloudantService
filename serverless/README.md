## Dieses Projekt beinhaltet die Serverless Funktionen für OpenWhisk

* Das Deployment wird mir dem Serverless-Framework realisiert

### OpenWhisk Installieren

`brew update`

`brew install wsk`

## Installation verifizieren
`wsk --help`

### IBM Cloud CLI installieren

`curl -sL https://raw.githubusercontent.com/IBM-Cloud/ibm-cloud-developer-tools/master/linux-installer/idt-installer | bash`

## Installation verifizieren

`ibmcloud dev help`

## IBM Cloud Zugang erstellen
* Die Funktionen habe ich in meinem IBM Cloud zugang deployed. Für das Deployment ist ein eigener IBM Cloud Zugang erforderlich <https://cloud.ibm.com/login>

* Ist dieser erstellt muss ein Cloudant Service erstellt werden. <https://cloud.ibm.com/catalog/services/cloudant?bss_account=ba3ff5448d654ed896e373d2e64aeb5a>
    - Region auf Frankfurt oder London
    - Instance: cloudant-db
    - Resourcegroup: Default
    - Plan: Lite

* Nach Erstellung anschließend unter Resource List <https://cloud.ibm.com/resources> in Cloudant-Service navigieren
    - Unter Launch Dashboard in die Cloudand Oberfläche navigieren <https://f45419af-37aa-4745-918a-a2dea44a7789-bluemix.cloudant.com/dashboard.html#/_all_dbs>
    - Dann eine neue Datenbank erstellen: name: mydatabase

* Unter Recource List <https://cloud.ibm.com/resources> wieder in Cloudant-Service navigieren und neuen Berechtigungsnachweis erstellen: 
 - name for-mydatabase
 - role: manager

## Login in Command Line Interface

`ibmcloud login`

`ibmcloud target --cf`

`wsk property get`

## Deployment mit Serverless-Framework

* Vor dem Deployment in der serverless.yml mit "replace all" /pascal.abotsitse@web.de_dev/ mit eigenem namespace austauschen.

### WSK Commands

Anschließend

* Pakete im Namespace aktualisieren. Die Aktualisierung erstellt automatisch eine Paketbindung für jede Cloudant-Dienstinstanz, für die ein Berechtigungsschlüssel definiert ist.

- `wsk package refresh`

- `wsk package bind /whisk.system/cloudant  cloudant-service-binding --param dbname mydatabase`

-`wsk package create cloudant-service`

* Im CLI ins Verzeichnis CloudantService/serverless navigieren

`serverless deploy`

# Trigger austauschen wenn mit

- mit `wsk trigger get cloudant-service-db-listener` Trigger aufrufen und überprüfen ob in den configs die Datenbankcredentials vorhanden sind
- Sind diese vorhanden weiter mit "Action save-database-entry ausführen"
- Sind diese nicht vorhanden:

- Unter <https://cloud.ibm.com/functions/actions> in die Funktion db-listener-sequence navigieren
- Unter Connected Triggers `cloudant-service-db-listener` löschen oder disablen
- Dann mit Add Trigger neuen Trigger erstellen -> Cloudant -> Namen eingeben -> Create

# Action save-database-entry ausführen

- Zweites CLI Fenster öffnen und mit `wsk actionctivation poll` die Logs aktivieren

- Dannach mit Funktion ausführen 
- `wsk action invoke --result /_/cloudant-service/save-entry-sequence --param name Name --param email Email`

- Im zweiten Fenster auf Output warten



# Nachfolgend die Readme von Serverless-Framwork

## Serverless OpenWhisk Node.js Template

Hello! 😎

This is a template Node.js service for the OpenWhisk platform. Before you can deploy your service, please follow the instructions below…

### Have you set up your account credentials?

Before you can deploy your service to OpenWhisk, you need to have an account registered with the platform.

- _Want to run the platform locally?_ Please read the project's [_Quick Start_](https://github.com/openwhisk/openwhisk#quick-start) guide for deploying it locally.
- _Want to use a hosted provider?_ Please sign up for an account with [IBM Bluemix](https://console.ng.bluemix.net/) and then follow the instructions for getting access to [OpenWhisk on Bluemix](https://console.ng.bluemix.net/openwhisk/).

Account credentials for OpenWhisk can be provided through a configuration file or environment variables. This plugin requires the API endpoint, namespace and authentication credentials.

**Do you want to use a configuration file for storing these values?** Please [follow the instructions](https://console.ng.bluemix.net/openwhisk/cli) for setting up the OpenWhisk command-line utility. This tool stores account credentials in the `.wskprops` file in the user's home directory. The plugin automatically extracts credentials from this file at runtime. No further configuration is needed.

**Do you want to use environment variables for credentials?** Use the following environment variables to be pass in account credentials. These values override anything extracted from the configuration file.

- _OW_APIHOST_ - Platform endpoint, e.g. `openwhisk.ng.bluemix.net`
- _OW_AUTH_ - Authentication key, e.g. `xxxxxx:yyyyy

### Have you installed the provider plugin?

Install project dependencies which includes the OpenWhisk provider plugin.

```
$ npm install
```

**_…and that's it!_**

### Deploy Service

Use the `serverless` command to deploy your service. The sample `handler.js` file can be deployed without modification.

```shell
serverless deploy
```

### Issues / Feedback / Feature Requests?

If you have any issues, comments or want to see new features, please file an issue in the project repository:

https://github.com/serverless/serverless-openwhisk
