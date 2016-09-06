package phoenix.delta;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;

public class Procedure implements Serializable{
    private int lastSessionID;
    private long lastSessionPrerewardDelay;
    public Session currentSession;

    private String subjectID;

    public Procedure(Context context, String subjectID) {
        this.subjectID = subjectID;
        File root = context.getFilesDir();
        File subjectFile = new File(root, subjectID);
        if (!subjectFile.exists())
        {
            subjectFile.mkdir();
        }
        try {
            FileInputStream fis = context.openFileInput(subjectID + File.pathSeparator + "progress.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line = reader.readLine();
            while(line != null){
                String[] parts = line.split(",");
                String sessionID = parts[0];
                String prerewardDelay = parts[1];
                lastSessionID = Integer.parseInt(sessionID);
                lastSessionPrerewardDelay = Long.parseLong(prerewardDelay);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            //first session, leave
            lastSessionID = -1;
        } catch (IOException e) {
            e.printStackTrace();
            lastSessionID = -1;
        }

    }

    public boolean startNewSession()
    {
        if (currentSession != null)
        {
            return false;
        }
        else
        {
            if (lastSessionID == -1)
            {
                currentSession = new Session(0);
                currentSession.initDelay = 1*1000L;
                currentSession.sessionType = SessionType.ESTABLISH_INDIFFERENCE;
            }
            else
            {
                currentSession = new Session(lastSessionID + 1);
                currentSession.initDelay = lastSessionPrerewardDelay;
                if (currentSession.sessionID <= 25) {
                    currentSession.sessionType = SessionType.SHAPING;
                }
                else
                {
                    currentSession.sessionType = SessionType.ESTABLISH_INDIFFERENCE;
                }
            }
        }
        return true;
    }

    public boolean endSession(Context context)
    {
        if(currentSession != null)
        {
            writeSessionToFile(context, currentSession);
            writeProgressToFile(context, currentSession);
            lastSessionID = currentSession.sessionID;
            lastSessionPrerewardDelay = currentSession.waitTime.getPrerewardDelay();
            currentSession = null;

            return true;
        }
        return false;
    }

    private void writeSessionToFile(Context context, Session currentSession) {
        String fileLocation = currentSession.sessionID + ".csv";
        FileHandling fh = new FileHandling();
        fh.fileWriter(context, subjectID, fileLocation, currentSession.fileContents());
    }

    private void writeProgressToFile (Context context, Session currentSession){
        String fileLocation = "progress.csv";
        FileHandling fh = new FileHandling();
        fh.fileAppender(context, subjectID, fileLocation, currentSession.sessionID + "," + currentSession.waitTime.getPrerewardDelay() + "\n");
    }
}