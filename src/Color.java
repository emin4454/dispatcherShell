public class Color {
    private static final String textColor[] = {"\u001B[31m",//Red
            "\u001B[32m",//Green
            "\u001B[33m",//Yellow
            "\u001B[34m",//Blue
            "\u001B[35m",//Purple
            "\u001B[36m",//Cyan
            "\u001B[37m"};//White

    private static final String backgroundColor[] = {
            "\u001B[41m",//Red
            "\u001B[42m",//Green
            "\u001B[43m",//Yellow
            "\u001B[44m",//Blue
            "\u001B[45m",//Purple
            "\u001B[46m",//Cyan
            "\u001B[47m",//White
            "\u001B[40m"};//Black

    public static int textColorSize = textColor.length;
    public static int backgroundColorSize = backgroundColor.length;
    private static int textColorCounter = 0;
    private static int backgroundColorCounter = 0;
    public static String[] setTextColor()
    {
        String returnColorArray[] = {"", ""};
        if(textColorCounter == backgroundColorCounter)
            textColorCounter++;
        textColorCounter = textColorCounter % textColorSize;
        backgroundColorCounter = backgroundColorCounter % backgroundColorSize;
        returnColorArray[0] = textColor[textColorCounter];
        returnColorArray[1] = backgroundColor[backgroundColorCounter];
        backgroundColorCounter++;
        if(backgroundColorCounter == 7)
            textColorCounter++;
        return returnColorArray;
    }
}
