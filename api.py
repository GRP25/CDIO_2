import requests
import json

link = 'http://127.0.0.1:8080/db/api/users'

def list_users():
    r = requests.get(link)
    print(json.dumps(r.json(), indent=4))

def create_user(user):
    r = requests.post(link, json=user)
    print(r.json())

def delete_user(id_):
    r = requests.delete(link+'/'+str(id_))
    print(r)

def get_user(id_):
    r = requests.get(link+'/'+str(id_))
    print(json.dumps(r.json(), indent=4))

def update_user(id_, user):
    user['id'] = id_
    r = requests.put(link+'/'+str(id_), json=user)
    print(r.text)

user = {
    "cpr": 1102924321,
    "id": 1,
    "initials": "BB",
    "name": "Bartin Båtson",
    "password": "BassBord",
    "roles": [
        "Admin",
        "Laborant"
    ]
}

create_user(user)
#list_users()
#delete_user(6)
#get_user(1)
#update_user(1,user)
