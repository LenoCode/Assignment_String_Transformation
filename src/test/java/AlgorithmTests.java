import org.example.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class AlgorithmTests {


    @Test
    public void TestShifting(){
        Transformation t = new Transformation() {
            @Override
            public String transform(String input) {
                return null;
            }
        };

        String example = "Abstraction";
        char[] array = example.toCharArray();
        char[] newArray = t.shiftPositions(array);
        Assertions.assertNotEquals(new String(newArray),example);


        HashMap<Character,Integer> exampleCharCounter = new HashMap<>();
        HashMap<Character,Integer> newWordCharCounter = new HashMap<>();

        for(int i = 0; i < array.length;++i){
            if(exampleCharCounter.containsKey(array[i])){
                exampleCharCounter.put(array[i],  exampleCharCounter.get(array[i])+1 );
            }else{
                exampleCharCounter.put(array[i],1);
            }
            if(newWordCharCounter.containsKey(newArray[i])){
                newWordCharCounter.put(newArray[i],  newWordCharCounter.get(newArray[i])+1 );
            }else{
                newWordCharCounter.put(newArray[i],1);
            }
        }


        exampleCharCounter.forEach((character, integer) -> Assertions.assertEquals(integer, newWordCharCounter.get(character)  ));
    }

    @Test
    public void TestShiftingInBetween(){


        String example = "Example";

        String result = Transformation.changeOrderOfWordsBetweenRange(example,2,6);

        Assertions.assertEquals('E', result.charAt(0));
        Assertions.assertEquals('x', result.charAt(1));
        Assertions.assertEquals('e', result.charAt(example.length()-1));

        Assertions.assertEquals("ampl",example.substring(2,6));
        Assertions.assertNotEquals("ampl",result.substring(2,6));

        System.out.println(result);
    }

    @Test
    public void TestShiftingInBetweenComplete(){


        String example = "Example";

        String result = Transformation.changeOrderOfWordsBetweenRange(example,0,7);

        Assertions.assertNotEquals('E', result.charAt(0));
        Assertions.assertNotEquals('e', result.charAt(example.length()-1));


        System.out.println(result);
    }


    @Test
    public void TestReversingObject(){
        Transformation t = new Transformation() {
            @Override
            public String transform(String input) {
                return null;
            }
        };

        String example = "Example";

        String result = new String(t.reverse(example.toCharArray()));

        Assertions.assertEquals("elpmaxE",result);
    }


    @Test
    public void TestSplittingIntoWords(){
        String example1="Test is here";
        String[] expected= new String[]{"Test"," ","is"," ","here"};
        String[] result = Transformation.splitIntoWordsAndSpecialChars(example1);

        Assertions.assertArrayEquals(expected,result);

        String example2="Comma,Seperated and I";
        String[] expected2 = new String[]{"Comma",",","Seperated"," ","and"," ","I"};
        String[] results2 = Transformation.splitIntoWordsAndSpecialChars(example2);
        Assertions.assertArrayEquals(expected2,results2);
    }


    @Test
    public void TestTransformationPermutation(){
        String example1="TesT HeRe, Now stop";

        PermutateLetterPositionInTheMiddleWordTransformation p = new PermutateLetterPositionInTheMiddleWordTransformation();

        String i = p.startTransform(example1);

        Assertions.assertEquals('T',i.charAt(0));
        Assertions.assertEquals('p',i.charAt(i.length()-1));
        System.out.println(i);
    }

    @Test
    public void TestTransformationReverseTheOrderLetterTransformation(){
        String example1="Test here, now stop";
        ReverseTheOrderLetterTransformation p = new ReverseTheOrderLetterTransformation();
        String i = p.startTransform(example1);
        Assertions.assertEquals("Tset ereh, won pots",i);
    }


    @ParameterizedTest
    @MethodSource("sentenceExamplesWithReverseSolution")
    public void TestTransformationReverseTheOrderWordsTransformation(String example,String expected){

        ReverseTheOrderWordsTransformation r = new ReverseTheOrderWordsTransformation();

        String i = r.startTransform(example);

        Assertions.assertEquals(expected,i);
    }
    private static Stream<Arguments> sentenceExamplesWithReverseSolution() {
        return Stream.of(
                arguments("Test here, now stop.", "Stop now, here test."),
                arguments("Moon is rising, towards here we will never be same.","Same be never, will we here towards rising is moon."),
                arguments("Walking,crying,smiling!!Life is here to begin and not stop.","Stop,not,and!!Begin to here is life smiling crying walking.")

        );
    }


    @ParameterizedTest
    @MethodSource("sentenceExamplesWithReverseSolutionSentence")
    public void TestTransformationReverseTheOrderSentenceTransformation(String example,String expected){

        ReverseTheOrderSentenceTransformation r = new ReverseTheOrderSentenceTransformation();

        String i = r.startTransform(example);

        Assertions.assertEquals(expected,i);
    }
    private static Stream<Arguments> sentenceExamplesWithReverseSolutionSentence() {
        return Stream.of(
                arguments("Test here, now stop.Someone show me the way.", "Someone show me the way.Test here, now stop."),
                arguments("There is a lady who is sure all that glitters is gold and she is buying it. When she gets bored. My freedom gets stucked.",
                          "My freedom gets stucked. When she gets bored.There is a lady who is sure all that glitters is gold and she is buying it.")
        );
    }



    @Test
    public void MultipleTransformationTest(){
        String example = "Test here, now stop.Someone show me the way.";

        ReverseTheOrderLetterTransformation l = new ReverseTheOrderLetterTransformation();
        ReverseTheOrderSentenceTransformation r = new ReverseTheOrderSentenceTransformation(l);

        String result = r.startTransform(example);
        String expected = "Enoemos wohs em eht yaw.Tset ereh, won pots.";
        Assertions.assertEquals(expected,result);
    }




    @Test
    public void TestWithAssignmentExampleLetterAssignment(){
        String example ="Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?";
        ReverseTheOrderLetterTransformation l = new ReverseTheOrderLetterTransformation();

        String result = l.startTransform(example);
        String result2 = l.startTransform(result);

        Assertions.assertEquals(example,result2);
    }

    @Test
    public void TestWithAssignmentExampleWordAssignment(){
        String example ="Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?";
        ReverseTheOrderWordsTransformation l = new ReverseTheOrderWordsTransformation();

        String result = l.startTransform(example);
        String result2 = l.startTransform(result);

        Assertions.assertEquals(example,result2);
    }



    @Test
    public void TestVowelCounter(){
        String example ="Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?";
        ReverseTheOrderWordsTransformation l = new ReverseTheOrderWordsTransformation();

        HashMap<Character,Integer> counter = l.vowelCounter(example);

        AtomicInteger complete = new AtomicInteger();
        counter.forEach((character, integer) -> complete.addAndGet(integer));

        Assertions.assertEquals(341,complete.get());
    }






}
