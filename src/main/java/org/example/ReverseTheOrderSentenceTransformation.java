package org.example;

import java.util.ArrayList;
import java.util.List;

public class ReverseTheOrderSentenceTransformation extends Transformation {

    public ReverseTheOrderSentenceTransformation(){
        super(null);
    }

    public ReverseTheOrderSentenceTransformation(Transformation transformation){
        super(transformation);
    }

    @Override
    public String transform(String input) {
        String[] inputs = splitIntoSentences(input);

        inputs = reverse(inputs);

        return appendArrayStringToString(inputs).replaceFirst("^\\s*", "");
    }
}
