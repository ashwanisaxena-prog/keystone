mvn clean package
az acr build --registry kamelregistry --image keystone:10
kamel delete 
kamel run  --image kamelregistry.azurecr.io/keystone:10
