//----------------------------------------------------
//
//  CREATE AND INITIATE THE REPLICA SETS AND SHARDS
//
//----------------------------------------------------
//
//------------------------------------------------
// 0. 
//------------------------------------------------
var myName = "Matas";
//
//------------------------------------------------
// 1. GLOBAL VARIABLES 
//------------------------------------------------
var db = db.getSisterDB("config");
var mongosConn = db;
var res = null;
var resRWConcern = null;

//
//------------------------------------------------
// 2. 
//------------------------------------------------
db.settings.save( { _id:"chunksize", value: 64 } )

//
//------------------------------------------------
// 3. NORTH
//------------------------------------------------
//
// 1. 
//
db = connect(myName + ".local:4100/test");

//
// 2. 
//
res = rs.initiate({
    "_id": "northReplSet",
    "members": [
        {_id: 0, host: myName + ".local:" + "4100"},
        {_id: 1, host: myName + ".local:" + "4101"},
        {_id: 2, host: myName + ".local:" + "4102"}
    ]
});

resRWConcern = db.adminCommand( {
	setDefaultRWConcern : 1,
	defaultReadConcern : {level: "majority"},
	defaultWriteConcern : {w: "majority"}
	});

while(resRWConcern.ok != 1) {
	sleep(10);
	print("Setting Concerns failed. Trying again");
	if(resRWConcern.ok != 1) {
		resRWConcern = db.adminCommand( {
			setDefaultRWConcern : 1,
			defaultReadConcern : {level: "majority"},
			defaultWriteConcern : {w: "majority"}
		});
	}
}

print("Concerns set for North Replica Set");

//
// 3. WAIT UNTIL ALL THE NODES OF THE REPLICA SET ARE UP AND RUNNING
//
while (res.ok != 1) {
    sleep(10);
}
print("North Replica Set Created!");

while (((rs.status().members[0].state != 1) && (rs.status().members[0].state != 2)) || 
       ((rs.status().members[1].state != 1) && (rs.status().members[1].state != 2)) || 
       ((rs.status().members[2].state != 1) && (rs.status().members[2].state != 2))) {
    sleep(10);
}
print("North Replica Set Up!");

//
// 4. 
//
db = mongosConn;
res = sh.addShard("northReplSet/" + myName + ".local:4100");

while (res.ok != 1) {
    sleep(60);
    if (res.ok != 1) {
        print("Adding Shard Failed. Trying it again");
        res = sh.addShard("northReplSet/" + myName + ".local:4100");
    }
}
print("North Shard Added!");

//
//------------------------------------------------
// 4. EAST
//------------------------------------------------
//
// 1. 
//
db = connect(myName + ".local:4200/test");

//
// 2. 
//
res = rs.initiate({
    "_id": "eastReplSet",
    "members": [
        {_id: 0, host: myName + ".local:" + "4200"},
        {_id: 1, host: myName + ".local:" + "4201"},
        {_id: 2, host: myName + ".local:" + "4202"}
    ]
});

resRWConcern = db.adminCommand( {
	setDefaultRWConcern : 1,
	defaultReadConcern : {level: "majority"},
	defaultWriteConcern : {w: "majority"}
	});

while(resRWConcern.ok != 1) {
	sleep(10);
	print("Setting Concerns failed. Trying again");
	if(resRWConcern.ok != 1) {
		resRWConcern = db.adminCommand( {
			setDefaultRWConcern : 1,
			defaultReadConcern : {level: "majority"},
			defaultWriteConcern : {w: "majority"}
		});
	}
}

print("Concerns set for East Replica Set");

//
// 3. WAIT UNTIL ALL THE NODES OF THE REPLICA SET ARE UP AND RUNNING
//
while (res.ok != 1) {
    sleep(10);
}
print("East Replica Set Created!");

while (((rs.status().members[0].state != 1) && (rs.status().members[0].state != 2)) || 
       ((rs.status().members[1].state != 1) && (rs.status().members[1].state != 2)) || 
       ((rs.status().members[2].state != 1) && (rs.status().members[2].state != 2))) {
    sleep(10);
}
print("East Replica Set Up!");

//
// 4. 
//
db = mongosConn;
res = sh.addShard("eastReplSet/" + myName + ".local:4200");

