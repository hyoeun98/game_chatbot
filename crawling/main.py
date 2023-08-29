import requests, json, os
from loading import printProgressBar
import concurrent.futures

FOLDER_PATH = "output"

if not os.path.exists(FOLDER_PATH):
	os.makedirs(FOLDER_PATH)

def call_api(idx):
	response = requests.get(f"https://steamspy.com/api.php?request=all&page={idx}")

	data = response.json()

	i = 0
	l = len(data)

	print('[Total data]:', l)
	# printProgressBar(0, l, prefix = 'Progress:', suffix = 'Complete', length = 50)

	for game_id, obj in data.items():
		game_name = obj["name"]
		try:
			res = requests.get(f"https://store.steampowered.com/api/appdetails?appids={game_id}")
			detail = res.json()

			if not detail[game_id]["success"]: continue

			with open(f"{FOLDER_PATH}/{game_name.replace('/', '')}-{game_id}.json", "w") as f:
				json.dump(detail, f, indent=4)
		except TypeError:
			print(res)
			...
		except:
			print(res)
		# printProgressBar(i + 1, l, prefix = 'Progress:', suffix = 'Complete', length = 50)
		i+= 1

	return 1
	
def multi_threading_call_api(page_num):
    result = 0
    with concurrent.futures.ThreadPoolExecutor(max_workers=3) as exe:
        future_to_api = {exe.submit(call_api, idx): idx for idx in range(page_num)}
        for future in concurrent.futures.as_completed(future_to_api):
            result += (future.result())


    print(result)
# multi_threading_call_api(5)
call_api(1)