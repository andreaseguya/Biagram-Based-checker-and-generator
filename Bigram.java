// Andrea Seguya 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

import javax.swing.JFileChooser;

public class Bigram{
	Map<String,Map<String, Integer>> Map ;

	public Bigram(String s){
		Map = new HashMap<String,Map<String,Integer>>();
		// scanner of objexts
		Scanner sc = new Scanner(s);

		String x, y = null;
		// while theres things in the scanner
		while(sc.hasNext())

		{
			//put these strings in the variable x
			x = sc.next();

			if(y != null)

			{
				//y is now my key variable
				if(Map.get(y) == null)

				{
					//if theres nothing in y, put that new thing in there
					Map.put(y, new TreeMap<String,Integer>());  

				}
				// if theres  nothing in key y and x
				if(Map.get(y).get(x) == null)

				{
					// for the value of y put x
					Map.get(y).put(x,1);

				}

				else

				{
					//both keys, count them both
					int count = Map.get(y).get(x);

					Map.get(y).put(x,count+1);

				}

			}

			y = x;

		}


	}

	// arraylist of words to be scanned
	//ArrayList<String > words=new ArrayList<String>()
	//		ArrayList<String> bigrams =new ArrayList<String>();
	//tokenizer 
	//		StringTokenizer itr = new StringTokenizer(s);

	//Scanner scan=new Scanner(s);
	//adding to the array list
	//		while(scan.hasNext()) {
	//			words.add(scan.next());
	//		}



	public boolean check(String sentence) {
		Scanner sc= new Scanner(sentence);

		boolean correct=true;

		///the two special variables are..
		String c,p=null;
		//again while theres things in there
		while(sc.hasNext()) {

			c=sc.next();
			if(p!=null) {

				//nothing in there
				if(Map.get(p)==null) {

					// if there's nothing in the key the check must be false
					correct=false;
					break;
				}

				//nothing in either elements
				if(Map.get(p).get(c)==null) {

					correct=false;

					break;
				}

			}
			p=c;
		}


		return correct;

	}
	public String[] generate(String word,int count) {
		String[] genString = new String[count];
	
		genString[0] = word;
		
		int index = 1;

		while(index < count){

			if(Map.get(genString[index-1]) == null){
				
				return Arrays.copyOf(genString, index);

			}

			//assigning a new key set using Set

			Set<String> ks = Map.get(genString[index-1]).keySet();

			//assign order list using List( order of things in the list)

			List<String> orderList = new ArrayList<String>(ks);

			

			// need a maximum word frequency

			int maxfreuq = 0;

			//where theres a max theres a minimum word frequency

			String maxword = null;
			//String maxwordfreq=null;
			

			for(String str : orderList)

			{

				if(maxfreuq == 0)
					// that must be the starting word I guess
				{
					//maxwordfreq=str;
					maxword = str;
					
					maxfreuq = Map.get(genString[index-1]).get(str);

				}

				else if (Map.get(genString[index-1]).get(str)==maxfreuq)

				{
					//if there's two that are the same we need to compare
					if(str.compareTo(maxword) < 0) {
						//if one of the words is less print the lesser one
						maxword = str;
					}
					// if one is greater than the other
					//return the less one
					//if the minword is greater?
					
					//if(str.compareTo(minwordfreq)>0) {
					
						//minwordfreq=str;
					
				//}
					
				}
				

			else if(Map.get(genString[index-1]).get(str)>maxfreuq) {
				
				maxfreuq=Map.get(genString[index-1]).get(str);
				
				maxword=str;
				
				//return null;
				}
					
				

					
					

				

			}

			genString[index++] = maxword;
			

		}

		return genString;

	}
	
}

