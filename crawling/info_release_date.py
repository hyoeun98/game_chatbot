import json
import os

# 원본 JSON 파일 경로
input_folder = "input_json_files"
# 결과 JSON 파일
output_json_file_path = "output.json"

# 결과를 초기화하기 위해 기존 파일을 빈 파일로 만듦
with open(output_json_file_path, "w", encoding="utf-8") as empty_output_file:
    empty_output_file.write("")

# 원본 폴더 내의 JSON 파일들 순회
for filename in os.listdir(input_folder):
    if filename.endswith(".json"):
        game_id = filename.split("-")[-1].split(".")[0]  # 파일 이름에서 숫자 부분 추출

        json_file_path = os.path.join(input_folder, filename)

        # JSON 파일 열기
        with open(json_file_path, "r", encoding="utf-8") as json_file:
            json_data = json_file.read()

        # JSON 데이터 파싱
        data = json.loads(json_data)

        # 필요한 정보 추출
        try:
            game_info = data[game_id]["data"]
            title = game_info["name"]
            price = "free" if game_info["is_free"] else "not free"
            short_description = game_info["short_description"]
            genres = [genre["description"] for genre in game_info["genres"]]
            categories = [category["description"] for category in game_info["categories"]]
            release_date = game_info["release_date"]["date"]

            # release_date에서 년도 추출
            release_parts = release_date.split(",")
            release_year = int(release_parts[-1])


            # 년도가 2022 이상인 경우에만 결과 저장
            if release_year>= 2022:
                new_data = {
                    "messages": [
                        {"role": "system", "content": "You are a chatbot who recommends games"},
                        {"role": "user", "content": f"Let me know what {title} is"},
                        {
                            "role": "assistant",
                            "content": f"description : {short_description}, it is {price} game, release_date: {release_date}, categories : [{', '.join(genres + categories)}]."
                        }
                    ]
                }

                new_data_json_string = json.dumps(new_data, ensure_ascii=False)
                with open(output_json_file_path, "a", encoding="utf-8") as output_file:
                    output_file.write(new_data_json_string + "\n")
        except KeyError:
            ...
        except ValueError:
            ...
print("모든 JSON 파일을 처리하여 결과를 저장했습니다.")
