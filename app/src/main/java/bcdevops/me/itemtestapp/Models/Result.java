package bcdevops.me.itemtestapp.Models;

public class Result {
    private String testNameLine;
    private String errorLine;
    private String messageLine;
    private long mSecondsRunTime;
    int numErrors;

    public Result (String testNameLine, String errorLine, String messageLine, int numErrors, long mSecondsRunTime){
        this.testNameLine = testNameLine;
        this.errorLine = errorLine;
        this.messageLine = messageLine;
        this.numErrors = numErrors;
        this.mSecondsRunTime = mSecondsRunTime;
    }

    public String getTestName(){
        return testNameLine;
    }

    public String getErrorLine() {
        return errorLine;
    }

    public String getMessageLine(){
        return messageLine;
    }

    public int getNumErrors(){
        return numErrors;
    }

}
