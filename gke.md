# Create default cluster
```bash
gcloud container clusters create test-cluster --zone us-central1-a
```
# Get credentials to kube.config
```bash
gcloud container clusters get-credentials test-cluster --zone=us-central1-a
```
# Enable GCR
```bash
gcloud services enable containerregistry.googleapis.com
```
# Docker Login GCR
## Access Denied fix to input [Y] for below command line
```bash
 gcloud auth configure-docker
```
```bash
docker login -u _json_key --password-stdin https://gcr.io < credentials.json
 
docker build -t us.gcr.io/{project_id}/demo:corretto11 .

docker push us.gcr.io/fresh-metrics-328615/demo:corretto11
```
# Enable Secret Manager API
```bash
gcloud services enable secretmanager.googleapis.com
```
# Update Auth(Not Needed)
```bash
gcloud compute instances set-service-account "INSTANCE_ID" \
    --service-account "SERVICE_ACCOUNT_EMAIL" \
    --scopes "https://www.googleapis.com/auth/cloud-platform"
```
# Create Secret by gcloud
```bash
gcloud secrets create gcstest --data-file=./credentials.json

gcloud secrects list
```
