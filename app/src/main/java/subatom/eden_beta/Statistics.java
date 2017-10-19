package subatom.eden_beta;

/* OPLAS HELP ME TTTTTTTTTTTT.TTTTTTTTTTTT */

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Statistics extends Activity {

    public class EmailSender implements View.OnClickListener{
        public void onClick(View v){
            Date currentTime = Calendar.getInstance().getTime();
            String s = currentTime.toString();
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:"));
            String[] to={"papelias1337@gmail.com"};
            intent.putExtra(Intent.EXTRA_EMAIL, to);
            intent.putExtra(Intent.EXTRA_SUBJECT, "EDEN Results of "+s);
            intent.putExtra(Intent.EXTRA_TEXT, txtStat.getText().toString());
            intent.setType("message/rfc822");
            Intent chooser = Intent.createChooser(intent, "Send Email");
            startActivity(chooser);

        }
    }

    private Button btnSend;
    private EditText txtStat;
    private EmotionPoints e = new EmotionPoints();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        btnSend = (Button)findViewById(R.id.btnSend);
        txtStat = (EditText) findViewById(R.id.editTextStat);

        txtStat.setText(e.updateConfusions() + "\n\n" + e.updateJoy());

        btnSend.setOnClickListener(new EmailSender());
    }

    public class EmotionPoints {
        int start, end;
        String point;
        boolean hasStart = false, hasEnd = false;
        int size = Emotion.attention.size(); //since pareha rag size tanan

        public String updateConfusions() {
            point = "I seem to be having difficulties between these time frames: \n";
            //Toast.makeText(getApplicationContext(), " " + Emotion.brow_furrow.get(10).getR() + " and " + Emotion.brow_furrow.get(60).getR(), Toast.LENGTH_SHORT).show();
            for (int i = 0; i < size; i++) {
                if (Emotion.brow_furrow.get(i).getR() >= 90 && !hasStart) {
                    start = i;
                    hasStart = true;
                }

                if (Emotion.brow_furrow.get(i).getR() < 90 && !hasEnd && hasStart) {
                    end = i;
                    hasEnd = true;
                }

                if (hasStart && hasEnd) {
                    hasStart = false;
                    hasEnd = false;
                    point += (" " + Emotion.brow_furrow.get(start).getL() + " and " + Emotion.brow_furrow.get(end).getL() + "\n");
                    start = 0;
                    end = 0;
                }

            }
//            start = 0;
//            end = 0;
            return point;
        }

        public String updateJoy() {
            point = "I seem to be pleased between these time frames: \n";
            //Toast.makeText(getApplicationContext(), " " + Emotion.brow_furrow.get(10).getR() + " and " + Emotion.brow_furrow.get(60).getR(), Toast.LENGTH_SHORT).show();
            for (int i = 0; i < size; i++) {
                if (Emotion.joy.get(i).getR() >= 90 && !hasStart) {
                    start = i;
                    hasStart = true;
                }

                if (Emotion.joy.get(i).getR() < 90 && !hasEnd && hasStart) {
                    end = i;
                    hasEnd = true;
                }

                if (hasStart && hasEnd) {
                    hasStart = false;
                    hasEnd = false;
                    point += (" " + Emotion.joy.get(start).getL() + " and " + Emotion.joy.get(end).getL() + "\n");
                    start = 0;
                    end = 0;
                }

            }
//            start = 0;
//            end = 0;
            return point;
        }


        public String updateNotPayingAttention() {
            point = "I didn't pay attention between these time frames: \n";
            //Toast.makeText(getApplicationContext(), " " + Emotion.brow_furrow.get(10).getR() + " and " + Emotion.brow_furrow.get(60).getR(), Toast.LENGTH_SHORT).show();
            for (int i = 0; i < size; i++) {
                if (Emotion.attention.get(i).getR() <= 70 && !hasStart) {
                    start = i;
                    hasStart = true;
                }

                if (Emotion.attention.get(i).getR() > 70 && !hasEnd  && hasStart) {
                    end = i;
                    hasEnd = true;
                }

                if (hasStart && hasEnd) {
                    hasStart = false;
                    hasEnd = false;
                    point += (" " + Emotion.attention.get(start).getL() + " and " + Emotion.attention.get(end).getL() + "\n");
                    start = 0;
                    end = 0;

                }
//                start = 0;
//                end = 0;
            }
            return point;
        }



    }
/*
    public void analyzeConfusion(){
        int start=0, end=0;
        boolean hasStart = false, hasEnd = false;
        int length = brow_furrowNew.size();
        for(int i=0; i<length; i++){
            if(brow_furrowNew.get(i) >= 90 && !hasStart){
                start = i;
                hasStart = true;
            }

            if(brow_furrowNew.get(i) < 90 && !hasEnd){
                end = i;
                hasEnd = true;
            }

            if(hasStart && hasEnd){
                hasStart = false;
                hasEnd = false;

                confused += " " + start + " and " + end;
                confused += "\n\n";

                start = 0;
                end = 0;
            }
        }


    }
*/


}
