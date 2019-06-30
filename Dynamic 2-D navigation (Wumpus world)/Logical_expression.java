import java.util.*;


public class Logical_expression {
    
    private String uniqueSymbol = null; 	// null if sentence is a more complex expression
	    private String connective = null;          
           static LinkedList<Symbols> symbol = new LinkedList<Symbols>();
		private Vector<Logical_expression> subexpressions = null;   // vector of Logical_expressions
		
		public Logical_expression()
		{
		
			this.subexpressions = new Vector<Logical_expression>();
		}
		
		
		public Logical_expression( Logical_expression oldExpression ) {
			
			if( oldExpression.getUniqueSymbol() == null) {
				this.uniqueSymbol = oldExpression.getUniqueSymbol();
			} else {
				// create a new logical expression from the one passed to it
				this.connective = oldExpression.getConnective();
			
				for( Enumeration e = oldExpression.getSubexpressions().elements(); e.hasMoreElements(); ) {
					Logical_expression nextExpression = (Logical_expression)e.nextElement();
					
					this.subexpressions.add( nextExpression );
				}
			}
			
		}
	
		public void setUniqueSymbol( String newSymbol ) 
            {
			boolean valid = true;
			newSymbol.trim();
			
			if( this.uniqueSymbol != null ) 
                  {
			      System.out.println("setUniqueSymbol(): - this LE already has a unique symbol!!!" +
							"\nswapping :->" + this.uniqueSymbol + "<- for ->" + newSymbol +"<-\n");
			} 
                  else if( valid ) 
                  {
					this.uniqueSymbol = newSymbol;
					
			} 
		}
		public String setConnective( String inputString ) {
			
			String connect;
			
			inputString.trim();
			
			// if the first character of the inputString is a '('
			// - remove the ')' and the ')' and any whitespace after it.
			if( inputString.startsWith("(") ) {
				inputString = inputString.substring( inputString.indexOf('('), inputString.length() );
				
				//trim the whitespace
				inputString.trim();
			}
	

			if( inputString.contains( " " ) ) {
				// remove the connective out of the string
				connect = inputString.substring( 0, inputString.indexOf( " " )) ;
				inputString = inputString.substring( ( connect.length() + 1 ), inputString.length() );
				
			} else {
				// just set to get checked and empty the inputString
				// huh?
				connect = inputString;
				inputString = "";
			}
			
			// if connect is a proper connective
			if ( connect.equalsIgnoreCase( "if" ) ||
					connect.equalsIgnoreCase( "iff" ) ||
					connect.equalsIgnoreCase( "and" ) ||
					connect.equalsIgnoreCase("or") ||
					connect.equalsIgnoreCase("xor") || 
					connect.equalsIgnoreCase( "not" ) ) {
				
				// set the connective
				this.connective = connect;
				
				return inputString;
				
			} else {
				System.out.println( "unexpected character!!! : invalid connective!! - setConnective():-" + inputString );
				this.exit_function( 0 );
			}
			
			// invalid connective
			System.out.println(" invalid connective! : setConnective:-" + inputString );
			return inputString;
		}
		
		public void setSubexpression( Logical_expression newSub ) {
			this.subexpressions.add(newSub);
		}
		
		public void setSubexpressions( Vector<Logical_expression> Symbols ) {
			this.subexpressions = Symbols;
			
		}
		
		public String getUniqueSymbol(){
			return this.uniqueSymbol;
		}
		
		public String getConnective() {
			return this.connective;
		}
		
		public Logical_expression getNextSubexpression() {
			return this.subexpressions.lastElement();
		}
		
		public Vector getSubexpressions() {
			return this.subexpressions;
		}

		/************************* end getters and setters *************/

		public void print_expression( String separator ) {

		  if ( !(this.uniqueSymbol == null) )
		  {
			  System.out.print( this.uniqueSymbol.toUpperCase() );
                         Symbols curr_symbol = new Symbols();
                          curr_symbol.symbol_name = this.uniqueSymbol;
                          symbol.add(curr_symbol);
		  } else {
			   //else the symbol is a nested logical expression not a unique symbol
			  Logical_expression nextExpression;
			  
			   //print the connective
			  System.out.print( "(" + this.connective.toUpperCase() );

			  // enumerate over the 'Symbols'
			  for( Enumeration e = this.subexpressions.elements(); e.hasMoreElements(); ) 
                          {
				  nextExpression = ( Logical_expression )e.nextElement();
                                  System.out.print(" ");
				  nextExpression.print_expression("");
				  System.out.print( separator );
				  }
			  
			  System.out.print(")");
			  }
		}

        private static void exit_function(int value) {
                System.out.println("exiting from Logical_expression");
                  System.exit(value);
                }
}