import requests, json, os
from loading import printProgressBar

FOLDER_PATH = "output"

if not os.path.exists(FOLDER_PATH):
	os.makedirs(FOLDER_PATH)

response = requests.get("https://steamspy.com/api.php?request=all&page=1%22")
# {
#   "applist": {
#     "apps": [
#       {"appid": 10, "name": "Counter-Strike"},
#       {"appid": 20, "name": "Team Fortress Classic"},
#       {"appid": 30, "name": "Day of Defeat"},
#       {"appid": 40, "name": "Deathmatch Classic"}
#     ]
#   }
# }

# {
#   "10": {
#     "success": true, 
#     "data": {
#       "type": "game",
#       "name": "Counter-Strike",
#       "steam_appid": 10,
#       "required_age": 0,
#       "is_free": false,
#       "detailed_description": "...",
#       "about_the_game": "...",
#       "short_description": "...",
#       "developers": ["Valve"],
#       "publishers": ["Valve"],
#       "EVEN_MORE_DATA": {}
#     }
#   }
# }

data = response.json()

i = 0
l = len(data)

print('[Total data]:', l)
printProgressBar(0, l, prefix = 'Progress:', suffix = 'Complete', length = 50)

for game_id, obj in data.items():
	game_name = obj["name"]
	try:
		res = requests.get(f"https://store.steampowered.com/api/appdetails?appids={game_id}")
		detail = res.json()

		if not detail[game_id]["success"]: continue

		with open(f"{FOLDER_PATH}/{game_name.replace('/', '')}-{game_id}.json", "w") as f:
			json.dump(detail, f, indent=4)
	except TypeError:
		...
	printProgressBar(i + 1, l, prefix = 'Progress:', suffix = 'Complete', length = 50)
	i+= 1


  
