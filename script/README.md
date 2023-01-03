### How to run the scrape script
1. Install the dependencies
    1. `pip3 install praw pandas tqdm`
2. Run the script
    1. `python3 reddit_AMA.py`
   2. In background
      1. `nohup python3 reddit_AMA.py > output.log &`
      2. `tail -f output.log`
      3. `ps -ef | grep reddit_AMA.py`
      4. `kill -9 <PID>`
2. Optional
   1. Change the limit of data to scrape. See code.