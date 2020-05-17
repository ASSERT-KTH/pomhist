package se.kth.castor.pomhist.git;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GitOutput extends Thread
{
    private final InputStream is;
    private static List<String> lines;

    public GitOutput(InputStream is)
    {
        this.is = is;
        lines = new ArrayList<>();
    }

    @Override
    public void run()
    {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is));) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static List<String> getLines()
    {
        return lines;
    }
}
