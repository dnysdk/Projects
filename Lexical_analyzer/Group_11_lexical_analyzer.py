
#group 11
# dnyaneshwar shendurwadkar 1401CS43
# vishnukant reddy 1401CS10
# venkatesh 1401CS32

'''
packages required for this program
'''
import re                                                   #This package provides full support for Perl-like regular expressions in Python.
import sys, traceback                                       
# SYS  module provides access to some variables used or maintained by the interpreter and to functions that interact strongly with the interpreter
# eg. sys.argv which is used to get command line arguments
'''
The syntax of the given imperative language
'''
# X ::= Identifier
# IDs ::= X, IDs | X
# n ::= Integer Constatn | Character Constant
# t ::= int | char
# e ::= x | n | e opa e
# b ::= true | false | b opb b | e opr e
# opa ::= + | - | * | /
# opb ::= and | or | not
# opr ::= > | < | >= | <= | == | ! =
# Statements:
# Stmt ::= X := e | skip | if (b) then {Stmts} else {Stmts}
# | while (b) {Stmts}
# Stmts ::= Stmt; Stmts | Stmt;

'''
Following are the Regex for the terminals used in given imperative language to check weather the given word satifies give syntax or not
'''
re_identifier = ur"[a-zA-Z_$][a-zA-Z_$0-9]*"                     # Regex for identifier       
re_integer_constant = r"[+|-]?(?<!\.)\b[0-9]+\b(?!\.[0-9])"      # Regex for integer constant
re_char_constant = r"'\w'"                                       # Regex for character constant
re_t = r"int|char"   						 # Regex for data type of variable
re_b = r"true|false"                                             # Regex for boolean values
re_opa = r"[/*-+]"                                               # Regex for arithmatic operators
re_opb = r"\band|or|not\b"                                       # Regex for boolean operators
re_opr = r">|<|>=|<=|==|!="                                      # Regex for relational operators
re_keywords = r"if|then|skip|else|while"                         # Regex for Keywords used in language

'''
re.compile() function takes some regex as argument and compile it  and return an objext of that specific regex

we can use that object to match any word to corresponding regex using match function
'''

p1=re.compile(re_identifier)                     # object for regex of identifier
p2=re.compile(re_integer_constant)               # object for regex of integer constant
p3=re.compile(re_char_constant)                  # object for regex of character constant
p4=re.compile(re_t)                              # object for regex of data type
p5=re.compile(re_b)                              # object for regex of boolean values
p6=re.compile(re_opr)                            # object for regex of relational operators 
p7=re.compile(re_opb)                            # object for regex of boolean operaotrs
p8=re.compile(re_opa)                            # object for regex of arithematic operators
p9=re.compile(re_keywords)                       # object for regex of keywords 


print "enter the name of file"
name_of_file = raw_input()                          # Taking the name of input file which we have to check  
fp = open(name_of_file,"r")                         # opening that file in read mode, here fp is file pointer
#fp = open(sys.argv[1],"r")                         # if file name is taken as command line argument

'''
input_string is the string contains all the file in single string without line break characters
'''
input_string = ""   
for line in fp:
 	input_string = input_string + line.rstrip("\n") + " "   # line.rstrip("\n") used to eliminate \n character at the end of line



print ""
print input_string                         # printing input string
print ""

'''
Initializing List for storing tokens
'''
identifier_list = []                      # List for storing all Identifiers
integer_constant_list = []                # List for storing all Integer constants
char_constant_list = []                   # List for storing all Character constants
type_list = []                            # List for storing all Data types
boolean_list = []                         # List for storing all Boolean values
opa_list = []                             # List for storing all Arithmatic operators
opr_list = []                             # List for storing all Relational operators
opb_list = []                             # List for storing all Boolean operators
keywords_list = []                        # List for storing all keywords used in the language

'''
Removing all Spaces, tabs and line breakers from input_string if any and resulting tokens being stored in 'tokens' list

re.findall is function under re package used to  tokenize input_string using regex passed as argument
'''
tokens = re.findall(r'\S+|\t+|\n+',input_string)
print "///////////////////tokens list///////////////////" 
print tokens

print "///////////////////////////////////////////////////////////////"

'''
Now traversing tokesn list word by word and matching with objects created above(Regex) 
First we are macthing keyword as keyword cannot be a identifier and so on
sequence of matching is important as one word can be in only one list Eg. keyword can not be identifier,etc
'''
for word in tokens:
	if(p9.match(word)):
		if(word not in keywords_list):
			keywords_list.append(word)	
		continue
	if(word=="-" or word=="/" or word=="*" or word=="+"):
		if(word not in opa_list):
			opa_list.append(word)
		continue
	if(p7.match(word)):
		if(word not in opb_list):
			opb_list.append(word)
		continue
	if(p6.match(word)):
		if(word not in opr_list):
			opr_list.append(word)
		continue
	if(p5.match(word)):
		if(word not in boolean_list):
			boolean_list.append(word)	
		continue
	if(p4.match(word)):
		if(word not in type_list):
			type_list.append(word)		
		continue
	if(p3.match(word)):
		if(word not in char_constant_list):
			char_constant_list.append(word)		
		continue
	if(p2.match(word)):
		if(word not in integer_constant_list):
			integer_constant_list.append(word)
		continue
	if(p1.match(word)):
		if(word not in identifier_list):
			identifier_list.append(word)
		continue


