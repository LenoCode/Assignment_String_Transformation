Idea behind solution:

    We have abstract class Transformation that has function for manipulating strings (reversing,splitting etc..)
    Then all the other transformation inherits that abstract class and implement their transform method

Example:

    public class PermutateLetterPositionInTheMiddleWordTransformation extends Transformation{


    public PermutateLetterPositionInTheMiddleWordTransformation(){
        super(null);
    }

    public PermutateLetterPositionInTheMiddleWordTransformation(Transformation transformation){
        super(transformation);
    }

    @Override
    public String transform(String input) { -> This is the method to override. So this function uses method
                                                splitIntoWordsAndSpecialChars that is written in Transform class
                                                and then uses own logic on what to do with that splitted.
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


