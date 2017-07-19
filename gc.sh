#!/bin/sh

declare project_dir=$(dirname $0)
declare docker_compose_file=${project_dir}/docker-compose.yml
declare geeksclub="geeksclub"

function start() {
    echo 'Starting GeeksClub....'
    docker-compose -f ${docker_compose_file} up --build --force-recreate -d ${geeksclub}
    docker-compose -f ${docker_compose_file} logs -f
}

function stop() {
    echo 'Stopping GeeksClub....'
    docker-compose -f ${docker_compose_file} stop
    docker-compose -f ${docker_compose_file} rm -f
}

action="start"

if [ $1 != "0"  ]
then
    action=$@
fi

eval ${action}