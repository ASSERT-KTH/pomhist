package se.kth.castor.pomhist.git;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class Git
{
    public static void gitLog(Path directory) throws IOException, InterruptedException
    {
        runCommand(directory, "git", "log", "-p", "-U0", "pom.xml");
    }

    private static void runCommand(Path directory, String... command) throws IOException, InterruptedException
    {
        Objects.requireNonNull(directory, "directory");
        if (!Files.exists(directory)) {
            throw new RuntimeException("can't run command in non-existing directory '" + directory + "'");
        }
        ProcessBuilder pb = new ProcessBuilder()
            .command(command)
            .directory(directory.toFile());
        Process p = pb.start();
        GitOutput errorProcess = new GitOutput(p.getErrorStream());
        GitOutput outputProcess = new GitOutput(p.getInputStream());
        outputProcess.start();
        errorProcess.start();
        int exit = p.waitFor();
        errorProcess.join();
        outputProcess.join();
        if (exit != 0) {
            throw new AssertionError(String.format("runCommand returned %d", exit));
        }
    }
}

