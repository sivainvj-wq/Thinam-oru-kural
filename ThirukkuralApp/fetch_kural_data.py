#!/usr/bin/env python3
"""
Fetches all 1330 Thirukkural entries from the open-source API
and merges them into the kurals.json asset file.

Usage:
    pip install requests
    python3 fetch_kural_data.py
"""
import json
import sys
import time

try:
    import requests
except ImportError:
    print("Install requests first: pip install requests")
    sys.exit(1)

ASSET_PATH = "app/src/main/assets/kurals.json"
API_BASE = "https://api.thirukkural.app/api/kural"

def fetch_kural(number: int) -> dict | None:
    try:
        r = requests.get(f"{API_BASE}/{number}", timeout=5)
        if r.status_code == 200:
            return r.json()
    except Exception as e:
        print(f"Error fetching kural {number}: {e}")
    return None

def main():
    print(f"Loading existing data from {ASSET_PATH}...")
    with open(ASSET_PATH, 'r', encoding='utf-8') as f:
        existing = json.load(f)

    real_data = {k['number']: k for k in existing if not k['translationEn'].startswith('[Translation')}
    print(f"Already have real data for {len(real_data)} kurals")

    all_kurals = {k['number']: k for k in existing}
    updated = 0

    for num in range(1, 1331):
        if num in real_data:
            continue  # Already have good data
        
        print(f"Fetching kural {num}...", end='\r')
        data = fetch_kural(num)
        
        if data:
            kural = all_kurals[num]
            kural['translationEn'] = data.get('transliteration', kural['translationEn'])
            kural['explanationEn'] = data.get('meaning', {}).get('en', kural['explanationEn'])
            kural['explanationTa'] = data.get('meaning', {}).get('ta', kural['explanationTa'])
            updated += 1
            time.sleep(0.05)  # Be polite to the API
        
        if num % 100 == 0:
            print(f"Progress: {num}/1330 ({updated} updated)")
            # Save intermediate progress
            kurals_list = sorted(all_kurals.values(), key=lambda x: x['number'])
            with open(ASSET_PATH, 'w', encoding='utf-8') as f:
                json.dump(kurals_list, f, ensure_ascii=False, indent=2)

    # Final save
    kurals_list = sorted(all_kurals.values(), key=lambda x: x['number'])
    with open(ASSET_PATH, 'w', encoding='utf-8') as f:
        json.dump(kurals_list, f, ensure_ascii=False, indent=2)

    print(f"\nDone! Updated {updated} kurals. Total: {len(kurals_list)}")

if __name__ == '__main__':
    main()
