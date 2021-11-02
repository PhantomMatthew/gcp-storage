mvn clean package
docker login -u _json_key --password-stdin https://gcr.io < credentials.json
docker build -t us.gcr.io/fresh-metrics-328615/demo:corretto11 .
docker push us.gcr.io/fresh-metrics-328615/demo:corretto11

