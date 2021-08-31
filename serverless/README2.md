### Insall OpenWhisk

brew update
brew install wsk

## Verify the installation
wsk --help

### Insall IBM Cloud CLI
curl -sL https://raw.githubusercontent.com/IBM-Cloud/ibm-cloud-developer-tools/master/linux-installer/idt-installer | bash

## Verify the installation
ibmcloud dev help

ibmcloud login

ibmcloud target --cf

wsk property get

### WSK Commands

# Invoke function save-database-entrywith parameter

wsk action invoke --result /pascal.abotsitse@web.de_dev/myaplication/save-database-entry --param name test123 

# Add cloudant read sequence to function myaplication/db-listener

wsk action update myaplication/db-listener-sequence --sequence /pascal.abotsitse@web.de_dev/binding-for-database/read,myaplication/db-listener

# Create trigger from the database changes feed

ibmcloud wsk trigger create db-change --feed /pascal.abotsitse@web.de_dev/binding-for-database/changes --param mydatabase name

# The trigger doesn't fire until it has at least one rule associated with it - so here's the command to link the above trigger to our sequence:

ibmcloud wsk rule create db-listen-on-change db-change myaplication/db-listener-sequence

## enable log

ibmcloud wsk activation poll

## Add cloudant create-document  function to sequence cloudant-service/save-database-entry-sequence

ibmcloud wsk action update cloudant-service/save-database-entry-sequence --sequence /pascal.abotsitse@web.de_dev/binding-for-database/create-document,cloudant-service/prepare-entry-for-save 