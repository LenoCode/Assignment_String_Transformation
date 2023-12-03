package org.example;

import java.util.ArrayList;
import java.util.List;

public class ReverseTheOrderWordsTransformation extends Transformation{
    public ReverseTheOrderWordsTransformation(){
        super(null);
    }

    public ReverseTheOrderWordsTransformation(Transformation transformation){
        super(transformation);
    }



    @Override
    public String transform(String input) {
        String[] inputs = splitIntoWordsAndSpecialChars(input);
        List<String> reversedInputs = new ArrayList<>();

        int length = inputs.length-1;
        for(int i = 0; i < inputs.length;++i){

            if(!isSpecialChar(inputs[length-i].charAt(0))){
                reversedInputs.add(inputs[length-i].toLowerCase());
            }
        }

        //Adding special characthers to the same place
        boolean removeUpperCaseBool = false;
        boolean removeLowerCaseBool = false;
        for(int i = 0; i < inputs.length;++i){

            if( isSpecialChar(inputs[i].charAt(0))){
                reversedInputs.add(i,inputs[i]);

                if(i == inputs.length - 1){
                    int j = i-1;
                    String temp = reversedInputs.get(j);

                    //If the end of sentence have ... then we have to loop until we find the last word
                    while(isSpecialChar(temp.charAt(0)) || isSpaceChar(temp.charAt(0))){
                        j--;
                        temp = reversedInputs.get(j);
                    }

                    temp = temp.replace(temp.charAt(0),Character.toLowerCase(temp.charAt(0)));
                    reversedInputs.set(j,temp);
                    removeLowerCaseBool = true;
                }
            }else{

                if(!removeUpperCaseBool){
                    String temp = reversedInputs.get(i);
                    temp = temp.replace(temp.charAt(0),Character.toUpperCase(temp.charAt(0)));
                    reversedInputs.set(i,temp);
                    removeUpperCaseBool = true;
                }
            }
        }

        if(!removeLowerCaseBool){
            String temp = reversedInputs.get(reversedInputs.size()-1);
            temp = temp.replace(temp.charAt(0),Character.toLowerCase(temp.charAt(0)));
            reversedInputs.set(reversedInputs.size()-1,temp);
        }


        return addUpperCasesToBeginOfSenteces( appendArrayStringToString(reversedInputs.toArray(new String[inputs.length])) );
    }
}
