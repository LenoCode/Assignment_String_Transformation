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


For checking the specific functions and how they work I suggest looking at the unit tests


<h1>Github action and docker</h1>
To test example, you can go to github actions and run workflow. This workflow will build jar, docker image
and then run it. 
To check result, go to step Run Image
