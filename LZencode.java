import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;

public class LZencode {

    private LZbytes root;
    private int phaseNumber;


    public class LZbytes{
            //set the variables for binary search tree
            private int key;
            private int phaseNumber;

            private LZbytes left;
            private LZbytes right;
            private LZbytes next;

            //constructor for BST

            public LZbytes(int k){

                this.key=k;
                this.left=null;
                this.right=null;
                this.next =null;
        }
        public LZbytes(){

            this.left=null;
            this.right=null;
            this.next =null;
        }
    }

    public static void main(String [] args) throws IOException {

        LZencode encode = new LZencode();

                if(args.length != 1){
                    System.err.println("Enter a filename");
                    return;
                }

                try {
                    encode.output(args[0]);

                }catch (Exception e){
                    System.err.println("There is no such a file");
                }

    }


    public void output( String file) throws IOException {

        //String file = "1.txt";
        FileInputStream fis = new FileInputStream(file);

        phaseNumber=0;


        int c;
        while ((c = fis.read()) != -1) {


            //First root initialization
            if (root == null) {

            root = new LZbytes(c);
            root.next =new LZbytes();
            root.phaseNumber = phaseNumber;
            phaseNumber++;
            root.next.phaseNumber=phaseNumber;

            System.out.println(root.phaseNumber + " " + (char) root.key);
        }

        else {
            LZbytes localRoot = root;

            //There is match
            while (has(c, localRoot)) {
                System.out.println(find(c,localRoot).next);

                //System.out.print((char) c);
                localRoot=find(c,localRoot).next;

                c = fis.read();
            }
            //Print mismatched character
            LZbytes misMatched = insert(localRoot, c);
            misMatched.next = new LZbytes();

                phaseNumber++;
            misMatched.next.phaseNumber=phaseNumber;

            System.out.println(misMatched.phaseNumber+" "+(char) c);
            //System.out.println((char)c);
        }
        }
    }


    public LZbytes insert(LZbytes localRoot, int c ){

        //Root key is empty (mismatch)
            LZbytes newNode =  new LZbytes(c);
            if(localRoot.key==0){
                localRoot.key=c;
                return localRoot;
            }

        //compare value with the root (larger)
        if (c>localRoot.key){

            if(localRoot.right!=null){
                //change the current position to right child
                localRoot=localRoot.right;
                //iterate until the value find a last leaf
                insert(localRoot,c);
            }

            //last leaf to add
            else {

                localRoot.right= newNode;
                return newNode;
            }
        }
        //smaller value than the root
        else if (c<localRoot.key){

            //check if there is more roots on the left
            if(localRoot.left!=null){

                // there is more so change the root position to the next left root
                localRoot=localRoot.left;

                //iterate until the value find a last leaf
                insert(localRoot,c);
            }

            //last leaf to add
            else{
                localRoot.left=newNode;
                return newNode;
            }
        }
        if(localRoot.next !=null){

            //change the current position to right child
            localRoot=localRoot.next;
            //iterate until the value find a last leaf
            insert(localRoot,c);
        }
        localRoot.next =newNode;
        return newNode;
    }

    public boolean has(int k, LZbytes localRoot){
        if (localRoot.key==k) {
            return true;
        }
        //while Root has value and the value is what we looking for keep searching
        while(localRoot!=null&&localRoot.key!=k){
            if(localRoot.key>k){
                //the value is smaller than root, to the left child
                localRoot=localRoot.left;
            } else {

                //the value is larger than root, to the right child
                localRoot=localRoot.right;
            }
        }

        //is localRoot has a value than return true
        if (localRoot==null)return false;
        return true;
    }

    public LZbytes find(int k,LZbytes localRoot){

        //while Root has value and the value is what we looking for keep searching
        while(localRoot!=null&& !(localRoot.key ==k)){

            if(localRoot.key<k){

                localRoot=localRoot.right;
            }
            else {
                localRoot = localRoot.left;
            }
        }

        // value found and return it
        return localRoot;
    }
}

