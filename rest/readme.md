# D&D Troops REST API
REST API for D&D Troops. In order to test this API, first you need to insert test data and login due to security restrictions
## Insert test data
`curl -i -X POST http://localhost:8080/pa165/rest/test`

## Login as admin
`curl -i -X GET http://localhost:8080/pa165/rest/users/login/admin/password/admin`

## List all users
`curl -i -X GET http://localhost:8080/pa165/rest/users/`

## List only given user
`curl -i -X GET http://localhost:8080/pa165/rest/users/1`

## Add this user new hero
`curl -i -X PUT  http://localhost:8080/pa165/rest/users/1/heroes/1/add`

## Add this hero new role
`curl -i -X PUT  http://localhost:8080/pa165/rest/heroes/1/roles/2/add`

## Check that previous operations went correctly
`curl -i -X GET http://localhost:8080/pa165/rest/users/1`

## Logout
`curl -i -X GET http://localhost:8080/pa165/rest/users/logout`