Goutham Munagala
1001565060
Java


Check_true_false.java contains the main function and takes three arguments - wumpus_rules.txt 
kb.txt 
rule_X.txt 
(X can be a, b or c)

The output is displayed on the screen will also be saved in result.txt.

Structure:
The program will call Logical_expression.java and load the kb and wumpus rules. A hashmap keeps the record of true and false of the wumpus rules. It also keeps the record of the statement file and then calls the check_entailment.java and modifies the output accordingly.

LogicalExpression.java has a Vector and LinkedList that keeps record of all the Symbols that will be extracted. It has a function setUniqueSymbol that tells whether the symbol given is valid and then add them to a Vector or LinkedList.

Entails.java contains the model. It takes symbols from the knowledge base. It has a function TT_Entails that takes kb, statement and model.
It then calls check_all and checks the availability of the statement. The PL_true function checks the uniqueness of the symbol. If the symbol is unique, it returns true else false is returned.


Running:
Navigate to the directory,
Compile using
> javac Check_true_false.java
> java Check_true_false wumpus_rules.txt kb.txt rule_a.txt