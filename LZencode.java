import org.w3c.dom.ls.LSException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class LZencode {

    private LZbytes root;
    private int phaseNumber;


    public LZencode() throws FileNotFoundException {
    }

    public class LZbytes{
            //set the variables for binary search tree
            private int key;
            private int phaseNumber;

            private LZbytes left;
            private LZbytes right;
            private LZbytes same;

            //constructor for BST

            public LZbytes(int k){

                this.key=k;
                this.left=null;
                this.right=null;
                this.same=null;
        }
    }

    public static void main(String [] args) throws IOException {

        LZencode encode = new LZencode();

         /*       if(args.length != 1){
                    System.err.println("Usage:  java TestStream <filename>");
                    return;
                }*/


        String file = "1.txt";
        FileInputStream fis = new FileInputStream((file));

        int c;
        while ((c = fis.read()) != -1) {

            encode.addRoot(c);
            encode.output(encode.root, fis.read());
            //System.out.println(c + " --> " + (char)c);
        }
    }


   /* private static void encode() throws IOException {
        LZencode LZ = new LZencode();
        String file ="1.txt";


        FileInputStream fis = new FileInputStream((file));
        int c;
        while((c = fis.read()) != -1){


        }
    }*/

        public void addRoot(int k) {

            LZbytes tmp = new LZbytes(k);

            if (root == null) {

                root = tmp;
                root.phaseNumber = 0;
                System.out.println(root.phaseNumber + " " + (char) root.key);
            }
        }




    public void output(LZbytes localRoot, int input ) throws IOException {

        if (has(input)){

            System.out.print((char) input);//print identical key;


            //Find NEXT DATUM
           // output(find(input),fis.read() );//wrong input(in 2nd place)

        }else {

            //Print mismatched character
            LZbytes misMatched=insert(localRoot,input);

            phaseNumber+=1;
            //INSERT NEW MISMATCH TO THE TREE with its phase number
            misMatched.phaseNumber=phaseNumber;
        }
    }

    public LZbytes insert(LZbytes localRoot, int c ){

            LZbytes newNode =  new LZbytes(c);
        //compare value with the root (larger)
        if (newNode.key>localRoot.key){

            if(localRoot.right!=null){

                //change the current position to right child
                localRoot=localRoot.right;
                //iterate until the value find a last leaf
                insert(localRoot,newNode.key);
            }

            //last leaf to add
            else {

                localRoot.right= newNode;
                phaseNumber+=1;
                return newNode;
            }
        }
        //smaller value than the root
        else if (newNode.key<localRoot.key){

            //check if there is more roots on the left
            if(localRoot.left!=null){

                // there is more so change the root position to the next left root
                localRoot=localRoot.left;

                //iterate until the value find a last leaf
                insert(localRoot,newNode.key);
            }

            //last leaf to add
            else{
                localRoot.left=newNode;
                phaseNumber+=1;
                return newNode;
            }
        }
        if(localRoot.same!=null){

            //change the current position to right child
            localRoot=localRoot.same;
            //iterate until the value find a last leaf
            insert(localRoot,newNode.key);
        }
        localRoot.same=newNode;
        phaseNumber+=1;
        return newNode;
    }

    public boolean has(int k){
        LZbytes curr=root;
        if (curr.key==k)return true;


        //while Root has value and the value is what we looking for keep searching
        while(curr!=null&&curr.key!=k){
            if(curr.key>k){
                //the value is smaller than root, to the left child
                curr=curr.left;
            } else {

                //the value is larger than root, to the right child
                curr=curr.right;
            }
        }

        //is curr has a value than return true
        if (curr==null)return false;
        return true;
    }

    public LZbytes find(int k){
        LZbytes curr=root;

        //while Root has value and the value is what we looking for keep searching
        while(curr!=null&& !(curr.key ==k)){

            if(curr.key<k){

                curr=curr.right;
            }
            else {
                curr = curr.left;
            }
        }

        // value found and return it
        return curr;
    }


}

