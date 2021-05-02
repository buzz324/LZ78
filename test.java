import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class test {

    /*public static void main(String [] args){


        LZencode encode = new LZencode();




                if(args.length != 1){
                    System.err.println("Usage:  java TestStream <filename>");
                    return;
                }


                try{
                    String file ="src/1.txt";

                    FileInputStream fis = new FileInputStream((file));
                    int c;

                    Path tempFile = Files.createTempFile("temp-", ".txt");

                    while((c = fis.read()) != -1){
                        encode.add(c);
                        //System.out.println(c + " --> " + (char)c);
                    }
                }catch(Exception e){
                }finally{
                }
            }*/



/*
        try{

            String file ="src/1.txt";
            BufferedReader br = new BufferedReader(new FileReader(file));


            String s=br.readLine();
            String delims = ",;:! \t\n";
            while(s!=null){
                StringTokenizer st = new StringTokenizer(s,delims);
                while(st.hasMoreTokens()){
                    String t = st.nextToken();
                    System.out.println("Token: "+t);

                }
                s=br.readLine();
            }
        }catch(Exception e){
            System.err.println("Trouble!");
        }
*/




    }


