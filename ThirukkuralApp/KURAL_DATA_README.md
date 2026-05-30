# Completing the Kural Data

The `kurals.json` currently has full data for the first 30 kurals.
Kurals 31–1330 have correct chapter/book metadata but placeholder translations.

## Quickest Way: Use the Open-Source Dataset

Run this Python script from the project root to fetch real data:

```bash
pip install requests
python3 fetch_kural_data.py
```

The script calls the free Thirukkural API at: https://github.com/tk120404/thirukkural

## Manual Option

Download the dataset CSV/JSON from:
- https://github.com/tk120404/thirukkural/tree/master/data
- https://github.com/SaravanaSenthivel/thirukkural-json

Then run: `python3 merge_kural_data.py <downloaded_file>`

## Data Already Included (Kurals 1–30)

The first 30 kurals include full Tamil + English content authored from 
well-known sources and are ready to use.
