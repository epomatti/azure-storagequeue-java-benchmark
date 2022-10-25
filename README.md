# azure-storagequeue-java-benchmark

## 🖥️ Local Development

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

## 🚀 Cloud Benchmark

```sh
az vm create -n "vm-benchmark" -g "rg-storagequeue-benchmark" --location "brazilsouth" --image "UbuntuLTS" --custom-data cloud-init.sh --size "Standard_D8s_v4" --public-ip-sku "Standard"
```

Connect to the VM:

```sh
ssh <user>@<public-ip>
```

Check if the cloud-init script ran correctly:

```sh
java --version
mvn --version
```

```sh
export MAVEN_OPTS="-Xms256m -Xmx16g"
```

```sh
mvn install
mvn exec:java
```