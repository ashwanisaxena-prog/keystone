pkill -f 'java -jar' || true
nohup java -jar target/keystone-shaded.jar > app.log 2>&1 &