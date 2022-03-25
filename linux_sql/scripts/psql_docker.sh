#! /bin/sh

# Assign arguments
cmd=$1
db_username=$2
db_password=$3

# Start docker if not running
sudo systemctl status docker || sudo systemctl start docker

docker container inspect jrvs-psql
container_status=$?

case $cmd in
  create)

# Check if container is already created
  if [ $container_status -eq 0 ]; then
		echo 'Container already exists'
		exit 1
	fi

# Check if a valid number of arguments have been entered
  if [ $# -ne 3 ]; then
    echo 'Create requires username and password'
    exit 1
  fi

	docker volume create pgdata
	docker run --name jrvs-psql -e POSTGRES_PASSWORD=$PGPASSWORD -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres:9.6-alpine
	exit $?
	;;

  start|stop)

# Check if container has not been built
  if [ $container_status -eq 1 ]; then
    echo 'Container is not created'
    exit 1
  fi

	docker container $cmd jrvs-psql
	exit $?
	;;

  *)
	echo 'Illegal command'
	echo 'Commands: start|stop|create'
	exit 1
	;;
esac