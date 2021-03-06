# cassandra-store

————————————————————————————————
Cassandra working with Jdk 11 + Cassandra 4
————————————————————————————————


Install latest python
————————------------
brew install python



python --version
Python 2.7.16



cat ~/.profile
export JAVA_HOME=$(/usr/libexec/java_home)



python3 --version
Python 3.9.5


cat ~/.profile
export JAVA_HOME=$(/usr/libexec/java_home)
alias python="python3"



—>
cat .bash_profile
alias python="python3"



After updating to python3, update by using source
—>
source ~/.profile




—> add Cassandra
source ~/.profile

cat ~/.profile
export JAVA_HOME=$(/usr/libexec/java_home)
alias python="python3"

# include locally installed Cassandra in PATH
if [ -d "$HOME/opt" ]; then
    PATH="$PATH:$HOME/opt/cassandra/bin"
fi



------------------->
source ~/.profile






Install cqlsh
----—--------->
pip3 install cql





Install Cassandra
------------------->

curl -O https://apachemirror.wuchna.com/cassandra/4.0-rc1/apache-cassandra-4.0-rc1-bin.tar.gz

gzip -dc apache-cassandra-4.0-rc1-bin.tar.gz | tar xf -

ln -s ~/opt/packages/apache-cassandra-4.0-rc1 ~/opt/cassandra
mkdir -p ~/opt/cassandra/data/data
mkdir -p ~/opt/cassandra/data/commitlog
mkdir -p ~/opt/cassandra/data/saved_caches
mkdir -p ~/opt/cassandra/logs




Remove JDK15 if you are using
------------------->
/Library/Java/JavaVirtualMachines.

sudo rm -rf jdk-15.0.1.jdk





Install JDK
------------->
https://www.oracle.com/in/java/technologies/javase-jdk11-downloads.html
Install jdk 11





which java
------------->
/usr/bin/java

srinivaspanaganti@Srinivass-MacBook-Pro jdk-11.0.11.jdk % java --version

java 11.0.11 2021-04-20 LTS
Java(TM) SE Runtime Environment 18.9 (build 11.0.11+9-LTS-194)
Java HotSpot(TM) 64-Bit Server VM 18.9 (build 11.0.11+9-LTS-194, mixed mode)










Run Cassandra
------------->


/Users/srinivaspanaganti/opt/cassandra
bin/cassandra -f




start cqlsh
------------->


/Users/srinivaspanaganti/opt/cassandra/bin
 sh cqlsh





Create key space
---------------------------------------------------->

create keyspace srinivart with replication={'class':'SimpleStrategy', 'replication_factor':3};


describe keyspaces;

srinivart  system_auth         system_schema  system_views
system     system_distributed  system_traces  system_virtual_schema




describe keyspaces;
use srinivart;



create table
---------------------------------------------------->
CREATE TABLE Product(
   id int PRIMARY KEY,
   name text
);





describe tables;
---------------------------------------------------->
product


describe product;
---------------------------------------------------->
describe product;


CREATE TABLE srinivart.product (
    id int PRIMARY KEY,
    name text
) WITH additional_write_policy = '99p'
    AND bloom_filter_fp_chance = 0.01
    AND caching = {'keys': 'ALL', 'rows_per_partition': 'NONE'}
    AND cdc = false
    AND comment = ''
    AND compaction = {'class': 'org.apache.cassandra.db.compaction.SizeTieredCompactionStrategy', 'max_threshold': '32', 'min_threshold': '4'}
    AND compression = {'chunk_length_in_kb': '16', 'class': 'org.apache.cassandra.io.compress.LZ4Compressor'}
    AND crc_check_chance = 1.0
    AND default_time_to_live = 0
    AND extensions = {}
    AND gc_grace_seconds = 864000
    AND max_index_interval = 2048
    AND memtable_flush_period_in_ms = 0
    AND min_index_interval = 128
    AND read_repair = 'BLOCKING'
    AND speculative_retry = '99p';















Use the follwing data center in your application
---------------------------------------------------->
./nodetool status
Datacenter: datacenter1
=======================
Status=Up/Down
|/ State=Normal/Leaving/Joining/Moving
--  Address    Load        Tokens  Owns (effective)  Host ID                               Rack
UN  127.0.0.1  119.51 KiB  16      100.0%            80af991a-de38-4c0d-99b8-0e9bb0945018  rack1




Use this as data centre
---------------------------------------------------->
spring.data.cassandra.local-datacenter=datacenter1




check which cassandra using
---------------------------------------------------->
$ ps -ax | grep cassandra



check which one using 8080 port
---------------------------------------------------->
lsof -i :8080 | grep LISTEN java














API requests
============



Post
------

http://localhost:8080/api/products



{
	"id" : 1,
	"name" : "macbook"
}








/Users/srinivaspanaganti/opt/cassandra/bin
 sh cqlsh


describe keyspaces;

use srinivart;

describe tables;

product

select * from product;

 id | name
----+---------
  1 | macbook

(1 rows)








GET
—

http://localhost:8080/api/products


[
    {
        "id": 1,
        "name": "macbook"
    }
]


GET
————

http://localhost:8080/api/products/1

{
    "id": 1,
    "name": "macbook"
}



Delete
————

http://localhost:8080/api/products/1





select * from product;

 id | name
----+------

(0 rows)














References
----------

Cassandra Tutorial
——

https://www.tutorialspoint.com/cassandra/index.htm

https://bezkoder.com/spring-boot-cassandra-crud/
http://allaboutscala.com/big-data/cassandra/
https://www.youtube.com/watch?v=JmwcGJP3pzM
https://github.com/shameed1910/springboot-cassandra-docker

https://docs.spring.io/spring-data/cassandra/docs/current/reference/html/#reference

https://docs.datastax.com/en/cassandra-oss/2.1/cassandra/configuration/configCassandra_yaml_r.html

https://stackoverflow.com/questions/60668792/spring-data-with-cassandra-giving-illegalstateexception

https://stackoverflow.com/questions/10877072/stop-cassandra-server-on-mac-os-x

