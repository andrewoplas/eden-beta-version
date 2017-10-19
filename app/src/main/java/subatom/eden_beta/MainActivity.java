package subatom.eden_beta;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends Activity {
    private Button btnPlay;
    private TextView txtMainName;
    private String vid_id;
    private EditText txtUrl;
    private Button btnPreview;


    //https://m.youtube.com/watch?v=4o1egAQ6gJQ
    //https://m.youtube.com/watch?v=4o1egAQ6gJQ
    private String video_url;

    private Uri uri;
    private String videoID/* = "czgOWmtGVGs"*/;
    private String url;
    private ImageView img;

    public class Transferrer implements View.OnClickListener{ //Pending
        public void onClick (View v){
            video_url = txtUrl.getText().toString();
            //the regex only WORKS ON MOBILE LINKS!
            Pattern pattern = Pattern.compile(
                    "^https?://.*(?:youtu.be/|v/|u/\\w/|embed/|watch?v=)([^#&?]*).*$",
                    Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(video_url);
            if (matcher.matches()){
                videoID = matcher.group(1);
            }
            Intent i = new Intent(MainActivity.this, YoutubePlayer.class);
            i.putExtra("url", videoID);
            startActivity(i);
        }
    }



    public void setPreview(){

        url = "http://img.youtube.com/vi/" + videoID + "/default.jpg";
        Picasso.with(this).load(url).into(img);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img= (ImageView) findViewById(R.id.imageView);
        btnPlay = (Button)findViewById(R.id.btnplay);
        txtMainName = (TextView)findViewById(R.id.txtMainName);
        txtUrl = (EditText)findViewById(R.id.txtUrl);

        btnPreview = (Button)findViewById(R.id.btnPreview);

        Intent intent = getIntent();
        String nameHandler = intent.getStringExtra("user_name");


        btnPlay.setOnClickListener(new Transferrer());
        txtMainName.setText("Hello, "+nameHandler);
        btnPreview.setOnClickListener(new OnClickPreview());
        //img.setImageResource(R.drawable.subtitles);


    }



    public class OnClickPreview implements View.OnClickListener {
       @Override
        public void onClick(View view) {
           video_url = txtUrl.getText().toString();

           Pattern pattern = Pattern.compile(
                   "^https?://.*(?:youtu.be/|v/|u/\\w/|embed/|watch?v=)([^#&?]*).*$",
                   Pattern.CASE_INSENSITIVE);
           Matcher matcher = pattern.matcher(video_url);
           if (matcher.matches()){
               videoID = matcher.group(1);
           }
           setPreview();
        }
    }




}
