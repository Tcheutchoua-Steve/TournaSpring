/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.steeboon.dev.tournaspring;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * This class parses XML feeds from stackoverflow.com.
 * Given an InputStream representation of a feed, it returns a List of entries,
 * where each list element represents a single entry (post) in the XML feed.
 */
public class StackOverflowXmlParser {
    private static final String ns = null;

    private int numberOfQuestions = 0;
    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }



    public static String startXmlTag = "Questions";
    // We don't use namespaces

    public List<Entry> parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readFeed(parser);
        } finally {
            in.close();
        }
    }

    private List<Entry> readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<Entry> entries = new ArrayList<Entry>();

        parser.require(XmlPullParser.START_TAG, ns, startXmlTag);
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();

            // Starts by looking for the entry tag
            if (name.equals("Question")) {
                numberOfQuestions++ ;
                entries.add(readEntry(parser));
            } else {
                skip(parser);
            }
        }
        return entries;
    }

    // This class represents a single entry (post) in the XML feed.
    // It includes the data members "question," "question_one," and "summary."
    public static class Entry {
        public final String question;
        public final String answer_one;
        public final String answer_two;
        public final String answer_three ;
        public final int correct_answer ;

        private Entry(String question, String answer_two, String answer_one, String answer_three, String correctAnswer, int correct_answer) {
            this.question = question;
            this.answer_two = answer_two;
            this.answer_one = answer_one;
            this.answer_three = answer_three ;
            this.correct_answer = correct_answer;
        }
    }

    // Parses the contents of an entry. If it encounters a question, answer_two, or answer_one tag, hands them
    // off
    // to their respective &quot;read&quot; methods for processing. Otherwise, skips the tag.
    private Entry readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "Question");
        String question = null;
        String answer_two = null;
        String answer_one = null;
        String answer_three = null ;
        String correct_answer = null;
        int answerID = 0 ;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("answer_one")) {
                answer_one = readAnswer1(parser);
            }
            else if (name.equals("question")) {
                question = readQuestion(parser);

            }
            else if (name.equals("answer_two")) {
                answer_two = readAnswer2(parser);

            }else if (name.equals("answer_three")) {
                answer_three = readAnswer3(parser);
            }
            else if (name.equals("answer")) {
                correct_answer = readCorrectAnswer(parser);
            }
            else {
                skip(parser);
            }

        }
        if (correct_answer.equals(answer_one)) answerID = 1 ;
        else if (correct_answer.equals(answer_two)) answerID = 2 ;
        else if (correct_answer.equals(answer_three)) answerID = 3 ;
        Log.i("compariq", " " + answerID);
        Log.i("compari1", " " + answer_one);
        Log.i("compari2", " " + answer_two);
        Log.i("comparia3", " " + answer_three);

        return new Entry(question , answer_two , answer_one , answer_three, correct_answer , answerID);
    }

    // Processes question tags in the feed.
    private String readQuestion(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "question");
        String question = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "question");
        return question;
    }

    // Processes answer_one tags in the feed.
    private String readAnswer1(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "answer_one");
        String answer_one = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "answer_one");
        return answer_one;
    }

    // Processes answer_two tags in the feed.
    private String readAnswer2(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "answer_two");
        String answer_two = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "answer_two");
        return answer_two;
    }

    // Processes answer_two tags in the feed.
    private String readAnswer3(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "answer_three");
        String answer_three = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "answer_three");
        return answer_three;
    }

    // Processes correct answer from tags in the feed.
    private String readCorrectAnswer(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "answer");
        String correct_answ = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "answer");
        return correct_answ;
    }

    // For the tags question and answer_two, extracts their text values.
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    // Skips tags the parser isn't interested in. Uses depth to handle nested tags. i.e.,
    // if the next tag after a START_TAG isn't a matching END_TAG, it keeps going until it
    // finds the matching END_TAG (as indicated by the value of "depth" being 0).
    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}
