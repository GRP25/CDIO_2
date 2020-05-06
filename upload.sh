#scp -r /home/martin/dtu/CDIO/projekt/cdio_2/src/main/webapp/* martin@srv2:Desktop/cdio2/build/
mvn clean && mvn package
scp -r /home/martin/dtu/CDIO/projekt/cdio_2/target/db.war martin@srv2:Desktop/
