import org.w3c.dom.ls.LSException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class LZencode {




    public static void main(String [] args){


        LZencode encode = new LZencode();

/*
                if(args.length != 1){
                    System.err.println("Usage:  java TestStream <filename>");
                    return;
                }*/


        try{
            String file ="src/1.txt";

            FileInputStream fis = new FileInputStream((file));
            int c;
            Path tempFile = Files.createTempFile("temp-", ".txt");

            Files.write(tempFile, "Hello From Temp File\n".getBytes(StandardCharsets.UTF_8));

            while((c = fis.read()) != -1){
                encode.add(c);
                //System.out.println(c + " --> " + (char)c);
            }
        }catch(Exception e){
        }finally{
        }
    }

    private LZbytes root;
    private int phaseNumber;


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

    public void add(int k){

        LZbytes tmp = new LZbytes(k);
        LZbytes pointer;

        if (root==null){
            root=tmp;
            phaseNumber=0;
            System.out.println(phaseNumber+" "+(char)root.key);
            return;
        }
        pointer=root;

        findLongest(pointer, tmp);
        phaseNumber += 1;
       // System.out.println(phaseNumber+" ");
    }


    public void findLongest(LZbytes localRoot, LZbytes input){

        if (has(input.key)){
            //FIND THE NODE
            LZbytes matchedLZbyte=find(input.key);
           System.out.println( matchedLZbyte.key);

        }else {
            //INSERT NEW MISMATCH TO THE TREE
            insert(localRoot, input);
        }
    }

    public void insert(LZbytes localRoot, LZbytes newNode){


        //compare value with the root (larger)
        if (newNode.key>localRoot.key){

            if(localRoot.right!=null){

                //change the current position to right child
                localRoot=localRoot.right;
                //iterate until the value find a last leaf
                insert(localRoot,newNode);
            }

            //last leaf to add
            else {

                localRoot.right= newNode;

            }
        }
        //smaller value than the root
        else {

            //check if there is more roots on the left
            if(localRoot.left!=null){

                // there is more so change the root position to the next left root
                localRoot=localRoot.left;

                //iterate until the value find a last leaf
                insert(localRoot,newNode);
            }

            //last leaf to add
            else{

                localRoot.left=newNode;

            }
        }
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

