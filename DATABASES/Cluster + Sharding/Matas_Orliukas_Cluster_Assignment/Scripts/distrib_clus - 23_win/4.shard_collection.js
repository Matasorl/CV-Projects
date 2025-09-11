//----------------------------------------------------
//
//  CREATE AND INITIATE THE REPLICA SETS AND SHARDS
//
//----------------------------------------------------
//
//------------------------------------------------
// 1. GLOBAL VARIABLES 
//------------------------------------------------
//
db = db.getSisterDB("test");
var res = null;

//
//------------------------------------------------
// 2. 
//------------------------------------------------
//
res = sh.enableSharding("test");
while (res.ok != 1) {
    sleep(10);
    if (res.ok != 1){
        print("Enable test for Sharding Failed. Trying it again");
        res = sh.enableSharding("test");
    }
}
print("Test enabled for Sharding!");

//
//------------------------------------------------
// 3. 
//------------------------------------------------
//

res = db.diabetesInfo.createIndex({ "Diabetes_012": 1, "_Age": 1, "BMI": 1 });
while(res.ok != 1) {
	sleep(10);
	if (res.ok != 1) {
		print("Creating index for diabetesInfo collection failed. Trying it again.");
		db.diabetesInfo.createIndex({ "Diabetes_012": 1, "_Age": 1, "Sex": 1, "BMI": 1 });
	}
}
print("diabetesInfo Collection Index Created!");

//
//------------------------------------------------
// 4. 
//------------------------------------------------
//
res = sh.shardCollection("test.diabetesInfo", { "Diabetes_012": 1, "_Age": 1, "BMI": 1 });
while(res.ok != 1) {
	sleep(10);
	if(res.ok != 1) {
		print("Sharding diabetesInfo collection failed. Trying it again.");
		res = sh.shardCollection("test.diabetesInfo", { "Diabetes_012": 1, "_Age": 1, "Sex": 1, "BMI": 1 });
	}
}
print("diabetesInfo Collection Sharded!");

//
//------------------------------------------------
// 5. Print the status
//------------------------------------------------
//
for (i = 0; i < 20; i++) {
    sh.status();
    sleep(10000);
}
//
//------------------------------------------------
// 7. QUIT
//------------------------------------------------
//
quit()


