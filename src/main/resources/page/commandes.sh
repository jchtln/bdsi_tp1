#Lancement du serveur appache avec partage de volume et mapping de ports
docker run --name myAppache -p 8080:80 -v "$(pwd)":"/usr/local/apache2/htdocs" -d httpd


#Récupérer adresse IP
docker inspect myAppache

#Visite du conteneur
docker exec -it myAppache  bash
