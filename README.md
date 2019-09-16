# find-top-url
find top 100 urls

一、Basic idea

    1. generate 100GB url file randomly
    2. split 100GB url file into multiple sub files by hash function
    3. iterate sub files in turn and build a 100 size minHeap
    4. output the top 100 urls and their occurrences

二、Running steps

    1. package project
        clean
        install
    2. start project
        java -jar -Xmx1g -Xms1g packagename.jar >logname.log 2>&1 &
        for example:
            java -jar -Xmx1g -Xms1g find-top-rul-1.0-SNAPSHOT.jar >find.log 2>&1 &
    3. generate 100GB url file by controller API
        http://localhost:8828/topUrl/createFile
    4. get top 100 urls and their occurrences by controller API
        http://localhost:8828/topUrl/fintTopUrl
    