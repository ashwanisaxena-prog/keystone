pkill -f 'java -jar' || true
sleep 1
nohup java -jar target/keystone-shaded.jar >> app.log 2>&1 &