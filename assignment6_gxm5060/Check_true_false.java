import java.io.*;
import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Check_true_false {

    public static void main(String[] args) {
        
        if( args.length != 3){
			System.out.println("Usage: " + args[0] +  " [wumpus-rules-file] [additional-knowledge-file] [input_file]\n");
			exit_function(0);
		}
		
		String buffer;
		BufferedReader inputStream;
		BufferedWriter outputStream;
		
		Logical_expression knowledge_base = new Logical_expression();
		Logical_expression statement = new Logical_expression();
                Logical_expression negate_statement = new Logical_expression();
                
		//open the wumpus_rules.txt
		try {
			inputStream = new BufferedReader( new FileReader( args[0] ) );
			
			System.out.println("loading the wumpus rules...");
			knowledge_base.setConnective("and");
		
			while(  ( buffer = inputStream.readLine() ) != null ) 
                        {
				if( !(buffer.startsWith("#") || (buffer.equals( "" )) )) 
                                {
					Logical_expression subExpression = readExpression( buffer );
					knowledge_base.setSubexpression( subExpression );
				} 
                                else 
                                {
				}
			}		
			
			inputStream.close();

		} catch(Exception e) 
                {
			System.out.println("failed to open " + args[0] );
			e.printStackTrace();
			exit_function(0);
		}		
		
		//read the additional knowledge file
                HashMap<String, Boolean> model = new HashMap<String, Boolean>();
		try {
			inputStream = new BufferedReader( new FileReader( args[1] ) );
			
			System.out.println("loading the additional knowledge...");
			
                        model.put("M_1_1", Boolean.FALSE);
                        model.put("M_1_2", Boolean.FALSE);
                        model.put("M_2_1", Boolean.FALSE);
                        model.put("M_2_2", Boolean.FALSE);
                        model.put("P_1_1", Boolean.FALSE);
                        model.put("P_1_2", Boolean.FALSE);
                        model.put("P_2_1", Boolean.FALSE);
                        model.put("P_2_2", Boolean.FALSE);
			while(  ( buffer = inputStream.readLine() ) != null) 
                        {
                                if( !(buffer.startsWith("#") || (buffer.equals("") ))) 
                                {
                                        if( !( buffer.contains("AND") || buffer.contains("and") || buffer.contains("OR") || buffer.contains("or") || buffer.contains("XOR") || buffer.contains("xor") || buffer.contains("IF") || buffer.contains("if") || buffer.contains("IFF") || buffer.contains("iff") ) )
                                        {
                                            if(buffer.contains(" "))
                                                model.put(buffer.substring(buffer.indexOf(" ") + 1, buffer.indexOf(")")), Boolean.FALSE);
                                            else
                                                model.put(buffer.trim(), Boolean.TRUE);
                                        }
					Logical_expression subExpression = readExpression( buffer );
					knowledge_base.setSubexpression( subExpression );
                                } 
                                else 
                                {
                                }
                          }
			
			inputStream.close();

		} catch(Exception e) {
			System.out.println("failed to open " + args[1] );
			e.printStackTrace();
			exit_function(0);
		}
		
		if( !valid_expression( knowledge_base ) ) {
			System.out.println("invalid knowledge base");
			exit_function(0);
		}
		
		knowledge_base.print_expression("\n");
		
		
		try {
			inputStream = new BufferedReader( new FileReader( args[2] ) );
			
			System.out.println("\n\nLoading the statement file...");
		
			while( ( buffer = inputStream.readLine() ) != null ) {
				if( !buffer.startsWith("#") ) {
						statement = readExpression( buffer );
                                                buffer = "(NOT " + buffer + " )";
                                                negate_statement = readExpression( buffer );
                                                break;
				} else {
				}
			}
			
			inputStream.close();

		} catch(Exception e) {
			System.out.println("failed to open " + args[2] );
			e.printStackTrace();
			exit_function(0);
		}
		
		if( !valid_expression( statement ) ) {
			System.out.println("invalid statement");
			exit_function(0);
		}
		
		statement.print_expression( "" );
		System.out.println("\n");
				
                LinkedList<Symbols> symbol = new LinkedList<Symbols>();
                Entails check = new Entails();
                boolean ss = check.TT_Entails(knowledge_base, statement, model);
                boolean nss = check.TT_Entails(knowledge_base, negate_statement, model);
                String content = "";
				if(ss && !nss)
                        content = "Definitely True";
                else if(ss && nss)
                        content = "Both True and False";
                else if( nss && !ss)
                       content = "Definitely False";
                else if( !ss && !nss)
                       content = "Possibly True, Possibly False";
				
				System.out.println("The Result is "+content);
				System.out.println();
				
				try {
					FileWriter fw = new FileWriter("result.txt");
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(content);

					System.out.println("Putting Output in result.txt");
					bw.close();
					fw.close();
				} catch (IOException e) {

					e.printStackTrace();

				} 
} 

	public static Logical_expression readExpression( String input_string ) 
        {
          Logical_expression result = new Logical_expression();
          
          input_string = input_string.trim();
          
          if( input_string.startsWith("(") ) 
          {
          
            String Symbolstring = "";
            Symbolstring = input_string.substring( 1 );
            	  
            if( !Symbolstring.endsWith(")" ) ) 
            {
            
              System.out.println("missing ')' !!! - invalid expression! - readExpression():-" + Symbolstring );
              exit_function(0);
              
            }
            else 
            {
              
              Symbolstring = Symbolstring.substring( 0 , ( Symbolstring.length() - 1 ) );
              Symbolstring.trim();
              
              Symbolstring = result.setConnective( Symbolstring );
            }
            
            result.setSubexpressions( read_subexpressions( Symbolstring ) );
            
          } 
          else 
          {   	
            // if the unique symbol is not valid, this will tell us.
            result.setUniqueSymbol( input_string );
          
          }
          
          return result;
        }

	
	public static Vector<Logical_expression> read_subexpressions( String input_string ) {

	Vector<Logical_expression> symbolList = new Vector<Logical_expression>();
	Logical_expression newExpression;// = new Logical_expression();
	String newSymbol = new String();
	
	input_string.trim();

	while( input_string.length() > 0 ) {
		
		newExpression = new Logical_expression();
		if( input_string.startsWith( "(" ) ) {
			
			int parenCounter = 1;
			int matchingIndex = 1;
			while( ( parenCounter > 0 ) && ( matchingIndex < input_string.length() ) ) {
					if( input_string.charAt( matchingIndex ) == '(') {
						parenCounter++;
					} else if( input_string.charAt( matchingIndex ) == ')') {
						parenCounter--;
					}
				matchingIndex++;
			}
			
			newSymbol = input_string.substring( 0, matchingIndex );
			
			newExpression = readExpression( newSymbol );

			// add the Logical_expression that it returns to the vector symbolList
			symbolList.add( newExpression );

			input_string = input_string.substring( newSymbol.length(), input_string.length() );

		} else {
			
			if( input_string.contains( " " ) ) {
				//remove the first string from the string
				newSymbol = input_string.substring( 0, input_string.indexOf( " " ) );
				input_string = input_string.substring( (newSymbol.length() + 1), input_string.length() );
				
				
			} else {
				newSymbol = input_string;
				input_string = "";
			}
			
			newExpression.setUniqueSymbol( newSymbol );
			
	    	symbolList.add( newExpression );
			
		}
		
		input_string.trim();
		
		if( input_string.startsWith( " " )) {
			//remove the leading whitespace
			input_string = input_string.substring(1);
		}
				
	}
	return symbolList;
}


	public static boolean valid_expression(Logical_expression expression)
	{
		
		// checks for an empty symbol and if not empty, return the validity of the symbol
		
		if ( !(expression.getUniqueSymbol() == null) && ( expression.getConnective() == null ) ) {
			// we have a unique symbol, check to see if its valid
			return valid_symbol( expression.getUniqueSymbol() );

			}

		
		if ( ( expression.getConnective().equalsIgnoreCase("if") )  ||
		      ( expression.getConnective().equalsIgnoreCase("iff") ) ) {
			
			// the connective is either 'if' or 'iff' - so check the number of connectives
			if (expression.getSubexpressions().size() != 2) {
				System.out.println("error: connective \"" + expression.getConnective() +
						"\" with " + expression.getSubexpressions().size() + " arguments\n" );
				return false;
				}
			}
		// end 'if / iff' check
	  
		// check for 'not'
		else   if ( expression.getConnective().equalsIgnoreCase("not") ) {
			// the connective is NOT - there can be only one symbol / subexpression
			if ( expression.getSubexpressions().size() != 1)
			{
				System.out.println("error: connective \""+ expression.getConnective() + "\" with "+ expression.getSubexpressions().size() +" arguments\n" ); 
				return false;
				}
			}
		// end check for 'not'
		
		// check for 'and / or / xor'
		else if ( ( !expression.getConnective().equalsIgnoreCase("and") )  &&
				( !expression.getConnective().equalsIgnoreCase( "or" ) )  &&
				( !expression.getConnective().equalsIgnoreCase("xor" ) ) ) {
			System.out.println("error: unknown connective " + expression.getConnective() + "\n" );
			return false;
			}
		// end check for 'and / or / not'

		for( Enumeration e = expression.getSubexpressions().elements(); e.hasMoreElements(); ) {
			Logical_expression testExpression = (Logical_expression)e.nextElement();
			
			if( !valid_expression( testExpression ) ) {
				return false;
			}
		}
		return true;
	}
	

	public static boolean valid_symbol( String symbol ) {
		if (  symbol == null || ( symbol.length() == 0 )) {
			
			return false;
		}

		for ( int counter = 0; counter < symbol.length(); counter++ ) {
			if ( (symbol.charAt( counter ) != '_') &&
					( !Character.isLetterOrDigit( symbol.charAt( counter ) ) ) ) {
				
				System.out.println("String: " + symbol + " is invalid! Offending character:---" + symbol.charAt( counter ) + "---\n");
				
				return false;
			}
		}
		
		return true;
	}

        private static void exit_function(int value) {
                System.out.println("exiting from Check_true_false");
                  System.exit(value);
                }
}  