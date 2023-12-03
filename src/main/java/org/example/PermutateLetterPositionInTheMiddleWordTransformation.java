package org.example;

import java.util.Arrays;

public class PermutateLetterPositionInTheMiddleWordTransformation extends Transformation{


    public PermutateLetterPositionInTheMiddleWordTransformation(){
        super(null);
    }

    public PermutateLetterPositionInTheMiddleWordTransformation(Transformation transformation){
        super(transformation);
    }

    @Override
    public String transform(String input) {
        String[] splitted = splitIntoWordsAndSpecialChars(input);

        assert splitted != null;

        for(int i = 0; i < splitted.length;++i){
            String s = splitted[i];

            if(s.length() > 1){
                String newString = changeOrderOfWordsBetweenRange(s,1,s.length()-1);
                splitted[i] = newString;
            }
        }
        return appendArrayStringToString(splitted);
    }
}
