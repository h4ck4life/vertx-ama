## Random Reddit AMA browser

### Tech stack
1. Vert.x
2. Angular
3. SQLite
4. Hibernate

### How to run locally
1. Frontend
   1. Run `npm install` in `web/reddit-ama-web`
   2. Run `ng serve` in `web/reddit-ama-web`
3. Backend
    1. Run `mvn clean compile exec:java -Dexec.mainClass=com.filavents.App`

Download the sqlite database here:
https://www.dropbox.com/s/bkxn5z74cs10cl4/reddit_full.sqlite?dl=0

### Todo
- [ ] Add caching
- [ ] Fulltext search
- [ ] Search UI rework
- [ ] Add more tests