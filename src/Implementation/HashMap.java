package Implementation;

public class HashMap {
    public class Node {
        String key;
        int value;
        Node next;
    }
    private Node[] buk;
    private int size;

    public HashMap(int n) {
        buk = new Node[n];
    }
    public HashMap() {
        this(4);
    }
    public void put(String key, int value) {
        int bn= hashfun(key);
        Node temp= buk[bn]; 
        while(temp!=null){
            if(temp.key.equals(key)){
                temp.value=value;
                return;
            }
            temp=temp.next;
        }
        Node newNode = new Node();
        newNode.key=key;
        newNode.value=value;
        newNode.next=buk[bn];
        buk[bn]=newNode;
        size++;
        double thf=2.0;
        double lf= (double)size / buk.length;
        if(lf>thf){
            rehashing();
        }
    }

    private void rehashing() {
        Node[] newBuk = new Node[buk.length*2];
        Node[] a=buk;
        buk=newBuk;
        size=0;
        for(Node temp:a){
            while(temp!=null){
                put(temp.key,temp.value);
                temp=temp.next;
            }
        }
    }


    public int hashfun(String key) {
        int idx= key.hashCode()%buk.length;
        if(idx<0){
            idx+=buk.length;
        }
        return idx;
    }
    public Integer get(String key) {
        int bn= hashfun(key);
        Node temp= buk[bn];
        while(temp!=null){
            if(temp.key.equals(key)){
                return temp.value;
            }
            temp=temp.next;
        }
        return null;
    }
    public boolean containsKey(String key) {
        int bn= hashfun(key);
        Node temp= buk[bn];
        while(temp!=null){
            if(temp.key.equals(key)){
                return true;
            }
            temp=temp.next;
        }
        return false;
    }
    public Integer remove(String key) {
        int bn= hashfun(key);
        Node curr= buk[bn];
        Node prev= null;
        while(curr!=null){
            if(curr.key.equals(key)){
                break;
            }
            prev=curr;
            curr=curr.next;
        }
        if(curr==null){
            return null;
        }
        if(prev==null){
            buk[bn]=curr.next;
            curr.next=null;
        }
        else{
            prev.next=curr.next;
            curr.next=null;
        }
        size--;
        return curr.value;
    }
    @Override
    public String toString() {
        String s="{";
        for(Node temp:buk){
            while(temp!=null){
                s=s+temp.key+"="+temp.value+" ";
                temp=temp.next;
            }
        }
        return s + "}";
    }
}
