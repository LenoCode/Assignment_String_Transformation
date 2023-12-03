package org.example;

public class ReverseTheOrderLetterTransformation extends Transformation{
    public ReverseTheOrderLetterTransformation(){
        super(null);
    }

    public ReverseTheOrderLetterTransformation(Transformation transformation){
        super(transformation);
    }

    @Override
    public String transform(String input) {
        String[]  splitted = splitIntoWordsAndSpecialChars(input);
        for(int i = 0; i < splitted.length;++i){
            String s = splitted[i];

            if(s.length() > 1) {
                boolean isUpper = Character.isUpperCase(s.charAt(0));
                char[] newString = reverse(s.toCharArray());
                if(isUpper){
                    newString[0] = Character.toUpperCase(newString[0]);
                    newString[newString.length-1] = Character.toLowerCase(newString[newString.length-1]);
                }
                splitted[i] = new String(newString);
            }
        }
        return appendArrayStringToString(splitted);
    }
}
