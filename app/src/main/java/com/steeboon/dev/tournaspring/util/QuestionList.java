package com.steeboon.dev.tournaspring.util;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by tcheutchoua on 7/20/16.
 */
public class QuestionList extends ArrayList<Question> implements Parcelable {

    // create a serializeable field because Parcelable uses serialize behind the scens
    private static final long serialVersionUID = 114455867L ;

    public QuestionList() {
    }

    public QuestionList(Parcel in){
        readFromParcel(in);
    }

    @SuppressWarnings("unchecked")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public QuestionList createFromParcel(Parcel in){
            return new QuestionList(in);
        }

        public Object[] newArray(int arg0){
            return null;
        }

    };

    private void readFromParcel(Parcel in){
        this.clear();

        // First we have to read the list size
        int size = in.readInt();

        //Remember the Order of writing to the parcle, first we have the
        // Question
        // Answer1
        // Answer2
        // Answer3
        // Answer4

        // Order if fundamental here

        for (int i=0 ; i < size ; i++){
            Question quest = new Question();
            quest.setQuestion_asked(in.readString());
            quest.setAnswer1(in.readString());
            quest.setAnswer2(in.readString());
            quest.setAnswer3(in.readString());
            quest.setAnswer4(in.readString());
            this.add(quest);
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {

        int size = this.size();

        // We have to write the list size, we need him recreating the list
        dest.writeInt(size);

        // we decide arbitarilty to write the Question, then the first answer,
        //second answer , third answer then the fouth answer

        for ( int i = 0 ; i< size ; i++){
            Question quest = this.get(i);
            dest.writeString(quest.getQuestion_asked());
            dest.writeString(quest.getAnswer1());
            dest.writeString(quest.getAnswer2());
            dest.writeString(quest.getAnswer3());
            dest.writeString(quest.getAnswer4());

        }
    }
}
