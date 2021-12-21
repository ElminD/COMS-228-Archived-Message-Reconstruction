import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Author Elmin Didic
 */

public class ArchMsgDecoder {

    private static int staticCharIdx = 0;

    public static void main(String[] args)
    {
        String archFileName;
        String codeMessage = "";

        Scanner scan = new Scanner(System.in);

        System.out.println("Please enter filename to decode:  ");
        archFileName = scan.next();

        ArrayList<String> lines = fileReader(archFileName);



        for(int i = 0; i < lines.size() - 2; i++) {
            codeMessage += lines.get(i) + '\n';
        }
        codeMessage += lines.get(lines.size() - 2);


        MsgTree mainTree = new MsgTree(codeMessage);

        mainTree.printCodes(mainTree, "");
        
        int firstln = lines.get(lines.size() - 1).length();

        while(staticCharIdx < firstln) {
            decode(mainTree, lines.get(lines.size() - 1));
        }


    }


    /**
     * Reades a given file an returns a string
     * @param fileName
     * @return
     */
     static ArrayList fileReader(String fileName)
    {

        ArrayList<String> lines = new ArrayList<String>();
        try
        {
            File file = new File(fileName);
          Scanner fileScanner = new Scanner(file);

          while(fileScanner.hasNext())
          {
              lines.add(fileScanner.nextLine());
          }
          fileScanner.close();
        }
        catch(Exception e)
        {
            System.out.println("File not found");
        }






        return lines;
    }


    /**
     * Uses a tree created by the file strings and plugs the
     * msg in to get a output
     * @param codes
     * @param msg
     *
     *
     */
        public static void decode(MsgTree codes, String msg) {


            while(codes.left != null && codes.right != null)
            {


                if(msg.charAt(staticCharIdx) == '0')
                {

                    codes = codes.left;
                }
                else
                {

                    codes = codes.right;
                }
                staticCharIdx +=1;
            }

            if(codes.payloadChar == '\n')
            {
                System.out.print("\n");
            }
            else {
                System.out.print(codes.payloadChar);
            }














        }














    }

