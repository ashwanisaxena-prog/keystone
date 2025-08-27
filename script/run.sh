pkill -f 'java -jar application.jar' || true
sleep 5
nohup java -jar application.jar >> application.log 2>&1 &