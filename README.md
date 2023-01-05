## Random Reddit AMA Browser

### Demo:
https://reddit-ama.filavents.com/

### Tech stack:
1. Vert.x
2. Angular + TailwindCSS
3. SQLite
4. Hibernate

### How to run locally:
1. Frontend
   1. Run `npm install` in `web/reddit-ama-web`
   2. Run `ng serve` in `web/reddit-ama-web`
3. Backend
   1. Download the sqlite database here:
      https://www.dropbox.com/s/bkxn5z74cs10cl4/reddit_full.sqlite?dl=0
   2. Place the `reddit_full.sqlite` file in the project root directory
   2. Run `mvn clean compile exec:java -Dexec.mainClass=com.filavents.App`

   
### Todo:
- [ ] Add caching
- [ ] Fulltext search
- [ ] Search UI rework
- [ ] Add more tests