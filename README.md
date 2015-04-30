## ecdiff
Comparing different columns of Excel between two files

##Usage

 by id cloumn

```
java -jar target/ecdiff-0.1-SNAPSHOT.jar  
-a "src/test/resources/test_1.xlsx" -b "src/test/resources/test_2.xlsx" 
-aid 0 -bid 0 -ai 1 -bi 1
```

 by row
 
 ```
 java -jar target/ecdiff-0.1-SNAPSHOT.jar  
 -a "src/test/resources/test_1.xlsx" -b "src/test/resources/test_2.xlsx" 
 -ai 1 -bi 1 -byrow
 ```
 
