package se.kth.castor.pomhist.git;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class ProcessGitOutputTest
{
    ProcessGitOutput processGitOutput;

    @Test
    void testAddAndRemoveDependency()
    {
        List<String> list = Arrays.asList(
            "+<artifactId>a</artifactId>",
            "-<artifactId>a</artifactId>");
        processGitOutput = new ProcessGitOutput(list);
        Assert.assertTrue(processGitOutput.processOutput().isEmpty());
    }

    @Test
    void testAddAndRemoveAndAddDependency()
    {
        List<String> list = Arrays.asList(
            "+<artifactId>a</artifactId>",
            "-<artifactId>a</artifactId>",
            "+<artifactId>a</artifactId>");
        processGitOutput = new ProcessGitOutput(list);
        Assert.assertArrayEquals(Arrays.asList("+a").toArray(), processGitOutput.processOutput().toArray());
    }

    @Test
    void testAddAndAddDependency()
    {
        List<String> list = Arrays.asList(
            "+<artifactId>a</artifactId>",
            "+<artifactId>a</artifactId>");
        processGitOutput = new ProcessGitOutput(list);
        Assert.assertTrue(processGitOutput.processOutput().isEmpty());
    }

    @Test
    void testAddAndAddAndAddDependency()
    {
        List<String> list = Arrays.asList(
            "+<artifactId>a</artifactId>",
            "+<artifactId>a</artifactId>",
            "+<artifactId>a</artifactId>");
        processGitOutput = new ProcessGitOutput(list);
        Assert.assertTrue(processGitOutput.processOutput().isEmpty());
    }

    @Test
    void testRemoveAndRemoveAndRemoveDependency()
    {
        List<String> list = Arrays.asList(
            "-<artifactId>a</artifactId>",
            "-<artifactId>a</artifactId>",
            "-<artifactId>a</artifactId>");
        processGitOutput = new ProcessGitOutput(list);
        Assert.assertTrue(processGitOutput.processOutput().isEmpty());
    }

    @Test
    void testMoreComplexCase()
    {
        List<String> list = Arrays.asList(
            "+<artifactId>a</artifactId>",
            "+<artifactId>b</artifactId>",
            "-<artifactId>a</artifactId>",
            "+<artifactId>c</artifactId>",
            "-<artifactId>c</artifactId>",
            "-<artifactId>b</artifactId>",
            "+<artifactId>c</artifactId>",
            "-<artifactId>a</artifactId>",
            "+<artifactId>b</artifactId>");
        processGitOutput = new ProcessGitOutput(list);
        Assert.assertArrayEquals(Arrays.asList("+b", "+c").toArray(), processGitOutput.processOutput().toArray());
    }

}
