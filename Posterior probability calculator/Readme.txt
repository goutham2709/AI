Goutham Munagala
1001565060
Java 

1.compute_a_posteriori
This determines the posterior probability of different hypotheses, given priors for these hypotheses, and given a sequence of observations and can determine the probability that the next observation will be of a specific type, priors for different hypotheses, and given a sequence of observatios.

2. bnet
This class implements a program that computes and prints out the probability of any combination of events given any other combination of events.

Functions: 
public static double callCompute(int bc,int ec,int ac,int jc,int mc,ArrayList<String> arrayProc)
This function calls computeProbability function to compute the probability by considering different combinations.

public static double computeProbability(boolean b, boolean e, boolean a, boolean j, boolean m)
This function has arguments which are boolean specifying if the corresponding event (burglary, earthquake, alarm, john-calls, mary-calls) is true or false.

Compilation Instructions:
1. javac compute_a_posteriori.java
2. javac bnet.java

Execution Instructions:
1. java compute_a_posteriori observations
2. java bnet Bt Af given Mf

