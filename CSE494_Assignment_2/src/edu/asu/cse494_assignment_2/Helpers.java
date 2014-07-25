package edu.asu.cse494_assignment_2;

import java.security.GeneralSecurityException;
import java.util.Random;

public class Helpers {

	public static float[] getRandomFloatArray(int size, int ceiling) {
		// Return a random float array in range (0,ceiling)
		float[] random = new float[size];
		Random gen = new Random();
		for (int i = 0; i < size; i++) {
			random[i] = gen.nextFloat() * gen.nextInt(ceiling);
		}
		return random;
	}
	
}
