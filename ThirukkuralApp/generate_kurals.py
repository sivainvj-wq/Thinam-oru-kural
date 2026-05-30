#!/usr/bin/env python3
"""
Script to generate/extend the kurals.json with all 1330 entries.
The first 30 kurals are already manually authored above.
This script generates placeholder entries for kurals 31-1330 with correct chapter/book metadata.
Replace the placeholder translationEn/explanationTa/explanationEn with real data from
an open-source Thirukkural API or dataset (e.g., https://github.com/tk120404/thirukkural).
"""

import json

# Complete chapter metadata: (start_kural, end_kural, chapter_num, chapter_ta, chapter_en, book_num, book_ta, book_en)
CHAPTERS = [
    (1, 10, 1, "கடவுள் வாழ்த்து", "Praise of God", 1, "அறத்துப்பால்", "Book of Virtue"),
    (11, 20, 2, "வான்சிறப்பு", "The Blessing of Rain", 1, "அறத்துப்பால்", "Book of Virtue"),
    (21, 30, 3, "நீத்தார் பெருமை", "The Greatness of Ascetics", 1, "அறத்துப்பால்", "Book of Virtue"),
    (31, 40, 4, "அறன் வலியுறுத்தல்", "Assertion of the Strength of Virtue", 1, "அறத்துப்பால்", "Book of Virtue"),
    (41, 50, 5, "இல்வாழ்க்கை", "Domestic Life", 1, "அறத்துப்பால்", "Book of Virtue"),
    (51, 60, 6, "வாழ்க்கைத் துணைநலம்", "The Worth of a Wife", 1, "அறத்துப்பால்", "Book of Virtue"),
    (61, 70, 7, "மக்கட்பேறு", "The Wealth of Children", 1, "அறத்துப்பால்", "Book of Virtue"),
    (71, 80, 8, "அன்பு டைமை", "The Possession of Love", 1, "அறத்துப்பால்", "Book of Virtue"),
    (81, 90, 9, "விருந்தோம்பல்", "Hospitality", 1, "அறத்துப்பால்", "Book of Virtue"),
    (91, 100, 10, "இனியவை கூறல்", "The Utterance of Pleasant Words", 1, "அறத்துப்பால்", "Book of Virtue"),
    (101, 110, 11, "செய்ந்நன்றி அறிதல்", "Gratitude", 1, "அறத்துப்பால்", "Book of Virtue"),
    (111, 120, 12, "நடுவுநிலைமை", "Impartiality", 1, "அறத்துப்பால்", "Book of Virtue"),
    (121, 130, 13, "அடக்கமுடைமை", "The Possession of Self-Restraint", 1, "அறத்துப்பால்", "Book of Virtue"),
    (131, 140, 14, "ஒழுக்கமுடைமை", "The Possession of Good Conduct", 1, "அறத்துப்பால்", "Book of Virtue"),
    (141, 150, 15, "பிறனில் விழையாமை", "Not Coveting Another's Wife", 1, "அறத்துப்பால்", "Book of Virtue"),
    (151, 160, 16, "பொறையுடைமை", "The Possession of Patience", 1, "அறத்துப்பால்", "Book of Virtue"),
    (161, 170, 17, "அழுக்காறாமை", "Not Envying", 1, "அறத்துப்பால்", "Book of Virtue"),
    (171, 180, 18, "வெஃகாமை", "Not Coveting", 1, "அறத்துப்பால்", "Book of Virtue"),
    (181, 190, 19, "புறங்கூறாமை", "Not Backbiting", 1, "அறத்துப்பால்", "Book of Virtue"),
    (191, 200, 20, "பயனில சொல்லாமை", "Against Vain Speaking", 1, "அறத்துப்பால்", "Book of Virtue"),
    (201, 210, 21, "தீவினையச்சம்", "Dread of Evil Deeds", 1, "அறத்துப்பால்", "Book of Virtue"),
    (211, 220, 22, "ஒப்புரவறிதல்", "Duty to Society", 1, "அறத்துப்பால்", "Book of Virtue"),
    (221, 230, 23, "ஈகை", "Benevolence", 1, "அறத்துப்பால்", "Book of Virtue"),
    (231, 240, 24, "புகழ்", "Fame", 1, "அறத்துப்பால்", "Book of Virtue"),
    (241, 250, 25, "அருளுடைமை", "Compassion", 1, "அறத்துப்பால்", "Book of Virtue"),
    (251, 260, 26, "புலான்மறுத்தல்", "Abstaining from Flesh", 1, "அறத்துப்பால்", "Book of Virtue"),
    (261, 270, 27, "தவம்", "Penance", 1, "அறத்துப்பால்", "Book of Virtue"),
    (271, 280, 28, "கூடா ஒழுக்கம்", "Imposture", 1, "அறத்துப்பால்", "Book of Virtue"),
    (281, 290, 29, "கள்ளாமை", "Freedom from Fraud", 1, "அறத்துப்பால்", "Book of Virtue"),
    (291, 300, 30, "வாய்மை", "Veracity", 1, "அறத்துப்பால்", "Book of Virtue"),
    (301, 310, 31, "வெகுளாமை", "Restraint of Anger", 1, "அறத்துப்பால்", "Book of Virtue"),
    (311, 320, 32, "இன்னா செய்யாமை", "Doing No Evil", 1, "அறத்துப்பால்", "Book of Virtue"),
    (321, 330, 33, "கொல்லாமை", "Not Killing", 1, "அறத்துப்பால்", "Book of Virtue"),
    (331, 340, 34, "நிலையாமை", "The Instability of Earthly Greatness", 1, "அறத்துப்பால்", "Book of Virtue"),
    (341, 350, 35, "துறவு", "Renunciation", 1, "அறத்துப்பால்", "Book of Virtue"),
    (351, 360, 36, "மெய்யுணர்தல்", "True Knowledge", 1, "அறத்துப்பால்", "Book of Virtue"),
    (361, 370, 37, "அவாவறுத்தல்", "Curbing Desire", 1, "அறத்துப்பால்", "Book of Virtue"),
    (371, 380, 38, "ஊழ்", "Fate", 1, "அறத்துப்பால்", "Book of Virtue"),
    # Book 2 - Porul (Chapters 39-108)
    (381, 390, 39, "இறைமாட்சி", "The Greatness of a King", 2, "பொருட்பால்", "Book of Wealth"),
    (391, 400, 40, "கல்வி", "Learning", 2, "பொருட்பால்", "Book of Wealth"),
    (401, 410, 41, "கல்லாமை", "Ignorance", 2, "பொருட்பால்", "Book of Wealth"),
    (411, 420, 42, "கேள்வி", "Listening", 2, "பொருட்பால்", "Book of Wealth"),
    (421, 430, 43, "அறிவுடைமை", "The Possession of Knowledge", 2, "பொருட்பால்", "Book of Wealth"),
    (431, 440, 44, "குற்றங்கடிதல்", "The Correction of Faults", 2, "பொருட்பால்", "Book of Wealth"),
    (441, 450, 45, "பெரியாரைத் துணைக்கோடல்", "Getting the Help of Great Men", 2, "பொருட்பால்", "Book of Wealth"),
    (451, 460, 46, "சிற்றினஞ்சேராமை", "Avoiding Mean Associations", 2, "பொருட்பால்", "Book of Wealth"),
    (461, 470, 47, "தெரிந்து செயல்வகை", "Acting after Due Deliberation", 2, "பொருட்பால்", "Book of Wealth"),
    (471, 480, 48, "வலிமையறிதல்", "Knowing One's Strength", 2, "பொருட்பால்", "Book of Wealth"),
    (481, 490, 49, "காலமறிதல்", "Knowing the Time", 2, "பொருட்பால்", "Book of Wealth"),
    (491, 500, 50, "இடனறிதல்", "Knowing the Place", 2, "பொருட்பால்", "Book of Wealth"),
    (501, 510, 51, "தெரிந்து தெளிதல்", "Selection and Confidence", 2, "பொருட்பால்", "Book of Wealth"),
    (511, 520, 52, "தெரிந்து வினையாடல்", "Selection and Employment", 2, "பொருட்பால்", "Book of Wealth"),
    (521, 530, 53, "சுற்றந் தழால்", "Cherishing Kinsmen", 2, "பொருட்பால்", "Book of Wealth"),
    (531, 540, 54, "பொச்சாவாமை", "Unforgetfulness", 2, "பொருட்பால்", "Book of Wealth"),
    (541, 550, 55, "செங்கோன்மை", "Just Government", 2, "பொருட்பால்", "Book of Wealth"),
    (551, 560, 56, "கொடுங்கோன்மை", "Cruel Government", 2, "பொருட்பால்", "Book of Wealth"),
    (561, 570, 57, "வெருவந்த செய்யாமை", "Not Causing Fear", 2, "பொருட்பால்", "Book of Wealth"),
    (571, 580, 58, "கண்ணோட்டம்", "Mercy", 2, "பொருட்பால்", "Book of Wealth"),
    (581, 590, 59, "ஒற்றாடல்", "Espionage", 2, "பொருட்பால்", "Book of Wealth"),
    (591, 600, 60, "ஊக்கமுடைமை", "Energetic Action", 2, "பொருட்பால்", "Book of Wealth"),
    (601, 610, 61, "மடியின்மை", "Unsluggishness", 2, "பொருட்பால்", "Book of Wealth"),
    (611, 620, 62, "ஆள்வினை யுடைமை", "Manly Effort", 2, "பொருட்பால்", "Book of Wealth"),
    (621, 630, 63, "இடுக்கண் அழியாமை", "Not Being Disheartened", 2, "பொருட்பால்", "Book of Wealth"),
    (631, 640, 64, "அமைச்சு", "The Office of Minister", 2, "பொருட்பால்", "Book of Wealth"),
    (641, 650, 65, "சொல்வன்மை", "Power of Speech", 2, "பொருட்பால்", "Book of Wealth"),
    (651, 660, 66, "வினைத்தூய்மை", "Purity in Action", 2, "பொருட்பால்", "Book of Wealth"),
    (661, 670, 67, "வினைத்திட்பம்", "Firmness in Action", 2, "பொருட்பால்", "Book of Wealth"),
    (671, 680, 68, "வினைசெயல் வகை", "The Mode of Action", 2, "பொருட்பால்", "Book of Wealth"),
    (681, 690, 69, "தூது", "Embassies", 2, "பொருட்பால்", "Book of Wealth"),
    (691, 700, 70, "மன்னரைச் சேர்ந்தொழுகல்", "Conduct in the Presence of a King", 2, "பொருட்பால்", "Book of Wealth"),
    (701, 710, 71, "குறிப்பறிதல்", "The Knowledge of Indications", 2, "பொருட்பால்", "Book of Wealth"),
    (711, 720, 72, "அவையறிதல்", "The Knowledge of the Council Chamber", 2, "பொருட்பால்", "Book of Wealth"),
    (721, 730, 73, "அவையஞ்சாமை", "Not Dreading the Council", 2, "பொருட்பால்", "Book of Wealth"),
    (731, 740, 74, "நாடு", "The Country", 2, "பொருட்பால்", "Book of Wealth"),
    (741, 750, 75, "அரண்", "The Fortification", 2, "பொருட்பால்", "Book of Wealth"),
    (751, 760, 76, "பொருள்செயல்வகை", "The Way of Accumulating Wealth", 2, "பொருட்பால்", "Book of Wealth"),
    (761, 770, 77, "படைமாட்சி", "The Excellence of an Army", 2, "பொருட்பால்", "Book of Wealth"),
    (771, 780, 78, "படைச்செருக்கு", "Military Spirit", 2, "பொருட்பால்", "Book of Wealth"),
    (781, 790, 79, "நட்பு", "Friendship", 2, "பொருட்பால்", "Book of Wealth"),
    (791, 800, 80, "நட்பாராய்தல்", "Examining Fitness for Friendship", 2, "பொருட்பால்", "Book of Wealth"),
    (801, 810, 81, "பழைமை", "Familiarity", 2, "பொருட்பால்", "Book of Wealth"),
    (811, 820, 82, "தீ நட்பு", "Evil Friendship", 2, "பொருட்பால்", "Book of Wealth"),
    (821, 830, 83, "கூடா நட்பு", "Unreal Friendship", 2, "பொருட்பால்", "Book of Wealth"),
    (831, 840, 84, "பேதைமை", "Folly", 2, "பொருட்பால்", "Book of Wealth"),
    (841, 850, 85, "புல்லறிவாண்மை", "Ignorant Leadership", 2, "பொருட்பால்", "Book of Wealth"),
    (851, 860, 86, "இகல்", "Hostility", 2, "பொருட்பால்", "Book of Wealth"),
    (861, 870, 87, "பகைமாட்சி", "The Might of Hatred", 2, "பொருட்பால்", "Book of Wealth"),
    (871, 880, 88, "பகைத்திறந்தெரிதல்", "Knowing the Quality of Hate", 2, "பொருட்பால்", "Book of Wealth"),
    (881, 890, 89, "உட்பகை", "Internal Enemies", 2, "பொருட்பால்", "Book of Wealth"),
    (891, 900, 90, "பெரியாரைப் பிழையாமை", "Not Offending the Great", 2, "பொருட்பால்", "Book of Wealth"),
    (901, 910, 91, "பெண்வழிச்சேறல்", "Being Led by Women", 2, "பொருட்பால்", "Book of Wealth"),
    (911, 920, 92, "வரைவின்மகளிர்", "Wanton Women", 2, "பொருட்பால்", "Book of Wealth"),
    (921, 930, 93, "கள்ளுண்ணாமை", "Not Drinking Palm Wine", 2, "பொருட்பால்", "Book of Wealth"),
    (931, 940, 94, "சூது", "Gambling", 2, "பொருட்பால்", "Book of Wealth"),
    (941, 950, 95, "மருந்து", "Medicine", 2, "பொருட்பால்", "Book of Wealth"),
    (951, 960, 96, "குடிமை", "Nobility", 2, "பொருட்பால்", "Book of Wealth"),
    (961, 970, 97, "மானம்", "Honour", 2, "பொருட்பால்", "Book of Wealth"),
    (971, 980, 98, "பெருமை", "Greatness", 2, "பொருட்பால்", "Book of Wealth"),
    (981, 990, 99, "சான்றாண்மை", "Perfectness", 2, "பொருட்பால்", "Book of Wealth"),
    (991, 1000, 100, "பண்புடைமை", "Courtesy", 2, "பொருட்பால்", "Book of Wealth"),
    (1001, 1010, 101, "நன்றியில்செல்வம்", "Wealth Without Beneficence", 2, "பொருட்பால்", "Book of Wealth"),
    (1011, 1020, 102, "நாணுடைமை", "Shame", 2, "பொருட்பால்", "Book of Wealth"),
    (1021, 1030, 103, "குடிசேர்தல்", "Acquiring Wealth for One's Family", 2, "பொருட்பால்", "Book of Wealth"),
    (1031, 1040, 104, "உழவு", "Agriculture", 2, "பொருட்பால்", "Book of Wealth"),
    (1041, 1050, 105, "நல்குரவு", "Poverty", 2, "பொருட்பால்", "Book of Wealth"),
    (1051, 1060, 106, "இரவு", "Mendicity", 2, "பொருட்பால்", "Book of Wealth"),
    (1061, 1070, 107, "இரவச்சம்", "The Dread of Asking Alms", 2, "பொருட்பால்", "Book of Wealth"),
    (1071, 1080, 108, "கயமை", "Baseness", 2, "பொருட்பால்", "Book of Wealth"),
    # Book 3 - Kama (Chapters 109-133)
    (1081, 1090, 109, "தகையணங்குறுத்தல்", "Pre-marital Love", 3, "காமத்துப்பால்", "Book of Love"),
    (1091, 1100, 110, "குறிப்பறிவுறுத்தல்", "Recognition of the Signs of Love", 3, "காமத்துப்பால்", "Book of Love"),
    (1101, 1110, 111, "புணர்ச்சி மகிழ்தல்", "The Delight of Embraces", 3, "காமத்துப்பால்", "Book of Love"),
    (1111, 1120, 112, "நலம்புனைந்துரைத்தல்", "The Praise of Her Beauty", 3, "காமத்துப்பால்", "Book of Love"),
    (1121, 1130, 113, "காதற்சிறப்பு உரைத்தல்", "The Declaration of Love's Excellence", 3, "காமத்துப்பால்", "Book of Love"),
    (1131, 1140, 114, "நாணுத்துறவுரைத்தல்", "The Abandonment of Reserve", 3, "காமத்துப்பால்", "Book of Love"),
    (1141, 1150, 115, "அலர்அறிவுறுத்தல்", "The announcement of the love rumour", 3, "காமத்துப்பால்", "Book of Love"),
    (1151, 1160, 116, "பிரிவாற்றாமை", "Separation Unendurable", 3, "காமத்துப்பால்", "Book of Love"),
    (1161, 1170, 117, "படர்மெலிந்திரங்கல்", "Pining and Pensiveness", 3, "காமத்துப்பால்", "Book of Love"),
    (1171, 1180, 118, "கண்விதுப்பழிதல்", "The Upbraiding of the Eye", 3, "காமத்துப்பால்", "Book of Love"),
    (1181, 1190, 119, "பசப்புறுபருவரல்", "The Pallor of Separation", 3, "காமத்துப்பால்", "Book of Love"),
    (1191, 1200, 120, "தனிப்படர்மிகுதி", "Solitary Anguish", 3, "காமத்துப்பால்", "Book of Love"),
    (1201, 1210, 121, "நினைந்தவர் புலம்பல்", "Sad Memories", 3, "காமத்துப்பால்", "Book of Love"),
    (1211, 1220, 122, "கனவுநிலையுரைத்தல்", "The Visions of the Night", 3, "காமத்துப்பால்", "Book of Love"),
    (1221, 1230, 123, "பொழுதுகண்டிரங்கல்", "Lament at Eventide", 3, "காமத்துப்பால்", "Book of Love"),
    (1231, 1240, 124, "உறுப்பு நலனழிதல்", "The Wasting of Personal Beauty", 3, "காமத்துப்பால்", "Book of Love"),
    (1241, 1250, 125, "நெஞ்சொடுகிளத்தல்", "Speaking to One's Own Heart", 3, "காமத்துப்பால்", "Book of Love"),
    (1251, 1260, 126, "நிறையழிதல்", "Reserve Overcome", 3, "காமத்துப்பால்", "Book of Love"),
    (1261, 1270, 127, "அவர்வயின்விதும்பல்", "Longing for the Beloved", 3, "காமத்துப்பால்", "Book of Love"),
    (1271, 1280, 128, "குறிப்பறிவுறுத்தல்", "The Signs of Passion", 3, "காமத்துப்பால்", "Book of Love"),
    (1281, 1290, 129, "புணர்ச்சிவிதும்பல்", "Yearning for Union", 3, "காமத்துப்பால்", "Book of Love"),
    (1291, 1300, 130, "நெஞ்சொடுபுலத்தல்", "Expostulation with One's Heart", 3, "காமத்துப்பால்", "Book of Love"),
    (1301, 1310, 131, "புலவி", "Sulking", 3, "காமத்துப்பால்", "Book of Love"),
    (1311, 1320, 132, "புலவி நுணுக்கம்", "The Subtlety of Sulking", 3, "காமத்துப்பால்", "Book of Love"),
    (1321, 1330, 133, "ஊடலுவகை", "The Pleasures of Temporary Estrangement", 3, "காமத்துப்பால்", "Book of Love"),
]

