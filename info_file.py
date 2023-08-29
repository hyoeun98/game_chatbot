import json
import os

# 원본 JSON 파일 경로
input_folder = "input_json_files"

# 결과를 저장할 폴더 경로
output_folder = "output_json_files"

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
        game_info = data[game_id]["data"]
        title = game_info["name"]
        price = "무료" if game_info["is_free"] else "유료"
        short_description = game_info["short_description"]
        genres = [genre["description"] for genre in game_info["genres"]]
        categories = [category["description"] for category in game_info["categories"]]

        # 새로운 JSON 데이터 생성
        new_data = {
            "messages": [
                {"role": "system", "content": "You are professional gamer"},
                {"role": "user", "content": f"{title}에 대해 알려줘"},
                {
                    "role": "assistant",
                    "content": {
                        "가격": price,
                        "설명": short_description,
                        "카테고리": ", ".join(genres + categories)
                    }
                }
            ]
        }

        # 결과를 저장할 파일 경로
        output_file_path = os.path.join(output_folder, filename.replace(".json", "_result.json"))

        # JSON 파일 저장
        with open(output_file_path, "w", encoding="utf-8") as output_file:
            json.dump(new_data, output_file, indent=4, ensure_ascii=False)

print("모든 JSON 파일을 처리하여 결과를 저장했습니다.")
