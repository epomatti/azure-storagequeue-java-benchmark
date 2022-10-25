# Azure Storage Queue

Benchmarking for Azure Storage queue.

## 🖥️ Local Development

Create the storage queue:

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

Create the `app.properties`:

```properties
# Storage Connection
app.storage.connectionstring=...
app.storage.queue_name=benchmark-queue

# Controls
app.init_consumer=false
app.init_sender=true

# Sender
app.concurrent_sender_clients=10
app.concurrent_processes_per_client=100
app.message_quantity_to_send_per_client=10000
app.message_size_in_bytes=1024
```

Start the app:

```
mvn install
mvn exec:java
```

## 🚀 Cloud Benchmark

Create a VM on Azure:

```sh
az vm create -n "vm-benchmark" -g "rg-storagequeue-benchmark" --location "brazilsouth" --image "UbuntuLTS" --custom-data cloud-init.sh --size "Standard_D8s_v4" --public-ip-sku "Standard"
```

Add a Private Endpoint for optimal performance.

Connect to the VM:

```sh
ssh <user>@<public-ip>
```

Check if the cloud-init script ran correctly:

```sh
java --version
mvn --version
```

Set memory parameters:

```sh
export MAVEN_OPTS="-Xms256m -Xmx16g"
```

Run the application:

```sh
mvn install
mvn exec:java
```