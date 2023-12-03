package org.example;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {


    public static void main(String[] args) {
        String input = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?";
        System.out.println("Starting transformation on ");
        System.out.println(input);
        System.out.println("\n");

        System.out.println("Processing \n");

        PermutateLetterPositionInTheMiddleWordTransformation permutateLetterPositionInTheMiddleWordTransformation = new PermutateLetterPositionInTheMiddleWordTransformation();
        ReverseTheOrderSentenceTransformation sentenceTransformation = new ReverseTheOrderSentenceTransformation(permutateLetterPositionInTheMiddleWordTransformation);
        ReverseTheOrderWordsTransformation wordsTransformation = new ReverseTheOrderWordsTransformation(sentenceTransformation);
        ReverseTheOrderLetterTransformation letterTransformation = new ReverseTheOrderLetterTransformation(wordsTransformation);

        String result = letterTransformation.startTransform(input);
        HashMap<Character, Integer> vowelCounter = letterTransformation.vowelCounter(result);

        System.out.print("Result ->  ");
        System.out.println(result);
        System.out.println("\n\n");

        System.out.println("Vowel statistic ");

        AtomicInteger complete = new AtomicInteger();

        vowelCounter.forEach((character, integer) ->{
            System.out.println("Vowel "+character+" : "+integer);
            complete.addAndGet(integer);
        } );
        System.out.println("Complete vowels :"+complete.get());
    }
}
