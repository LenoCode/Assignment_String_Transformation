package org.example;

import java.text.BreakIterator;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Transformation implements ITransfrom{
    private static final Random r = new Random();
    protected final Transformation nextTransformation;


    protected Transformation(){
        this.nextTransformation = null;
    }
    protected Transformation(Transformation transformation){
        this.nextTransformation = transformation;
    }



    public String startTransform(String input){
        String result = transform(input);
        if(nextTransformation == null)
            return result;
        else
            return nextTransformation.startTransform(result);
    }




    public static String changeOrderOfWordsBetweenRange(String word,int start,int end){
        String beginPart = word.substring(0,start);
        String endPart = word.substring(end,word.length());

        char[] changedOrder = shiftPositions(word.substring(start,end).toCharArray());

        return beginPart + new String(changedOrder) + endPart;
    }



    public static char[] shiftPositions(char[] positions){
        char[] newArray = new char[positions.length];
        int position;
        int randomCounter;

        for(int i = 0; i < positions.length;++i){
            char nextChar = positions[i];

            if(positions.length > 1){
                position = r.nextInt(positions.length - 1);
            }else{
                position = 0;
            }

            randomCounter = 0;
            while(newArray[position] != '\0'){
                position = r.nextInt(positions.length - 1);

                if(randomCounter++ == 5){
                    for(int j = 0; j<positions.length;++j){
                        if(newArray[j] == '\0'){
                            position = j;
                            newArray[position] = newArray[0];
                            position = 0;
                            newArray[0] = '\0';
                        }
                    }
                }
            }
            newArray[position] = nextChar;
        }
        return newArray;
    }


    public static  String[] reverse(String[] obj){
        String[] newArray = new String[obj.length];
        int j = obj.length - 1;
        for(int i = 0; i < obj.length;++i ){
            newArray[i] = obj[j-i];
        }
        return newArray;
    }

    public char[] reverse(char[] word){
        char[] newArray = new char[word.length];
        int j = word.length - 1;
        for(int i = 0; i < word.length;++i ){
            newArray[i] = word[j-i];
        }
        return newArray;
    }

    public static String[] splitIntoWordsAndSpecialChars(String input){
        String thePattern = "[^A-Za-z0-9]+";
        boolean isFound = Pattern.compile(thePattern).matcher(input).find();
        if(isFound){
            return input.split(("((?=[@,.?!\s])|(?<=[@,?.!\s]))"));
        }
        return null;
    }


    public static String[] splitIntoSentences(String input){
        List<String> senteces = new ArrayList<>();
        Pattern pattern = Pattern.compile("[.?!]");
        Matcher matcher = pattern.matcher(input);

        int beginIndex = 0;
        while(matcher.find()){
            String sentence = input.substring(beginIndex,matcher.end());
            beginIndex=matcher.end();
            senteces.add(sentence);
        }
        return senteces.toArray(new String[senteces.size()]);
    }


    public String appendArrayStringToString(String[] words){
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < words.length;++i){
            s.append(words[i]);
        }
        return s.toString();
    }


    public HashMap<Character,Integer> vowelCounter(String input){
        HashMap<Character,Integer> counter = new HashMap<>();
        counter.put('a',0);
        counter.put('e',0);
        counter.put('i',0);
        counter.put('o',0);
        counter.put('u',0);
        for(char c : input.toCharArray()){
            if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')counter.put(c,counter.get(c)+1);
        }
        return counter;
    }


    public boolean isSpecialChar(char c){
        return !(Character.isLetter(c) || Character.isDigit(c));
    }
    public boolean isSpaceChar(char c){
        return Character.isSpaceChar(c);
    }


    public String addUpperCasesToBeginOfSenteces(String input){
        String[] senteces = splitIntoSentences(input);

        for(int i =0; i < senteces.length; ++i){
            char[] array = senteces[i].toCharArray();

            int index=0;

            while(index < array.length && (!Character.isLetter(array[index]) && !Character.isLetter(array[index]) ))index++;

            if(index < array.length && Character.isLetter(array[index])){
                array[index] = Character.toUpperCase(array[index]);
            }

            senteces[i] = new String(array);
        }
        return appendArrayStringToString(senteces);
    }
}
