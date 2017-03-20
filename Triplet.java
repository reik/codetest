import java.util.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

class Triplet {
 public static void main(String args[]) {

  // array S of n integers
  Integer[] S =  {-1,0,1,2,-1,-4};
  
  Triplet tri = new Triplet();
  tri.createTripletArray(S);
 }

 private void createTripletArray(Integer[] sArray) {
  // sort the argument array
  List < Integer > sampleArray = new ArrayList < > (Arrays.asList(sArray));
  Collections.sort(sampleArray);

  // list of array to return
  List < ArrayList < Integer >> tripletArray = new ArrayList < ArrayList < Integer >> ();

  List < Integer > negativeArray = new ArrayList < Integer > ();
  List < Integer > zeroArray = new ArrayList < Integer > ();
  List < Integer > positiveArray = new ArrayList < Integer > ();

  // separate integer in argument array to list 
  // for negatives, zeros and postives
  for (int i = 0; i < sampleArray.size(); i++) {
   if (sampleArray.get(i) < 0) {
    negativeArray.add(sampleArray.get(i));

   } else if (sampleArray.get(i) == 0) {
    zeroArray.add(sampleArray.get(i));
   } else {
    positiveArray.add(sampleArray.get(i));
   }
  }

  // get array of 2 integers combination 
  // for negative numbers and positive numbers
  List < ArrayList < Integer >> negativeCombArray = new ArrayList < ArrayList < Integer >> ();
  List < ArrayList < Integer >> positiveCombArray = new ArrayList < ArrayList < Integer >> ();

  for (int j = 0; j < negativeArray.size(); j++) {
   for (int k = j + 1; k < negativeArray.size(); k++) {

    final int x = j;
    final int y = k;
    final List < Integer > tmpNegativeArray = negativeArray;

    ArrayList < Integer > combo = new ArrayList < Integer > () {
     {
      add(tmpNegativeArray.get(x));
      add(tmpNegativeArray.get(y));
     }
    };
    negativeCombArray.add(combo);
   }
  }

  for (int j = 0; j < positiveArray.size(); j++) {
   for (int k = j + 1; k < positiveArray.size(); k++) {

    final int x = j;
    final int y = k;
    final List < Integer > tmpPositiveArray = positiveArray;

    ArrayList < Integer > combo = new ArrayList < Integer > () {
     {
      add(tmpPositiveArray.get(x));
      add(tmpPositiveArray.get(y));
     }
    };
    positiveCombArray.add(combo);
   }
  }

  // in case there are more than 3 zeros
  if (zeroArray.size() >= 3) {
   tripletArray.add(new ArrayList < > (Arrays.asList(0, 0, 0)));
  }

  // in case there is at least 1 zero
  // find a negative number matching to -(potisive number)
  if (zeroArray.size() > 0) {
   for (int negativeNumber: negativeArray) {
    int plusNumberIndex = positiveArray.indexOf(-(negativeNumber));

    if (plusNumberIndex != -1) {
     ArrayList < Integer > triplet =
      new ArrayList < > (Arrays.asList(negativeNumber, 0, -(negativeNumber)));
     if (!tripletArray.contains(triplet)) {
      tripletArray.add(triplet);
     }
    }
   }
  }

  // get the sum of negative 2 integers combination
  // and find if there is a positive number matching to -(potisive number)
  for (ArrayList < Integer > tempCombArray: negativeCombArray) {
   ArrayList < Integer > tempListArray = new ArrayList < Integer > ();
   Integer sum = 0;
   for (Integer i: tempCombArray) {
    sum += i;
    tempListArray.add(i);
   }

   int matchPlusNumberIndex = positiveArray.indexOf(-(sum));
   if (matchPlusNumberIndex != -1) {
    tempListArray.add(-sum);

    if (!tripletArray.contains(tempListArray)) {
     tripletArray.add(tempListArray);
    }

   }
  }

  // get the sum of positive 2 integers combination
  // and find if there is a negative number matching to -(negative number)
  for (ArrayList < Integer > tempCombArray: positiveCombArray) {
   ArrayList < Integer > tempListArray = new ArrayList < Integer > ();
   Integer sum = 0;
   for (Integer i: tempCombArray) {
    sum += i;
    tempListArray.add(i);
   }

   int matchPlusNumberIndex = negativeArray.indexOf(-(sum));
   if (matchPlusNumberIndex != -1) {
    Collections.reverse(tempListArray);
    tempListArray.add(-sum);
    Collections.reverse(tempListArray);

    if (!tripletArray.contains(tempListArray)) {
     tripletArray.add(tempListArray);
    }

   }
  }
  // print out all the triple unique array
  System.out.println(tripletArray);
 }
}
