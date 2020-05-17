package se.kth.castor.pomhist;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import se.kth.castor.pomhist.git.Git;
import se.kth.castor.pomhist.git.GitOutput;
import se.kth.castor.pomhist.git.ProcessGitOutput;

public class Main
{

    public static void main(String[] args) throws IOException, InterruptedException
    {
        if (args.length == 1) {
            Path projectDir = Paths.get(args[0]);
            Files.createDirectories(projectDir);
            Git git = new Git();
            git.gitLog(projectDir);
            List<String> lines = GitOutput.getLines();
            Collections.reverse(lines);
            ProcessGitOutput processGitOutput = new ProcessGitOutput(lines);
            List<String> output = processGitOutput.processOutput();
            output.forEach(System.out::println);
        } else {
            System.out.println("Run: <pomhist-version-jar-with-dependencies.jar> <path-to-a-maven-project>");
        }
    }
}
