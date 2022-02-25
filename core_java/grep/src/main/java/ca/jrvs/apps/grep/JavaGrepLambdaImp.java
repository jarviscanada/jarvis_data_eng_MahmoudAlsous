package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaGrepLambdaImp extends JavaGrepImp {
    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Usage: JavaGrep regex rootPath outFile");
        }

        //Creating JavaGrepLambdaImp instead of JavaGrepImp
        //JavaGrepLambda inherits all methods except two override method in read lines and list file
        JavaGrepLambdaImp javaGrepLambdaImp = new JavaGrepLambdaImp();
        javaGrepLambdaImp.setRegex(args[0]);
        javaGrepLambdaImp.setRootPath(args[1]);
        javaGrepLambdaImp.setOutFile(args[2]);

        try {
            //calling parent method,
            //but it will call override method (in this class)
            javaGrepLambdaImp.process();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Implement using lambda and stream APIs
     * @param inputFile
     * @return
     */
    @Override
    public List<String> readLines(File inputFile){
        List<String> lines = new ArrayList<String>();
        try {
            Stream<String> file = Files.lines(inputFile.toPath());
            file.forEach(line -> lines.add(line));

        } catch (IOException ex) {
            logger.error("Errors: Failed to read file", ex);
        }
        return lines;
    }

    /**
     * Implement using lambda and stream APIs
     * @param rootDir
     * @return
     */
    @Override
    public List<File> listFiles(String rootDir){
        File file = new File(rootDir);
        List<File> fileList = new ArrayList<File>();
        file.listFiles(f -> fileList.add(f));

        return fileList;
    }
}