# azure-storagequeue-java-benchmark


```sh
location="brazilsouth"
group="rg-storagequeue-benchmark"
storage="stqueuebench999" # change to a unique name

az group create -n $group -l $location

az storage account create \
  --name $storage \
  --resource-group $group \
  --location $location \
  --sku "Standard_GZRS" \
  --kind "StorageV2"

az storage queue create -n "benchmark-queue" --account-name $storage

az storage account show-connection-string --name $storage --resource-group $group --query connectionString --output tsv
```

```
mvn exec:java
```