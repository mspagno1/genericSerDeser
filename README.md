
#Compilation
From the folder: spagnoli_matthew_assign5/genericDeser/src

ant clean
ant all


TO RUN:
From the folder: spagnoli_matthew_assign5/genericDeser/src

ant run -Darg0=<input-file-name> -Darg1=<output-file-name> -Darg2=<debug_value>

Example:
ant run -Darg0=input.txt -Darg1=out.txt -Darg2=0

The files are read from/to here:
	spagnoli_matthew_assign5/genericDeser
	

DEBUG_VALUE=8 [Print result string from an object in DMPL]
DEBUG_VALUE=7 [Prints classNames in DMPL]
DEBUG_VALUE=6 [Print method names in DMPL]
DEBUG_VALUE=5 [Print types of information of an objects field type, fieldName and value]
DEBUG_VALUE=4 [Print how many duplicates for each counter location]
DEBUG_VALUE=3 [Print toString of objects data before we add it to data structure]
DEBUG_VALUE=2 [Print every input line]
DEBUG_VALUE=1 [Print constructor call]
DEBUG_VALUE=0 [No output should be printed from the application, except normal output of the number of objects and the number of duplicates]