while (res.ok != 1) {
    sleep(60);
    if (res.ok != 1) {
        print("Adding Shard Failed. Trying it again");
        res = sh.addShard("eastReplSet/" + myName + ".local:4200");
    }
}
print("East Shard Added!");

//
//------------------------------------------------
// 5. SOUTH
//------------------------------------------------
//
// 1. 
//
db = connect(myName + ".local:4300/test");

//
// 2. 
//
res = rs.initiate({
    "_id": "southReplSet",
    "members": [
        {_id: 0, host: myName + ".local:" + "4300"},
        {_id: 1, host: myName + ".local:" + "4301"},
        {_id: 2, host: myName + ".local:" + "4302"}
    ]
});

resRWConcern = db.adminCommand( {
	setDefaultRWConcern : 1,
	defaultReadConcern : {level: "majority"},
	defaultWriteConcern : {w: "majority"}
	});

while(resRWConcern.ok != 1) {
	sleep(10);
	print("Setting Concerns failed. Trying again");
	if(resRWConcern.ok != 1) {
		resRWConcern = db.adminCommand( {
			setDefaultRWConcern : 1,
			defaultReadConcern : {level: "majority"},
			defaultWriteConcern : {w: "majority"}
		});
	}
}

print("Concerns set for South Replica Set");

//
// 3. WAIT UNTIL ALL THE NODES OF THE REPLICA SET ARE UP AND RUNNING
//
while (res.ok != 1) {
    sleep(10);
}
print("South Replica Set Created!");

while (((rs.status().members[0].state != 1) && (rs.status().members[0].state != 2)) || 
       ((rs.status().members[1].state != 1) && (rs.status().members[1].state != 2)) || 
       ((rs.status().members[2].state != 1) && (rs.status().members[2].state != 2))) {
    sleep(10);
}
print("South Replica Set Up!");

//
// 4. 
//
db = mongosConn;
res = sh.addShard("southReplSet/" + myName + ".local:4300");

while (res.ok != 1) {
    sleep(60);
    if (res.ok != 1) {
        print("Adding Shard Failed. Trying it again");
        res = sh.addShard("southReplSet/" + myName + ".local:4300");
    }
}
print("South Shard Added!");

//
//------------------------------------------------
// 6. WEST
//------------------------------------------------
//
// 1. 
//
db = connect(myName + ".local:4400/test");

//
// 2. 
//
res = rs.initiate({
    "_id": "westReplSet",
    "members": [
        {_id: 0, host: myName + ".local:" + "4400"},
        {_id: 1, host: myName + ".local:" + "4401"},
        {_id: 2, host: myName + ".local:" + "4402"}
    ]
});

resRWConcern = db.adminCommand( {
	setDefaultRWConcern : 1,
	defaultReadConcern : {level: "majority"},
	defaultWriteConcern : {w: "majority"}
	});

while(resRWConcern.ok != 1) {
	sleep(10);
	print("Setting Concerns failed. Trying again");
	if(resRWConcern.ok != 1) {
		resRWConcern = db.adminCommand( {
			setDefaultRWConcern : 1,
			defaultReadConcern : {level: "majority"},
			defaultWriteConcern : {w: "majority"}
		});
	}
}

print("Concerns set for West Replica Set");

//
// 3. WAIT UNTIL ALL THE NODES OF THE REPLICA SET ARE UP AND RUNNING
//
while (res.ok != 1) {
    sleep(10);
}
print("West Replica Set Created!");

while (((rs.status().members[0].state != 1) && (rs.status().members[0].state != 2)) || 
       ((rs.status().members[1].state != 1) && (rs.status().members[1].state != 2)) || 
       ((rs.status().members[2].state != 1) && (rs.status().members[2].state != 2))) {
    sleep(10);
}
print("West Replica Set Up!");

//
// 4.
//
db = mongosConn;
res = sh.addShard("westReplSet/" + myName + ".local:4400");

while (res.ok != 1) {
    sleep(60);
    if (res.ok != 1) {
        print("Adding Shard Failed. Trying it again");
        res = sh.addShard("westReplSet/" + myName + ".local:4400");
    }
}
print("West Shard Added!");

//
//------------------------------------------------
// 7. QUIT
//------------------------------------------------
quit()