def get_chapter_info(kural_num):
    for start, end, ch_num, ch_ta, ch_en, book_num, book_ta, book_en in CHAPTERS:
        if start <= kural_num <= end:
            return ch_num, ch_ta, ch_en, book_num, book_ta, book_en
    return 1, "அதிகாரம்", "Chapter", 1, "அறத்துப்பால்", "Book of Virtue"

# Load existing first 30 kurals
with open('/home/claude/ThirukkuralApp/app/src/main/assets/kurals.json', 'r', encoding='utf-8') as f:
    existing = json.load(f)

existing_nums = {k['number'] for k in existing}

# Generate remaining kurals 31-1330 as placeholders
# NOTE: Replace with real data from https://github.com/tk120404/thirukkural or similar open dataset
new_kurals = list(existing)

for num in range(31, 1331):
    if num in existing_nums:
        continue
    ch_num, ch_ta, ch_en, book_num, book_ta, book_en = get_chapter_info(num)
    new_kurals.append({
        "number": num,
        "chapter": ch_num,
        "chapterName": ch_ta,
        "chapterNameEn": ch_en,
        "book": book_num,
        "bookName": book_ta,
        "bookNameEn": book_en,
        "line1": f"குறள் {num} - வரி 1",
        "line2": f"குறள் {num} - வரி 2",
        "translationEn": f"[Translation for Kural {num} - Please add from open dataset]",
        "explanationTa": f"[குறள் {num} - தமிழ் விளக்கம் சேர்க்கவும்]",
        "explanationEn": f"[Explanation for Kural {num} - Please add from open dataset. Source: github.com/tk120404/thirukkural]"
    })

# Sort by number
new_kurals.sort(key=lambda x: x['number'])

with open('/home/claude/ThirukkuralApp/app/src/main/assets/kurals.json', 'w', encoding='utf-8') as f:
    json.dump(new_kurals, f, ensure_ascii=False, indent=2)

print(f"Total kurals written: {len(new_kurals)}")
