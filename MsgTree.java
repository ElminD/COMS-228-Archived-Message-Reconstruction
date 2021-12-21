/**
 * @Author Elmin Didic
 */

public class MsgTree {

    public char payloadChar;
    public MsgTree left;
    public MsgTree right;



    /*Can use a static char idx to the tree string for recursive
    solution, but it is not strictly necessary*/
    private static int staticCharIdx = 0;

    //Constructor building the tree from a string
    public MsgTree(String encodingString){
        payloadChar = encodingString.charAt(staticCharIdx);

        staticCharIdx += 1;

        left = new MsgTree(encodingString.charAt(staticCharIdx));

        if(left.payloadChar == '^')
        {
            left = new MsgTree(encodingString);
        }

        staticCharIdx += 1;

        right = new MsgTree(encodingString.charAt(staticCharIdx));

        if(right.payloadChar == '^')
        {
            right = new MsgTree(encodingString);
        }
    }

    //Constructor for a single node with null children
    public MsgTree(char payloadChar){
        this.payloadChar = payloadChar;
    }

    //method to print characters and their binary codes
    public static void printCodes(MsgTree root, String code){

        if(root == null)
        {
            return;
        }

        if(root.payloadChar != '^' )
        {
            if(root.payloadChar == '\n')
            {
                System.out.println("\\n " + code);
            }
            else {
                System.out.println(root.payloadChar + "  " + code);
            }
        }


        printCodes(root.left, code + "0");
        printCodes(root.right, code + "1");

    }


}
