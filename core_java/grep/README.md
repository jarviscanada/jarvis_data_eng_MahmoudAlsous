# Introduction
This is one of the three applications of the core_java project. The Grep application is designed to act as the `grep` command in Linux, which allows users to search all files in directories for matching strings when given a root directory. This app consists of two classes called `JavaGrepImp` and `JavaGrepLambdaImp` where the first uses for loops to iterate through text lines in files while the latter uses Lambda and Stream APIs to provide more convenient search implementation. The application was tested using test cases and Junit testing in the main method. The application is also wrapped in a FatJar and deployed on docker for image usage purposes.

List of technologies  used:
- Java
- Maven
- Lambda and Stream API
- Regex
- Slf4j and log4j loggers for errors

# Quick Start
The applications requires the user to provide the following three arguments to execute the application:
- `regex` - regex that is being searched for
- `rootPath` - path of root directory
- `outFile` - the output file name

To execute the application you can use the CLI and run the JAR file with the following:

`cd core_java/grep`

`mvn clean compile package`
    
 `java -cp target/grep-1.0-SNAPSHOT.jar ca.jrvs.apps.grep.JavaGrepImp ${regex} ./${rootPath} ./out/${outfile}`
 
 or you can execute the application use docker with the following:
 
`cd core_java/grep`

`mvn clean compile package`

`docker build -t grep_app .`

```docker run --rm -v `pwd`/data:/data -v `pwd`/log:/log grep_app ${regex} /${rootPath} ./out/${outfile}```

# Implemenation
## Pseudocode
Process() method:
```
matchedLines = []
for file in listFilesRecursively(rootDir)
  for line in readLines(file)
      if containsPattern(line)
        matchedLines.add(line)
writeToFile(matchedLines)
```

## Performance Issue
The application will suffer from memory issues when running large data files through the JVM, resulting in an OutOfMemoryError exception. This can happen when using list data structures since they may store large amounts of string lines if the file contains many. To mitigate this, Lambda and Stream APIs were used instead of lists and arrays because Stream APIs do not store data and focus more on processing the data directly, which can save a large amount of memory.

Lambdas also contain functions that can replace for loops to minimize memory usage for the JVM, since they are more memory efficient than for loops.

# Test
The Grep app was tested by inputting sample data in the program. The data was entered manually with different root directory paths, ouput file names, and regex strings. The results of the grep app were compared to the grep command results in Linux.

# Deployment
To deploy the grep app, a Docker Image was created and stored into the Docker Hub to allow for easier access. To view the docker repository, click on the following link:
https://hub.docker.com/r/grepdocker/grep

# Improvement
- Updating the methods in the interface to return streams rather than lists for more memory usage optimization.
- Adding more information on the matched strings such as the location of the file and string location in the file.
- Adding more error handling and method exceptions.