'''
This is for symbol table
Int_list is the list of all integer identifiers
Char_list is the list of all character identifiers
'''

int_list = []

char_list = []

'''
We are traversing token list till we get int, as soon as we find int we store the token next to int in int_list
If we get ',' after storing first int identifier we will also store the token next to ',' in int_list. 
Eg : int a , b ; 
Both a and b will get stored in int_list

Repeat same procedure for char_list
'''
i=0;
while(i<len(tokens)):
	#print i
	if(tokens[i]=="int"):
		i=i+1;
		
		if(tokens[i] not in int_list and tokens[i] in identifier_list):
			int_list.append(tokens[i])
		i=i+1;	
		while(tokens[i]==","):
			i=i+1
				
			if(tokens[i] not in int_list and tokens[i] in identifier_list):
				int_list.append(tokens[i])
			i=i+1
	i=i+1		

i=0;
while(i<len(tokens)):
	if(tokens[i]=="char"):
		i+=1;
		if(tokens[i] not in char_list and tokens[i] in identifier_list):
			char_list.append(tokens[i])
		i+=1;	
		while(tokens[i]==","):
			i+=1;	
			if(tokens[i] not in char_list and tokens[i] in identifier_list):
				char_list.append(tokens[i])
			i+=1;
	i+=1		

'''
Printing all lists 
'''
if(not identifier_list):
	print "No elements in identifier list"
else:	
	print "List of Identifiers          -->",identifier_list
print ""
if(not integer_constant_list):
	print "no elements in integer constant"
else:
	print "List of Integer Constants    -->",integer_constant_list
print ""
if(not char_constant_list):
	print "no elements Character constants"
else:
	print "List of Character constants  -->",char_constant_list
print ""
if(not type_list):
	print "no element in data types"
else:
	print "List of Data types           -->",type_list
print ""
if(not boolean_list):
	print "no element in boolean list"
else:
	print "List of Boolean values       -->",boolean_list
print ""
if(not opa_list):
	print "no element in Arithmetic operators list"
else:
	print "List of Arithmetic operators -->",opa_list
print ""
if(not opr_list):
	print "no element in relational operators list"
else:
	print "List of Relational operators -->",opr_list
print ""
if(not opb_list):
	print "no element in boolean opeartor list"
else:	
	print "List of Boolean operators    -->",opb_list
print ""
if(not keywords_list):
	print "no keywords used"
else:
	print "List of keywords             -->",keywords_list
print ""
input_list = re.split(';| |\n|\{|\}|\(|\)|,|\t+|\n+',input_string)   # splitting input_string using arguments passed in re.split()



'''
default size of int is 4 bytes
default size of char is 1 bytes
'''
print "SYMBOL TABLE :-"
print "default int size 4 byts"
print "default char size 1 byts"
print "int list -> ",int_list
print ""
print "char list -> ",char_list

'''

printing symbol table as following format
Eg.
identifier  type    size

a           int      4
b           char     1
'''

for letter in int_list:
	print letter," 'int' '4'"
for letter in char_list:
	print letter," 'char' '1'"	


'''
The following code checks for any tokens which DONT SATISFY the given imperative language

We are traversing all words in input_list checking if that word exist in any of the Lists such as identifier_list,integer_constant_list,char_constant_list,type_list,
boolean_list,opa_list,opr_list,opb_list,keywords_list,etc

if that word contains in any of above mentioned list then that will satisfy given language
if that word does not contains in any list that will give error and get printed

'''
flag=0
print "///////////////////////////////////////////////////////////////////////////////////"
print "error words are :- "
flag1=0;
for word in input_list:
	flag=0
	#print word
	if(word!=''):
		b=word in identifier_list
		if(b==True):
			flag=1
		b=word in integer_constant_list
		if(b==True):
			flag=1
		b=word in char_constant_list
		if(b==True):
			flag=1		
		b=word in type_list
		if(b==True):
			flag=1
		b=word in boolean_list
		if(b==True):
			flag=1
		b=word in opa_list
		if(b==True):
			flag=1
		b=word in opr_list
		if(b==True):
			flag=1
		b=word in opb_list
		if(b==True):
			flag=1
		b=word in keywords_list
		if(b==True):
			flag=1		
	if(flag==0 and word!="" and word!="="):
		print word
		flag1=1
if(flag1==0):
	print "no wrror word"		
					
