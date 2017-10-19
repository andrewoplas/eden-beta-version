package subatom.eden_beta;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by JSP on 10/6/2017.
 */

public class Emotion {
    public static boolean detect = false;
    public static ArrayList<Pair<String,Float>> joy  = new ArrayList<>();
    public static ArrayList<Pair<String,Float>> attention = new ArrayList<>();
    public static ArrayList<Pair<String,Float>> brow_furrow = new ArrayList<>();
    public static ArrayList<Pair<String,Float>> engagement = new ArrayList<>();

    public static void addJoy(Pair p) { joy.add(p);}
    public static void addAttention(Pair p) { attention.add(p);}
    public static void addBrowFurrow(Pair p) { brow_furrow.add(p);}
    public static void addEngagement(Pair p) { engagement.add(p);}
//
    public static Pair<String, Float> getJoy(int i) {return joy.get(i);}
    public static Pair<String, Float> getAttention(int i) {return attention.get(i);}
    public static Pair<String, Float> getBrowFurrow(int i) {return brow_furrow.get(i);}
    public static Pair<String, Float> getEngagement(int i) {return engagement.get(i);}

    private static String printAnEmotion(int i) {
        return getAttention(i).toString();
    }

    public static void printResults(Context c) {
        String print = "Attention: ";
        /*for (int i = 0; i < attention.size(); i+=100) {
            print += (printAnEmotion(i) + " ");
        }*/
        print += (printAnEmotion(engagement.size() - 1) + " ");

        Toast.makeText(c, print, Toast.LENGTH_LONG).show();
    }


    //A helper class for pairing a reading of an emotion in a certain period of time in the video
    public class Pair<L,R> {
        private L l;
        private R r;
        public Pair(L l, R r){
            this.l = l;
            this.r = r;
        }
        public L getL(){ return l; }
        public R getR(){ return r; }
        public void setL(L l){ this.l = l; }
        public void setR(R r){ this.r = r; }
        public String toString() { return l.toString() + " " + r.toString() + "     ";}
    }



}
