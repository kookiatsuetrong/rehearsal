/*

The String Matching Problem is a problem to find all 
occurrences of a given pattern in the text. 

An example is to find "ring" in "duringthattime"

duringthattime
  ^^^^

The naive algorithm is to start from left to right, and
match each character one-by-one, such as:

.-----------------> The first character is not identical,
ring                move to the next character in the text.
duringthattime
01234567890123

 .----------------> The second character is not identical,
 ring               move to the next character in the text.
duringthattime
01234567890123

  .---------------> The third character is identical,
  ring              consider the next character in the pattern.
duringthattime
01234567890123

   .--------------> The third character is identical,
  ring              consider the next character in the pattern.
duringthattime
01234567890123

    .-------------> The fourth character is identical,
  ring              consider the next character in the pattern.
duringthattime
01234567890123

*/

void main() {
	System.out.println(match("ababaca", "abababacababaca"));
	
	System.out.println("Assertions");
	
	assert match("ring",    "duringthattime") .equals("2 ");
	
	assert match("ababaca", "abababacababaca").equals("2 8 ");
	assert match("ababaca", "abababaca")      .equals("2 ");
	assert match("ababaca", "ababaca")        .equals("0 ");
	assert match("ababaca", "")               .equals("");
	assert match("ababaca", "ababac")         .equals("");
	
	assert match("a",       "cacaa")          .equals("1 3 4 ");
	assert match("aa",      "cacaaa")         .equals("3 4 ");
	assert match("a",       "aaa")            .equals("0 1 2 ");
	assert match("aa",      "aaaa")           .equals("0 1 2 ");
	assert match("aaa",     "bbbbbb")         .equals("");
	
	System.out.println("The code is correct");
}

String match(String pattern, String text) {
	if (pattern == null) return "";
	if (text == null) return "";
	char[] p = pattern.toCharArray();
	char[] t = text.toCharArray();
	int[] restart = createRestart(p);
	
	String result = "";
	int j = 0;
	for (int i = 0; i < t.length; i++) {
		boolean matched = t[i] == p[j];
		if (matched == true)  { j++; }
		if (matched == false) {
			if (j > 0) {
				j = restart[j - 1];
				i--;
			}
		}
		
		if (j == p.length) {
			int position = i - j + 1;
			result += position + " ";
			j = restart[j - 1];
		}
	}
	
	return result;
}

int[] createRestart(char[] p) {
	int[] restart = new int[p.length];
	
	int j = 0;
	for (int i = 1; i < p.length; i++) {
		boolean matched = p[i] == p[j];
		if (matched == true)  { 
			if (i > 0) {
				restart[i] = restart[i - 1] + 1; 
			}
			j++;
		}
		if (matched == false) { 
			restart[i] = 0; 
			j = 0; 
		}
	}
	return restart;
}


String naive(String pattern, String text) {
	if (pattern == null) return "";
	if (text    == null) return "";
	char[] p = pattern.toCharArray();
	char[] t = text.toCharArray();
	
	String result = "";
	
	int j = 0;
	for (int i = 0; i < t.length; i++) {
		boolean matched = t[i] == p[j];
		if (matched == true)  { j++; }
		if (matched == false) {
			int position = i - j;
			i = position; 
			j = 0;
		}
		if (j == p.length) {
			int position = i - j + 1;
			i = position;
			j = 0;
			result += position + " ";
		}
	}
	
	return result;
}

