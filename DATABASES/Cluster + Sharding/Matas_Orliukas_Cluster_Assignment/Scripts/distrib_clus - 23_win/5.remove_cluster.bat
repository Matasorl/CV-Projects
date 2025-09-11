REM #
REM #---------------------------------------------------#
REM #						        #
REM # 7. 			        #
REM #						        #
REM #---------------------------------------------------#
REM #	
RMDIR /S /Q "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\cfg0"
RMDIR /S /Q "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\cfg1"
RMDIR /S /Q "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\cfg2"
REM # 
RMDIR /S /Q "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\north0"
RMDIR /S /Q "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\north1"
RMDIR /S /Q "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\north2"
REM # 
RMDIR /S /Q "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\east0"
RMDIR /S /Q "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\east1"
RMDIR /S /Q "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\east2"
REM # 
RMDIR /S /Q "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\south0"
RMDIR /S /Q "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\south1"
RMDIR /S /Q "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\south2"
REM # 
RMDIR /S /Q "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\west0"
RMDIR /S /Q "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\west1"
RMDIR /S /Q "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\west2"
REM # 


Collection: orders
Shard Key: order_id
Chunk Size Limit: 64 MB

If a chunk contains order_id values from 1000 to 2000.
The chunk exceeds 64 MB, so MongoDB splits it.
MongoDB splits at the middle, around order_id 1500.
Chunk 1: order_id 1000–1500
Chunk 2: order_id 1501–2000
Two smaller chunks are created.
MongoDB may move these chunks to different shards for balanced data distribution.

