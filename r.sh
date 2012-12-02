curl --header "Content-type: application/json" --request POST --data @post-data.txt http://localhost:9000/batch
#curl --header "Content-type: application/json" --request POST --data '{"ops":[{"method":"get","url":"/patrons"}]}' http://localhost:9000/batch
