package se.kth.castor.pomhist.git;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class ProcessGitOutput
{
    List<String> processOutput;

    public ProcessGitOutput(final List<String> processOutput)
    {
        this.processOutput = processOutput;
    }

    public ArrayList<String> processOutput()
    {
        List<String> output = new ArrayList<>();
        for (String s : processOutput) {
            if (isArtifactId(s)) {
                output.add(s
                    .replace(" ", "")
                    .replace("\t", "")
                    .replace("<artifactId>", "")
                    .replace("</artifactId>", ""));
            }
        }
        return detect(output);
    }

    private boolean isArtifactId(final String s)
    {
        return s.matches("^+.*<artifactId>.*</artifactId>$") ||
            s.matches("^-.*<artifactId>.*</artifactId>$");
    }

    private ArrayList<String> detect(List<String> output)
    {
        TreeSet<String> treeSet = new TreeSet<>();
        for (int i = 0; i < output.size() - 1; ++i) {
            String current = output.get(i);
            if (current.startsWith("+")) {
                boolean a = false;
                boolean b = false;
                for (int j = ++i; j < output.size(); ++j) {
                    if (output.get(j).equals(current)) {
                        a = true;
                    }
                    if (output.get(j).equals(current.replace("+", "-"))) {
                        b = true;
                    }
                }
                if (a && b) {
                    treeSet.add(current);
                }
            }
        }
        return new ArrayList<>(treeSet);
    }
}
