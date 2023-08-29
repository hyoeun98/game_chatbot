import json

# JSON 파일 경로
json_file_path = "test.json"

# JSON 파일 열기
with open(json_file_path, "r", encoding="utf-8") as json_file:
    json_data = json_file.read()

# JSON 데이터 파싱
data = json.loads(json_data)

# 필요한 정보 추출
game_info = data["10"]["data"]
title = game_info["name"]
price = "무료" if game_info["is_free"] else "유료"
detailed_description = game_info["detailed_description"]
genres = [genre["description"] for genre in game_info["genres"]]
categories = [category["description"] for category in game_info["categories"]]
recommendations = game_info["recommendations"]["total"]

# 추출한 정보 출력
print("Title:", title)
print("price:", price)
print("Detailed Description:", detailed_description)
print("Categories:", ", ".join(categories))
print("Recommendations:", recommendations)
print("Genres:", ",".join(genres))

# 새로운 JSON 데이터 생성
new_data = {
    "messages": [
        {"role": "system", "content": "You are professional gamer"},
        {"role": "user", "content": f"{title}에 대해 알려줘"},
        {
            "role": "assistant",
            "content": {
                "가격": price,
                "설명": detailed_description,
                "카테고리": ", ".join(genres+categories),
                "추천수": recommendations
            }
        }
    ]
}

# JSON 파일 저장
new_json_file_path = "game_info.json"
with open(new_json_file_path, "w", encoding="utf-8") as new_json_file:
    json.dump(new_data, new_json_file, indent=4, ensure_ascii=False)
