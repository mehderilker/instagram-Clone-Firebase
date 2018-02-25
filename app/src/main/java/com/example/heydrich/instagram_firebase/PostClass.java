package com.example.heydrich.instagram_firebase;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by heydrich on 24.2.2018.
 */

public class PostClass extends ArrayAdapter<String> {

    private final ArrayList<String> userEmail;
    private final ArrayList<String> userImage;
    private final ArrayList<String> userComment;
    private final Activity context;

    public PostClass(ArrayList<String> userEmail, ArrayList<String> userImage, ArrayList<String> userComment, Activity context) {
        super(context,R.layout.custom_view,userEmail);
        this.userEmail = userEmail;
        this.userImage = userImage;
        this.userComment = userComment;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View customView = layoutInflater.inflate(R.layout.custom_view,null,true);

        TextView userEmailText = (TextView) customView.findViewById(R.id.userName);
        TextView commentText = customView.findViewById(R.id.commentText);
        ImageView imageView = customView.findViewById(R.id.imageView2);

        userEmailText.setText(userEmail.get(position));
        commentText.setText(userComment.get(position));

        Picasso.with(context).load(userImage.get(position)).into(imageView);

        return customView;
    }
}
