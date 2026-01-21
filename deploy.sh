sudo docker stop departamentos-container 2>/dev/null
sudo docker rm departamentos-container 2>/dev/null

sudo docker build -t departamentos .

sudo docker run \
           --restart always \
           -d -p 8088:8088 \
           --env-file .env \
           --network appx \
           --add-host=host.docker.internal:host-gateway \
           --name departamentos-container departamentos
