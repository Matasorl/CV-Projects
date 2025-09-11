var myName = "Matas";

var db = connect(myName + ".local:4000");
var res = null;
var resRWConcern = null;

res = rs.initiate(
  {
    _id: "configReplSet",
    configsvr: true,
    members: [
	{ _id: 0, host: myName + ".local:" + "4000"},
	{ _id: 1, host: myName + ".local:" + "4001"},
	{ _id: 2, host: myName + ".local:" + "4002"}
      ]
    }
);

while(res.ok != 1) {
	sleep(10);
	if(res.ok != 1) {
		print("Creating replica set failed. Try again");
		res = rs.initiate(
                   {
                     _id: "configReplSet",
    		     configsvr: true,
    		     members: [
			{ _id: 0, host: myName + ".local:" + "4000"},
			{ _id: 1, host: myName + ".local:" + "4001"},
			{ _id: 2, host: myName + ".local:" + "4002"}
      		   ]
    		}
	    );
	}		
}
print("Config Server Replica Set Created");

while (((rs.status().members[0].state != 1) && (rs.status().members[0].state != 2)) || 
       ((rs.status().members[1].state != 1) && (rs.status().members[1].state != 2)) || 
       ((rs.status().members[2].state != 1) && (rs.status().members[2].state != 2))) {
    sleep(10);
}
print("Config Server Replica Set Up");


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

print("Concerns set for Config Server Replica Set");







